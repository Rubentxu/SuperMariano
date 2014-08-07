package com.indignado.games.smariano.config;

import com.badlogic.gdx.ai.fsm.DefaultStateMachine;
import com.badlogic.gdx.ai.fsm.StateMachine;
import com.indignado.games.smariano.BaseGame;
import com.indignado.games.smariano.model.fms.GameState;
import com.indignado.games.smariano.view.screens.MenuScreen;
import com.indignado.games.smariano.view.screens.SplashScreen;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

/**
 * Created by Rubentxu on 25/06/14.
 */
@Module(injects = {BaseGame.class, SplashScreen.class, MenuScreen.class}, complete = false)
public class ScreensModule {


    @Provides
    @Singleton
    StateMachine<BaseGame> provideGameStateMachine(BaseGame game) {
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
