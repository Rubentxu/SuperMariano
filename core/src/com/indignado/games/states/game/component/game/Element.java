package com.indignado.games.states.game.component.game;

import com.ilargia.games.entitas.api.IComponent;
import com.ilargia.games.entitas.codeGenerator.Component;

@Component(pools = {"Game"})
public class Element implements IComponent {
    public String type;
    public String tags;
}
