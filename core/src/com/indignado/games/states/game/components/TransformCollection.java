package com.indignado.games.states.game.components;

import com.ilargia.games.entitas.codeGenerator.Component;
import com.ilargia.games.entitas.interfaces.IComponent;
import com.indignado.games.states.game.data.TextureView;
import com.indignado.games.states.game.data.Transform;

import java.util.Map;

@Component(pools = {"SMCore"})
public class TransformCollection implements IComponent {
   public Map<String, Transform> transform;
}
