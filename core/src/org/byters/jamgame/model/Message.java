package org.byters.jamgame.model;

public class Message {
    private int messageID;
    private long timeShow;
    private boolean isInteracted;

    public static Message newMessageTimed(int messageID, long timeShow) {
        Message result = new Message();
        result.isInteracted = false;
        result.messageID = messageID;
        result.timeShow = timeShow;
        return result;
    }

    public static Message newMessageInteracted(int messageID) {
        Message result = new Message();
        result.isInteracted = true;
        result.messageID = messageID;
        result.timeShow = 0;
        return result;
    }

    public int getMessageID() {
        return messageID;
    }

    public boolean isInteracted() {
        return isInteracted;
    }

    public long getTimeShow() {
        return timeShow;
    }
}