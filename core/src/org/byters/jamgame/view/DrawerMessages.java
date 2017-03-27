package org.byters.jamgame.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Align;
import org.byters.jamgame.controller.ControllerMessage;
import org.byters.jamgame.controller.ControllerPlayer;
import org.byters.jamgame.model.Strings;

public class DrawerMessages {

    private static final String FILE_BUTTON_PROCEED = "graphics/buttons/button_proceed.png";
    private BitmapFont font;
    private GlyphLayout layout;
    private TextureRegion textureButton;

    public void load() {
        font = HelperFont.getInstance().newInstanceFont();
        layout = new GlyphLayout();
        textureButton = new TextureRegion(new Texture(Gdx.files.internal(FILE_BUTTON_PROCEED)));
    }

    void dispose() {
        font.dispose();
        textureButton.getTexture().dispose();
    }

    private void updateLayout() {
        String message = Strings.getInstance().getString(ControllerMessage.getInstance().getMessage().getMessageID());
        layout.setText(font, message, font.getColor(), ControllerMessage.getInstance().getMessageWidth(), Align.left, true);
    }

    void draw(SpriteBatch batch) {
        if (ControllerMessage.getInstance().getMessage() == null) return;
        ControllerMessage.getInstance().setFontColor(font);
        updateLayout();

        font.draw(batch, layout
                , ControllerPlayer.getInstance().getMessagePositionX()
                , ControllerPlayer.getInstance().getMessagePositionY());

        batch.draw(textureButton
                , ControllerMessage.getInstance().getButtonPositionX()
                , ControllerMessage.getInstance().getButtonPositionY()
                , 0, 0
                , textureButton.getTexture().getWidth()
                , textureButton.getTexture().getHeight()
                , ControllerMessage.getInstance().getButtonSize() / (float) textureButton.getTexture().getWidth()
                , ControllerMessage.getInstance().getButtonSize() / (float) textureButton.getTexture().getHeight()
                , 0);
    }
}
