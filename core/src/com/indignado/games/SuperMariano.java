package com.indignado.games;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.assets.AssetManager;
import com.ilargia.games.egdx.EGEventBus;
import com.ilargia.games.egdx.interfaces.EventBus;
import com.ilargia.games.egdx.managers.EGAssetsManager;
import com.ilargia.games.egdx.managers.EGPreferencesManager;
import com.ilargia.games.entitas.Systems;
import com.indignado.games.states.splash.SplashState;
import net.engio.mbassy.bus.MBassador;

public class SuperMariano implements ApplicationListener {
        private static SMGame game;
        public static final int SCREEN_WIDTH = 800;
        public static final int SCREEN_HEIGHT = 480;
        public static final int PLAYER_WIDTH = 20;
        public static final int PLAYER_HEIGHT = 120;
        public static float PLAYER_SPEED = 300;

    @Override
    public void create() {
        AssetManager assetsManager = new AssetManager();
        EGPreferencesManager preferencesManager =  new EGPreferencesManager();
        SMEngine engine = new SMEngine(new Systems(), new EGAssetsManager(assetsManager, preferencesManager));
        game = new SMGame(engine, new EGEventBus(new MBassador()));
        game.init(null);
        game.pushState(new SplashState());

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void render() {
        game.runGame();
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
