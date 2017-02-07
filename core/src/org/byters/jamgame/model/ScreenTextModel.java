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
        return state == ScreenState.STORY
                ? Strings.getInstance().story
                : state == ScreenState.STORY1
                ? Strings.getInstance().story1
                : state == ScreenState.GAMEOVER
                ? Strings.getInstance().gameover
                : state == ScreenState.GAMEOVER1
                ? Strings.getInstance().gameover1
                : state == ScreenState.DAY_OVER
                ? Strings.getInstance().dayover
                : "";
    }

    private enum ScreenState {
        STORY, STORY1, GAMEOVER, GAMEOVER1, DAY_OVER
    }
}
