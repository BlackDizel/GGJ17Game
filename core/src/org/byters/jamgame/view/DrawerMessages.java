package org.byters.jamgame.view;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.byters.jamgame.controller.ControllerMessage;
import org.byters.jamgame.controller.ControllerPlayer;

public class DrawerMessages {

    private BitmapFont font;

    public void load() {
        font = HelperFont.getInstance().newInstanceFont();

    }

    void dispose() {
        font.dispose();
    }

    void draw(SpriteBatch batch) {
        if (ControllerMessage.getInstance().getMessage() == null) return;
        ControllerMessage.getInstance().setFontColor(font);
        font.draw(batch, ControllerMessage.getInstance().getMessage()
                , ControllerPlayer.getInstance().getMessagePositionX()
                , ControllerPlayer.getInstance().getMessagePositionY());
    }
}
