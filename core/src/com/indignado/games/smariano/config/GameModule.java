package com.indignado.games.smariano.config;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.indignado.games.smariano.BaseGame;
import com.indignado.games.smariano.SMariano;
import com.indignado.games.smariano.controller.WorldController;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

/**
 * Created by Rubentxu on 25/06/14.
 */
@Module(injects = {SMariano.class, WorldController.class},includes = {GameManagersModule.class,GameServicesModule.class,
RenderModule.class,ScreensModule.class},library = true)
public class GameModule {
    public BaseGame game;

    public GameModule(BaseGame game) {
        this.game = game;
    }

    @Provides
    @Singleton
    BaseGame provideBaseGame() {
        return game;
    }


    @Provides
    @Singleton
    WorldController provideWorldController() {
        return new WorldController();
    }



    @Provides
    @Singleton
    World providePhysics() {
        return new com.badlogic.gdx.physics.box2d.World(new Vector2(0, -9.81f), true);
    }




}
