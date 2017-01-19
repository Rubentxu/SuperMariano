package com.indignado.games.states.game.actuator;


import com.indignado.games.states.game.component.Motion;

public interface VelocityActuator {
    void apply(Motion motion);
}
