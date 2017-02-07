package com.indignado.games.states.game.system;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.ilargia.games.entitas.api.system.IInitializeSystem;
import com.indignado.games.states.game.component.game.PlayerInputController;
import com.indignado.games.states.game.gen.GameContext;
import com.indignado.games.states.game.gen.GameEntity;


public class InputsControllerSystem extends InputAdapter implements IInitializeSystem {
    private GameContext context;
    private GameEntity player;

    public InputsControllerSystem(GameContext context) {
        this.context = context;
    }

    @Override
    public void initialize() {
        Gdx.input.setInputProcessor(this);
        player = context.getPlayerInputControllerEntity();

    }


    @Override
    public boolean keyDown(int keycode) {
        PlayerInputController stateController = player.getPlayerInputController();
        switch (keycode) {
            case Input.Keys.LEFT:
                player.replacePlayerInputController(true, false, stateController.jumpPressed);
                break;
            case Input.Keys.RIGHT:
                player.replacePlayerInputController(false, true, stateController.jumpPressed);
                break;
            case Input.Keys.Z:
                player.replacePlayerInputController(stateController.leftPressed,
                        stateController.rightPressed, true);
                break;
            default:

        }

        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        PlayerInputController stateController = player.getPlayerInputController();
        switch (keycode) {
            case Input.Keys.LEFT:
                player.replacePlayerInputController(false, false, stateController.jumpPressed);
                break;
            case Input.Keys.RIGHT:
                player.replacePlayerInputController(false, false, stateController.jumpPressed);
                break;
            case Input.Keys.Z:
                player.replacePlayerInputController(stateController.leftPressed,
                        stateController.rightPressed, true);
                break;
            default:

        }

        return true;
    }

}
