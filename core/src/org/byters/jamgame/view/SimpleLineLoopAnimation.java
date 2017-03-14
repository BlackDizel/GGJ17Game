package org.byters.jamgame.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import org.byters.engine.controller.ControllerMain;

public class SimpleLineLoopAnimation {

    private final String file;
    private final int framesNum;
    private final float frameDurationSeconds;
    private int frameWidth;
    private int frameHeight;
    private Animation<TextureRegion> animation;
    private TextureRegion currentFrame;

    SimpleLineLoopAnimation(String file, int framesNum, float frameDurationSeconds) {
        this.file = file;
        this.framesNum = framesNum;
        this.frameDurationSeconds = frameDurationSeconds;
    }

    public void load() {

        Texture texture = new Texture(Gdx.files.internal(file));
        frameWidth = texture.getWidth() / framesNum;
        frameHeight = texture.getHeight();
        TextureRegion[][] frames = TextureRegion.split(texture, frameWidth, frameHeight);
        TextureRegion[] framesLine = new TextureRegion[framesNum];
        for (int i = 0; i < framesNum; ++i)
            framesLine[i] = frames[0][i];

        animation = new Animation<TextureRegion>(frameDurationSeconds, framesLine);
        animation.setPlayMode(Animation.PlayMode.LOOP);

        update();
    }

    void update() {
        float stateTime = ControllerMain.getInstance().getGameTime() / 1000f;
        int keyFrameIndex = animation.getKeyFrameIndex(stateTime);
        currentFrame = animation.getKeyFrames()[keyFrameIndex];
    }

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
