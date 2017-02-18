package com.indignado.games;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.ilargia.games.egdx.base.BaseEngine;


public class SMEngine extends BaseEngine {

    public SMEngine() {
        super(new SpriteBatch(), new World(new Vector2(0F,15.0F), true), new BitmapFont());

    }

}