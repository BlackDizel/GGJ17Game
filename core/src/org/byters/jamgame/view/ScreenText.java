package org.byters.jamgame.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.byters.engine.view.IScreen;
import org.byters.jamgame.controller.ControllerScreenText;

public class ScreenText implements IScreen {

    private BitmapFont font;
    private GlyphLayout layout;

    @Override
    public void draw(SpriteBatch batch) {
        //todo implement string array to display long text
        font.draw(batch, layout
                , (Gdx.graphics.getWidth() - layout.width) / 2, Gdx.graphics.getHeight() / 2);
    }

    @Override
    public void load(SpriteBatch batch) {
        font = new BitmapFont();
        layout = new GlyphLayout();
        layout.setText(font, ControllerScreenText.getInstance().getText());
    }

    @Override
    public void update() {

    }

    @Override
    public void input() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)
                || Gdx.input.isKeyJustPressed(Input.Keys.ENTER))
            ControllerScreenText.getInstance().navigateNextScreen();
    }

    @Override
    public void dispose() {

    }

}
