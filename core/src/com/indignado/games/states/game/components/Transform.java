package com.indignado.games.states.game.components;

import com.badlogic.gdx.math.Vector2;
import com.ilargia.games.entitas.codeGenerator.Component;
import com.ilargia.games.entitas.interfaces.IComponent;

@Component(pools = {"SMCore"})
public class Transform implements IComponent {
    public Vector2 position;
    public float rotation;
    public Vector2 scale;

    public Transform(float x, float y, float rotation, float scaleX, float scaleY) {
        this.position = new Vector2(x,y);
        this.rotation = rotation;
        this.scale = new Vector2(scaleX, scaleY);
    }
}