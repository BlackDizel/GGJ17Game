package org.byters.jamgame.controller;

import org.byters.jamgame.model.ItemsDayModel;
import org.byters.jamgame.model.Strings;
import org.byters.jamgame.model.inventory.*;

import java.util.ArrayList;
import java.util.Collections;

public class ControllerItems {
    public static final int NO_VALUE = -1;
    private static ControllerItems instance;
    private Items itemsData;

    private ControllerItems() {

    }

    public static ControllerItems getInstance() {
        if (instance == null) instance = new ControllerItems();
        return instance;
    }

    public void load() {
        itemsData = ControllerJson.getInstance().readItems();
    }

    public String getItemTitle(int id) {
        for (ItemInfo item : itemsData.getItems()) {
            if (item.getId() == id) {
                String title = Strings.getInstance().getString(item.getTitleID());
                return title == null ? "" : title;
            }
        }
        return "";
    }

    public String getTitleCombined(InventoryCell cell1, InventoryCell cell2) {
        if (cell1 == null || cell2 == null || cell1.isCellEmpty() || cell2.isCellEmpty())
            return null;

        //todo refactor this
        for (CraftInfo info : itemsData.getCraft()) {
            if (isContains(info.getItems(), cell1, cell2))
                return InventoryCell.getTitle(cell1, cell2);
        }
        return null;
    }

    public int getItemIdCombined(InventoryCell cell1, InventoryCell cell2) {
        if (cell1 == null || cell2 == null || cell1.isCellEmpty() || cell2.isCellEmpty())
            return NO_VALUE;

        //todo refactor this
        for (CraftInfo info : itemsData.getCraft()) {
            if (isEquals(info.getItems(), cell1, cell2))
                return info.getItemId();
        }
        return NO_VALUE;
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

    int getInteractedItemTime() {
        int id = ControllerItemsDay.getInstance().getInteractedItemID();
        if (id == ItemsDayModel.NO_VALUE)
            return NO_VALUE;

        if (itemsData == null || itemsData.getTimeInteract() == null) return NO_VALUE;
        for (InteractInfo item : itemsData.getTimeInteract())
            if (item.getItemId() == id)
                return item.getTimeInteract();
        return NO_VALUE;
    }

    public int[] getParts(InventoryCell item) {
        if (itemsData == null || itemsData.getDisassembly() == null)
            return null;

        for (DisassemblyInfo info : itemsData.getDisassembly()) {
            ArrayList<Integer> ids = item.getItems();
            if (ids == null || ids.size() == 0) continue;
            if (info.getItemId() == item.getItems().get(0))
                return info.getParts();
        }
        return null;
    }
}
