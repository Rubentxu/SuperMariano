package com.indignado.games.states.splash;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.ilargia.games.egdx.interfaces.GameState;
import com.ilargia.games.egdx.managers.EGAssetsManager;
import com.indignado.games.SMEngine;
import com.indignado.games.SMGame;
import com.indignado.games.SuperMariano;
import com.indignado.games.states.splash.systems.DelaySystem;
import com.indignado.games.states.splash.systems.RendererSplashSystem;

public class SplashState implements GameState<SMEngine> {
    private String splash = "assets/textures/pong.jpg";
    private EGAssetsManager assetsManager;


    @Override
    public void loadResources(SMEngine engine) {
        assetsManager = engine.getManager(EGAssetsManager.class);
        assetsManager.loadAsset(splash, Texture.class);
        assetsManager.finishLoading();

    }

    @Override
    public void init(SMEngine engine) {
       // Context context = engine.context;
//        engine._systems
//                .addSystem(context.core, new DelaySystem())
//                .addSystem(context.core, new RendererSplashSystem(engine.sr, engine.cam, engine.batch, engine.font));

        Texture texture = assetsManager.getTexture(splash);

//        context.core.createEntity()
//                .addTextureView("Pong", new TextureRegion(texture, 0, 0, texture.getWidth(), texture.getHeight()), new Vector2(),
//                        0, SuperMariano.SCREEN_HEIGHT, SuperMariano.SCREEN_WIDTH)
//                .addDelay(3);
    }

    @Override
    public void onResume(SMEngine engine) {

    }

    @Override
    public void onPause(SMEngine engine) {

    }

    @Override
    public void unloadResources(SMEngine engine) {
        assetsManager.unloadAsset(splash);
//        engine.context.core.destroyAllEntities();
        engine._systems.clearSystems();
    }
}
