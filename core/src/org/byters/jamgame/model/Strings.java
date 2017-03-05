package org.byters.jamgame.model;

public class Strings {

    private static Strings instance;

    public String control_hint = "Use A,D to move, Space to interact, use TAB to open/close inventory";
    public String story = "This is short story about hope and strength of mind";
    public String story1 = "Once man awake at the strange place...";
    public String gameover = "I use transmitter and sending \"SOS\" again and again.\nWhen the battery is over, speaker say:\n\"Coast patrol listen. Rescue group on the way\"";
    public String gameover1 = "Thanks for playing";
    public String dayover = "I can't do anything more today and wait next day.\nAt dawn waves throw on shore more strange objects";
    public String cannot_use = "Cannot use this";
    public String success = "Success";
    public String cannot_craft = "Cannot craft";

    private Strings() {
    }

    public static Strings getInstance() {
        if (instance == null) instance = new Strings();
        return instance;
    }

}
