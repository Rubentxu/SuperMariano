package com.indignado.games.smariano.config;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.indignado.games.smariano.BaseGame;
import com.indignado.games.smariano.SMariano;
import com.indignado.games.smariano.controller.WorldController;
import com.indignado.games.smariano.model.entities.World;
import com.indignado.games.smariano.model.managers.*;
import com.indignado.games.smariano.model.services.AudioService;
import com.indignado.games.smariano.model.services.interfaces.ILevelService;
import com.indignado.games.smariano.model.services.interfaces.IProfileService;
import com.indignado.games.smariano.model.services.interfaces.IResourcesService;
import com.indignado.games.smariano.view.ModelsAndViews;
import com.indignado.games.smariano.view.WorldRenderer;
import dagger.Module;
import dagger.Provides;

import javax.inject.Named;
import javax.inject.Singleton;

/**
 * Created by Rubentxu on 25/06/14.
 */
@Module(injects = {SMariano.class, WorldController.class, WorldRenderer.class, com.badlogic.gdx.physics.box2d.World.class}
        , includes = {GameManagersModule.class, GameServicesModule.class,
        RenderModule.class, ScreensModule.class}, library = true)
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
    World provideWorld(com.badlogic.gdx.physics.box2d.World physics, ILevelService levelService, IResourcesService resourceService) {
        return new World(physics, levelService, resourceService);

    }


    @Provides
    WorldController provideWorldController(World world, HeroManager heroManager, PlatformManager platformManager, WaterManager waterManager,
                                           EnemyManager enemyManager, ItemsManager itemsManager, CheckPointManager checkPointManager,
                                           IProfileService profileService, AudioService audioService) {
        return new WorldController(world, heroManager, platformManager, waterManager, enemyManager, itemsManager, checkPointManager,
                profileService, audioService);

    }


    @Provides
    WorldRenderer provideWorldRenderer(World world, @Named("camera") OrthographicCamera cam, OrthogonalTiledMapRenderer renderer,
                                       SpriteBatch spriteBatch, ModelsAndViews modelsAndViews) {
        return new WorldRenderer(world, cam, renderer, spriteBatch, modelsAndViews);

    }


    @Provides
    com.badlogic.gdx.physics.box2d.World providePhysics() {
        return new com.badlogic.gdx.physics.box2d.World(new Vector2(0, -9.81f), true);

    }


}
