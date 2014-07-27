package com.indignado.games.smariano.pantallas.transitions;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;

/**
 * Created by Rubentxu on 22/06/14.
 */
public class FadeTransition implements Transition {
    private Float duration;


    public FadeTransition(Float duration) {
        this.duration = duration;
    }


    @Override
    public float getDuration() {
        return duration;
    }


    @Override
    public void render(SpriteBatch batch, Texture current, Texture next, float alpha) {
        float w = current.getWidth();
        float h = current.getHeight();
        alpha = Interpolation.fade.apply(alpha);
        Gdx.gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.setColor(1, 1, 1, 1);
        batch.draw(current, 0, 0, 0, 0, w, h, 1, 1, 0, 0, 0, current.getWidth(), current.getHeight(), false, true);
        batch.setColor(1, 1, 1, alpha);
        batch.draw(next, 0, 0, 0, 0, w, h, 1, 1, 0, 0, 0, next.getWidth(), next.getHeight(), false, true);
        batch.end();

    }

}
