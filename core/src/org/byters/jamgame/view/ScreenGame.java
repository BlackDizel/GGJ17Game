package org.byters.jamgame.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.byters.engine.view.DrawerDrawableObjectList;
import org.byters.engine.view.IScreen;
import org.byters.jamgame.controller.*;
import org.byters.jamgame.model.DrawableObjectsEnum;
import org.byters.jamgame.model.ItemDayMeta;
import org.byters.jamgame.model.PlayerModel;
import org.byters.jamgame.model.Strings;

public class ScreenGame implements IScreen {

    private PlayerAnimation playerAnimation;
    private SimpleLineLoopAnimation waterRingsAnimation;
    private SimpleLineLoopAnimation waterFishAnimation;
    private DrawerDrawableObjectList drawerVerticalSortedObjects;

    private BitmapFont font;
    private TexturesGame texturesGame;
    private DrawerMessages drawerMessages;

    @Override
    public void draw(SpriteBatch batch) {
        drawBackground(batch);
        drawObjects(batch);
        drawUI(batch);
    }

    private void drawObjects(SpriteBatch batch) {
        drawerVerticalSortedObjects.draw(batch, ControllerDays.getInstance().getDrawableObjects());
        if (playerAnimation.getTextureTinted() != null) {
            batch.setColor(PlayerModel.TINT_COLOR);
            batch.draw(playerAnimation.getTextureTinted()
                    , ControllerPlayer.getInstance().getPlayerPositionX()
                    , ControllerPlayer.getInstance().getPlayerPositionY());
            batch.setColor(Color.WHITE);

            if (waterRingsAnimation.getCurrentFrame() != null) {
                batch.draw(waterRingsAnimation.getCurrentFrame()
                        , ControllerPlayer.getInstance().getPlayerPositionX() + playerAnimation.getFrameWidth() / 2 - waterRingsAnimation.getFrameWidth() / 2
                        , playerAnimation.getDrawableObject().getY() - waterRingsAnimation.getFrameHeight() / 2);
            }
        }
    }

    private void drawUI(SpriteBatch batch) {
        ControllerDialogSelector.getInstance().draw(batch);
        drawerMessages.draw(batch);
        drawHint(batch);
        ControllerInventory.getInstance().drawInventory(batch);
    }

    private void drawBackground(SpriteBatch batch) {
        batch.draw(texturesGame.tSky
                , -Gdx.graphics.getWidth(), Gdx.graphics.getHeight() / 3 * 2
                , 0, 0
                , Gdx.graphics.getWidth() * 3, Gdx.graphics.getHeight() / 3);

        batch.draw(texturesGame.tWater
                , -Gdx.graphics.getWidth(), 0
                , 0, 0
                , Gdx.graphics.getWidth() * 3, Gdx.graphics.getHeight() / 3 * 2);

        batch.draw(texturesGame.tIsland, (Gdx.graphics.getWidth() - texturesGame.tIsland.getWidth()) / 2
                , (Gdx.graphics.getHeight() - texturesGame.tIsland.getHeight()) / 2 - 40);

        batch.draw(waterFishAnimation.getCurrentFrame()
                , Gdx.graphics.getWidth() * ControllerObjectsDay.FISH_POSITION_X_FACTOR
                , Gdx.graphics.getHeight() * ControllerObjectsDay.FISH_POSITION_Y_FACTOR);
    }

    private void drawHint(SpriteBatch batch) {
        font.draw(batch, Strings.getInstance().control_hint, 20, 20);
    }

    @Override
    public void update() {
        ControllerPlayer.getInstance().update();
        ControllerMessage.getInstance().update();
        playerAnimation.update();
        waterRingsAnimation.update();
        waterFishAnimation.update();
        drawerVerticalSortedObjects.setTexture(playerAnimation.getTexture(), playerAnimation.getDrawableObject().getID());

        ControllerItemsDay.getInstance().update();
        ControllerDays.getInstance().update();
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

        drawerVerticalSortedObjects = new DrawerDrawableObjectList();

        texturesGame = new TexturesGame();
        texturesGame.load();

        playerAnimation = new PlayerAnimation();
        playerAnimation.load();

        waterRingsAnimation = new SimpleLineLoopAnimation(TexturesGame.ANIMATION_FILE_WATER_RINGS, 3, 0.09f);
        waterRingsAnimation.load();

        waterFishAnimation = new SimpleLineLoopAnimation(TexturesGame.ANIMATION_FILE_FISH_WATER, 3, 0.09f);
        waterFishAnimation.load();

        ControllerPlayer.getInstance().load();
        ControllerItems.getInstance().load();
        ControllerInventory.getInstance().load();

        drawerMessages = new DrawerMessages();
        drawerMessages.load();
        ControllerItemsDay.getInstance().load();
        loadDay();
    }

    private void loadDay() {
        ControllerItemsDay.getInstance().loadItems();

        drawerVerticalSortedObjects.clear();

        drawerVerticalSortedObjects.setTexture(playerAnimation.getTexture(), playerAnimation.getDrawableObject().getID());
        ControllerDays.getInstance().addDrawableObject(playerAnimation.getDrawableObject());

        drawerVerticalSortedObjects.setTexture(texturesGame.getCrab(), ControllerCrab.getInstance().getDrawableObject().getID());
        ControllerDays.getInstance().addDrawableObject(ControllerCrab.getInstance().getDrawableObject());

        if (ControllerItemsDay.getInstance().getItems() != null)
            for (ItemDayMeta item : ControllerItemsDay.getInstance().getItems()) {
                drawerVerticalSortedObjects.setTexture(texturesGame.getItemTexture(item), DrawableObjectsEnum.getItemID(item));
                ControllerDays.getInstance().addDrawableObject(item);
            }
    }

    @Override
    public void dispose() {
        texturesGame.dispose();
        drawerMessages.dispose();
    }
}
