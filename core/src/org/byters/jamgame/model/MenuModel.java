package org.byters.jamgame.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.google.gson.Gson;

public class MenuModel {
    public static final int NO_VALUE = -1;
    private static final String MENU_FILE = "menu.json";
    private MenuData menu;
    private MenuData currentMenu;
    private int currentIndex;

    public int getItemsCount() {
        return currentMenu == null || currentMenu.getChilds() == null ? 0 : currentMenu.getChilds().size();
    }

    public int getCurrentIndex() {
        return currentIndex;
    }

    public void next() {
        if (getItemsCount() == 0) return;
        ++currentIndex;
        currentIndex %= getItemsCount();
    }

    public void previous() {
        if (getItemsCount() == 0) return;
        currentIndex = (getItemsCount() + currentIndex - 1) % getItemsCount();
    }

    public int getId(int position) {
        return currentMenu == null || currentMenu.getChilds() == null || currentMenu.getChilds().size() <= position
                ? NO_VALUE
                : currentMenu.getChilds().get(position).getId();
    }

    public void load() {

        FileHandle handle = Gdx.files.internal(MENU_FILE);
        String json = handle.readString();
        menu = new Gson().fromJson(json, MenuData.class);
        currentMenu = menu;
        currentIndex = 0;
    }

    public String getTitle(int position) {
        return currentMenu == null || currentMenu.getChilds() == null || currentMenu.getChilds().size() <= position
                ? null
                : currentMenu.getChilds().get(position).getTitle();
    }

    public int getCurrentItemId() {
        return getId(currentIndex);
    }
}
