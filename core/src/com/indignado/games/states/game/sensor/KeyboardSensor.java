package com.indignado.games.states.game.sensor;

import com.indignado.games.states.game.gen.Entity;

@FunctionalInterface
public interface KeyboardSensor {

    enum Type { KeyUp, KeyDown}

    boolean query(Entity entity, Type type, int keycode, char character);


}