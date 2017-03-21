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

    public String getText() { //todo move to json
        return Strings.getInstance().getString(state == ScreenState.STORY
                ? Strings.STORY_ID
                : state == ScreenState.STORY1
                ? Strings.STORY1_ID
                : state == ScreenState.GAMEOVER
                ? Strings.GAMEOVER_ID
                : state == ScreenState.GAMEOVER1
                ? Strings.GAMEOVER1_ID
                : state == ScreenState.DAY_OVER
                ? Strings.DAYOVER_ID
                : Strings.NO_VALUE_ID);
    }

    private enum ScreenState {
        STORY, STORY1, GAMEOVER, GAMEOVER1, DAY_OVER
    }
}
