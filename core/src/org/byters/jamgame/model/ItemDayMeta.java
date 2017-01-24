package org.byters.jamgame.model;

public class ItemDayMeta {
    private int itemId;
    private int xPos;
    private int yPos;

    public ItemDayMeta(int itemId, int xPos, int yPos) {
        this.itemId = itemId;
        this.xPos = xPos;
        this.yPos = yPos;
    }

    public int getItemId() {
        return itemId;
    }

    public int getPosX() {
        return xPos;
    }

    public int getPosY() {
        return yPos;
    }
}
