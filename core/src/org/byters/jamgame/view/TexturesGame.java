package org.byters.jamgame.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import org.byters.jamgame.model.ItemDayMeta;

class TexturesGame {

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

    Texture tIsland, tWater, tSky, tCrab;

    private Texture tBox, tPlank1, tPlank2, tBottle, tTV, tStone, tAlarm, tCamera;

    void load() {
        tBox = new Texture(Gdx.files.internal(FILE_TEXTURE_ITEM_BOX));
        tPlank1 = new Texture(Gdx.files.internal(FILE_TEXTURE_ITEM_PLANK1));
        tPlank2 = new Texture(Gdx.files.internal(FILE_TEXTURE_ITEM_PLANK2));
        tBottle = new Texture(Gdx.files.internal(FILE_TEXTURE_ITEM_BOTTLE));
        tTV = new Texture(Gdx.files.internal(FILE_TEXTURE_ITEM_TV));
        tAlarm = new Texture(Gdx.files.internal(FILE_TEXTURE_ITEM_ALARM));
        tStone = new Texture(Gdx.files.internal(FILE_TEXTURE_ITEM_STONE));
        tCamera = new Texture(Gdx.files.internal(FILE_TEXTURE_ITEM_CAMERA));

        tIsland = new Texture(Gdx.files.internal(FILE_TEXTURE_ISLAND));
        tCrab = new Texture(Gdx.files.internal(FILE_TEXTURE_CRAB));


        tWater = new Texture(Gdx.files.internal(FILE_TEXTURE_WATER));
        tWater.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);

        tSky = new Texture(Gdx.files.internal(FILE_TEXTURE_SKY));
        tSky.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
    }

    void dispose() {
        //todo refactor use texture atlas
        tBox.dispose();
        tPlank1.dispose();
        tPlank2.dispose();
        tBottle.dispose();
        tTV.dispose();
        tAlarm.dispose();
        tStone.dispose();
        tCamera.dispose();

        tIsland.dispose();
        tCrab.dispose();
        tWater.dispose();
        tSky.dispose();
    }

    Texture getItemTexture(ItemDayMeta item) {
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
