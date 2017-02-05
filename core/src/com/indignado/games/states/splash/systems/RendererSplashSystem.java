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
import com.ilargia.games.entitas.api.system.IExecuteSystem;
import com.ilargia.games.entitas.api.system.IInitializeSystem;
import com.indignado.games.states.splash.gen.SplashContext;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeIn;


public class RendererSplashSystem implements IInitializeSystem, IExecuteSystem {

    private final Stage stage;
    private final Table mainTable;
    private OrthographicCamera cam;
    private Batch batch;
    private SplashContext context;

    public RendererSplashSystem(SplashContext context, OrthographicCamera cam, Batch batch) {
        this.context = context;
        this.cam = cam;
        this.batch = batch;
        mainTable = new Table();
        mainTable.setFillParent(true);
        mainTable.getColor().a = 0f;
        mainTable.addAction(fadeIn(1f));
        stage = new Stage(new FillViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
        stage.addActor(mainTable);

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
        mainTable.setBackground(new SpriteDrawable(new Sprite(context.getTextureView().texture)));

    }
}
