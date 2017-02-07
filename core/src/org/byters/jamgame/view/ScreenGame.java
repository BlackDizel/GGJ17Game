package org.byters.jamgame.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.byters.engine.view.IScreen;
import org.byters.jamgame.controller.*;
import org.byters.jamgame.model.ItemDayMeta;

public class ScreenGame implements IScreen {

    private PlayerAnimation playerAnimation;

    private BitmapFont font;
    private TexturesGame texturesGame;

    @Override
    public void draw(SpriteBatch batch) {
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
        playerAnimation.draw(batch);
        drawCrab(batch);
        drawItems(batch);
        ControllerDialogSelector.getInstance().draw(batch);
        ControllerMessage.getInstance().draw(batch);
        drawHint(batch);
        ControllerInventory.getInstance().drawInventory(batch);
    }

    private void drawCrab(SpriteBatch batch) {
        batch.draw(texturesGame.tCrab, ControllerCrab.getInstance().getPosX(), ControllerCrab.getInstance().getPosY());
    }

    private void drawHint(SpriteBatch batch) {
        font.draw(batch, "Use A,D to move, Space to interact, use TAB to open/close inventory", 20, 20);
    }

    private void drawItems(SpriteBatch batch) {
        if (ControllerItemsDay.getInstance().getItems() == null) return;
        for (ItemDayMeta item : ControllerItemsDay.getInstance().getItems()) {
            batch.draw(texturesGame.getItemTexture(item), item.getPosX(), item.getPosY());
        }
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

        texturesGame = new TexturesGame();
        texturesGame.load();

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
        texturesGame.dispose();
    }
}
