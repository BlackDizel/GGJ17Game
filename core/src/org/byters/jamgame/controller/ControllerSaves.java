package org.byters.jamgame.controller;

public class ControllerSaves {
    private static ControllerSaves instance;

    public static ControllerSaves getInstance() {
        if (instance == null) instance = new ControllerSaves();
        return instance;
    }
}
