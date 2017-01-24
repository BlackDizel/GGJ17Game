package org.byters.jamgame;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.byters.jamgame.controller.ControllerCamera;
import org.byters.jamgame.controller.ControllerMain;
import org.byters.jamgame.controller.ControllerPlayer;

public class GameMain extends ApplicationAdapter {
    SpriteBatch batch;

    @Override
    public void create() {
        batch = new SpriteBatch();
        load();
    }

    private void load() {
        ControllerCamera.getInstance().load();
        //todo this must be async
        ControllerMain.getInstance().getCurrentScreen().load();
    }

    @Override
    public void render() {
        update();
        input();
        draw();
    }

    private void input() {
        ControllerMain.getInstance().getCurrentScreen().input();
    }

    private void update() {
        ControllerMain.getInstance().getCurrentScreen().update();
        ControllerPlayer.getInstance().update();
    }

    private void draw() {

        Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.setProjectionMatrix(ControllerCamera.getInstance().getCameraProjection());
        batch.begin();
        ControllerMain.getInstance().getCurrentScreen().draw(batch);
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
    }
}
