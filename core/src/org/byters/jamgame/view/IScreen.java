package org.byters.jamgame.view;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public interface IScreen {
    void draw(SpriteBatch batch);

    void load();

    void update();

    void input();

    void dispose();
}
