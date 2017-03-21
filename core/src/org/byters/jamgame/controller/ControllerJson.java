package org.byters.jamgame.controller;

import com.badlogic.gdx.utils.JsonValue;
import org.byters.engine.controller.ControllerJsonBase;
import org.byters.jamgame.model.ContainerStringId;
import org.byters.jamgame.model.ItemsDayDataCollection;
import org.byters.jamgame.model.MessageTimed;
import org.byters.jamgame.model.inventory.Items;

import java.util.ArrayList;
import java.util.HashMap;

public class ControllerJson {
    static final String FILE_JSON_MENU = "en/menu.json";
    private static final String FILE_JSON_ITEMS = "en/items.json";
    private static final String FILE_JSON_STRINGS = "en/strings.json";
    private static final String FILE_JSON_ITEMS_DAY = "items_day.json";

    private static ControllerJson instance;

    public static ControllerJson getInstance() {
        if (instance == null) instance = new ControllerJson();
        return instance;
    }

    public ArrayList<MessageTimed> readMessages() {
        //todo implement
        return null;
    }

    Items readItems() {
        return ControllerJsonBase.getInstance().readFile(Items.class, FILE_JSON_ITEMS);
    }

    public HashMap<Integer, String> readStrings() {
        HashMap<Integer, String> result = new HashMap<Integer, String>();
        ArrayList<JsonValue> list = ControllerJsonBase.getInstance().readFile(ArrayList.class, FILE_JSON_STRINGS);

        for (JsonValue v : list) {
            ContainerStringId item = ControllerJsonBase.getInstance().getJson()
                    .readValue(ContainerStringId.class, v);
            result.put(item.getId(), item.getMessage());
        }

        return result;
    }

    public ItemsDayDataCollection getItemsDay() {
        return ControllerJsonBase.getInstance().readFile(ItemsDayDataCollection.class, FILE_JSON_ITEMS_DAY);
    }
}
