package org.byters.jamgame.model;

public enum DrawableObjectsEnum {
    PLAYER_ID, CRAB_ID;

    public static int getItemID(ItemDayMeta item) {
        return getItemID(item.getItemId());
    }

    public static int getItemID(int itemId) {
        return values().length + itemId;
    }
}
