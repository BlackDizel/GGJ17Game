package org.byters.jamgame.controller;

import org.byters.engine.controller.ControllerMain;
import org.byters.engine.model.IDrawableObject;
import org.byters.jamgame.model.DrawableObjectsEnum;
import org.byters.jamgame.model.ItemDayMeta;
import org.byters.jamgame.model.ItemsDayModel;
import org.byters.jamgame.view.ScreenGame;

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

    public void interactComplete() {
        int itemId = model.getItemInteractedId();
        if (itemId == model.NO_VALUE) return;
        ControllerInventory.getInstance().addItem(itemId);
        ((ScreenGame)ControllerMain.getInstance().getCurrentScreen()).removeDrawableObjectItemsDay(model.getItemInteractedId());
        model.removeItemInteracted();

        //todo temporary #NEWDAY
        if (model.isNoAvailableItems() && !ControllerDays.getInstance().isLastDay()) {
            ControllerDays.getInstance().newDay();
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

    public IDrawableObject getDrawableObject(ItemDayMeta item) {
        return new ItemDayDrawableObject(item);
    }

    private class ItemDayDrawableObject implements IDrawableObject {

        private final ItemDayMeta item;

        ItemDayDrawableObject(ItemDayMeta item) {
            this.item = item;
        }

        @Override
        public int getX() {
            return item.getPosX();
        }

        @Override
        public int getY() {
            return item.getPosY();
        }

        @Override
        public int getOriginY() {
            return item.getPosY();
        }

        @Override
        public int getID() {
            return DrawableObjectsEnum.getItemID(item);
        }
    }

}
