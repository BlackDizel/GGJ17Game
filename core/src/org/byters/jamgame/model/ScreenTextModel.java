package org.byters.jamgame.model;

public class ScreenTextModel {

    private ScreenState state;

    public void setStateStory() {
        state = ScreenState.STORY;
    }

    public void setStateGameOver() {
        state = ScreenState.GAMEOVER;
    }

    public void setStateDayOver() {
        state = ScreenState.DAY_OVER;
    }

    public Screens getScreenNext() {
        if (state == ScreenState.STORY) {
            state = ScreenState.STORY1;
            return Screens.TEXT;
        } else if (state == ScreenState.STORY1) {
            return Screens.GAME;
        } else if (state == ScreenState.DAY_OVER) {
            return Screens.GAME;
        } else if (state == ScreenState.GAMEOVER) {
            state = ScreenState.GAMEOVER1;
            return Screens.TEXT;
        } else if (state == ScreenState.GAMEOVER1) {
            return Screens.MENU;
        }
        return null;
    }

    public String getText() {
        //todo mode data to json file
        return state == ScreenState.STORY
                ? "This is short story about hope and strength of mind"
                : state == ScreenState.STORY1
                ? "Once man awake at the strange place..."
                : state == ScreenState.GAMEOVER
                ? "I use transmitter and sending \"SOS\" again and again.\nWhen the battery is over, speaker say:\n\"Coast patrol listen. Rescue group on the way\""
                : state == ScreenState.GAMEOVER1
                ? "Thanks for playing"
                : state == ScreenState.DAY_OVER
                ? "I can't do anything more today and wait next day.\nAt dawn waves throw on shore more strange objects"
                : "";
    }

    private enum ScreenState {
        STORY, STORY1, GAMEOVER, GAMEOVER1, DAY_OVER
    }
}
