package com.indignado.games.states.game.component.game;

import com.ilargia.games.entitas.api.IComponent;
import com.ilargia.games.entitas.codeGenerator.Component;
import com.indignado.games.states.game.data.StateCharacter;

@Component(pools = {"Game"})
public class Character implements IComponent {
    public StateCharacter currentState;
    public boolean facingLeft;

    public Character(StateCharacter currentState, boolean facingLeft) {
        this.currentState = currentState;
        this.facingLeft = facingLeft;

    }

}
