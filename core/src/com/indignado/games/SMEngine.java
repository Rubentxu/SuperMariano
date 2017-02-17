package com.indignado.games;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;


public class SMEngine extends com.ilargia.games.egdx.EGEngine {

    public SMEngine() {
        super(new SpriteBatch(), new World(new Vector2(0F,15.0F), true), new BitmapFont());

    }

}