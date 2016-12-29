package com.indignado.games.states.splash.systems;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.ilargia.games.entitas.Group;
import com.ilargia.games.entitas.interfaces.IExecuteSystem;
import com.ilargia.games.entitas.interfaces.ISetPool;
import com.indignado.games.states.splash.components.TextureView;
import com.indignado.games.states.splash.gen.SplashEntity;
import com.indignado.games.states.splash.gen.SplashMatcher;
import com.indignado.games.states.splash.gen.SplashPool;


public class RendererSplashSystem implements IExecuteSystem, ISetPool<SplashPool> {


    private OrthographicCamera cam;
    private Batch batch;
    private Group<SplashEntity> _groupTextureView;

    public RendererSplashSystem(OrthographicCamera cam, Batch batch) {
        this.cam = cam;
        this.batch = batch;

    }

    @Override
    public void setPool(SplashPool pool) {
        _groupTextureView = pool.getGroup(SplashMatcher.TextureView());
    }

    @Override
    public void execute(float deltatime) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        cam.update();

        batch.begin();

        for (SplashEntity e : _groupTextureView.getEntities()) {
            TextureView textureView = e.getTextureView();
            batch.draw(textureView.texture, textureView.position.x , textureView.position.y ,
                    0, 0, textureView.width, textureView.height, 1, 1, textureView.rotation);


        }
        batch.end();
    }

}
