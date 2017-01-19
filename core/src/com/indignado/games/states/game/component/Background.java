package com.indignado.games.states.game.component;

import com.badlogic.gdx.graphics.Texture;
import com.ilargia.games.entitas.codeGenerator.Component;
import com.ilargia.games.entitas.interfaces.IComponent;

@Component(pools = {"SMCore"}, isSingleEntity = true)
public class Background implements IComponent {
    public Texture front;
    public Texture middle;
    public Texture back;

}