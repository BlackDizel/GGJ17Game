package org.byters.jamgame.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import org.byters.jamgame.model.ItemDayMeta;

import java.util.ArrayList;

class TexturesGame {

    static final String ANIMATION_FILE_WATER_RINGS = "graphics/waterRings.png";
    static final String ANIMATION_FILE_FISH_WATER = "graphics/fishInWater.png";

    private static final String FILE_TEXTURE_ITEM_BOX = "graphics/items/box.png";
    private static final String FILE_TEXTURE_ITEM_PLANK1 = "graphics/items/h5.png";
    private static final String FILE_TEXTURE_ITEM_PLANK2 = "graphics/items/h6.png";
    private static final String FILE_TEXTURE_ITEM_BOTTLE = "graphics/items/bottle.png";
    private static final String FILE_TEXTURE_ITEM_TV = "graphics/items/tv.png";
    private static final String FILE_TEXTURE_ITEM_STONE = "graphics/items/h3.png";
    private static final String FILE_TEXTURE_ITEM_ALARM = "graphics/items/alarm.png";
    private static final String FILE_TEXTURE_ITEM_CAMERA = "graphics/items/photo.png";

    private static final String FILE_TEXTURE_ISLAND = "graphics/island2.png";
    private static final String FILE_TEXTURE_WATER = "graphics/tile.png";
    private static final String FILE_TEXTURE_SKY = "graphics/sky.png";
    private static final String FILE_TEXTURE_CRAB = "graphics/crab.png";

    Texture tIsland, tWater, tSky;

    private TextureRegion tBox, tPlank1, tPlank2, tBottle, tTV, tStone, tAlarm, tCamera, tCrab;
    private ArrayList<Texture> textures;

    void load() {
        tBox = convertToTextureRegion(new Texture(Gdx.files.internal(FILE_TEXTURE_ITEM_BOX)));
        tPlank1 = convertToTextureRegion(new Texture(Gdx.files.internal(FILE_TEXTURE_ITEM_PLANK1)));
        tPlank2 = convertToTextureRegion(new Texture(Gdx.files.internal(FILE_TEXTURE_ITEM_PLANK2)));
        tBottle = convertToTextureRegion(new Texture(Gdx.files.internal(FILE_TEXTURE_ITEM_BOTTLE)));
        tTV = convertToTextureRegion(new Texture(Gdx.files.internal(FILE_TEXTURE_ITEM_TV)));
        tAlarm = convertToTextureRegion(new Texture(Gdx.files.internal(FILE_TEXTURE_ITEM_ALARM)));
        tStone = convertToTextureRegion(new Texture(Gdx.files.internal(FILE_TEXTURE_ITEM_STONE)));
        tCamera = convertToTextureRegion(new Texture(Gdx.files.internal(FILE_TEXTURE_ITEM_CAMERA)));

        tCrab = convertToTextureRegion(new Texture(Gdx.files.internal(FILE_TEXTURE_CRAB)));

        tIsland = new Texture(Gdx.files.internal(FILE_TEXTURE_ISLAND));

        tWater = new Texture(Gdx.files.internal(FILE_TEXTURE_WATER));
        tWater.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);

        tSky = new Texture(Gdx.files.internal(FILE_TEXTURE_SKY));
        tSky.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
    }

    private TextureRegion convertToTextureRegion(Texture texture) {
        if (textures == null) textures = new ArrayList<Texture>();
        textures.add(texture);

        return new TextureRegion(texture, texture.getWidth(), texture.getHeight());
    }

    TextureRegion getCrab() {
        return tCrab;
    }

    void dispose() {
        if (textures == null) return;
        for (Texture texture : textures)
            texture.dispose();

        tIsland.dispose();
        tWater.dispose();
        tSky.dispose();
    }

    TextureRegion getItemTexture(ItemDayMeta item) {
        return item.getItemId() == 3 //todo refactor
                ? tPlank1
                : item.getItemId() == 2
                ? tBottle
                : item.getItemId() == 1
                ? tPlank2
                : item.getItemId() == 7
                ? tAlarm
                : item.getItemId() == 4
                ? tStone
                : item.getItemId() == 6
                ? tCamera
                : item.getItemId() == 5
                ? tTV
                : tBox;
    }
}
