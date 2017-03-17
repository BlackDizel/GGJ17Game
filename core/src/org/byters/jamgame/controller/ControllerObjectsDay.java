package org.byters.jamgame.controller;

import com.badlogic.gdx.Gdx;

public class ControllerObjectsDay {

    public static final float FISH_POSITION_X_FACTOR = 0.1f;
    public static final float FISH_POSITION_Y_FACTOR = 0.3f;
    static final float INTERACTION_DISTANCE_ITEM_FACTOR = 0.04f;
    private static final float INTERACTION_DISTANCE_FISH_FACTOR = 0.2f;
    private static ControllerObjectsDay instance;

    public static ControllerObjectsDay getInstance() {
        if (instance == null) instance = new ControllerObjectsDay();
        return instance;
    }

    boolean isFishInteractPosition(float playerPositionX) {
        System.out.println(Math.abs(playerPositionX - Gdx.graphics.getWidth() * FISH_POSITION_X_FACTOR) + " " + INTERACTION_DISTANCE_FISH_FACTOR * Gdx.graphics.getWidth());
        return Math.abs(playerPositionX - Gdx.graphics.getWidth() * FISH_POSITION_X_FACTOR) < INTERACTION_DISTANCE_FISH_FACTOR * Gdx.graphics.getWidth();
    }
}
