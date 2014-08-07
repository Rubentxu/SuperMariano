package com.indignado.games.smariano.config;

import com.indignado.games.smariano.model.entities.World;
import com.indignado.games.smariano.model.managers.*;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

/**
 * Created by Rubentxu on 25/06/14.
 */
@Module(injects = {World.class},library = true,complete = false)
public class GameManagersModule {

    @Provides
    @Singleton
    World provideWorld() {
        return new World();
    }


    @Provides
    @Singleton
    CheckPointManager provideCheckPointManager() {
        return new CheckPointManager();
    }


    @Provides
    @Singleton
    EnemyManager provideEnemyManager() {
        return new EnemyManager();
    }


    @Provides
    @Singleton
    HeroManager provideHeroManager() {
        return new HeroManager();
    }


    @Provides
    @Singleton
    ItemsManager provideItemsManager() {
        return new ItemsManager();
    }


    @Provides
    @Singleton
    MillManager provideMillManager() {
        return new MillManager();
    }


    @Provides
    @Singleton
    PlatformManager providePlatformManager() {
        return new PlatformManager();
    }


    @Provides
    @Singleton
    WaterManager provideWaterManager() {
        return new WaterManager();
    }


}
