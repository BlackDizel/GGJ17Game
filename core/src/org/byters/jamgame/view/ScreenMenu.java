package org.byters.jamgame.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.byters.engine.view.IScreen;
import org.byters.jamgame.controller.ControllerMenu;

public class ScreenMenu implements IScreen {

    @Override
    public void draw(SpriteBatch batch) {
        ControllerMenu.getInstance().draw(batch);
    }

    @Override
    public void update() {

    }

    @Override
    public void input() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN))
            ControllerMenu.getInstance().menuItemNext();
        else if (Gdx.input.isKeyJustPressed(Input.Keys.UP))
            ControllerMenu.getInstance().menuItemPrevous();
        else if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)
                || (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)))
            ControllerMenu.getInstance().selectMenu();
    }

    @Override
    public void load(SpriteBatch batch) {
        ControllerMenu.getInstance().load();
    }

    @Override
    public void dispose() {

    }
}
