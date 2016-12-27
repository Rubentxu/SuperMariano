package com.indignado.games.smariano.config;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.indignado.games.smariano.config.constantes.Env;
import com.indignado.games.smariano.model.services.Styles;
import com.indignado.games.smariano.utils.builders.GuiBuilder;
import com.indignado.games.smariano.utils.parallax.ParallaxBackground;
import com.indignado.games.smariano.view.ModelsAndViews;
import dagger.Module;
import dagger.Provides;

import javax.inject.Named;
import javax.inject.Singleton;

/**
 * Created by Rubentxu on 25/06/14.
 */
@Module(library = true, complete = false, injects = {Styles.class, ModelsAndViews.class, GuiBuilder.class, ParallaxBackground.class})
public class RenderModule {


    @Provides
    @Singleton
    SpriteBatch provideSpriteBatch() {
        return new SpriteBatch();
    }


    @Provides
    @Singleton
    Stage provideStage(SpriteBatch batch) {
        return new Stage();
    }


    @Provides
    @Singleton
    Styles provideStyles() {
        return new Styles();
    }


    @Provides
    @Singleton
    @Named("camera")
    OrthographicCamera provideOrthographicCamera() {
        OrthographicCamera camera = new OrthographicCamera(Env.VIRTUAL_WIDTH, Env.VIRTUAL_HEIGHT);
        camera.position.set(0, camera.viewportHeight / 2, 0);
        camera.update();
        return camera;

    }


    @Provides
    @Singleton
    @Named("uiCamera")
    OrthographicCamera provideUiOrthographicCamera() {
        OrthographicCamera camera = new OrthographicCamera(Env.VIRTUAL_WIDTH, Env.VIRTUAL_HEIGHT);
        camera.position.set(0, camera.viewportHeight / 2, 0);
        camera.update();
        return camera;

    }


    @Provides
    @Singleton
    Box2DDebugRenderer provideBox2DDebugRenderer() {
        return new Box2DDebugRenderer();

    }


    @Provides
    @Singleton
    GuiBuilder provideGuiBuilder() {
        return new GuiBuilder();
    }


}
