package com.indignado.games.states.game.component;

import com.ilargia.games.entitas.api.IComponent;
import com.ilargia.games.entitas.codeGenerator.Component;
import com.indignado.games.states.game.data.StateCharacter;

@Component(pools = {"Game"})
public class Character implements IComponent {
    public StateCharacter currentState;
    public float maxVelocity;
    public float jumpForce;
    public boolean isfacingLeft;
    public boolean onGround;

    public Character(StateCharacter currentState, float maxVelocity, float jumpForce, boolean facingLeft, boolean onGround) {
        this.currentState = currentState;
        this.maxVelocity = maxVelocity;
        this.jumpForce = jumpForce;
        this.isfacingLeft = facingLeft;
        this.onGround = onGround;
    }

}
