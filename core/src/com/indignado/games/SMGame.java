package com.indignado.games;

import com.ilargia.games.egdx.base.BaseGame;
import com.ilargia.games.egdx.base.interfaces.EventBus;
import com.ilargia.games.egdx.base.interfaces.commands.ChangeStateCommand;
import com.ilargia.games.egdx.base.interfaces.events.GameEvent;
import com.ilargia.games.egdx.managers.EGAssetsManager;
import com.ilargia.games.egdx.transitions.FadeTransition;
import com.indignado.games.states.menu.MenuState;
import net.engio.mbassy.listener.Handler;

public class SMGame extends BaseGame<SMEngine> {

    public SMGame(SMEngine engine, EventBus bus) {
        super(engine, bus);
        ebus.subscribe(this);
    }

    @Handler
    public void handleChangeState(ChangeStateCommand command) {
        command.change("GameState", this);
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