package org.byters.jamgame.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.byters.engine.model.Menu;
import org.byters.jamgame.model.Strings;
import org.byters.jamgame.view.HelperFont;

public class ControllerMenu {
    private static ControllerMenu instance;
    private BitmapFont font;
    private Menu menu;

    private ControllerMenu() {
        menu = new Menu();
    }

    public static ControllerMenu getInstance() {
        if (instance == null) instance = new ControllerMenu();
        return instance;
    }

    public void draw(SpriteBatch batch) {
        if (menu.getItemsCount() == 0)
            return;
        for (int i = 0; i < menu.getItemsCount(); ++i) {
            int id = menu.getMenuTitleId(i);
            if (id == Menu.NO_VALUE)
                continue;
            String title = Strings.getInstance().getString(id);
            if (title == null || title.trim().equals("")) continue;
            font.setColor(menu.isCurrentIndex(i) ? Color.RED : Color.WHITE);
            font.draw(batch, title, Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2 - i * 20);
        }
    }

    public void load() {
        font = HelperFont.getInstance().getFont();
        menu.load(ControllerJson.FILE_JSON_MENU);
        menu.setTextBackID(Strings.BACK_ID);
    }

    public void menuItemNext() {
        menu.next();
    }

    public void menuItemPrevous() {
        menu.previous();
    }

    public void selectMenu() {
        int id = menu.getCurrentItemId();
        if (id == Menu.NO_VALUE)
            return;

        if (id == 1) {
            ControllerScreenText.getInstance().navigateScreenStory();
        } else if (id == 2) {
            Gdx.app.exit();// todo do not show on android or html
        }
    }
}
