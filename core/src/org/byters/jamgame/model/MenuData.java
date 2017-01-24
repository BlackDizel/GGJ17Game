package org.byters.jamgame.model;

import java.util.ArrayList;

class MenuData {
    private int id;
    private String title;
    private ArrayList<MenuData> childs;

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public ArrayList<MenuData> getChilds() {
        return childs;
    }
}
