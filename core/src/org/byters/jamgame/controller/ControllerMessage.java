package org.byters.jamgame.controller;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ControllerMessage {
    private static final long TIME_SHOW_MILLIS = 1200;
    private static ControllerMessage instance;
    private BitmapFont font;
    private String message;
    private long timeStart;

    public static ControllerMessage getInstance() {
        if (instance == null) instance = new ControllerMessage();
        return instance;
    }

    public void showMessage(String message) {
        timeStart = System.currentTimeMillis();
        this.message = message;
    }

    public void load() {
        font = new BitmapFont();
    }

    public void draw(SpriteBatch batch) {
        if ((System.currentTimeMillis() - timeStart > TIME_SHOW_MILLIS) || message == null)
            return;
        font.setColor(0.5f, 0.5f, 0.5f, 1 - (System.currentTimeMillis() - timeStart) / (float) TIME_SHOW_MILLIS);
        font.draw(batch, message, ControllerPlayer.getInstance().getMessagePositionX()
                , ControllerPlayer.getInstance().getMessagePositionY());
    }
}
