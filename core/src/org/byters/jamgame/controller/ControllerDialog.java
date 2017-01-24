package org.byters.jamgame.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.google.gson.Gson;
import org.byters.jamgame.model.dialog.ModelDialog;
import org.byters.jamgame.model.dialog.ModelDialogStep;

import java.util.HashMap;

public class ControllerDialog {

    private HashMap<Integer, Integer> dialogHistory; //todo replace with list
    private ModelDialog dialog;
    private ModelDialogStep currentDialogStep;
    private int selectedAnswerIndex;

    public int getSelectedAnswerIndex() {
        return selectedAnswerIndex;
    }

    public int getNPCPhrasesNum() {
        return currentDialogStep == null || currentDialogStep.getNpc_phrases() == null
                ? 0
                : currentDialogStep.getNpc_phrases().size();

    }

    public int getAnswersNum() {
        return currentDialogStep == null || currentDialogStep.getAnswers() == null
                ? 0
                : currentDialogStep.getAnswers().size();
    }

    public void init(String file) {
        FileHandle fileHandle = Gdx.files.internal(file);
        String json = fileHandle.readString();
        if (json == null) return;
        dialog = new Gson().fromJson(json, ModelDialog.class);
        currentDialogStep = firstDialogStep();
        dialogHistory = new HashMap<Integer, Integer>();
    }


    private ModelDialogStep firstDialogStep() {
        if (dialog == null || dialog.getPhrases() == null) return null;
        for (ModelDialogStep item : dialog.getPhrases())
            if (item.getId() == 0)
                return item;
        return null;
    }

    public String answerMessage(int i) {
        return currentDialogStep == null
                || currentDialogStep.getAnswers() == null
                || currentDialogStep.getAnswers().size() <= i
                ? null
                : currentDialogStep.getAnswers().get(i).getMessage();
    }

    public String npcPhrase(int i) {
        return currentDialogStep == null
                || currentDialogStep.getNpc_phrases() == null
                || currentDialogStep.getNpc_phrases().size() <= i
                ? null
                : currentDialogStep.getNpc_phrases().get(i).getMessage();
    }

    public Integer npcID(int i) {
        return currentDialogStep == null
                || currentDialogStep.getNpc_phrases() == null
                || currentDialogStep.getNpc_phrases().size() <= i
                ? null
                : currentDialogStep.getNpc_phrases().get(i).getNpc_id();
    }

    public void nextSelectedAnswerIndex() {
        if (getAnswersNum() == 0) return;
        ++selectedAnswerIndex;
        selectedAnswerIndex %= getAnswersNum();
    }


    public void previousSelectedAnswerIndex() {
        if (getAnswersNum() == 0) return;
        selectedAnswerIndex = (getAnswersNum() + selectedAnswerIndex - 1) % getAnswersNum();
    }

    public void answer(int i) {
        selectedAnswerIndex = i;
        answer();
    }

    public void answer() {
        if (getAnswersNum() == 0) {
            currentDialogStep = null;
            ControllerDialogSelector.getInstance().cancelDialog();
            return;
        }

        if (getAnswersNum() <= selectedAnswerIndex)
            return;

        Integer next_id = currentDialogStep.getAnswers().get(selectedAnswerIndex).getNext_id();
        selectedAnswerIndex = 0;

        dialogHistory.put(currentDialogStep.getId(), next_id);

        if (next_id == null) {
            currentDialogStep = null;
            ControllerDialogSelector.getInstance().cancelDialog();
            return;
        }

        for (ModelDialogStep step : dialog.getPhrases())
            if (step.getId() == next_id) {
                currentDialogStep = step;
                break;
            }
    }
}
