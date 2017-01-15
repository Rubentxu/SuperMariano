package com.indignado.games.states.menu.components;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.ilargia.games.entitas.codeGenerator.Component;
import com.ilargia.games.entitas.interfaces.IComponent;

@Component(pools = {"SM"}, isSingleEntity = true)
public class Background implements IComponent {
    public Texture front;
    public Texture middle;
    public Texture back;



}