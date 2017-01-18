package com.indignado.games.states.game.components;

import com.badlogic.gdx.math.Vector2;
import com.ilargia.games.entitas.codeGenerator.Component;
import com.ilargia.games.entitas.interfaces.IComponent;
import com.indignado.games.states.game.data.TextureView;

import java.util.Map;

@Component(pools = {"SMCore"})
public class ViewCollection implements IComponent {
   public Map<String, TextureView> views;
}
