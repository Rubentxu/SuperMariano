package com.indignado.games.smariano;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ai.fsm.StateMachine;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.indignado.games.smariano.config.GameModule;
import com.indignado.games.smariano.model.fms.GameState;
import com.indignado.games.smariano.model.services.interfaces.IAudioService;
import com.indignado.games.smariano.model.services.interfaces.ILevelService;
import com.indignado.games.smariano.model.services.interfaces.IResourcesService;
import com.indignado.games.smariano.utils.debug.GameLogger;
import com.indignado.games.smariano.view.screens.*;
import dagger.Lazy;
import dagger.ObjectGraph;

import javax.inject.Inject;
import javax.inject.Named;

public class BaseGame implements ApplicationListener {
    public static ObjectGraph objectGraph;
    @Inject
    public IResourcesService resourcesService;
    @Inject
    public IAudioService audioService;
    @Inject
    public ILevelService levelService;
    @Inject
    @Named("game")
    public SpriteBatch batch;
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
    public Lazy<GameScreen> gameScreen;
    @Inject
    public Lazy<GameOverScreen> gameOverScreen;
    @Inject
    public StateMachine<BaseGame> gameStateMachine;
    private BaseScreen currScreen;
    private BaseScreen nextScreen;



    @Override
    public void render() {
        float deltaTime = Math.min(Gdx.graphics.getDeltaTime(), 1.0f / 60.0f);
        gameStateMachine.update();

        /*switch (SMariano.getGameState()) {
            case GAME_RUNNING:
                if (currScreen != null) currScreen.render(deltaTime);
                break;
            case GAME_PAUSED:
                currScreen.showDialog();
                currScreen.render(deltaTime);
                break;
            case GAME_UPDATE:
                break;
            case GAME_OVER:
                currScreen.showMessage("Game Over", 1.5f, GameState.GAME_SHOW_SCORE);
                currScreen.render(deltaTime);
                break;
            case GAME_WIN:
                break;
            case GAME_LEVELWIN:
                currScreen.showMessage("Nivel Completado", 1f, GameState.GAME_SHOW_SCORE);
                currScreen.render(deltaTime);
                break;
            case GAME_IDLE:
                break;
            case GAME_SLOWMOTION:
                break;
            case GAME_BACK:
                SMariano.setGameState(GameState.GAME_PAUSED);
                break;
            case GAME_SHOW_SPLASH:
                ScreenTransition transition = ScreenTransitionFade.init(0.75f);
                SplashScreen splash = new SplashScreen();
                setScreen(splash, transition);
                break;
            case GAME_SHOW_MENU:
                if (menuScreen == null) menuScreen = new MenuScreen();
                setScreen(menuScreen, menuScreen.getTransition());
                break;
            case GAME_SHOW_SCORE:
                if (scoreScreen == null) scoreScreen = new ScoreScreen();
                setScreen(scoreScreen, scoreScreen.getTransition());
                break;
            case GAME_SHOW_LEVEL_MENU:
                if (levelScreen == null) levelScreen = new SelectLevelScreen();
                setScreen(levelScreen, levelScreen.getTransition());
                break;
            case GAME_SHOW_OPTIONS:
                if (optionScreen == null) optionScreen = new OptionScreen();
                setScreen(optionScreen, optionScreen.getTransition());
                break;
            case GAME_SHOW_HIGHSCORES:
                if (highScoreScreen == null) highScoreScreen = new HighScoresScreen();
                setScreen(highScoreScreen, highScoreScreen.getTransition());
                break;
            case GAME_SHOW_GAME:
                if (gameScreen == null) gameScreen = new GameScreen();
                setScreen(gameScreen, gameScreen.getTransition());
                break;
            case SCREEN_TRANSITION:
                float duration = 0;
                if (screenTransition != null)
                    duration = screenTransition.getDuration();

                t = Math.min(t + deltaTime, duration);
                if (screenTransition == null || t >= duration) {
                    if (currScreen != null) currScreen.hide();
                    nextScreen.resume();
                    currScreen = nextScreen;
                    nextScreen = null;
                    screenTransition = null;
                    Gdx.input.setInputProcessor(currScreen.getInputProcessor());
                    SMariano.setGameState(GameState.GAME_RUNNING);
                } else {
                    currFbo.begin();
                    if (currScreen != null) currScreen.render(deltaTime);
                    currFbo.end();
                    nextFbo.begin();
                    nextScreen.render(deltaTime);
                    nextFbo.end();

                    float alpha = t / duration;
                    screenTransition.render(batch, currFbo.getColorBufferTexture(), nextFbo.getColorBufferTexture(), alpha);
                }
                break;
            case GAME_EXIT:
                Gdx.app.exit();
        }*/
    }


    @Override
    public void create() {
        GameLogger.info("BaseGame", "Ciclo Create del juego");
        objectGraph = ObjectGraph.create(new GameModule(this));
        objectGraph.inject(this);
        currScreen = splashScreen.get();
        nextScreen = menuScreen.get();

        objectGraph.inject(currScreen);
        objectGraph.inject(nextScreen);

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
        this.currScreen=null;
        this.currScreen = currScreen;

    }
}

