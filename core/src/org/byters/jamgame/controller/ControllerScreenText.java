package org.byters.jamgame.controller;

import org.byters.jamgame.model.ScreenTextModel;
import org.byters.jamgame.model.Screens;

public class ControllerScreenText {
    private static ControllerScreenText instance;

    private ScreenTextModel model;

    private ControllerScreenText() {
        model = new ScreenTextModel();
    }

    public static ControllerScreenText getInstance() {
        if (instance == null) instance = new ControllerScreenText();
        return instance;
    }

    void navigateScreenStory() {
        model.setStateStory();
        ControllerMain.getInstance().navigateScreen(Screens.TEXT);
    }

    void navigateScreenGameOver() {
        model.setStateGameOver();
        ControllerMain.getInstance().navigateScreen(Screens.TEXT);
    }

    void navigateScreenDayOver() {
        model.setStateDayOver();
        ControllerMain.getInstance().navigateScreen(Screens.TEXT);
    }

    public String getText() {
        return model.getText();
    }

    public void navigateNextScreen() {
        ControllerMain.getInstance().navigateScreen(model.getScreenNext());
    }
}
