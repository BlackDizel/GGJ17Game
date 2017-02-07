package org.byters.jamgame.controller;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.byters.jamgame.view.DialogWindow;

public class ControllerDialogSelector {
    private static ControllerDialogSelector instance;
    private DialogWindow dialog;

    private ControllerDialogSelector() {
    }

    public static ControllerDialogSelector getInstance() {
        if (instance == null) instance = new ControllerDialogSelector();
        return instance;
    }

    public boolean isDialogShown() {
        return dialog != null && dialog.isShown();
    }

    void showDialog() {
        dialog = new DialogWindow();
        dialog.load();//todo must be async
        dialog.show();
    }

    public void draw(SpriteBatch batch) {
        if (dialog == null || !isDialogShown()) return;
        dialog.draw(batch);
    }

    public void input() {
        if (dialog == null) return;
        dialog.input();
    }
}
