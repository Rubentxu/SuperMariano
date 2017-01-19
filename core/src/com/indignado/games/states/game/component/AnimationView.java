package com.indignado.games.states.game.component;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.ilargia.games.entitas.codeGenerator.Component;
import com.ilargia.games.entitas.interfaces.IComponent;
import java.util.Map;

@Component(pools = {"SMCore"})
public class AnimationView implements IComponent {
    public Map<Integer,Animation> animations;

    public AnimationView(Map<Integer, Animation> animations) {
        this.animations = animations;
    }


}
