package com.indignado.games.smariano.config;

import com.badlogic.gdx.ai.fsm.DefaultStateMachine;
import com.badlogic.gdx.ai.fsm.StateMachine;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.indignado.games.smariano.BaseGame;
import com.indignado.games.smariano.controller.WorldController;
import com.indignado.games.smariano.model.entities.World;
import com.indignado.games.smariano.model.fms.GameState;
import com.indignado.games.smariano.model.services.PreferencesService;
import com.indignado.games.smariano.model.services.interfaces.IProfileService;
import com.indignado.games.smariano.model.services.interfaces.IResourcesService;
import com.indignado.games.smariano.utils.builders.GuiBuilder;
import com.indignado.games.smariano.utils.gui.mtx.ButtonLevel;
import com.indignado.games.smariano.view.WorldRenderer;
import com.indignado.games.smariano.view.screens.GameScreen;
import com.indignado.games.smariano.view.screens.MenuScreen;
import com.indignado.games.smariano.view.screens.SplashScreen;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

/**
 * Created by Rubentxu on 25/06/14.
 */
@Module(injects = {BaseGame.class, SplashScreen.class, MenuScreen.class,ButtonLevel.class}, complete = false)
public class ScreensModule {


    @Provides
    @Singleton
    StateMachine<BaseGame> provideGameStateMachine(BaseGame game) {
        return new DefaultStateMachine<BaseGame>(game, GameState.RUNNING);

    }


    @Provides
    Stage provideStage() {
        return new Stage();
    }


    @Provides
    GameScreen provideGameScreen(World world, WorldController worldController, WorldRenderer worldRenderer,IProfileService profileService,
                                  IResourcesService resourceService,PreferencesService preferencesService,GuiBuilder guiBuilder){
        return new GameScreen(world,worldController,worldRenderer,profileService,resourceService,preferencesService,guiBuilder);
    }


}
