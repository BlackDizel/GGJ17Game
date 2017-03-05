package org.byters.jamgame.model.inventory;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import org.byters.jamgame.controller.ControllerDays;
import org.byters.jamgame.controller.ControllerItems;
import org.byters.jamgame.controller.ControllerMessage;
import org.byters.jamgame.controller.ControllerPlayer;
import org.byters.jamgame.model.Strings;

public class Inventory {
    public static final int INVENTORY_WIDTH = 200;
    private static final String TEXTURE_INVENTORY_BACKGROUND = "texture_inventory_background.png";
    private static final String TEXTURE_INVENTORY_CRAFT = "graphics/buttons/craft.png";
    private static final String TEXTURE_INVENTORY_USE = "graphics/buttons/use.png";
    private static final String TEXTURE_INVENTORY_CRAFT_PRESSED = "graphics/buttons/crafth.png";
    private static final String TEXTURE_INVENTORY_USE_PRESSED = "graphics/buttons/useh.png";
    private static final int MAX_ITEMS_NUM = 20;
    private static final int ITEM_HEIGHT = 20;
    private static final String TITLE_ITEM_QUEST = "transmitter";
    private static final String TITLE_ITEM_CAMERA = "camera";
    private static final String TITLE_ITEM_TV = "TV";
    private static final String TITLE_ITEM_ALARM = "alarm";
    private static final int ID_MICROPHONE = 12;
    private static final int ID_ANTENNA = 11;
    private static final int ID_WIRES = 10;
    private static final int ID_CIRCUIT_BOARD = 9;
    private static final int ID_BATTERY = 8;

    private boolean isVisible;
    private Texture tInventoryBackground;
    private Texture tCraft, tUse, tCraftPressed, tUsePressed;
    private BitmapFont font;
    private InventoryCell[] itemsIDs;
    private Rectangle rButtonUse, rButtonCraft;
    private Mode mode;
    private InventoryCell selectedItem;
    private Vector2 first_item_position;

    public Inventory() {
        isVisible = false;
        itemsIDs = new InventoryCell[MAX_ITEMS_NUM];
        first_item_position = new Vector2(Gdx.graphics.getWidth() - INVENTORY_WIDTH + 40, Gdx.graphics.getHeight() / 6 * 5);
        setMode(Mode.VIEW);
    }

    private void cancelSelection() {
        selectedItem = null;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void changeVisibility() {
        isVisible = !isVisible;
        setMode(Mode.VIEW);
    }

    public void load() {
        tInventoryBackground = new Texture(Gdx.files.internal(TEXTURE_INVENTORY_BACKGROUND));
        tCraft = new Texture(Gdx.files.internal(TEXTURE_INVENTORY_CRAFT)); //todo use spritesheet
        tUse = new Texture(Gdx.files.internal(TEXTURE_INVENTORY_USE));
        tCraftPressed = new Texture(Gdx.files.internal(TEXTURE_INVENTORY_CRAFT_PRESSED));
        tUsePressed = new Texture(Gdx.files.internal(TEXTURE_INVENTORY_USE_PRESSED));
        tInventoryBackground.setWrap(Texture.TextureWrap.ClampToEdge, Texture.TextureWrap.Repeat);
        font = new BitmapFont();

        rButtonCraft = new Rectangle(Gdx.graphics.getWidth() - INVENTORY_WIDTH + 40, tCraft.getHeight() + 20, tCraft.getWidth(), tCraft.getHeight());
        rButtonUse = new Rectangle(Gdx.graphics.getWidth() - INVENTORY_WIDTH + 40 + rButtonCraft.getWidth() + 20, tUse.getHeight() + 20, tUse.getWidth(), tUse.getHeight());
    }

    public void draw(SpriteBatch batch) {
        if (tInventoryBackground == null || font == null) return;
        batch.draw(tInventoryBackground
                , ControllerPlayer.getInstance().getCameraInventoryTranslateX() + Gdx.graphics.getWidth() - INVENTORY_WIDTH, 0
                , 0, 0
                , INVENTORY_WIDTH, Gdx.graphics.getHeight());

        for (int i = 0; i < itemsIDs.length; ++i) {
            if (itemsIDs[i] == null) continue;

            font.setColor(itemsIDs[i] == selectedItem ? Color.YELLOW : Color.GRAY);
            font.draw(batch, itemsIDs[i].getTitle()
                    , first_item_position.x + ControllerPlayer.getInstance().getCameraInventoryTranslateX()
                    , first_item_position.y - i * ITEM_HEIGHT);
        }

        batch.draw(mode == Mode.CRAFT ? tCraftPressed : tCraft, ControllerPlayer.getInstance().getCameraInventoryTranslateX() + rButtonCraft.getX(), rButtonCraft.getY());
        batch.draw(mode == Mode.USE ? tUsePressed : tUse, ControllerPlayer.getInstance().getCameraInventoryTranslateX() + rButtonUse.getX(), rButtonUse.getY());
    }

    public boolean addItem(int id) {
        for (int i = 0; i < itemsIDs.length; ++i) {
            if (itemsIDs[i] == null)
                itemsIDs[i] = new InventoryCell();
            if (itemsIDs[i].isCellEmpty()) {
                itemsIDs[i].setItem(id);
                return true;
            }
        }
        return false;
    }

    boolean combineItems(InventoryCell cell1, InventoryCell cell2) {

        int itemId;
        itemId = ControllerItems.getInstance().getItemIdCombined(cell1, cell2);
        if (itemId != ControllerItems.NO_VALUE) {
            removeItem(cell1);
            removeItem(cell2);
            addItem(itemId);
            selectedItem = null;
            return true;
        } else {
            String title = ControllerItems.getInstance().getTitleCombined(cell1, cell2);
            if (title == null) return false;
            removeItem(cell1);
            removeItem(cell2);
            addItem(title, cell1, cell2);
            selectedItem = null;
            return true;
        }
    }

    private void addItem(String title, InventoryCell cell1, InventoryCell cell2) {
        for (int i = 0; i < itemsIDs.length; ++i) {
            if (itemsIDs[i] == null) {
                itemsIDs[i] = new InventoryCell();
                itemsIDs[i].setTitle(title);
                itemsIDs[i].setItems(cell1, cell2);
                return;
            }
        }
    }

    private void removeItem(InventoryCell cell) {
        for (int i = 0; i < itemsIDs.length; ++i) {
            if (itemsIDs[i] == cell) //todo check
            {
                itemsIDs[i] = null;
                return;
            }
        }
    }

    public void input(int x, int y) {
        int yPos = Gdx.graphics.getHeight() - y;

        if (rButtonUse.contains(x, yPos)) {
            setMode(mode == Mode.USE ? Mode.VIEW : Mode.USE);
            cancelSelection();
            return;
        } else if (rButtonCraft.contains(x, yPos)) {
            setMode(mode == Mode.CRAFT ? Mode.VIEW : Mode.CRAFT);
            return;
        }

        checkItemSelect(x, yPos);
    }

    private void setMode(Mode mode) {
        this.mode = mode;
        cancelSelection();
    }

    private void checkItemSelect(int x, int y) {
        if (mode == Mode.VIEW)
            return;

        InventoryCell item = getItem(x, y);
        if (item == null) return;

        if (mode == Mode.USE) {
            if (item.getTitle().equals(TITLE_ITEM_QUEST)) {
                ControllerDays.getInstance().dayOver();
                return;
            }
            if (checkDisassembly(item)) return;
            ControllerMessage.getInstance().showMessage(Strings.getInstance().cannot_use);
        } else if (mode == Mode.CRAFT) {
            if (selectedItem == null) {
                selectedItem = item;
                return;
            }

            if (selectedItem == item) {
                selectedItem = null;
                return;
            }

            ControllerMessage.getInstance().showMessage(
                    combineItems(selectedItem, item) ? Strings.getInstance().success : Strings.getInstance().cannot_craft);
        }
    }

    private boolean checkDisassembly(InventoryCell item) {
        //todo move this to json
        if (item.getTitle().equals(TITLE_ITEM_CAMERA)) {
            removeItem(item);
            addItem(ID_MICROPHONE);
            return true;
        }
        if (item.getTitle().equals(TITLE_ITEM_TV)) {
            removeItem(item);
            addItem(ID_ANTENNA);
            addItem(ID_WIRES);
            addItem(ID_CIRCUIT_BOARD);
            return true;
        }

        if (item.getTitle().equals(TITLE_ITEM_ALARM)) {
            removeItem(item);
            addItem(ID_BATTERY);
            return true;
        }
        return false;
    }

    private InventoryCell getItem(int x, int y) {
        if (x < first_item_position.x || y > first_item_position.y)
            return null;

        for (int i = 0; i < itemsIDs.length; ++i) {
            if (y > first_item_position.y - (i + 1) * ITEM_HEIGHT) {
                return itemsIDs[i];
            }
        }

        return null;
    }

    public void removeItems() {
        isVisible = false;
        for (int i = 0; i < itemsIDs.length; ++i)
            itemsIDs[i] = null;

        setMode(Mode.VIEW);
    }

    private enum Mode {CRAFT, USE, VIEW}
}
