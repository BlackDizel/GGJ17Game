package org.byters.jamgame.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import org.byters.engine.controller.ControllerMain;
import org.byters.jamgame.controller.ControllerPlayer;
import org.byters.jamgame.model.PlayerModel;

public class PlayerAnimation {
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
    private Animation<TextureRegion> aCurrent;

    public void load() {
        aMove = initAnimation(SPRITE_FILE_MOVE, FRAMES_NUM_MOVE);
        aInteract = initAnimation(SPRITE_FILE_INTERACT, FRAMES_NUM_INTERACT);
        aStand = initAnimation(SPRITE_FILE_STAND, FRAMES_NUM_STAND);
    }

    private Animation<TextureRegion> initAnimation(String file, int framesNum) {

        Texture texture = new Texture(Gdx.files.internal(file));
        TextureRegion[][] frames = TextureRegion.split(texture, texture.getWidth() / framesNum, texture.getHeight());

        TextureRegion[] framesMove = new TextureRegion[framesNum];
        for (int i = 0; i < framesNum; ++i)
            framesMove[i] = frames[0][i];

        return new Animation<TextureRegion>(FRAME_DURATION_SECONDS, framesMove);
    }

    void update() {
        aCurrent = ControllerPlayer.getInstance().getPlayerAnimationState() == PlayerModel.AnimationState.STAND
                ? aStand
                : ControllerPlayer.getInstance().getPlayerAnimationState() == PlayerModel.AnimationState.MOVE
                ? aMove
                : ControllerPlayer.getInstance().getPlayerAnimationState() == PlayerModel.AnimationState.INTERACT
                ? aInteract
                : null;
    }

    public void draw(SpriteBatch batch) {
        if (aCurrent == null) return;
        TextureRegion currentFrame = aCurrent.getKeyFrame(
                ControllerMain.getInstance().getGameTime() / 1000f % aCurrent.getAnimationDuration(), true);

        if (!ControllerPlayer.getInstance().isPlayerDirectionRight() && !currentFrame.isFlipX())
            currentFrame.flip(true, false);
        else if (ControllerPlayer.getInstance().isPlayerDirectionRight() && currentFrame.isFlipX())
            currentFrame.flip(true, false);

        batch.draw(currentFrame
                , ControllerPlayer.getInstance().getPlayerPositionX()
                , ControllerPlayer.getInstance().getPlayerPositionY());
    }
}
