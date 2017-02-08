package org.byters.jamgame.model.inventory;

import org.byters.jamgame.controller.ControllerItems;

import java.util.ArrayList;

public class InventoryCell {
    private static final int MAX_CAPACITY = 6;
    private int[] ids;
    private String title;

    InventoryCell() {
        ids = new int[MAX_CAPACITY];
    }

    public static String getTitle(InventoryCell cell1, InventoryCell cell2) {
        return cell1.getTitle() + cell2.getTitle(); //no String.format support in html project
    }

    public boolean isCellEmpty() {
        for (int item : ids)
            if (item != 0) return false;
        return true;
    }

    void setItem(int id) {
        clearCell();
        ids[0] = id;
    }

    private void clearCell() {
        for (int i = 0; i < ids.length; ++i)
            ids[i] = 0;
    }

    String getTitle() {
        if (title == null || title.trim().equals(""))
            title = findTitle();
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    private String findTitle() {
        String result = "";
        for (int id : ids) {
            if (id == 0) continue;
            result = result + (result.equals("") ? "" : "+") + ControllerItems.getInstance().getItemTitle(id);
        }
        return result;
    }

    public boolean isSubset(int[] items) {
        for (int id : ids) {
            if (id == 0) continue;
            if (!contains(items, id))
                return false;
        }
        return true;
    }

    private boolean contains(int[] items, int testId) {
        for (int id : items)
            if (id == testId)
                return true;
        return false;
    }

    public ArrayList<Integer> getItems() {
        ArrayList<Integer> result = null;
        for (int id : ids) {
            if (id == 0) continue;
            if (result == null) result = new ArrayList<Integer>();
            result.add(id);
        }
        return result;
    }

    void setItems(InventoryCell cell1, InventoryCell cell2) {
        int position = 0;
        for (int id : cell1.ids) {
            if (id == 0) continue;
            ids[position] = id;
            ++position;
        }

        for (int id : cell2.ids) {
            if (id == 0) continue;
            ids[position] = id;
            ++position;
            if (position >= MAX_CAPACITY)
                throw new IllegalArgumentException("error on combine items. Max items capacity is 5");
        }
    }
}
