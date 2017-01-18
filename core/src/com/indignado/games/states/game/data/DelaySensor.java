package com.indignado.games.states.game.data;

import com.indignado.games.states.game.gen.Entity;

@FunctionalInterface
public interface DelaySensor {
    float delay = 0;
    float duration = 0;
    boolean repeat = false;
    // Signal
    float time = 0;

    boolean query(Entity entity, float deltaTime);

}