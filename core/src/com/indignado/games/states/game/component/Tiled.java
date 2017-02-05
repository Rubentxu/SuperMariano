package com.indignado.games.states.game.component;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.ilargia.games.entitas.api.IComponent;
import com.ilargia.games.entitas.codeGenerator.Component;


@Component(pools = {"Scene"}, isSingleEntity = true)
public class Tiled implements IComponent {
    public String tileMapName;
    public float unitScale;

}