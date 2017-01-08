package com.indignado.games;

import com.ilargia.games.egdx.base.BaseGame;
import com.ilargia.games.egdx.base.interfaces.EventBus;
import com.ilargia.games.egdx.events.game.GameEvent;
import com.ilargia.games.egdx.managers.EGAssetsManager;
import com.ilargia.games.entitas.Systems;
import com.indignado.games.states.menu.MenuState;
import net.engio.mbassy.listener.Handler;

public class SMGame extends BaseGame<SMEngine> {

    public SMGame(SMEngine engine, EventBus bus) {
        super(engine, bus);
        ebus.subscribe(this);
    }


    @Handler
    public void handleNextState(GameEvent gmEvent) {
        if (gmEvent.equals(GameEvent.NEXT_STATE)) {
            EGAssetsManager assetManager = _engine.getManager(EGAssetsManager.class);
            Styles styles = new Styles(assetManager);
            changeState(new MenuState(styles, _engine));
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