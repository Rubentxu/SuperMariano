package com.indignado.games.states.menu;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.Align;
import com.ilargia.games.egdx.base.interfaces.GameState;
import com.ilargia.games.egdx.managers.EGAssetsManager;
import com.indignado.games.SMEngine;
import com.indignado.games.Styles;

public class MenuState implements GameState {
    private Styles styles;
    private Stage stage;
    private Table mainTable;
    private SMEngine engine;
    private EGAssetsManager assetsManager;

    public MenuState(Styles styles, SMEngine engine) {
        this.styles = styles;
        this.engine = engine;
        this.stage = new Stage();
        mainTable = new Table();
        mainTable.setFillParent(true);

    }

    @Override
    public void loadResources() {
        assetsManager = engine.getManager(EGAssetsManager.class);
        stage.clear();
        stage.getViewport().update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true);
        InputMultiplexer multiplexer = new InputMultiplexer();
        Gdx.input.setInputProcessor(multiplexer);
        multiplexer.addProcessor(stage);
        Gdx.app.log("Menu","LoadResources");
    }

    @Override
    public void init() {
        Gdx.app.log("Menu","Init");
        int pad = (int) (20 * Styles.ScaleUtil.getSizeRatio());
        int pad2 = (int) (60 * Styles.ScaleUtil.getSizeRatio());
        final TextButton btnStart = new TextButton("Comenzar", styles.skin);
        btnStart.pad(pad, pad2, pad, pad2);
        final TextButton btnOptions = new TextButton("Opciones", styles.skin);
        btnOptions.pad(pad, pad2, pad, pad2);
        final TextButton btnScores = new TextButton("Puntuaciones", styles.skin);
        btnScores.pad(pad, pad2, pad, pad2);
        final TextButton button3 = new TextButton("Creditos", styles.skin);
        button3.pad(pad, pad2, pad, pad2);
        button3.setChecked(false);

        btnStart.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Click Comenzar...");
//                game.setNextScreen(game.selectLevelScreen.get());
//                gameStateMachine.changeState(GameState.SHOW_NEXT_SCREEN);
            }
        });

        btnOptions.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Click optionScreen...");
//                game.setNextScreen(game.optionScreen.get());
//                gameStateMachine.changeState(GameState.SHOW_NEXT_SCREEN);
            }
        });

        btnScores.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Click highScoreScreen...");
//                game.setNextScreen(game.highScoresScreen.get());
//                gameStateMachine.changeState(GameState.SHOW_NEXT_SCREEN);
            }
        });

        Label label = new Label("SUPER MARIANO", styles.skin, "header", Color.CYAN);
        label.setAlignment(Align.center, Align.center);
        mainTable.defaults().padBottom(pad);
        if (Gdx.graphics.getHeight() < 480) mainTable.defaults().height(Gdx.graphics.getHeight() / 5f - pad);
        mainTable.add(label);
        mainTable.row();
        mainTable.add(btnStart);
        mainTable.row();
        mainTable.add(btnOptions);
        mainTable.row();
        mainTable.add(btnScores);
        mainTable.row();
        mainTable.add(button3);
        mainTable.row();
        mainTable.setBackground(new SpriteDrawable(new Sprite((Texture) assetsManager.getTexture(Styles.MENU_BACKGROUND))));
        mainTable.row();
        this.stage.addActor(mainTable);
    }

    @Override
    public void update(float deltaTime) {
        Gdx.app.log("Menu","Update");
        Gdx.gl.glClearColor(0.1f, 0f, 0f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(deltaTime);

    }

    @Override
    public void render() {
        Gdx.app.log("Menu","Render");
        stage.draw();
    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void dispose() {
        stage.clear();
        stage = null;
    }
}
