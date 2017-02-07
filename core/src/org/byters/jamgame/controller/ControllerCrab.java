package org.byters.jamgame.controller;

public class ControllerCrab {
    private static ControllerCrab instance;

    public static ControllerCrab getInstance() {
        if (instance == null) instance = new ControllerCrab();
        return instance;
    }

    public float getPosX() {
        //todo implement
        return 220;
    }

    public float getPosY() {
        //todo implement
        return 184;
    }
}
