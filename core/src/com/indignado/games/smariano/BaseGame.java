package com.indignado.games.smariano;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ai.fsm.StateMachine;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.indignado.games.smariano.config.ScreensModule;
import com.indignado.games.smariano.model.fms.GameState;
import com.indignado.games.smariano.model.services.interfaces.IAudioService;
import com.indignado.games.smariano.model.services.interfaces.IResourcesService;
import com.indignado.games.smariano.view.screens.*;
import com.indignado.games.smariano.utils.debug.GameLogger;
import dagger.Lazy;
import dagger.ObjectGraph;

import javax.inject.Inject;

public class BaseGame implements ApplicationListener {

    @Inject
    public IResourcesService resourcesService;
    @Inject
    public IAudioService audioService;
    @Inject
    public SpriteBatch batch;
    @Inject
    protected Lazy<SplashScreen> splashScreen;
    @Inject
    protected Lazy<MenuScreen> menuScreen;
    @Inject
    protected Lazy<OptionScreen> optionScreen;
    @Inject
    protected Lazy<HighScoresScreen> highScoresScreen;
    @Inject
    protected Lazy<ScoreScreen> scoreScreen;
    @Inject
    protected Lazy<SelectLevelScreen> selectLevelScreen;
    @Inject
    protected Lazy<GameScreen> gameScreen;
    @Inject
    protected Lazy<GameOverScreen> gameOverScreen;
    @Inject
    protected StateMachine<BaseGame> gameStateMachine;
    protected BaseScreen currScreen;
    protected BaseScreen nextScreen;



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
        ObjectGraph objectGraph = ObjectGraph.create(new ScreensModule(this));
        objectGraph.inject(this);

        objectGraph.inject(splashScreen);
        objectGraph.inject(menuScreen);
        currScreen = splashScreen;
        currScreen.show();
        nextScreen = menuScreen;
        gameStateMachine.changeState(GameState.SHOW_NEXT_SCREEN);
        GameLogger.info("BaseGame", "Finalizo el Ciclo Create del juego");

    }

    @Override
    public void resize(int width, int height) {
        audioService.stopMusic();
        if (currScreen != null) currScreen.resize(width, height);
        if (nextScreen != null) nextScreen.resize(width, height);
    }

    @Override
    public void pause() {
        if (currScreen != null) currScreen.pause();
    }

    @Override
    public void resume() {
        if (currScreen != null) currScreen.resume();
    }

    @Override
    public void dispose() {
        resourcesService.dispose();
        if (currScreen != null) currScreen.hide();
        if (nextScreen != null) nextScreen.hide();

    }

    public BaseScreen getNextScreen() {
        return nextScreen;
    }


    public void setNextScreen(BaseScreen nextScreen) {
        this.nextScreen = nextScreen;
    }


}

