package com.indignado.games.states.splash;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.ilargia.games.egdx.base.BaseGameState;
import com.ilargia.games.egdx.managers.EGAssetsManager;
import com.indignado.games.SMEngine;
import com.indignado.games.SuperMariano;
import com.indignado.games.states.splash.gen.SplashContext;
import com.indignado.games.states.splash.systems.DelaySystem;
import com.indignado.games.states.splash.systems.RendererSplashSystem;

public class SplashState extends BaseGameState {
    public static final String SPLASH = "imagenes/fondos/splash.jpg";
    private EGAssetsManager assetsManager;
    private SMEngine engine;
    private SplashContext context;

    public SplashState(SMEngine engine) {
        this.engine = engine;
        context = new SplashContext();
    }

    @Override
    public void loadResources() {
        assetsManager = engine.getManager(EGAssetsManager.class);
        assetsManager.loadTexture(SPLASH);
        assetsManager.finishLoading();

    }

    @Override
    public void initialize() {
        context = new SplashContext();
        systems.addSystem(context.splash, new DelaySystem())
                .addSystem(context.splash, new RendererSplashSystem(engine.cam, engine.batch));

        Texture texture = assetsManager.getTexture(SPLASH);

        context.splash.setTextureView("Splash", new TextureRegion(texture, 0, 0, texture.getWidth(), texture.getHeight()), new Vector2(),
                0, SuperMariano.SCREEN_HEIGHT, SuperMariano.SCREEN_WIDTH)
                .addDelay(3);
    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void unloadResources() {
        assetsManager.unloadTexture(SPLASH);
        context.splash.destroyAllEntities();
        systems.clearSystems();
    }
}
