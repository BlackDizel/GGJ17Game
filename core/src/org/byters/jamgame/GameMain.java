package org.byters.jamgame;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Color;
import org.byters.engine.Engine;
import org.byters.jamgame.view.ScreenMenu;

public class GameMain extends ApplicationAdapter {

    @Override
    public void create() {
        Engine.getInstance().create(new ScreenMenu());
        setColorClear();
    }

    private void setColorClear() {
        Color colorClear = new Color();
        colorClear.r = 0.1f;
        colorClear.g = 0.1f;
        colorClear.b = 0.1f;
        colorClear.a = 1f;
        Engine.getInstance().setColorClear(colorClear);
    }

    @Override
    public void render() {
        Engine.getInstance().render();
    }

    @Override
    public void dispose() {
        Engine.getInstance().despose();
    }

    public void resize(int width, int height) {
        Engine.getInstance().resize(width, height);
    }

}
