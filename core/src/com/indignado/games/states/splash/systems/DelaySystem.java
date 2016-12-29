package com.indignado.games.states.splash.systems;

import com.ilargia.games.egdx.EGGame;
import com.ilargia.games.egdx.events.game.GameEvent;
import com.ilargia.games.entitas.Group;
import com.ilargia.games.entitas.interfaces.IExecuteSystem;
import com.ilargia.games.entitas.interfaces.ISetPool;
import com.indignado.games.states.splash.components.Delay;
import com.indignado.games.states.splash.gen.SplashEntity;
import com.indignado.games.states.splash.gen.SplashMatcher;
import com.indignado.games.states.splash.gen.SplashPool;


public class DelaySystem implements IExecuteSystem, ISetPool<SplashPool> {

    private Group<SplashEntity> _group;

    @Override
    public void setPool(SplashPool pool) {
        _group = pool.getGroup(SplashMatcher.Delay());

    }

    @Override
    public void execute(float deltatime) {

        for (SplashEntity e : _group.getEntities()) {
            Delay delay = e.getDelay();
            delay.time += deltatime;
            if (delay.time > delay.duration) {
                EGGame.ebus.post(GameEvent.NEXT_STATE);
                delay.time = 0;
            }

        }
    }


}


