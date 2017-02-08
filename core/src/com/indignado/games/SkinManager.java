package com.indignado.games;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.ilargia.games.egdx.managers.EGAssetsManager;
import com.ilargia.games.egdx.managers.ISkin;

public class SkinManager implements ISkin {

    public static final String GUI_ATLAS = "gui/gui.pack";
    public static final String GUI_PACK_ATLAS = "gui/gui-pack.pack";
    public static final String UISKIN_ATLAS = "gui/uiskin.pack";
    public static final String SPRITE_ATLAS = "imagenes/animaciones/sprites.pack";
    public static final String VARIOS_ATLAS = "imagenes/varios/varios.pack";
    public static final String OBJECTS_ATLAS = "imagenes/varios/objects.pack";
    public static final String SPLASH = "imagenes/fondos/splash.jpg";
    public static final String DEFAULT_FONT = "fonts/Brushy_Cre-64.fnt";
    public static final String DEBUG_FONT = "fonts/SuperMarioBros-96.fnt";
    public static final String HEADER_FONT = "fonts/MarioLuigi2-96.fnt";
    public static final String DEBUG_BACKGROUND = "imagenes/fondos/debug.jpg";
    public static final String TREE_BACKGROUND = "imagenes/fondos/arboles.png";
    public static final String LEVEL1_BACKGROUND = "imagenes/fondos/fondo.jpg";
    public static final String STATS_BACKGROUND = "imagenes/fondos/fondoStats.png";
    public static final String CLOUD_BACKGROUND = "imagenes/fondos/nubes.png";
    public static final String MENU_BACKGROUND = "imagenes/fondos/menu-backgroud.jpg";
    public static final String PARTICLE_EFFECT = "particles/dust.pfx";
    public static final String PARTICLE_EFFECT_CONTACT = "particles/contact.pfx";
    public static final String MUSIC_MENU = "sounds/music/MonkeysSpinningMonkeys.mp3";
    // Sonidos
    public static final String HIT_SOUND = "sounds/sound/Hit_Hurt.ogg";
    public static final String JUMP_SOUND = "sounds/sound/Jump.ogg";
    public static final String PICKUP_COIN_SOUND = "sounds/sound/Pickup_Coin.ogg";
    public static final String POWERUP_SOUND = "sounds/sound/Powerup.ogg";
    public BitmapFont font;
    public BitmapFont font2;
    public Skin skin;

    public SkinManager(EGAssetsManager assetsManager) {
        loadAssets(assetsManager);
        this.skin = createSkin(assetsManager);
    }

    @Override
    public void dispose() {
        if (skin != null) {
            skin.dispose();
            skin = null;
        }
    }

    @Override
    public void loadAssets(EGAssetsManager assetsManager) {
        assetsManager.loadFont(DEFAULT_FONT);
        assetsManager.loadFont(HEADER_FONT);
        assetsManager.loadTextureAtlas(GUI_ATLAS);
        assetsManager.loadTextureAtlas(GUI_PACK_ATLAS);
        assetsManager.loadTextureAtlas(UISKIN_ATLAS);
        assetsManager.loadTexture(STATS_BACKGROUND);
        assetsManager.loadTexture(MENU_BACKGROUND);
        assetsManager.finishLoading();
    }

    @Override
    public Skin createSkin(EGAssetsManager assetsManager) {

        skin = new Skin();
        font = assetsManager.getFont(DEFAULT_FONT);
        font.getData().setScale(ScaleUtil.getSizeRatio());
        font.setUseIntegerPositions(false);
        font2 = assetsManager.getFont(HEADER_FONT);
        font2.getData().setScale(ScaleUtil.getSizeRatio());
        font2.setUseIntegerPositions(false);

        skin.add("default", font);
        skin.add("header", font2);

        skin.add("lt-blue", new Color(.62f, .76f, .99f, 1f));
        skin.add("lt-green", new Color(.39f, .9f, .6f, 1f));
        skin.add("dark-blue", new Color(.79f, .95f, 91f, 1f));

        skin.addRegions(assetsManager.getTextureAtlas(GUI_ATLAS));
        skin.addRegions(assetsManager.getTextureAtlas(GUI_PACK_ATLAS));


        TextureRegionDrawable touchpad_background = new TextureRegionDrawable(((TextureAtlas) assetsManager.getTextureAtlas(GUI_ATLAS)).findRegion("touchpad_background"));
        TextureRegionDrawable touchpad_thumb = new TextureRegionDrawable(((TextureAtlas) assetsManager.getTextureAtlas(GUI_ATLAS)).findRegion("touchpad_thumb"));


        TextureRegionDrawable checkox_true = new TextureRegionDrawable(((TextureAtlas) assetsManager.getTextureAtlas(UISKIN_ATLAS)).findRegion("check-on"));

        TextureRegionDrawable checkox_false = new TextureRegionDrawable(((TextureAtlas) assetsManager.getTextureAtlas(UISKIN_ATLAS)).findRegion("check-off"));

        TextureRegionDrawable slider_knob = new TextureRegionDrawable(((TextureAtlas) assetsManager.getTextureAtlas(UISKIN_ATLAS)).findRegion("default-slider-knob"));
        TextureRegionDrawable slider = new TextureRegionDrawable(((TextureAtlas) assetsManager.getTextureAtlas(UISKIN_ATLAS)).findRegion("default-slider"));

        CheckBox.CheckBoxStyle checkBoxStyle = new CheckBox.CheckBoxStyle(checkox_false, checkox_true, font, Color.WHITE);


        SpriteDrawable stats = new SpriteDrawable(new Sprite((Texture) assetsManager.getTexture(STATS_BACKGROUND)));


        Slider.SliderStyle sliderStyle = new Slider.SliderStyle(slider, slider_knob);
        skin.add("default", new Window.WindowStyle(font2, Color.ORANGE, skin.getDrawable("debug")));
        skin.add("stats", stats);


        Label.LabelStyle lbs = new Label.LabelStyle();
        lbs.font = font;
        lbs.fontColor = Color.WHITE;
        skin.add("default", lbs);

        Label.LabelStyle lbsHeader = new Label.LabelStyle();
        lbsHeader.font = font2;
        lbsHeader.fontColor = Color.WHITE;
        skin.add("header", lbsHeader);

        TextButton.TextButtonStyle tbs = new TextButton.TextButtonStyle(skin.getDrawable("btnMenu"), skin.getDrawable("btnMenuPress"), skin.getDrawable("btnMenu"), font);
        tbs.fontColor = skin.getColor("dark-blue");
        tbs.pressedOffsetX = Math.round(1f * Gdx.graphics.getDensity());
        tbs.pressedOffsetY = tbs.pressedOffsetX * -1f;

        ImageButton.ImageButtonStyle ImageButtonLeft = new ImageButton.ImageButtonStyle(skin.getDrawable("buttonLeft"), skin.getDrawable("buttonLeftPress"),
                skin.getDrawable("buttonLeft"), null, null, null);
        ImageButton.ImageButtonStyle ImageButtonRight = new ImageButton.ImageButtonStyle(skin.getDrawable("buttonRight"), skin.getDrawable("buttonRightPress"),
                skin.getDrawable("buttonRight"), null, null, null);
        ImageButton.ImageButtonStyle ImageButtonUp = new ImageButton.ImageButtonStyle(skin.getDrawable("buttonUp"), skin.getDrawable("buttonUpPress"),
                skin.getDrawable("buttonUp"), null, null, null);


        Touchpad.TouchpadStyle touchpadStyle = new Touchpad.TouchpadStyle();
        touchpadStyle.background = touchpad_background;
        touchpadStyle.knob = touchpad_thumb;


        skin.add("default", tbs);
        skin.add("buttonLeft", ImageButtonLeft);
        skin.add("buttonRight", ImageButtonRight);
        skin.add("buttonUp", ImageButtonUp);
        skin.add("default", touchpadStyle);
        skin.add("default", checkBoxStyle);
        skin.add("default-horizontal", sliderStyle);

        return skin;
    }

    public static class ScaleUtil {

        public static final float WORLD_HEIGHT = 16.875f; // 1080 / 64 =16.875 px
        public static final float WORLD_WIDTH = 30f;     //  1920 / 64 = 30f px
        public static final float VIRTUAL_HEIGHT = 1080f; // 16.875 x 64 =1080 px
        public static final float VIRTUAL_WIDTH = 1920f;     //  30 x 64 = 1920 px

        public static float getRatioX() {
            return Gdx.graphics.getWidth() / VIRTUAL_WIDTH;
        }

        public static float getRatioY() {
            return Gdx.graphics.getHeight() / VIRTUAL_HEIGHT;
        }

        public static float getSizeRatio() {
            if (getRatioX() < getRatioY())
                return getRatioX();
            else
                return getRatioY();
        }

        public float getWorldRatioX() {
            return Gdx.graphics.getWidth() / WORLD_WIDTH;
        }

        public float getWorldRatioY() {
            return Gdx.graphics.getHeight() / WORLD_HEIGHT;
        }

        public float getWorldSizeRatio() {
            if (getWorldRatioX() < getWorldRatioY())
                return getWorldRatioX();
            else
                return getWorldRatioY();
        }
    }
}