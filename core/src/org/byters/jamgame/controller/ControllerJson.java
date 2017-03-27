package org.byters.jamgame.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.sun.istack.internal.Nullable;
import org.byters.engine.controller.ControllerJsonBase;
import org.byters.jamgame.model.ContainerStringId;
import org.byters.jamgame.model.ItemsDayDataCollection;
import org.byters.jamgame.model.Message;
import org.byters.jamgame.model.inventory.Items;

import java.util.ArrayList;
import java.util.HashMap;

public class ControllerJson {
    static final String FILE_JSON_MENU = "menu.json";
    private static final String FILE_JSON_ITEMS = "items.json";
    private static final String FILE_JSON_STRINGS = "ru/strings.json";
    private static final String FILE_JSON_ITEMS_DAY = "items_day.json";
    private static final String FILE_JSON_MESSAGES = "messages.json";

    private static ControllerJson instance;

    public static ControllerJson getInstance() {
        if (instance == null) instance = new ControllerJson();
        return instance;
    }

    @Nullable
    public ArrayList<Message> readMessages() {
        ArrayList<Message> result = null;

        JsonValue list = new JsonReader().parse(Gdx.files.internal(FILE_JSON_MESSAGES));
        for (JsonValue value : list) {
            Integer id = ControllerJsonBase.getInstance().getJson().readValue(Integer.class, value);
            if (result == null) result = new ArrayList<Message>();
            result.add(Message.newMessageInteracted(id));
        }

        return result;
    }

    Items readItems() {
        return ControllerJsonBase.getInstance().readFile(Items.class, FILE_JSON_ITEMS);
    }

    public HashMap<Integer, String> readStrings() {
        HashMap<Integer, String> result = null;
        ArrayList<JsonValue> list = ControllerJsonBase.getInstance().readFile(ArrayList.class, FILE_JSON_STRINGS);

        for (JsonValue v : list) {
            ContainerStringId item = ControllerJsonBase.getInstance().getJson()
                    .readValue(ContainerStringId.class, v);
            if (result == null) result = new HashMap<Integer, String>();
            result.put(item.getId(), item.getMessage());
        }

        return result;
    }

    public ItemsDayDataCollection getItemsDay() {
        return ControllerJsonBase.getInstance().readFile(ItemsDayDataCollection.class, FILE_JSON_ITEMS_DAY);
    }
}
