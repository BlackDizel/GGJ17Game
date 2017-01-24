package org.byters.jamgame.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.byters.jamgame.model.MenuModel;

public class ControllerMenu {
    private static ControllerMenu instance;
    private BitmapFont font;
    private MenuModel menu;

    private ControllerMenu() {
        menu = new MenuModel();
    }

    public static ControllerMenu getInstance() {
        if (instance == null) instance = new ControllerMenu();
        return instance;
    }

    public void draw(SpriteBatch batch) {
        if (menu.getItemsCount() == 0)
            return;
        for (int i = 0; i < menu.getItemsCount(); ++i) {
            String title = menu.getTitle(i);
            if (title == null || title.trim().equals("")) continue;
            font.setColor(menu.getCurrentIndex() == i ? Color.RED : Color.WHITE);
            font.draw(batch, title, Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2 - i * 20);
        }
    }

    public void load() {
        font = new BitmapFont();
        menu.load();
    }

    public void menuItemNext() {
        menu.next();
    }

    public void menuItemPrevous() {
        menu.previous();
    }

    public void selectMenu() {
        int id = menu.getCurrentItemId();
        if (id == MenuModel.NO_VALUE)
            return;

        if (id == 1) {
            ControllerScreenText.getInstance().navigateScreenStory();
        } else if (id == 2) {
            Gdx.app.exit();// todo do not show on android or html
        }
    }
}
