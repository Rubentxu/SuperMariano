package com.indignado.games.smariano.config;

import com.badlogic.gdx.ai.fsm.DefaultStateMachine;
import com.badlogic.gdx.ai.fsm.StateMachine;
import com.indignado.games.smariano.BaseGame;
import com.indignado.games.smariano.fms.GameState;
import com.indignado.games.smariano.pantallas.MenuScreen;
import com.indignado.games.smariano.pantallas.SplashScreen;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

/**
 * Created by Rubentxu on 25/06/14.
 */
@Module(injects = {BaseGame.class, SplashScreen.class, MenuScreen.class}, includes = {ResourcesModule.class, ManagersModule.class})
public class ScreensModule {
    BaseGame game;

    public ScreensModule(BaseGame game) {
        this.game = game;
    }

    @Provides
    BaseGame provideBaseGame() {
        return game;
    }


    @Provides
    @Singleton
    StateMachine<BaseGame> provideGameStateMachine() {
        return new DefaultStateMachine<BaseGame>(game, GameState.RUNNING);
    }

    @Provides
    @Singleton
    SplashScreen provideSplashScreen() {
        return new SplashScreen();
    }

    @Provides
    @Singleton
    MenuScreen provideMenuScreen() {
        return new MenuScreen();
    }


}
