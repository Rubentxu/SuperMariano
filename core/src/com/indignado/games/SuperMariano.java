package com.indignado.games;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.ilargia.games.egdx.EGEventBus;
import com.ilargia.games.egdx.api.managers.ProfileManager;
import com.ilargia.games.egdx.base.managers.BaseAssetsManager;
import com.ilargia.games.egdx.base.managers.BasePreferencesManager;
import com.ilargia.games.egdx.base.managers.BaseProfileManager;
import com.indignado.games.states.options.Profile;
import com.indignado.games.states.splash.SplashState;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.engio.mbassy.bus.MBassador;
import net.engio.mbassy.bus.error.IPublicationErrorHandler;
import net.engio.mbassy.bus.error.PublicationError;

public class SuperMariano implements ApplicationListener {
    public static final int SCREEN_WIDTH = 800;
    public static final int SCREEN_HEIGHT = 480;
    private static SMGame game;

    @Override
    public void create() {
        AssetManager assetsManager = new AssetManager();
        BasePreferencesManager preferencesManager = new BasePreferencesManager();
        SMEngine engine = new SMEngine();
        ProfileManager profileManager = new BaseProfileManager(new Profile(new ObjectArrayList<>()), preferencesManager);
        engine.addManager(preferencesManager);
        engine.addManager(profileManager);
        engine.addManager(new BaseAssetsManager(assetsManager, preferencesManager));
        game = new SMGame(engine, new EGEventBus(new MBassador(new IPublicationErrorHandler() {
            @Override
            public void handleError(PublicationError error) {
                Gdx.app.error("BusException", error.toString());
            }
        })));
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
