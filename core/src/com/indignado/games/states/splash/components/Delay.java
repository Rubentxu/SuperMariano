package com.indignado.games.states.splash.components;

import com.ilargia.games.entitas.api.IComponent;
import com.ilargia.games.entitas.codeGenerator.Component;

@Component(pools = {"Splash"})
public class Delay implements IComponent {
    public float duration;
    public float time;

    public Delay(float duration) {
        this.duration = duration;
        this.time = 0;
    }
}