package com.indignado.games;

import com.badlogic.gdx.math.Interpolation;
import com.ilargia.games.egdx.base.BaseGame;
import com.ilargia.games.egdx.api.EventBus;
import com.ilargia.games.egdx.api.StateTransition;
import com.ilargia.games.egdx.base.commands.ChangeStateCommand;
import com.ilargia.games.egdx.base.managers.BaseAssetsManager;
import com.ilargia.games.egdx.transitions.FadeTransition;
import com.ilargia.games.egdx.transitions.SlideTransition;
import com.indignado.games.manager.SMGUIManager;
import com.indignado.games.states.menu.MenuState;
import com.indignado.games.states.options.OptionsState;
import com.indignado.games.states.score.ScoresState;
import net.engio.mbassy.listener.Handler;

public class SMGame extends BaseGame<SMEngine> {
    private MenuState menuState;
    private SMGUIManager skinManager;
    private FadeTransition fadeTransition;
    private OptionsState optionState;
    private ScoresState scoresState;
    private StateTransition slideTransition;


    public SMGame(SMEngine engine, EventBus bus) {
        super(engine, bus);
        ebus.subscribe(this);
        skinManager = new SMGUIManager(_engine.getManager(BaseAssetsManager.class));
        _engine.addManager(skinManager);

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

    public MenuState getMenuState() {
        if (menuState == null) {
            menuState = new MenuState(_engine);
        }
        return menuState;
    }

    public FadeTransition getFadeTransition() {
        if (fadeTransition == null) {
            fadeTransition = new FadeTransition(1, _engine.batch);
        }
        return fadeTransition;
    }

    public OptionsState getOptionState() {
        if (optionState == null) {
            optionState = new OptionsState(_engine);
        }
        return optionState;

    }

    public ScoresState getScoresState() {
        if (scoresState == null) {
            scoresState = new ScoresState(_engine);
        }
        return scoresState;
    }

    public StateTransition getSlideTransition() {
        if (slideTransition == null) {
            slideTransition = new SlideTransition(1, SlideTransition.LEFT, false, Interpolation.bounce, _engine.batch);
        }
        return slideTransition;

    }
}