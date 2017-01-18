package com.indignado.games.states.game.data;

import com.indignado.games.states.game.gen.Entity;

@FunctionalInterface
public interface KeyboardSensor {

    public enum Type { KeyUp, KeyDown}

    boolean query(Entity entity, Type type, int keycode, char character);


}