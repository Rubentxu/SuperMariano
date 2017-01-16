package com.indignado.games.states.game.components;

import com.ilargia.games.entitas.codeGenerator.Component;
import com.ilargia.games.entitas.interfaces.IComponent;

@Component(pools = {"SMCore"}, isSingleEntity = true)
public class PlayerAnimationState implements IComponent {
    public enum AnimationState {
        IDLE, WALKING, JUMPING, DYING, FALL, SWIMMING, PROPULSION, WIN;
    }
    public AnimationState currentAnimationState;


}