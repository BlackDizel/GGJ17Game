package org.byters.jamgame.controller;

public class ControllerMissions {
    static final long FISH_INTERACTION_TIME_MILLIS = 3000;
    private static ControllerMissions instance;

    public static ControllerMissions getInstance() {
        if (instance == null) instance = new ControllerMissions();
        return instance;
    }

    boolean isMissionFoodSearch() {
        return ControllerDays.getInstance().isFirstDay();
    }
}
