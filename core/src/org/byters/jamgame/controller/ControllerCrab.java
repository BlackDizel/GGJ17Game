package org.byters.jamgame.controller;

import com.badlogic.gdx.Gdx;
import org.byters.engine.model.IDrawableObject;
import org.byters.jamgame.model.DrawableObjectsEnum;

public class ControllerCrab {
    private static ControllerCrab instance;
    private CrabDrawableObject drawableObject;

    private ControllerCrab() {
        drawableObject = new CrabDrawableObject();
    }

    public static ControllerCrab getInstance() {
        if (instance == null) instance = new ControllerCrab();
        return instance;
    }

    private float getPosX() {
        return Gdx.graphics.getWidth() / 3;
    }

    private float getPosY() {
        return Gdx.graphics.getHeight() / 15 * 6;
    }

    public IDrawableObject getDrawableObject() {
        return drawableObject;
    }

    private class CrabDrawableObject implements IDrawableObject {

        @Override
        public int getX() {
            return (int) getPosX();
        }

        @Override
        public int getY() {
            return (int) getPosY();
        }

        @Override
        public int getOriginY() {
            return (int) getPosY();
        }

        @Override
        public int getID() {
            return DrawableObjectsEnum.CRAB_ID.ordinal();
        }
    }
}
