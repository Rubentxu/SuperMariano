package com.indignado.games;

import com.ilargia.games.egdx.EGGame;
import com.ilargia.games.egdx.events.game.GameEvent;
import com.ilargia.games.egdx.interfaces.Engine;
import com.ilargia.games.egdx.interfaces.EventBus;
import net.engio.mbassy.listener.Handler;

public class SMGame extends EGGame {

    public SMGame(Engine engine, EventBus bus) {
        super(engine, bus);
        ebus.subscribe(this);
    }


    @Handler
    public void handleNextState(GameEvent gmEvent) {
        if (gmEvent.equals(GameEvent.NEXT_STATE)) {
            //changeState(new PongState());
        }
    }

}