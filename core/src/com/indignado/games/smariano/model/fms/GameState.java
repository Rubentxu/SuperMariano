package com.indignado.games.smariano.model.fms;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ai.fsm.State;
import com.badlogic.gdx.ai.msg.Telegram;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.indignado.games.smariano.BaseGame;
import com.indignado.games.smariano.model.services.ResourceService;
import com.indignado.games.smariano.model.services.interfaces.ILevelService;
import com.indignado.games.smariano.utils.debug.GameLogger;
import com.indignado.games.smariano.view.screens.GameScreen;
import com.indignado.games.smariano.view.screens.MenuScreen;
import com.indignado.games.smariano.view.screens.transitions.Transition;
import com.indignado.games.smariano.view.screens.transitions.TransitionFactory;

import javax.inject.Inject;


/**
 * Created by Rubentxu on 26/06/14.
 */
public enum GameState implements State<BaseGame> {
    RUNNING {
        @Override
        public void update(BaseGame game) {
            if (game.getCurrScreen() != null) game.getCurrScreen().render(Gdx.graphics.getDeltaTime());
        }
    },
    PAUSED {
        @Override
        public void update(BaseGame game) {
            game.getCurrScreen().showDialog();
            game.getCurrScreen().render(Math.min(Gdx.graphics.getDeltaTime(), 1.0f / 60.0f));
        }
    },
    UPDATE {
        @Override
        public void update(BaseGame game) {

        }
    },
    LEVELWIN {
        @Override
        public void update(BaseGame game) {
            game.setNextScreen(game.highScoresScreen.get());
            game.getCurrScreen().showMessage("Level completed ", 1.5f, GameState.SHOW_NEXT_SCREEN);
            game.getCurrScreen().render(Math.min(Gdx.graphics.getDeltaTime(), 1.0f / 60.0f));

        }
    },
    IDLE {
        @Override
        public void update(BaseGame game) {

        }
    },
    SLOWMOTION {
        @Override
        public void update(BaseGame game) {

        }
    },
    BACK {
        @Override
        public void update(BaseGame game) {

        }
    },
    GAME_OVER {
        @Override
        public void update(BaseGame game) {
            game.setNextScreen(game.highScoresScreen.get());
            game.getCurrScreen().showMessage("Game Over", 1.5f, GameState.SHOW_NEXT_SCREEN);
            game.getCurrScreen().render(Math.min(Gdx.graphics.getDeltaTime(), 1.0f / 60.0f));
        }
    },
    EXIT {
        @Override
        public void update(BaseGame game) {
            Gdx.app.exit();
        }
    },
    SHOW_NEXT_SCREEN {
        private boolean init = false;
        private boolean screen_transition = false;
        private float timeTransition;
        private FrameBuffer currFbo;
        private FrameBuffer nextFbo;
        private Transition transition;

        @Override
        public void update(BaseGame game) {

            if (screen_transition) {
                float duration = 0;

                if (transition != null)
                    duration = transition.getDuration();

                timeTransition = Math.min(timeTransition + Gdx.graphics.getDeltaTime(), duration);
                if (transition == null || timeTransition >= duration) {
                    if (game.getCurrScreen() != null) {
                        game.getCurrScreen().pause();
                        game.getCurrScreen().hide();
                    }

                    game.getNextScreen().resume();
                    game.setCurrScreen(game.getNextScreen());
                    game.setNextScreen(null);
                    transition = null;
                    Gdx.input.setInputProcessor(game.getCurrScreen().getInputProcessor());
                    screen_transition=false;
                    game.gameStateMachine.changeState(GameState.RUNNING);
                } else {

                    currFbo.begin();
                        game.getCurrScreen().render(Gdx.graphics.getDeltaTime());
                    currFbo.end();

                    nextFbo.begin();
                        game.getNextScreen().render(Gdx.graphics.getDeltaTime());
                    nextFbo.end();

                    float alpha = timeTransition / duration;
                    transition.render(game.batch, currFbo.getColorBufferTexture(), nextFbo.getColorBufferTexture(), alpha);

                }

            } else {
                if (game.getNextScreen() == null) {
                    GameLogger.error("GameState", "No existe una próxima pantalla a la que poder cambiar");
                    return;
                }
                GameLogger.info("GameState", "Comienza el Estado SHOW_NEXT_SCREEN NextScreen: %s"
                        , game.getNextScreen().getClass().getSimpleName());

                int w = Gdx.graphics.getWidth();
                int h = Gdx.graphics.getHeight();
                if (!init) {
                    nextFbo = new FrameBuffer(Pixmap.Format.RGBA8888, w, h, true);
                    currFbo = new FrameBuffer(Pixmap.Format.RGBA8888, w, h, true);

                    init = true;
                }

                game.getNextScreen().show();
                game.getNextScreen().resize(w, h);
                game.getCurrScreen().pause();
                game.getNextScreen().pause();
                Gdx.input.setInputProcessor(null); // disable input

                timeTransition = 0;
                if (game.getCurrScreen() != null && !screen_transition) {
                    transition = TransitionFactory.getTransition(game.getNextScreen());
                    screen_transition = true;
                }
            }
            if (game.getCurrScreen() instanceof GameScreen) {
                GameLogger.info("GameState", "music Game");
                game.audioService.stopMusic();
                game.audioService.playMusic(game.levelService.getCurrentLevel().getMusic());
            } else if (game.getCurrScreen() instanceof MenuScreen) {
                GameLogger.info("GameState", "music Menu");
                game.audioService.stopMusic();
                game.audioService.playMusic(ResourceService.MUSIC_MENU);

            }

        }
    };


    private static final String TAG = "GameState";



    @Override
    public void enter(BaseGame game) {
        if (game.gameStateMachine.getCurrentState().equals(SHOW_NEXT_SCREEN)) {
            GameLogger.info(TAG, "Entrando en el Estado.......%s...........%s", game.gameStateMachine.getCurrentState(), game.getNextScreen().getClass().getSimpleName());
        } else {
            GameLogger.info(TAG, "Entrando en el Estado.......%s...........%s", game.gameStateMachine.getCurrentState(), game.getCurrScreen().getClass().getSimpleName());
        }

    }


    @Override
    public void exit(BaseGame game) {
        GameLogger.info(TAG, "Saliendo del Estado.........%s...........", game.gameStateMachine.getCurrentState());

    }


    @Override
    public boolean onMessage(Telegram telegram) {
        return false;
    }


}
