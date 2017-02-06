package com.indignado.games.states.game.component;

import com.ilargia.games.entitas.api.IComponent;
import com.ilargia.games.entitas.codeGenerator.Component;


@Component(pools = {"Gui"}, isSingleEntity = true)
public class TouchPad implements IComponent {
    public int value;
}
