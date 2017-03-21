package org.byters.jamgame.model;

import com.badlogic.gdx.Gdx;
import com.sun.istack.internal.Nullable;
import org.byters.engine.controller.ControllerMain;
import org.byters.jamgame.controller.ControllerJson;

import java.util.ArrayList;

public class ItemsDayModel {

    public static final int NO_VALUE = -1;
    private static final double SIN_SPEED_FACTOR = 1000d;
    private static final double SIN_HEIGHT_SCREEN_PERCENT = 0.01f;
    private ItemsDayDataCollection itemsAll;
    private ArrayList<ItemDayMeta> itemsAvailable;
    @Nullable
    private ItemDayMeta itemInteracted;

    public void load() {
        itemsAll = ControllerJson.getInstance().getItemsDay();
    }

    public void loadItems(int day) {
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

        if (itemsAvailable == null) itemsAvailable = new ArrayList<ItemDayMeta>();
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
        if (itemsAvailable == null || itemInteracted == null) return;
        itemsAvailable.remove(itemInteracted);
    }

    public void setItemInteracted(@Nullable ItemDayMeta item) {
        itemInteracted = item;
    }

    public boolean isNoAvailableItems() {
        return itemsAvailable == null || itemsAvailable.size() == 0;
    }

    public void update() {
        if (itemsAvailable == null) return;
        for (ItemDayMeta item : itemsAvailable) {
            if (itemInteracted != null && item.getID() == itemInteracted.getID()) continue;
            float value = (float) (Math.sin(System.currentTimeMillis() / SIN_SPEED_FACTOR) * Gdx.graphics.getHeight() / 100f * SIN_HEIGHT_SCREEN_PERCENT);
            item.translateY(value);
        }
    }

    public void resetItems() {
        itemsAvailable = null;
    }
}
