package com.indignado.games.states.game.components;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.ilargia.games.entitas.codeGenerator.Component;
import com.ilargia.games.entitas.interfaces.IComponent;

@Component(pools = {"SMCore"})
public class TextureView implements IComponent {
    public final TextureRegion texture;
    public final int height;
    public final int width;


    public TextureView(TextureRegion texture, int height, int width) {
        this.texture = texture;
        this.height = height;
        this.width = width;

    }
}
