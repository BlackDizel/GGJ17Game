package org.byters.jamgame.controller;

import org.byters.jamgame.model.ItemDayMeta;
import org.byters.jamgame.model.ItemsDayModel;

import java.util.ArrayList;

public class ControllerItemsDay {
    private static final float INTERACTION_DISTANCE = 30;
    private static ControllerItemsDay instance;
    private ItemsDayModel model;

    private ControllerItemsDay() {
        model = new ItemsDayModel();
    }

    public static ControllerItemsDay getInstance() {
        if (instance == null) instance = new ControllerItemsDay();
        return instance;
    }

    public void load() {
        model.load();
    }

    public void loadItems() {
        model.loadItems(ControllerDays.getInstance().getDay());
    }

    public ArrayList<ItemDayMeta> getItems() {
        return model.getItemsDay();
    }

    void interactComplete() {
        int itemId = model.getItemInteractedId();
        if (itemId == ItemsDayModel.NO_VALUE) return;
        ControllerInventory.getInstance().addItem(itemId);
        ControllerDays.getInstance().removeDrawableObject(itemId);
        model.removeItemInteracted();

        //todo temporary #NEWDAY
        if (model.isNoAvailableItems() && !ControllerDays.getInstance().isLastDay()) {
            ControllerDays.getInstance().dayOver();
        }
    }

    boolean setItemInteract() {
        if (model.getItemsDay() == null) return false;
        for (ItemDayMeta item : model.getItemsDay()) {
            if (Math.abs(item.getPosX() - ControllerPlayer.getInstance().getPositionXCenter()) < INTERACTION_DISTANCE) {
                model.setItemInteracted(item);
                return true;
            }
        }
        return false;
    }

    public void update() {
        model.update();
    }
}
