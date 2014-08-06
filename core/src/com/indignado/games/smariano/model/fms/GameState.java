package com.indignado.games.smariano.model.fms;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ai.fsm.State;
import com.badlogic.gdx.ai.msg.Telegram;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.indignado.games.smariano.BaseGame;
import com.indignado.games.smariano.model.services.LevelService;
import com.indignado.games.smariano.model.services.ResourceService;
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
            if (game.currScreen != null) game.currScreen.render(Gdx.graphics.getDeltaTime());
        }
    },
    PAUSED {
        @Override
        public void update(BaseGame game) {
            game.currScreen.showDialog();
            game.currScreen.render(Math.min(Gdx.graphics.getDeltaTime(), 1.0f / 60.0f));
        }
    },
    UPDATE {
        @Override
        public void update(BaseGame game) {

        }
    },
    OVER {
        @Override
        public void update(BaseGame game) {
            game.setNextScreen(game.highScoresScreen.get());
            game.currScreen.showMessage("Game Over", 1.5f, GameState.SHOW_NEXT_SCREEN);
            game.currScreen.render(Math.min(Gdx.graphics.getDeltaTime(), 1.0f / 60.0f));
        }
    },
    WIN {
        @Override
        public void update(BaseGame game) {

        }
    },
    LEVELWIN {
        @Override
        public void update(BaseGame game) {

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

        }
    },
    SCREEN_TRANSITION {
        @Override
        public void update(BaseGame game) {
            float duration = 0;
            GameLogger.info("GameState", "Estado SCREEN_TRANSITION NextScreen: %s timeTransition %f"
                    , game.nextScreen.getClass().getName(), timeTransition);


            if (transition != null)
                duration = transition.getDuration();

            timeTransition = Math.min(timeTransition + Gdx.graphics.getDeltaTime(), duration);
            if (transition == null || timeTransition >= duration) {
                if (game.currScreen != null) game.currScreen.hide();
                game.nextScreen.resume();
                game.currScreen = game.nextScreen;
                game.nextScreen = null;
                transition = null;
                //Gdx.input.setInputProcessor(currScreen.getInputProcessor());
                game.gameStateMachine.changeState(GameState.RUNNING);
            } else {
                currFbo.begin();
                if (game.currScreen != null) game.currScreen.render(Gdx.graphics.getDeltaTime());
                currFbo.end();
                nextFbo.begin();
                if (game.nextScreen != null) game.nextScreen.render(Gdx.graphics.getDeltaTime());
                nextFbo.end();

                float alpha = timeTransition / duration;
                transition.render(game.batch, currFbo.getColorBufferTexture(), nextFbo.getColorBufferTexture(), alpha);

            }
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

        @Override
        public void update(BaseGame game) {
            if (game.nextScreen == null) {
                GameLogger.error("GameState", "No existe una próxima pantalla a la que poder cambiar");
                return;
            }
            GameLogger.info("GameState", "Comienza el Estado SHOW_NEXT_SCREEN NextScreen: %s"
                    , game.nextScreen.getClass().getSimpleName());

            int w = Gdx.graphics.getWidth();
            int h = Gdx.graphics.getHeight();
            if (!init) {
                currFbo = new FrameBuffer(Pixmap.Format.RGB888, w, h, false);
                nextFbo = new FrameBuffer(Pixmap.Format.RGB888, w, h, false);
                init = true;
            }


            game.nextScreen.show();
            game.nextScreen.resize(w, h);
            //game.nextScreen.render(0);
            game.nextScreen.pause();
            Gdx.input.setInputProcessor(null); // disable input

            timeTransition = 0;
            if (game.currScreen != null) {
                game.currScreen.pause();
                game.gameStateMachine.changeState(GameState.SCREEN_TRANSITION);
                GameLogger.info("GameState", "Finalizo el Estado SHOW_NEXT_SCREEN NextScreen: %s"
                        , game.nextScreen.getClass().getSimpleName());
                transition = TransitionFactory.getTransition(game.nextScreen);
            }
            if (game.currScreen instanceof GameScreen) {
                GameLogger.info("GameState", "music Game");
                game.audioService.stopMusic();
                game.audioService.playMusic(levelService.getCurrentLevel().getMusic());
            } else if (game.currScreen instanceof MenuScreen) {
                GameLogger.info("GameState", "music Menu");
                game.audioService.stopMusic();
                game.audioService.playMusic(ResourceService.MUSIC_MENU);

            }

        }
    };

    protected static float timeTransition;
    protected static FrameBuffer currFbo;
    protected static FrameBuffer nextFbo;
    private static final String TAG = "GameState";
    private static Transition transition;
    @Inject
    LevelService levelService;

    @Override
    public void enter(BaseGame game) {
        if (game.gameStateMachine.getCurrentState().equals(SHOW_NEXT_SCREEN)) {
            GameLogger.info(TAG, "Entrando en el Estado.......%s...........%s", game.gameStateMachine.getCurrentState(), game.nextScreen.getClass().getSimpleName());
        } else {
            GameLogger.info(TAG, "Entrando en el Estado.......%s...........%s", game.gameStateMachine.getCurrentState(), game.currScreen.getClass().getSimpleName());
        }

    }


    @Override
    public void exit(BaseGame game) {
        if (game.gameStateMachine.getCurrentState().equals(SHOW_NEXT_SCREEN)) {
            GameLogger.info(TAG, "Saliendo del Estado.........%s...........%s", game.gameStateMachine.getCurrentState(), game.nextScreen.getClass().getSimpleName());
        } else {
            GameLogger.info(TAG, "Saliendo del Estado.........%s...........%s", game.gameStateMachine.getCurrentState(), game.currScreen.getClass().getSimpleName());
        }


    }


    @Override
    public boolean onMessage(Telegram telegram) {
        return false;
    }


}
