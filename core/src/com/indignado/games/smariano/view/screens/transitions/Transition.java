package com.indignado.games.smariano.view.screens.transitions;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by Rubentxu on 22/06/14.
 */
public interface Transition {
    public float getDuration();

    public void render(SpriteBatch batch, Texture current, Texture next, float alpha);

}
