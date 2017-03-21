package org.byters.jamgame.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.byters.engine.controller.ControllerDialog;

public class DialogWindow {

    private static final String dialog_file = "dialog.json";
    private static final int vertical_space = 20;
    private static final int answer_vertical_space = 20;
    private static final String npc_bob = "Bob: ";
    private static final String npc_mary = "Mary: ";
    private ControllerDialog controllerDialog;
    private BitmapFont dialogFont;

    public void draw(SpriteBatch spriteBatch) {
        drawNPCPhrases(spriteBatch);
        drawAnswers(spriteBatch);
    }

    private void drawAnswers(SpriteBatch spriteBatch) {
        for (int i = 0; i < controllerDialog.getAnswersNum(); ++i) {
            if (controllerDialog.answerMessage(i) == null
                    || controllerDialog.answerMessage(i).trim().equals("")) continue;

            dialogFont.setColor(i == controllerDialog.getSelectedAnswerIndex() ? Color.WHITE : Color.LIGHT_GRAY);
            dialogFont.draw(spriteBatch
                    , answerNum(i) + controllerDialog.answerMessage(i)
                    , 0
                    , Gdx.graphics.getHeight() - (i * vertical_space + controllerDialog.getNPCPhrasesNum() * vertical_space + answer_vertical_space)
            );
        }
    }

    private String answerNum(int i) {
        return i < 9 ? (i + 1) + ". " : "";
    }


    private void drawNPCPhrases(SpriteBatch spriteBatch) {

        for (int i = 0; i < controllerDialog.getNPCPhrasesNum(); ++i) {
            if (controllerDialog.npcPhrase(i) == null
                    || controllerDialog.npcPhrase(i).trim().equals("")) continue;
            dialogFont.setColor(npcColor(i));
            dialogFont.draw(spriteBatch
                    , npcName(i) + controllerDialog.npcPhrase(i)
                    , 0
                    , Gdx.graphics.getHeight() - (i * vertical_space));
        }
    }

    private Color npcColor(int i) {
        return controllerDialog.npcID(i) != null && controllerDialog.npcID(i) == 1
                ? Color.YELLOW
                : controllerDialog.npcID(i) != null && controllerDialog.npcID(i) == 2
                ? Color.GREEN
                : Color.LIGHT_GRAY;
    }

    private String npcName(int i) {
        return controllerDialog.npcID(i) != null && controllerDialog.npcID(i) == 1
                ? npc_bob
                : controllerDialog.npcID(i) != null && controllerDialog.npcID(i) == 2
                ? npc_mary
                : "";
    }

    public void input() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)
                || Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            controllerDialog.answer();
            return;
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN))
            controllerDialog.nextSelectedAnswerIndex();

        if (Gdx.input.isKeyJustPressed(Input.Keys.UP))
            controllerDialog.previousSelectedAnswerIndex();

        inputNumbers();
    }

    private void inputNumbers() {
        for (int i = 0; i < Math.min(controllerDialog.getAnswersNum(), 9); ++i)
            if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_1 + i))
                controllerDialog.answer(i);
    }

    public void load() {
        controllerDialog = new ControllerDialog();
        controllerDialog.init(dialog_file);
        dialogFont = HelperFont.getInstance().newInstanceFont();
    }

    public boolean isShown() {
        return controllerDialog.isShown();
    }

    public void show() {
        controllerDialog.show();
    }
}
