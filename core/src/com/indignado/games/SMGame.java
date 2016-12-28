package com.indignado.games;

import com.ilargia.games.egdx.EGGame;
import com.ilargia.games.egdx.events.game.GameEvent;
import net.engio.mbassy.listener.Handler;

public class SMGame extends EGGame {

    public SMGame(SMEngine engine) {
        super(engine);
        ebus.subscribe(this);
    }


    @Handler
    public void handleNextState(GameEvent gmEvent) {
        if (gmEvent.equals(GameEvent.NEXT_STATE)) {
            //changeState(new PongState());
        }
    }

}