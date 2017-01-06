package com.indignado.games;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.ilargia.games.egdx.EGEventBus;
import com.ilargia.games.egdx.managers.EGAssetsManager;
import com.ilargia.games.egdx.managers.EGPreferencesManager;
import com.ilargia.games.entitas.Systems;
import com.indignado.games.states.splash.SplashState;
import net.engio.mbassy.bus.MBassador;

public class SuperMariano implements ApplicationListener {
    public static final int SCREEN_WIDTH = 800;
    public static final int SCREEN_HEIGHT = 480;
    private static SMGame game;

    @Override
    public void create() {
        AssetManager assetsManager = new AssetManager();
        EGPreferencesManager preferencesManager = new EGPreferencesManager();
        SMEngine engine = new SMEngine();
        engine.addManager(new EGAssetsManager(assetsManager, preferencesManager));
        game = new SMGame(engine, new Systems(), new EGEventBus(new MBassador()));
        game.init();
        game.pushState(new SplashState(engine));

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void render() {
        game.update(Gdx.graphics.getDeltaTime());
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {


    }
}
