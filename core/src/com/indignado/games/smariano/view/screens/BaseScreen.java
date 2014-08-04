package com.indignado.games.smariano.view.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ai.fsm.StateMachine;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.indignado.games.smariano.BaseGame;
import com.indignado.games.smariano.config.constantes.Env;
import com.indignado.games.smariano.model.fms.GameState;
import com.indignado.games.smariano.model.services.interfaces.IResourcesService;
import com.indignado.games.smariano.model.services.Styles;

import javax.inject.Inject;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeIn;

public abstract class BaseScreen implements Screen {

    protected Table mainTable;
    protected Window dialog;
    protected Label message;
    protected Stack container;


    @Inject
    protected BaseGame game;
    @Inject
    protected IResourcesService resourcesManager;
    @Inject
    protected Styles styles;
    @Inject
    protected OrthographicCamera camera;
    protected Stage stage;
    @Inject
    public StateMachine<BaseGame> gameStateMachine;


    // ScreenTransition transition = ScreenTransitionSlice.init(2,ScreenTransitionSlice.UP_DOWN, 10, Interpolation.pow5Out);

    protected String getName() {
        return this.getClass().getName();
    }

    @Override
    public void show() {
        mainTable = new Table();
        mainTable.setFillParent(true);

    }

    @Override
    public void resize(int width, int height) {
        Gdx.app.log(Env.LOG, "Resizing screen: " + getName() + " to: " + width + " x " + height);
        stage.clear();
        stage.getViewport().update(width, height, true);
        setBackBackButton();
        mainTable.getColor().a = 0f;
        mainTable.addAction(fadeIn(0.2f));
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void hide() {
        Gdx.app.log(Env.LOG, "Hiding screen: " + getName());
    }

    @Override
    public void pause() {
        Gdx.app.log(Env.LOG, "Pausing screen: " + getName());
    }

    @Override
    public void resume() {
        Gdx.app.log(Env.LOG, "Resuming screen: " + getName());
    }

    @Override
    public void dispose() {
        Gdx.app.log(Env.LOG, "Disposing screen: " + getName());
        stage.dispose();
        mainTable = null;

    }



    public void showDialog() {
        if (dialog == null) {
            dialog = new Window("Que desea hacer ?", styles.skin);

            TextButton btnSalir = new TextButton("Salir", styles.skin);
            TextButton btnContinuar = new TextButton("Continuar", styles.skin);
            btnSalir.addListener(new ClickListener() {
                public void clicked(InputEvent event, float x, float y) {
                    System.out.println("Click Salir...");
                    gameStateMachine.changeState(GameState.EXIT);
                }
            });
            btnContinuar.addListener(new ClickListener() {
                public void clicked(InputEvent event, float x, float y) {
                    System.out.println("Click Continuar...");
                    gameStateMachine.changeState(GameState.RUNNING);
                    dialog.remove();
                    dialog = null;
                }
            });

            dialog.defaults().spaceBottom(10);
            dialog.row().fill().expandX();
            dialog.add(btnContinuar);
            dialog.add(btnSalir);
            dialog.pack();
            dialog.setPosition(Gdx.graphics.getWidth() / 2 - dialog.getWidth() / 2, Gdx.graphics.getHeight() / 2 - dialog.getHeight() / 2);
            stage.addActor(dialog);
        }

    }

    public void showMessage(String text, float time, final GameState gameState) {
        Gdx.app.debug(Env.LOG, "Show Message !");
        if (message == null) {
            message = new Label(text, styles.skin, "header", Color.ORANGE);
            container = new Stack();
            container.add(message);
            container.setPosition(Gdx.graphics.getWidth() / 2 - container.getWidth() / 2,
                    Gdx.graphics.getHeight() + container.getHeight());
            container.setVisible(false);
            stage.addActor(container);

        } else if (!container.isVisible()) {
            message.setText(text);
            container.pack();
            MoveToAction action = Actions.action(MoveToAction.class);
            action.setPosition(Gdx.graphics.getWidth() / 2 - container.getWidth() / 2,
                    Gdx.graphics.getHeight() / 2 - container.getHeight() / 2);
            action.setDuration(time);
            action.setInterpolation(Interpolation.bounceIn);

            container.addAction(Actions.sequence(action, Actions.delay(1f),
                    Actions.run(new Runnable() {
                        public void run() {
                            gameStateMachine.changeState(gameState);
                            container.setVisible(false);
                        }
                    })
            ));
            container.setVisible(true);
        }

    }

    public InputProcessor getInputProcessor() {
        return stage;
    }

    private void setBackBackButton() {
        stage.addListener(new InputListener() {

            @Override
            public boolean keyDown(InputEvent event, int keycode) {
                if ((keycode == Input.Keys.ESCAPE) || (keycode == Input.Keys.BACK)) {
                    Gdx.app.log(Env.LOG, "PRESS BUTTON: GAME_BACK");
                    gameStateMachine.changeState(GameState.BACK);
                }

                return false;
            }
        });
    }

    public static enum SCREEN {SPLASH, MENU, GAME, OPTIONS, HIGHSCORES, SCORE, CREDITS, LEVELSCREEN}


}