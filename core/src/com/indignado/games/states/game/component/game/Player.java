package com.indignado.games.states.game.component.game;

import com.ilargia.games.entitas.api.IComponent;
import com.ilargia.games.entitas.codeGenerator.Component;

@Component(pools = {"Game"}, isSingleEntity = true)
public class Player implements IComponent {
    public boolean leftPressed = false;
    public boolean rightPressed = false;
    public boolean jumpPressed = false;


}
