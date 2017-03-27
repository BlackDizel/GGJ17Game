package org.byters.jamgame.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;
import org.byters.jamgame.model.Message;

public class ControllerMessage {
    private static final long TIME_DEFAULT_SHOW_MILLIS = 2000;
    @NotNull
    private static ControllerMessage instance;
    @Nullable
    private Message message;
    private long timeStart;

    public static ControllerMessage getInstance() {
        if (instance == null) instance = new ControllerMessage();
        return instance;
    }

    public void showMessage(int messageID) {
        timeStart = System.currentTimeMillis();
        this.message = Message.newMessageTimed(messageID, TIME_DEFAULT_SHOW_MILLIS);
    }

    void showMessage(Message message) {
        timeStart = System.currentTimeMillis();
        this.message = message;
    }

    public void update() {
        if (message != null
                && System.currentTimeMillis() - timeStart > message.getTimeShow()
                && !message.isInteracted()) removeMessage();
    }

    @Nullable
    public Message getMessage() {
        return message;
    }

    public void setFontColor(BitmapFont font) {
        if (message.isInteracted()) {
            font.setColor(1, 1, 1, 1);
            return;
        }
        font.setColor(0.9f, 0.9f, 0.9f, 1 - (System.currentTimeMillis() - timeStart) / (float) TIME_DEFAULT_SHOW_MILLIS);
    }

    boolean isMessageInteracted() {
        return message != null && message.isInteracted();
    }

    void removeMessage() {
        message = null;
    }

    boolean isButtonNextClicked() {
        return Gdx.input.justTouched()
                && getButtonPositionX() < Gdx.input.getX()
                && getButtonPositionX() + getButtonSize() > Gdx.input.getX()
                && getButtonPositionY() < Gdx.graphics.getHeight() - Gdx.input.getY()
                && getButtonPositionY() + getButtonSize() > Gdx.graphics.getHeight() - Gdx.input.getY();
    }

    public int getButtonSize() {
        return Gdx.graphics.getWidth() / 20;
    }

    public float getMessageWidth() {
        return Gdx.graphics.getWidth() / 3;
    }

    public float getButtonPositionX() {
        return ControllerPlayer.getInstance().getMessagePositionX() + getMessageWidth();
    }

    public float getButtonPositionY() {
        return ControllerPlayer.getInstance().getMessagePositionY() - getButtonSize();
    }
}
