package com.indignado.games.states.game.components;

import com.badlogic.gdx.math.Vector2;
import com.ilargia.games.entitas.codeGenerator.Component;
import com.ilargia.games.entitas.interfaces.IComponent;

@Component(pools = {"SMCore"})
public class Motion implements IComponent {
    public float velocityX;
    public float velocityY;
    public float angularVelocity;

    public Motion(float x, float y, float angularVelocity ) {
        this.velocityX = x;
        this.velocityY = y;
        this.angularVelocity = angularVelocity;

    }


}
