package org.byters.jamgame.controller;

public class ControllerDays {
    private static final int DAY_LAST = 3;
    private static ControllerDays instance;
    private int currentDay;

    private ControllerDays() {
        resetDays();
    }

    public static ControllerDays getInstance() {
        if (instance == null) instance = new ControllerDays();
        return instance;
    }

    public void loadSavedState(int day) {
        currentDay = day;
    }

    int getDay() {
        return currentDay;
    }

    boolean isLastDay() {
        return currentDay == DAY_LAST;
    }

    public void newDay() {
        ++currentDay;
        if (currentDay > DAY_LAST) ControllerScreenText.getInstance().navigateScreenGameOver();
        else ControllerScreenText.getInstance().navigateScreenDayOver();
    }

    public void resetDays() {
        currentDay = 1;
    }
}
