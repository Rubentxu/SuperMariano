package com.indignado.games.smariano.config;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.indignado.games.smariano.config.constantes.Env;
import com.indignado.games.smariano.model.services.Styles;
import dagger.Module;
import dagger.Provides;

import javax.inject.Named;
import javax.inject.Singleton;

/**
 * Created by Rubentxu on 25/06/14.
 */
@Module(library = true)
public class RenderModule {

    @Provides
    @Singleton
    SpriteBatch provideSpriteBatch() {
        return new SpriteBatch();
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




}
