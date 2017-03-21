package org.byters.jamgame.model;

import com.sun.istack.internal.Nullable;
import org.byters.jamgame.controller.ControllerJson;

import java.util.HashMap;

public class Strings {

    public static final int BACK_ID = 9; //todo move to json
    public static final int CANNOT_USE_ID = 6;
    public static final int SUCCESS_ID = 7;
    public static final int CANNOT_CRAFT_ID = 8;
    public static final int CONTROL_HINT_ID = 0;
    public static final int STORY_ID = 1;
    public static final int STORY1_ID = 2;
    public static final int GAMEOVER_ID = 3;
    public static final int GAMEOVER1_ID = 4;
    public static final int DAYOVER_ID = 5;
    public static final int NO_VALUE_ID = -1;

    private static Strings instance;
    private HashMap<Integer, String> strings;

    private Strings() {
    }

    public static Strings getInstance() {
        if (instance == null) instance = new Strings();
        return instance;
    }

    @Nullable
    public String getString(int id) {
        if (strings == null || !strings.containsKey(id))
            return null;
        return strings.get(id);
    }

    public void load() {
        strings = ControllerJson.getInstance().readStrings();
    }

}
