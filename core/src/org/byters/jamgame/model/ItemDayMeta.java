package org.byters.jamgame.model;

import org.byters.engine.model.IDrawableObject;

public class ItemDayMeta implements IDrawableObject {
    private int itemId;
    private float xPos;
    private float yPos;

    ItemDayMeta(int itemId, int xPos, int yPos) {
        this.itemId = itemId;
        this.xPos = xPos;
        this.yPos = yPos;
    }

    public int getItemId() {
        return itemId;
    }

    void translateY(float value) {
        yPos += value;
    }

    @Override
    public int getX() {
        return (int) xPos;
    }

    @Override
    public int getY() {
        return (int) yPos;
    }

    @Override
    public int getOriginY() {
        return (int) yPos;
    }

    @Override
    public int getID() {
        return DrawableObjectsEnum.getItemID(itemId);
    }
}
