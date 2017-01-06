package com.indignado.games;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class SMEngine extends com.ilargia.games.egdx.EGEngine {

    public SMEngine() {
        super(new SpriteBatch(), new BitmapFont(), new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));

    }

}