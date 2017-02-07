package org.byters.jamgame.model;

import com.badlogic.gdx.Gdx;
import com.google.gson.Gson;
import org.byters.engine.controller.ControllerMain;

import java.util.ArrayList;

public class ItemsDayModel {

    public static final int NO_VALUE = -1;
    private ItemsDayDataCollection itemsAll;
    private ArrayList<ItemDayMeta> itemsAvailable;
    private ItemDayMeta itemInteracted;


    public void load() {

        String json = Gdx.files.internal("items_day.json").readString();
        itemsAll = new Gson().fromJson(json, ItemsDayDataCollection.class);
    }

    public void loadItems(int day) {
        itemsAvailable = null;
        if (itemsAll == null) return;

        int[] itemsIdsDay = null;
        for (ItemsDayData item : itemsAll.getItemsCollection()) {
            if (item.getDay() == day) {
                itemsIdsDay = item.getItems();
                break;
            }
        }

        if (itemsIdsDay == null || itemsIdsDay.length == 0)
            return;

        itemsAvailable = new ArrayList<ItemDayMeta>();
        for (int id : itemsIdsDay) {

            int x = Gdx.graphics.getWidth() / 3 + (int) (ControllerMain.getInstance().getRandom().nextFloat() * (Gdx.graphics.getWidth() / 3));
            int y = Gdx.graphics.getHeight() / 24 * 9 + (int) (ControllerMain.getInstance().getRandom().nextFloat() * (Gdx.graphics.getHeight() / 24 * 2));
            itemsAvailable.add(new ItemDayMeta(id, x, y));
        }
    }

    public ArrayList<ItemDayMeta> getItemsDay() {
        return itemsAvailable;
    }

    public int getItemInteractedId() {
        return itemInteracted == null ? NO_VALUE : itemInteracted.getItemId();
    }

    public void removeItemInteracted() {
        if (itemsAvailable == null) return;
        itemsAvailable.remove(itemInteracted);
    }

    public void setItemInteracted(ItemDayMeta item) {
        itemInteracted = item;
    }

    public boolean isNoAvailableItems() {
        return itemsAvailable == null || itemsAvailable.size() == 0;
    }
}
