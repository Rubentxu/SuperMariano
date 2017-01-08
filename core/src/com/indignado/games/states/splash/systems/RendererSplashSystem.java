package com.indignado.games.states.splash.systems;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.ilargia.games.entitas.Group;
import com.ilargia.games.entitas.interfaces.IExecuteSystem;
import com.ilargia.games.entitas.interfaces.IInitializeSystem;
import com.ilargia.games.entitas.interfaces.ISetPool;
import com.indignado.games.states.splash.components.TextureView;
import com.indignado.games.states.splash.gen.SplashEntity;
import com.indignado.games.states.splash.gen.SplashMatcher;
import com.indignado.games.states.splash.gen.SplashPool;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeIn;


public class RendererSplashSystem implements IInitializeSystem, IExecuteSystem, ISetPool<SplashPool> {

    private final Stage stage;
    private final Table mainTable;
    private OrthographicCamera cam;
    private Batch batch;
    private SplashPool pool;

    public RendererSplashSystem(OrthographicCamera cam, Batch batch) {
        this.cam = cam;
        this.batch = batch;
        mainTable = new Table();
        mainTable.setFillParent(true);
        mainTable.getColor().a = 0f;
        mainTable.addAction(fadeIn(1f));
        stage= new Stage(new FillViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
        stage.addActor(mainTable);
    }

    @Override
    public void setPool(SplashPool pool) {
        this.pool = pool;
    }

    @Override
    public void execute(float deltatime) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        cam.update();
        stage.act(deltatime);
        stage.draw();

    }

    @Override
    public void initialize() {
       mainTable.setBackground(new SpriteDrawable(new Sprite(pool.getTextureView().texture)));

    }
}
