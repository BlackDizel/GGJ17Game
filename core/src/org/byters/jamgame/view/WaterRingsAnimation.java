package org.byters.jamgame.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.sun.istack.internal.Nullable;
import org.byters.engine.controller.ControllerMain;

public class WaterRingsAnimation {

    private static final String FILE_TEXTURE_WATER_RINGS = "graphics/waterRings.png";
    private static final int FRAMES_NUM = 3;
    private static final float FRAME_DURATION_SECONDS = 0.09f;
    private int frameWidth;
    private int frameHeight;
    private Animation<TextureRegion> animation;
    private TextureRegion currentFrame;

    public void load() {

        Texture texture = new Texture(Gdx.files.internal(FILE_TEXTURE_WATER_RINGS));
        frameWidth = texture.getWidth() / FRAMES_NUM;
        frameHeight = texture.getHeight();
        TextureRegion[][] frames = TextureRegion.split(texture, frameWidth, frameHeight);
        TextureRegion[] framesLine = new TextureRegion[FRAMES_NUM];
        for (int i = 0; i < FRAMES_NUM; ++i)
            framesLine[i] = frames[0][i];

        animation = new Animation<TextureRegion>(FRAME_DURATION_SECONDS, framesLine);
        animation.setPlayMode(Animation.PlayMode.LOOP);
    }

    void update() {
        float stateTime = ControllerMain.getInstance().getGameTime() / 1000f;
        int keyFrameIndex = animation.getKeyFrameIndex(stateTime);
        currentFrame = animation.getKeyFrames()[keyFrameIndex];
    }

    @Nullable
    TextureRegion getCurrentFrame() {
        return currentFrame;
    }

    int getFrameWidth() {
        return frameWidth;
    }

    public int getFrameHeight() {
        return frameHeight;
    }
}
