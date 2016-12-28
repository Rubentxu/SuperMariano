package com.indignado.games.smariano.config;

import com.badlogic.gdx.ai.fsm.DefaultStateMachine;
import com.badlogic.gdx.ai.fsm.StateMachine;
import com.indignado.games.smariano.BaseGame;
import com.indignado.games.smariano.controller.WorldController;
import com.indignado.games.smariano.model.entities.World;
import com.indignado.games.smariano.model.fms.GameState;
import com.indignado.games.smariano.utils.builders.GuiBuilder;
import com.indignado.games.smariano.utils.gui.mtx.ButtonLevel;
import com.indignado.games.smariano.view.WorldRenderer;
import com.indignado.games.smariano.view.screens.*;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

/**
 * Created by Rubentxu on 25/06/14.
 */
@Module(injects = {BaseGame.class, ButtonLevel.class}, complete = false)
public class ScreensModule {


    @Provides
    @Singleton
    StateMachine<BaseGame,GameState> provideGameStateMachine(BaseGame game) {
        return new DefaultStateMachine<BaseGame,GameState>(game, GameState.RUNNING);

    }


    @Provides
    GameScreen provideGameScreen(BaseGame game, World world, GuiBuilder guiBuilder) {
        return new GameScreen(game, world, new WorldController(world), new WorldRenderer(game, world), guiBuilder);

    }


    @Provides
    SplashScreen provideSplashScreen(BaseGame game) {
        return new SplashScreen(game);
    }


    @Provides
    MenuScreen provideMenuScreen(BaseGame game) {
        return new MenuScreen(game);
    }


    @Provides
    OptionScreen provideOptionScreen(BaseGame game) {
        return new OptionScreen(game);
    }


    @Provides
    HighScoresScreen provideHighScoresScreen(BaseGame game) {
        return new HighScoresScreen(game);
    }


    @Provides
    ScoreScreen provideScoreScreen(BaseGame game) {
        return new ScoreScreen(game);
    }


    @Provides
    SelectLevelScreen provideSelectLevelScreen(BaseGame game) {
        return new SelectLevelScreen(game);
    }


    @Provides
    GameOverScreen provideGameOverScreen(BaseGame game) {
        return new GameOverScreen(game);
    }

}
