package com.indignado.games.states.game.data;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.ilargia.games.entitas.codeGenerator.Component;
import com.ilargia.games.entitas.interfaces.IComponent;
import com.indignado.games.states.game.data.Bounds;

@Component(pools = {"SMCore"})
public class TextureView implements IComponent {
    public String name;
    public TextureRegion texture;
    public Bounds bounds;
    public boolean flipX ;
    public boolean flipY;
    public int opacity;
    public int layer;
    public Color tint;

    public TextureView(String name, TextureRegion texture, Bounds bounds, boolean flipX, boolean flipY,
                       int opacity, int layer, Color tint) {
        this.name = name;
        this.texture = texture;
        this.bounds = bounds;
        this.flipX = flipX;
        this.flipY = flipY;
        this.opacity = opacity;
        this.layer = layer;
        this.tint = tint;
    }
}