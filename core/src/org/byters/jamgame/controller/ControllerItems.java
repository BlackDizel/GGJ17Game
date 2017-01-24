package org.byters.jamgame.controller;

import com.badlogic.gdx.Gdx;
import com.google.gson.Gson;
import org.byters.jamgame.model.inventory.CraftInfo;
import org.byters.jamgame.model.inventory.InventoryCell;
import org.byters.jamgame.model.inventory.ItemInfo;
import org.byters.jamgame.model.inventory.Items;

import java.util.ArrayList;
import java.util.Collections;

public class ControllerItems {
    private static final String ITEMS_JSON = "items.json";
    private static ControllerItems instance;
    private Items itemsData;

    private ControllerItems() {

    }

    public static ControllerItems getInstance() {
        if (instance == null) instance = new ControllerItems();
        return instance;
    }

    public void load() {
        String json = Gdx.files.internal(ITEMS_JSON).readString();
        itemsData = new Gson().fromJson(json, Items.class);
    }

    public String getItemTitle(int id) {
        for (ItemInfo item : itemsData.getItems()) {
            if (item.getId() == id)
                return item.getTitle();
        }
        return "";
    }

    public String getTitleCombined(InventoryCell cell1, InventoryCell cell2) {
        if (cell1 == null || cell2 == null || cell1.isCellEmpty() || cell2.isCellEmpty())
            return null;

        //todo refactor this
        for (CraftInfo info : itemsData.getCraft()) {
            if (isEquals(info.getItems(), cell1, cell2))
                return info.getTitle();
            if (isContains(info.getItems(), cell1, cell2))
                return InventoryCell.getTitle(cell1, cell2);
        }
        return null;
    }

    private boolean isContains(int[] items, InventoryCell cell1, InventoryCell cell2) {
        return cell1.isSubset(items) && cell2.isSubset(items);
    }

    private boolean isEquals(int[] items, InventoryCell cell1, InventoryCell cell2) {
        ArrayList<Integer> cellItems = cell1.getItems();
        if (cellItems == null) cellItems = cell2.getItems();
        else
            cellItems.addAll(cell2.getItems());
        if (cellItems == null) return false;
        Collections.sort(cellItems);

        return cellItems.size() == items.length && isEquals(items, cellItems);
    }

    private boolean isEquals(int[] items, ArrayList<Integer> cellItems) {
        for (int i = 0; i < items.length; ++i)
            if (cellItems.get(i) != items[i])
                return false;
        return true;
    }

}
