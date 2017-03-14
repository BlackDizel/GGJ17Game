package org.byters.jamgame.controller;

import org.byters.engine.controller.ControllerSortedVerticalDrawableObjectList;
import org.byters.engine.model.IDrawableObject;
import org.byters.jamgame.model.DrawableObjectsEnum;

import java.util.ArrayList;

public class ControllerDays {
    private static final int DAY_LAST = 3;
    private static ControllerDays instance;
    private int currentDay;
    private ControllerSortedVerticalDrawableObjectList controllerDrawableObjects;

    private ControllerDays() {
        resetDays();
    }

    public static ControllerDays getInstance() {
        if (instance == null) instance = new ControllerDays();
        return instance;
    }

    int getDay() {
        return currentDay;
    }

    boolean isLastDay() {
        return currentDay == DAY_LAST;
    }

    public void dayOver() {
        ++currentDay;
        controllerDrawableObjects = new ControllerSortedVerticalDrawableObjectList();
        if (currentDay > DAY_LAST) ControllerScreenText.getInstance().navigateScreenGameOver();
        else ControllerScreenText.getInstance().navigateScreenDayOver();
    }

    public void resetDays() {
        controllerDrawableObjects = new ControllerSortedVerticalDrawableObjectList();
        currentDay = 1;
    }

    public ArrayList<IDrawableObject> getDrawableObjects() {
        return controllerDrawableObjects.getItems();
    }

    public void addDrawableObject(IDrawableObject item) {
        controllerDrawableObjects.addItem(item);
    }

    public void removeDrawableObject(int itemId) {
        controllerDrawableObjects.removeItem(DrawableObjectsEnum.getItemID(itemId));
    }

    public void update() {
        controllerDrawableObjects.sort();
    }
}
