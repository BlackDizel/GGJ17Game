package org.byters.jamgame.controller;


import org.byters.jamgame.model.Message;
import org.byters.jamgame.model.Messages;

public class ControllerMessagesQueue {
    private static ControllerMessagesQueue instance;
    private Messages model;

    private ControllerMessagesQueue() {
        model = new Messages();
    }

    public static ControllerMessagesQueue getInstance() {
        if (instance == null) instance = new ControllerMessagesQueue();
        return instance;
    }

    void reset() {
        ControllerMessage.getInstance().removeMessage();
        model.reset();
    }

    public void load() {
        model.load();
    }

    public void update() {
        if (model.isAllMessagesShown() || isMessageShown()) return;
        Message message = model.getMessage();
        if (message == null) return;
        ControllerMessage.getInstance().showMessage(message);
    }

    public boolean isMessageShown() {
        return ControllerMessage.getInstance().getMessage() != null;
    }

    public boolean isMessageInteracted() {
        return ControllerMessage.getInstance().isMessageInteracted();
    }

    public void input() {
        if (ControllerMessage.getInstance().isButtonNextClicked())
            ControllerMessage.getInstance().removeMessage();
    }
}
