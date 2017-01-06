package com.indignado.games;

import com.ilargia.games.egdx.base.BaseGame;
import com.ilargia.games.egdx.base.interfaces.EventBus;
import com.ilargia.games.egdx.events.game.GameEvent;
import com.ilargia.games.entitas.Systems;
import net.engio.mbassy.listener.Handler;

public class SMGame extends BaseGame<SMEngine> {

    public SMGame(SMEngine engine, Systems systems, EventBus bus) {
        super(engine, bus);
        ebus.subscribe(this);
    }


    @Handler
    public void handleNextState(GameEvent gmEvent) {
        if (gmEvent.equals(GameEvent.NEXT_STATE)) {
            //changeState(new PongState());
        }
    }

    @Override
    public void init() {

    }

    @Override
    public boolean isRunning() {
        return false;
    }

    @Override
    public int getErrorState() {
        return 0;
    }
}