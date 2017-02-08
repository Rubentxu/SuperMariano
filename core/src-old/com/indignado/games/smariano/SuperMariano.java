package com.indignado.games.smariano;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.FPSLogger;

public class SuperMariano extends BaseGame {
    public static boolean DEBUG = false;
    public static FPSLogger log;

    @Override
    public void create() {
        super.create();
        Gdx.input.setCatchBackKey(true);
        log = new FPSLogger();

    }

    @Override
    public void dispose() {
        super.dispose();
    }

    @Override
    public void render() {
        super.render();
        log.log();

    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
    }

    @Override
    public void pause() {
        super.pause();
    }

    @Override
    public void resume() {
        super.resume();
    }
}
