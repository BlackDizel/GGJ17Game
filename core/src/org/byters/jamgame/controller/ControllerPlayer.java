package org.byters.jamgame.controller;

import com.badlogic.gdx.math.Vector2;
import org.byters.jamgame.model.PlayerModel;

public class ControllerPlayer {
    private static ControllerPlayer instance;
    private PlayerModel model;
    private Vector2 vMoveDirection;

    private ControllerPlayer() {
        vMoveDirection = new Vector2();
        model = new PlayerModel();
    }

    public static ControllerPlayer getInstance() {
        if (instance == null) instance = new ControllerPlayer();
        return instance;
    }

    public void update() {
        model.update();
    }

    public void load() {
        model.load();
    }

    public void moveDown() {
        vMoveDirection.set(0, -1);
        model.move(vMoveDirection);
    }

    public void moveLeft() {
        vMoveDirection.set(-1, 0);
        model.move(vMoveDirection);
    }


    public void moveUp() {
        vMoveDirection.set(0, 1);
        model.move(vMoveDirection);
    }

    public void moveRight() {
        vMoveDirection.set(1, 0);
        model.move(vMoveDirection);
    }

    public void interact() {
        if (model.isInteract()) return;
        if (!ControllerItemsDay.getInstance().setItemInteract()) return;

        //ControllerDialogSelector.getInstance().showDialog(); //todo temp remove dialogs from game :(
        model.interact(3000);  //todo placeholder
    }

    public void stop() {
        vMoveDirection.set(0, 0);
        model.move(vMoveDirection);
    }

    float getMessagePositionX() {
        return model.getMessagePostitionX();
    }

    float getMessagePositionY() {
        return model.getMessagePostitionY();
    }

    public float getCameraInventoryTranslateX() {
        return model.getCameraInventoryTranslateX();
    }

    int getPositionXCenter() {
        return model.getPositionCenterX();
    }

    public boolean isPlayerDirectionRight() {
        return model.isRightDirection();
    }

    public float getPlayerPositionX() {
        return model.getPositionX();
    }

    public float getPlayerPositionY() {
        return model.getPositionY();
    }

    public PlayerModel.AnimationState getPlayerAnimationState() {
        return model.getAnimationState();
    }
}
