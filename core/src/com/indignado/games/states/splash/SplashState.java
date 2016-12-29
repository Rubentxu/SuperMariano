package com.indignado.games.states.splash;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.ilargia.games.egdx.interfaces.GameState;
import com.ilargia.games.egdx.managers.EGAssetsManager;
import com.indignado.games.SMEngine;
import com.indignado.games.SMGame;
import com.indignado.games.SuperMariano;
import com.indignado.games.states.splash.gen.SplashContext;
import com.indignado.games.states.splash.systems.DelaySystem;
import com.indignado.games.states.splash.systems.RendererSplashSystem;

public class SplashState implements GameState<SMEngine> {
    public static final String SPLASH = "imagenes/fondos/splash.jpg";
    private EGAssetsManager assetsManager;
    private SplashContext context;


    @Override
    public void loadResources(SMEngine engine) {
        assetsManager = engine.getManager(EGAssetsManager.class);
        assetsManager.loadAsset(SPLASH, Texture.class);
        assetsManager.finishLoading();

    }

    @Override
    public void init(SMEngine engine) {
        context = new SplashContext();
        engine._systems
                .addSystem(context.splash, new DelaySystem())
                .addSystem(context.splash, new RendererSplashSystem(engine.cam, engine.batch));

        Texture texture = assetsManager.getTexture(SPLASH);

        context.splash.createEntity()
                .addTextureView("Splash", new TextureRegion(texture, 0, 0, texture.getWidth(), texture.getHeight()), new Vector2(),
                        0, SuperMariano.SCREEN_HEIGHT, SuperMariano.SCREEN_WIDTH)
                .addDelay(3);
    }

    @Override
    public void onResume(SMEngine engine) {

    }

    @Override
    public void onPause(SMEngine engine) {

    }

    @Override
    public void unloadResources(SMEngine engine) {
        assetsManager.unloadAsset(SPLASH);
        context.splash.destroyAllEntities();
        engine._systems.clearSystems();
    }
}
