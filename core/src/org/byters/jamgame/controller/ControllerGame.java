package org.byters.jamgame.controller;

import org.byters.jamgame.model.Screens;

public class ControllerGame {
    private static ControllerGame instance;

    private ControllerGame() {
    }

    public static ControllerGame getInstance() {
        if (instance == null) instance = new ControllerGame();
        return instance;
    }

    public void showMenu() {
        ControllerMain.getInstance().navigateScreen(Screens.MENU);
    }
}
