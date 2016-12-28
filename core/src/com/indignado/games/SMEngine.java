package com.indignado.games;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;


public class SMEngine  extends com.ilargia.games.egdx.EGEngine {

   // public Context context;
    public ShapeRenderer sr;
    public Batch batch;
    public BitmapFont font;
    public OrthographicCamera cam;

    public SMEngine(com.ilargia.games.entitas.Systems systems, com.ilargia.games.egdx.interfaces.managers.Manager... managers) {
        super(systems, managers);
        sr = new ShapeRenderer();
        batch = new SpriteBatch();
        font = new BitmapFont();
        cam = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    @Override
    public void configure(String[] args) {
        //context = new Context();

    }

    @Override
    public void init() {

    }

}