package com.indignado.games.smariano.config;

import com.indignado.games.smariano.BaseGame;
import com.indignado.games.smariano.SuperMariano;
import com.indignado.games.smariano.controller.WorldController;
import com.indignado.games.smariano.model.entities.World;
import com.indignado.games.smariano.model.managers.*;
import com.indignado.games.smariano.view.WorldRenderer;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;


/**
 * Created by Rubentxu on 25/06/14.
 */
@Module(injects = {SuperMariano.class, WorldController.class, WorldRenderer.class}
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
    World provideWorld(BaseGame game, HeroManager heroManager, PlatformManager platformManager, WaterManager waterManager,
                       EnemyManager enemyManager, ItemsManager itemsManager, CheckPointManager checkPointManager) {
        return new World(game, heroManager, platformManager, waterManager, enemyManager, itemsManager, checkPointManager);

    }


}
