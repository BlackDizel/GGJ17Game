package org.byters.jamgame.controller;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;

public class ControllerMessage {
    private static final long TIME_SHOW_MILLIS = 2000;
    @NotNull
    private static ControllerMessage instance;
    @Nullable
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

    public void update() {
        if (message != null && System.currentTimeMillis() - timeStart > TIME_SHOW_MILLIS) message = null;

    }

    @Nullable
    public String getMessage() {
        return message;
    }

    public void setFontColor(BitmapFont font) {
        font.setColor(0.9f, 0.9f, 0.9f, 1 - (System.currentTimeMillis() - timeStart) / (float) TIME_SHOW_MILLIS);
    }
}
