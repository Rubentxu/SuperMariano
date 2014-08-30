package com.indignado.games.smariano;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ai.fsm.StateMachine;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.indignado.games.smariano.config.GameModule;
import com.indignado.games.smariano.model.services.PreferencesService;
import com.indignado.games.smariano.model.services.ProfileService;
import com.indignado.games.smariano.model.services.Styles;
import com.indignado.games.smariano.model.services.interfaces.IAudioService;
import com.indignado.games.smariano.model.services.interfaces.ILevelService;
import com.indignado.games.smariano.model.services.interfaces.IResourcesService;
import com.indignado.games.smariano.utils.debug.GameLogger;
import com.indignado.games.smariano.view.screens.*;
import dagger.Lazy;
import dagger.ObjectGraph;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Provider;

public class BaseGame implements ApplicationListener {
    public static ObjectGraph objectGraph;
    @Inject
    public IResourcesService resourcesService;
    @Inject
    public IAudioService audioService;
    @Inject
    public ILevelService levelService;
    @Inject
    public PreferencesService preferencesService;
    @Inject
    public ProfileService profileService;
    @Inject
    public Styles styles;
    @Inject
    public Lazy<SplashScreen> splashScreen;
    @Inject
    public Lazy<MenuScreen> menuScreen;
    @Inject
    public Lazy<OptionScreen> optionScreen;
    @Inject
    public Lazy<HighScoresScreen> highScoresScreen;
    @Inject
    public Lazy<ScoreScreen> scoreScreen;
    @Inject
    public Lazy<SelectLevelScreen> selectLevelScreen;
    @Inject
    public Provider<GameScreen> gameScreen;
    @Inject
    public Lazy<GameOverScreen> gameOverScreen;
    @Inject
    public StateMachine<BaseGame> gameStateMachine;
    private BaseScreen currScreen;
    private BaseScreen nextScreen;
    @Inject
    @Named("camera")
    public OrthographicCamera camera;
    @Inject
    public SpriteBatch batch;
    @Inject
    public Stage stage;


    @Override
    public void render() {
        float deltaTime = Math.min(Gdx.graphics.getDeltaTime(), 1.0f / 60.0f);
        gameStateMachine.update();


    }


    @Override
    public void create() {
        GameLogger.info("BaseGame", "Ciclo Create del juego");
        objectGraph = ObjectGraph.create(new GameModule(this));
        objectGraph.inject(this);
        currScreen = splashScreen.get();
        nextScreen = menuScreen.get();

        currScreen.show();
        GameLogger.info("BaseGame", "Finalizo el Ciclo Create del juego");

    }


    @Override
    public void resize(int width, int height) {
        GameLogger.info("BaseGame", "Ciclo Resize del juego");
        audioService.stopMusic();
        if (currScreen != null) currScreen.resize(width, height);

    }


    @Override
    public void pause() {
        GameLogger.info("BaseGame", "Ciclo Pause del juego");
        if (currScreen != null) currScreen.pause();
    }


    @Override
    public void resume() {
        GameLogger.info("BaseGame", "Ciclo Resume del juego");
        if (currScreen != null) currScreen.resume();
    }


    @Override
    public void dispose() {
        GameLogger.info("BaseGame", "Ciclo Dispose del juego");
        resourcesService.dispose();
        if (currScreen != null) {
            currScreen.hide();
            currScreen.dispose();
            currScreen=null;
        }
        if (nextScreen != null) {
            nextScreen.hide();
            nextScreen.dispose();
            nextScreen= null;
        }

    }


    public BaseScreen getNextScreen() {
        return nextScreen;
    }


    public void setNextScreen(BaseScreen nextScreen) {
        GameLogger.info("BaseGame", "Set next screen %s",(nextScreen==null)? "Null": nextScreen.getClass().getSimpleName());
        this.nextScreen = nextScreen;
    }


    public BaseScreen getCurrScreen() {
        return currScreen;
    }


    public void setCurrScreen(BaseScreen currScreen) {
        GameLogger.info("BaseGame", "Set current screen %s", currScreen.getClass().getSimpleName());
        this.currScreen.pause();
        this.currScreen.hide();
        this.currScreen.dispose();
        this.currScreen=null;
        this.currScreen = currScreen;

    }

}

