package org.byters.jamgame.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.byters.engine.view.IScreen;
import org.byters.jamgame.controller.*;
import org.byters.jamgame.model.ItemDayMeta;

public class ScreenGame implements IScreen {
    private static final String FILE_TEXTURE_ITEM_BOX = "graphics/items/box.png";
    private static final String FILE_TEXTURE_ITEM_PLANK1 = "graphics/items/h5.png";
    private static final String FILE_TEXTURE_ITEM_PLANK2 = "graphics/items/h6.png";
    private static final String FILE_TEXTURE_ITEM_BOTTLE = "graphics/items/bottle.png";
    private static final String FILE_TEXTURE_ITEM_TV = "graphics/items/tv.png";
    private static final String FILE_TEXTURE_ITEM_STONE = "graphics/items/h3.png";
    private static final String FILE_TEXTURE_ITEM_ALARM = "graphics/items/alarm.png";
    private static final String FILE_TEXTURE_ITEM_CAMERA = "graphics/items/photo.png";

    private static final String FILE_TEXTURE_ISLAND = "graphics/island2.png";
    private static final String FILE_TEXTURE_WATER = "graphics/tile.png";
    private static final String FILE_TEXTURE_SKY = "graphics/sky.png";

    private Texture tBox, tPlank1, tPlank2, tBottle, tTV, tStone, tAlarm, tCamera;
    private Texture tIsland, tWater, tSky;
    private PlayerAnimation playerAnimation;

    private BitmapFont font;

    @Override
    public void draw(SpriteBatch batch) {
        batch.draw(tSky
                , -Gdx.graphics.getWidth(), Gdx.graphics.getHeight() / 3 * 2
                , 0, 0
                , Gdx.graphics.getWidth() * 3, Gdx.graphics.getHeight() / 3);

        batch.draw(tWater
                , -Gdx.graphics.getWidth(), 0
                , 0, 0
                , Gdx.graphics.getWidth() * 3, Gdx.graphics.getHeight() / 3 * 2);

        batch.draw(tIsland, (Gdx.graphics.getWidth() - tIsland.getWidth()) / 2
                , (Gdx.graphics.getHeight() - tIsland.getHeight()) / 2 - 40);
        drawItems(batch);
        playerAnimation.draw(batch);
        ControllerDialogSelector.getInstance().draw(batch);
        ControllerMessage.getInstance().draw(batch);
        drawHint(batch);
        ControllerInventory.getInstance().drawInventory(batch);
    }

    private void drawHint(SpriteBatch batch) {
        font.draw(batch, "Use A,D to move, use Space to interact, use TAB to open inventory", 20, 20);
    }

    private void drawItems(SpriteBatch batch) {
        if (ControllerItemsDay.getInstance().getItems() == null) return;
        for (ItemDayMeta item : ControllerItemsDay.getInstance().getItems()) {
            batch.draw(getItemTexture(item), item.getPosX(), item.getPosY());
        }
    }

    private Texture getItemTexture(ItemDayMeta item) {
        return item.getItemId() == 3 //todo refactor
                ? tPlank1
                : item.getItemId() == 2
                ? tBottle
                : item.getItemId() == 1
                ? tPlank2
                : item.getItemId() == 7
                ? tAlarm
                : item.getItemId() == 4
                ? tStone
                : item.getItemId() == 6
                ? tCamera
                : item.getItemId() == 5
                ? tTV
                : tBox;
    }

    @Override
    public void update() {
        ControllerPlayer.getInstance().update();
        playerAnimation.update();
    }

    @Override
    public void input() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            ControllerGame.getInstance().showMenu();
            ControllerInventory.getInstance().clearInventory();
            ControllerDays.getInstance().resetDays();
        }

        if (ControllerDialogSelector.getInstance().isDialogShown()) {
            ControllerDialogSelector.getInstance().input();
            return;
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.TAB)
                || Gdx.input.isKeyJustPressed(Input.Keys.I)
                || Gdx.input.isKeyJustPressed(Input.Keys.B)
                || Gdx.input.isKeyJustPressed(Input.Keys.BACKSPACE)
                || Gdx.input.isKeyJustPressed(Input.Keys.CONTROL_LEFT)
                || Gdx.input.isKeyJustPressed(Input.Keys.CONTROL_RIGHT)) {
            ControllerPlayer.getInstance().stop();
            ControllerInventory.getInstance().changeInventoryVisibility();
        }

        if (ControllerInventory.getInstance().isInventoryVisible()) {
            ControllerInventory.getInstance().input();
            return;
        }

        inputPlayer();
    }

    private void inputPlayer() {
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)
                || Gdx.input.isKeyPressed(Input.Keys.A))
            ControllerPlayer.getInstance().moveLeft();
                /*else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)
                || Gdx.input.isKeyPressed(Input.Keys.S))
            ControllerPlayer.getInstance().moveDown();*/

        /*else if (Gdx.input.isKeyPressed(Input.Keys.UP)
                || Gdx.input.isKeyPressed(Input.Keys.W))
            ControllerPlayer.getInstance().moveUp();
        */
        else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)
                || Gdx.input.isKeyPressed(Input.Keys.D))
            ControllerPlayer.getInstance().moveRight();
        else ControllerPlayer.getInstance().stop();

        if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)
                || Gdx.input.isKeyJustPressed(Input.Keys.SPACE)
                || Gdx.input.isKeyJustPressed(Input.Keys.E)
                || Gdx.input.isKeyJustPressed(Input.Keys.F))
            ControllerPlayer.getInstance().interact();
    }

    @Override
    public void load(SpriteBatch batch) {
        font = new BitmapFont();

        tBox = new Texture(Gdx.files.internal(FILE_TEXTURE_ITEM_BOX));
        tPlank1 = new Texture(Gdx.files.internal(FILE_TEXTURE_ITEM_PLANK1));
        tPlank2 = new Texture(Gdx.files.internal(FILE_TEXTURE_ITEM_PLANK2));
        tBottle = new Texture(Gdx.files.internal(FILE_TEXTURE_ITEM_BOTTLE));
        tTV = new Texture(Gdx.files.internal(FILE_TEXTURE_ITEM_TV));
        tAlarm = new Texture(Gdx.files.internal(FILE_TEXTURE_ITEM_ALARM));
        tStone = new Texture(Gdx.files.internal(FILE_TEXTURE_ITEM_STONE));
        tCamera = new Texture(Gdx.files.internal(FILE_TEXTURE_ITEM_CAMERA));

        tIsland = new Texture(Gdx.files.internal(FILE_TEXTURE_ISLAND));
        tWater = new Texture(Gdx.files.internal(FILE_TEXTURE_WATER));
        tWater.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);

        tSky = new Texture(Gdx.files.internal(FILE_TEXTURE_SKY));
        tSky.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);

        ControllerPlayer.getInstance().load();
        ControllerItems.getInstance().load();
        ControllerInventory.getInstance().load();
        ControllerMessage.getInstance().load();
        ControllerItemsDay.getInstance().load();
        loadNewDay();

        playerAnimation = new PlayerAnimation();
        playerAnimation.load();
    }

    private void loadNewDay() {
        ControllerItemsDay.getInstance().loadItems();
    }

    @Override
    public void dispose() {

    }
}
