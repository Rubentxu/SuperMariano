package com.indignado.games.smariano.model.services;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.utils.Disposable;
import com.indignado.games.smariano.config.constantes.Env;
import com.indignado.games.smariano.model.services.interfaces.IResourcesService;
import com.indignado.games.smariano.utils.debug.GameLogger;

public class ResourceService implements Disposable, IResourcesService {

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
    private AssetManager assetManager;


    public ResourceService() {
        this.assetManager = new AssetManager();
        loadSplash();
        loadAssetsGame();
        loadSounds();
        this.finishLoading();
    }


    public void finishLoading() {
        GameLogger.info("ResourcesManager", "Finish Loading Assets: ");
        assetManager.finishLoading();

    }


    @Override
    public void dispose() {
        assetManager.dispose();
        assetManager = null;
    }


    public AssetManager getAssetManager() {
        return assetManager;
    }


    public synchronized <T> void load(String fileName, Class<T> type) {
        Gdx.app.log(Env.LOG, "Load Asset: " + fileName + " Type: " + type.getName());
        assetManager.load(fileName, type, null);
    }


    private void loadAssetsGame() {
        Gdx.app.log(Env.LOG, "Load ResourcesManager Game");
        this.load(DEFAULT_FONT, BitmapFont.class);
        this.load(DEBUG_FONT, BitmapFont.class);
        this.load(HEADER_FONT, BitmapFont.class);
        this.load(DEBUG_BACKGROUND, Texture.class);
        this.load(MENU_BACKGROUND, Texture.class);
        this.load(STATS_BACKGROUND, Texture.class);
        this.load(SPRITE_ATLAS, TextureAtlas.class);
        this.load(VARIOS_ATLAS, TextureAtlas.class);
        this.load(OBJECTS_ATLAS, TextureAtlas.class);
        this.load(GUI_ATLAS, TextureAtlas.class);
        this.load(GUI_PACK_ATLAS, TextureAtlas.class);
        this.load(UISKIN_ATLAS, TextureAtlas.class);
        this.load(PARTICLE_EFFECT, ParticleEffect.class);
        this.load(PARTICLE_EFFECT_CONTACT, ParticleEffect.class);
        assetManager.setLoader(TiledMap.class, new TmxMapLoader(new InternalFileHandleResolver()));
        this.load(MUSIC_MENU, Music.class);

    }

    public void loadSplash() {
        this.load(SPLASH, Texture.class);
    }

    @Override
    public void loadResourcesSplashScreen() {

    }

    @Override
    public void loadResourcesMenuScreen() {

    }


    public Music getMusic(String name) {
        if (assetManager.isLoaded(name, Music.class)) return assetManager.get(name, Music.class);
        this.load(name, Music.class);
        this.finishLoading();
        return assetManager.get(name, Music.class);
    }


    public void loadSounds() {
        this.load(HIT_SOUND, Sound.class);
        this.load(JUMP_SOUND, Sound.class);
        this.load(PICKUP_COIN_SOUND, Sound.class);
        this.load(POWERUP_SOUND, Sound.class);
    }

    public Sound getSound(String sound) {
        return assetManager.get(sound, Sound.class);
    }


}
