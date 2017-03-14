package org.byters.jamgame.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import org.byters.jamgame.model.inventory.Inventory;

public class PlayerModel {
    public static final Color TINT_COLOR = Color.CYAN;
    private static final float COLLISION_RIGHT_FACTOR = 0.8f;
    private static final float COLLISION_LEFT_FACTOR = 0.2f;
    private static final float speed = 52f;
    private static final float PLAYER_WIDTH = 40;
    private static final float PLAYER_HEIGHT = 64;
    private static final float MAX_VERTICAL_DELTA = 80;
    private Rectangle newPosition;
    private float xDirection, yDirection;
    private Rectangle bounds;

    private boolean isInteract;
    private long timeStartInteractMillis;
    private long timeInteractionMillis;
    private AnimationState animationState;

    public PlayerModel() {
        newPosition = new Rectangle();
    }

    public AnimationState getAnimationState() {
        return animationState;
    }

    public void load() {
        animationState = AnimationState.STAND;

        bounds = new Rectangle(0, 0, PLAYER_WIDTH, PLAYER_HEIGHT);
        bounds.setPosition((Gdx.graphics.getWidth() - PLAYER_WIDTH) / 2
                , (Gdx.graphics.getHeight() - PLAYER_HEIGHT) / 2);
    }

    public void update() {
        if (bounds == null) return;
        float delta = Gdx.graphics.getDeltaTime();

        newPosition.set(bounds.getX() + xDirection * delta * speed, bounds.getY() + yDirection * delta * speed
                , bounds.getWidth(), bounds.getHeight());

        if (isCollision(newPosition))
            return;

        //xPosition==(Gdx.graphics.getWidth() - PLAYER_WIDTH) / 2 ? yPos=  Gdx.graphics.getHeight() - PLAYER_HEIGHT) / 2

/*      bounds.setPosition(bounds.getX() + xDirection * delta * speed
                , bounds.getY() + yDirection * delta * speed);*/

        float xPos = bounds.getX() + xDirection * delta * speed;
        float xOrigin = (Gdx.graphics.getWidth() - PLAYER_WIDTH) / 2;
        float yFactor = (float) Math.pow((xPos - xOrigin) / (Gdx.graphics.getWidth() / 2), 2);
        float yPos = (Gdx.graphics.getHeight() - PLAYER_HEIGHT) / 2 - MAX_VERTICAL_DELTA * yFactor;
        bounds.setPosition(xPos, yPos);
    }

    public boolean updateInteraction() {
        if (!isInteract || timeStartInteractMillis + timeInteractionMillis > System.currentTimeMillis()) return false;
        isInteract = false;
        animationState = AnimationState.STAND;
        return true;
    }

    public void move(Vector2 vMoveDirection) {
        if (!isInteract) {
            if (xDirection == 0 && yDirection == 0)
                animationState = AnimationState.STAND;
            else animationState = AnimationState.MOVE;
        }

        xDirection = isInteract ? 0 : vMoveDirection.x;
        yDirection = isInteract ? 0 : vMoveDirection.y;
    }

    private boolean isCollision(Rectangle bounds) {
        return Gdx.graphics.getWidth() * COLLISION_RIGHT_FACTOR < (bounds.getX() + bounds.getWidth())
                || Gdx.graphics.getHeight() < (bounds.getY() + bounds.getHeight())
                || bounds.getX() < Gdx.graphics.getWidth() * COLLISION_LEFT_FACTOR
                || bounds.getY() < 0;
    }

    public boolean isInteract() {
        return isInteract;
    }

    public void interact(long timeInteractionMillis) {
        isInteract = true;
        timeStartInteractMillis = System.currentTimeMillis();
        this.timeInteractionMillis = timeInteractionMillis;
        animationState = AnimationState.INTERACT;
    }

    public float getMessagePostitionX() {
        return bounds.getX() + bounds.getWidth() + 20;
    }

    public float getMessagePostitionY() {
        return bounds.getY() + bounds.getHeight();
    }

    public float getCameraInventoryTranslateX() {
        return bounds.getX() + bounds.getWidth() / 2 - Gdx.graphics.getWidth() / 2 + Inventory.INVENTORY_WIDTH / 2;
    }

    public int getPositionCenterX() {
        return (int) (bounds.getX() + bounds.getWidth() / 2);
    }

    public float getPositionX() {
        return bounds.getX();
    }

    public float getPositionY() {
        return bounds.getY();
    }

    public boolean isRightDirection() {
        return xDirection >= 0;
    }

    public enum AnimationState {
        STAND, MOVE, INTERACT
    }
}
