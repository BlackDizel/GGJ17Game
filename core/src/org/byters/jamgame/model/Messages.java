package org.byters.jamgame.model;

import com.sun.istack.internal.Nullable;
import org.byters.jamgame.controller.ControllerJson;

import java.util.ArrayList;

public class Messages {
    private int currentItemPosition;
    private ArrayList<Message> messages;

    public void load() {
        messages = ControllerJson.getInstance().readMessages();
        reset();
    }

    public boolean isAllMessagesShown() {
        return messages.isEmpty();
    }

    @Nullable
    public Message getMessage() {
        if (++currentItemPosition >= messages.size()) return null;
        return messages.get(currentItemPosition);
    }

    public void reset() {
        currentItemPosition = -1;
    }
}
