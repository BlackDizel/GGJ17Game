package org.byters.jamgame.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.byters.jamgame.model.inventory.Inventory;

public class ControllerInventory {
    private static ControllerInventory instance;
    private Inventory inventory;

    private ControllerInventory() {
        inventory = new Inventory();
    }

    public static ControllerInventory getInstance() {
        if (instance == null) instance = new ControllerInventory();
        return instance;
    }

    public void loadSavedState() {
        //todo implement
    }

    public void input() {
        if (Gdx.input.justTouched())
            inventory.input(Gdx.input.getX(), Gdx.input.getY());
    }

    public void changeInventoryVisibility() {
        inventory.changeVisibility();
        if (isInventoryVisible())
            ControllerCamera.getInstance().translateCamera(ControllerPlayer.getInstance().getCameraInventoryTranslateX(), 0); //todo set player center on visible area
        else ControllerCamera.getInstance().resetCamera();
    }

    public void drawInventory(SpriteBatch batch) {
        if (!inventory.isVisible()) return;
        inventory.draw(batch);
    }

    public void load() {
        inventory.load();
    }

    void addItem(int id) {
        inventory.addItem(id);
    }

    public boolean isInventoryVisible() {
        return inventory.isVisible();
    }

    public void clearInventory() {
        inventory.removeItems();
    }
}
