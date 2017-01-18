package com.indignado.games.states.game.data;

import com.indignado.games.states.game.gen.Entity;

@FunctionalInterface
public interface MouseSensor {

    boolean query(Entity entity, int screenX, int screenY, int scrolledAmount, int button);

}