package org.byters.jamgame.controller;

import org.byters.jamgame.model.Screens;
import org.byters.jamgame.view.IScreen;
import org.byters.jamgame.view.ScreenMenu;

import java.util.Random;

public class ControllerMain {
    private static ControllerMain instance;
    private IScreen currentScreen;
    private Random random;
    private long timeStartMillis;

    private ControllerMain() {
        timeStartMillis = System.currentTimeMillis();
        random = new Random();
        currentScreen = new ScreenMenu(); //todo implement
    }

    public static ControllerMain getInstance() {
        if (instance == null) instance = new ControllerMain();
        return instance;
    }

    public long getGameTime() {
        return System.currentTimeMillis() - timeStartMillis;
    }

    public Random getRandom() {
        return random;
    }

    public IScreen getCurrentScreen() {
        return currentScreen;
    }

    void navigateScreen(Screens screen) {
        ControllerCamera.getInstance().resetCamera();
        currentScreen = screen.getScreen();
        updateScreen();
    }

    private void updateScreen() {
        //todo here we need to load new screen, dispose old screen data and show loading screen.
        //todo but not now :)

        currentScreen.load();
    }
}
