package com.indignado.games.states.splash;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.ilargia.games.egdx.api.base.BaseGameState;
import com.ilargia.games.egdx.managers.EGAssetsManager;
import com.indignado.games.SMEngine;
import com.indignado.games.SuperMariano;
import com.indignado.games.states.splash.gen.Entitas;
import com.indignado.games.states.splash.systems.DelaySystem;
import com.indignado.games.states.splash.systems.RendererSplashSystem;

public class SplashState extends BaseGameState {
    public static final String SPLASH = "imagenes/fondos/splash.jpg";
    private EGAssetsManager assetsManager;
    private SMEngine engine;
    private Entitas entitas;

    public SplashState(SMEngine engine) {
        this.engine = engine;
        entitas = new Entitas();
    }

    @Override
    public void loadResources() {
        assetsManager = engine.getManager(EGAssetsManager.class);
        assetsManager.loadTexture(SPLASH);
        assetsManager.finishLoading();

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void initialize() {
        systems.add(new DelaySystem(entitas.splash))
                .add(new RendererSplashSystem(entitas.splash, engine.cam, engine.batch));

        Texture texture = assetsManager.getTexture(SPLASH);

        entitas.splash.setTextureView("Splash", new TextureRegion(texture, 0, 0, texture.getWidth(), texture.getHeight()), new Vector2(),
                0, SuperMariano.SCREEN_HEIGHT, SuperMariano.SCREEN_WIDTH)
                .addDelay(3);
    }

    @Override
    public void unloadResources() {
        assetsManager.unloadTexture(SPLASH);
        entitas.splash.destroyAllEntities();
        systems.clearSystems();
    }


}
