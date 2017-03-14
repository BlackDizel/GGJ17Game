package org.byters.jamgame.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.sun.istack.internal.Nullable;
import org.byters.engine.controller.ControllerMain;
import org.byters.engine.model.IDrawableObject;
import org.byters.jamgame.controller.ControllerPlayer;
import org.byters.jamgame.model.DrawableObjectsEnum;
import org.byters.jamgame.model.PlayerModel;

public class PlayerAnimation {
    private static final float WATER_HEIGHT_FACTOR = 0.4f;
    private static final String SPRITE_FILE_MOVE = "graphics/walk.png";
    private static final String SPRITE_FILE_INTERACT = "graphics/action.png";
    private static final String SPRITE_FILE_STAND = "graphics/hero.png";
    private static final int FRAMES_NUM_MOVE = 4;
    private static final float FRAME_DURATION_SECONDS = 0.09f;
    private static final int FRAMES_NUM_INTERACT = 4;
    private static final int FRAMES_NUM_STAND = 1;
    private Animation<TextureRegion> aStand;
    private Animation<TextureRegion> aMove;
    private Animation<TextureRegion> aInteract;
    private TextureRegion currentFrame;
    private AnimationDrawableObject animationDrawableObject;
    private int frameWidth, frameHeight;
    private TextureRegion textureTinted;
    private int heightNotTinted;

    public AnimationDrawableObject getDrawableObject() {
        return animationDrawableObject;
    }

    public void load() {
        animationDrawableObject = new AnimationDrawableObject();
        aMove = initAnimation(SPRITE_FILE_MOVE, FRAMES_NUM_MOVE);
        aInteract = initAnimation(SPRITE_FILE_INTERACT, FRAMES_NUM_INTERACT);
        aStand = initAnimation(SPRITE_FILE_STAND, FRAMES_NUM_STAND);

        textureTinted = new TextureRegion();
    }

    private Animation<TextureRegion> initAnimation(String file, int framesNum) {

        Texture texture = new Texture(Gdx.files.internal(file));
        frameWidth = texture.getWidth() / framesNum; //TODO use in one texture for all animations
        frameHeight = texture.getHeight();
        TextureRegion[][] frames = TextureRegion.split(texture, frameWidth, frameHeight);
        TextureRegion[] framesMove = new TextureRegion[framesNum];
        for (int i = 0; i < framesNum; ++i)
            framesMove[i] = frames[0][i];

        Animation<TextureRegion> result = new Animation<TextureRegion>(FRAME_DURATION_SECONDS, framesMove);
        result.setPlayMode(Animation.PlayMode.LOOP);
        return result;
    }

    void update() {
        Animation<TextureRegion> aCurrent = ControllerPlayer.getInstance().getPlayerAnimationState() == PlayerModel.AnimationState.STAND
                ? aStand
                : ControllerPlayer.getInstance().getPlayerAnimationState() == PlayerModel.AnimationState.MOVE
                ? aMove
                : ControllerPlayer.getInstance().getPlayerAnimationState() == PlayerModel.AnimationState.INTERACT
                ? aInteract
                : null;

        if (aCurrent == null) return;

        float stateTime = ControllerMain.getInstance().getGameTime() / 1000f;
        int keyFrameIndex = aCurrent.getKeyFrameIndex(stateTime);
        currentFrame = aCurrent.getKeyFrames()[keyFrameIndex];

        updateTintHeight();
        currentFrame.setRegion(keyFrameIndex * frameWidth, 0, frameWidth, Math.min(heightNotTinted, frameHeight));

        checkFlip(currentFrame);

        if (heightNotTinted >= frameHeight) {
            textureTinted.setTexture(null);
        } else {
            textureTinted.setTexture(currentFrame.getTexture());
            textureTinted.setRegion(keyFrameIndex * frameWidth, heightNotTinted, frameWidth, frameHeight - heightNotTinted);
            checkFlip(textureTinted);
        }
    }

    private void updateTintHeight() {
        heightNotTinted = (int) (ControllerPlayer.getInstance().getPlayerPositionY() + frameHeight - Gdx.graphics.getHeight() * WATER_HEIGHT_FACTOR);
    }

    private void checkFlip(TextureRegion textureRegion) {
        if (!ControllerPlayer.getInstance().isPlayerDirectionRight() && !textureRegion.isFlipX()
                || ControllerPlayer.getInstance().isPlayerDirectionRight() && textureRegion.isFlipX())
            textureRegion.flip(true, false);
    }

    @Nullable
    TextureRegion getTexture() {
        return currentFrame;
    }

    @Nullable
    TextureRegion getTextureTinted() {
        if (textureTinted.getTexture() == null) return null;
        return textureTinted;
    }

    int getFrameWidth() {
        return frameWidth;
    }

    public int getHeightTinted() {
        return Math.max(0, frameHeight - heightNotTinted);
    }

    public class AnimationDrawableObject implements IDrawableObject {

        @Override
        public int getX() {
            return (int) ControllerPlayer.getInstance().getPlayerPositionX();
        }

        @Override
        public int getY() {
            return (int) ControllerPlayer.getInstance().getPlayerPositionY() + getHeightTinted();
        }

        @Override
        public int getOriginY() {
            return (int) ControllerPlayer.getInstance().getPlayerPositionY();
        }

        @Override
        public int getID() {
            return DrawableObjectsEnum.PLAYER_ID.ordinal();
        }
    }
}
