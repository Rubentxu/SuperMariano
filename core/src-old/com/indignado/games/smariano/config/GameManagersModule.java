package com.indignado.games.smariano.config;

import com.badlogic.gdx.ai.fsm.StateMachine;
import com.indignado.games.smariano.BaseGame;
import com.indignado.games.smariano.model.fms.GameState;
import com.indignado.games.smariano.model.managers.*;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

/**
 * Created by Rubentxu on 25/06/14.
 */
@Module(library = true, complete = false)
public class GameManagersModule {


    @Provides
    @Singleton
    CheckPointManager provideCheckPointManager(StateMachine<BaseGame,GameState> stateMachine) {
        return new CheckPointManager(stateMachine);
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
