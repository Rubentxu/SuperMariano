package com.indignado.games.states.splash.components;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.ilargia.games.entitas.api.IComponent;
import com.ilargia.games.entitas.codeGenerator.Component;

@Component(pools = {"Splash"}, isSingleEntity = true)
public class TextureView implements IComponent {
    public String name;
    public TextureRegion texture;
    public Vector2 position;
    public float rotation = 0;
    public int height;
    public int width;


    public TextureView(String name, TextureRegion texture, Vector2 position, float rotation, int height, int width) {
        this.name = name;
        this.texture = texture;
        this.position = position;
        this.rotation = rotation;
        this.height = height;
        this.width = width;

    }
}
