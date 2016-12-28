package com.indignado.games.smariano.utils.gui;

import com.badlogic.gdx.Gdx;
import com.indignado.games.smariano.config.constantes.Env;

public class ScaleUtil {

    public static float getRatioX() {
        return Gdx.graphics.getWidth() / Env.VIRTUAL_WIDTH;
    }

    public static float getRatioY() {
        return Gdx.graphics.getHeight() / Env.VIRTUAL_HEIGHT;
    }

    public static float getWorldRatioX() {
        return Gdx.graphics.getWidth() / Env.WORLD_WIDTH;
    }

    public static float getWorldRatioY() {
        return Gdx.graphics.getHeight() / Env.WORLD_HEIGHT;
    }

    public static float getSizeRatio() {
        if (getRatioX() < getRatioY())
            return getRatioX();
        else
            return getRatioY();
    }

    public static float getWorldSizeRatio() {
        if (getWorldRatioX() < getWorldRatioY())
            return getWorldRatioX();
        else
            return getWorldRatioY();
    }
}
