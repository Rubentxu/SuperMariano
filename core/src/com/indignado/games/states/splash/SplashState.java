package com.indignado.games.states.splash;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.ilargia.games.egdx.base.interfaces.GameState;
import com.ilargia.games.egdx.managers.EGAssetsManager;
import com.ilargia.games.entitas.Systems;
import com.indignado.games.SMEngine;
import com.indignado.games.SuperMariano;
import com.indignado.games.states.splash.gen.SplashContext;
import com.indignado.games.states.splash.systems.DelaySystem;
import com.indignado.games.states.splash.systems.RendererSplashSystem;

public class SplashState implements GameState<SMEngine> {
    public static final String SPLASH = "imagenes/fondos/splash.jpg";
    private EGAssetsManager assetsManager;
    private SMEngine engine;
    private Systems systems;
    private SplashContext context;

    public SplashState(Systems systems) {
        this.systems = systems;
        context = new SplashContext();
    }

    @Override
    public void setEngine(SMEngine engine) {
        this.engine = engine;
    }

    @Override
    public void loadResources() {
        assetsManager = engine.getManager(EGAssetsManager.class);
        assetsManager.loadAsset(SPLASH, Texture.class);
        assetsManager.finishLoading();

    }

    @Override
    public void init() {
        context = new SplashContext();
        systems.addSystem(context.splash, new DelaySystem())
                .addSystem(context.splash, new RendererSplashSystem(engine.cam, engine.batch));

        Texture texture = assetsManager.getTexture(SPLASH);

        context.splash.createEntity()
                .addTextureView("Splash", new TextureRegion(texture, 0, 0, texture.getWidth(), texture.getHeight()), new Vector2(),
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
        assetsManager.unloadAsset(SPLASH);
        context.splash.destroyAllEntities();
        systems.clearSystems();
    }
}
