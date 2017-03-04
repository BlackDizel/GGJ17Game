package org.byters.jamgame.controller;

import com.badlogic.gdx.Gdx;

public class ControllerCrab {
    private static ControllerCrab instance;

    public static ControllerCrab getInstance() {
        if (instance == null) instance = new ControllerCrab();
        return instance;
    }

    public float getPosX() {
        return Gdx.graphics.getWidth() / 3;
    }

    public float getPosY() {
        return Gdx.graphics.getHeight() / 15 * 6;
    }
}
