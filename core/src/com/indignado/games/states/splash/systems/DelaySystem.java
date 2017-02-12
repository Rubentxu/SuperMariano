package com.indignado.games.states.splash.systems;

import com.ilargia.games.egdx.base.commands.ChangeStateCommand;
import com.ilargia.games.entitas.api.system.IExecuteSystem;
import com.ilargia.games.entitas.group.Group;
import com.indignado.games.SMGame;
import com.indignado.games.states.splash.components.Delay;
import com.indignado.games.states.splash.gen.SplashContext;
import com.indignado.games.states.splash.gen.SplashEntity;
import com.indignado.games.states.splash.gen.SplashMatcher;


public class DelaySystem implements IExecuteSystem {

    private Group<SplashEntity> _group;

    public DelaySystem(SplashContext context) {
        _group = context.getGroup(SplashMatcher.Delay());

    }

    @Override
    public void execute(float deltatime) {
        for (SplashEntity e : _group.getEntities()) {
            Delay delay = e.getDelay();
            delay.time += deltatime;
            if (delay.time > delay.duration) {
                SMGame.ebus.post((ChangeStateCommand<SMGame>) (nameState, game) -> {
                    game.changeState(game.getMenuState(), game.getFadeTransition());
                });
                delay.time = 0;
            }

        }
    }


}


