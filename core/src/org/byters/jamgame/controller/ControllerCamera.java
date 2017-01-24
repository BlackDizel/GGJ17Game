package org.byters.jamgame.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;

public class ControllerCamera {

    private static ControllerCamera instance;
    private OrthographicCamera camera;
    private Vector3 originPosition;

    public static ControllerCamera getInstance() {
        if (instance == null) instance = new ControllerCamera();
        return instance;
    }

    public void load() {
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        this.originPosition = camera.position.cpy();
    }

    public Matrix4 getCameraProjection() {
        return camera.combined;
    }

    void translateCamera(float x, float y) {
        camera.translate(x, y);
        camera.update();
    }

    void resetCamera() {
        camera.position.set(originPosition);
        camera.update();
    }
}
