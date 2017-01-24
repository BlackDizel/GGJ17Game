package org.byters.jamgame.controller;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.byters.jamgame.view.DialogWindow;

public class ControllerDialogSelector {
    private static ControllerDialogSelector instance;
    private boolean isDialogShown;
    private DialogWindow dialog;

    private ControllerDialogSelector() {
        isDialogShown = false;
    }

    public static ControllerDialogSelector getInstance() {
        if (instance == null) instance = new ControllerDialogSelector();
        return instance;
    }

    public boolean isDialogShown() {
        return isDialogShown;
    }

    void showDialog() {
        isDialogShown = true;
        dialog = new DialogWindow();
        dialog.load();//todo must be async
    }

    public void draw(SpriteBatch batch) {
        if (dialog == null || !isDialogShown()) return;
        dialog.draw(batch);
    }

    public void input() {
        if (dialog == null) return;
        dialog.input();
    }

    public void cancelDialog() {
        isDialogShown = false;
    }
}
