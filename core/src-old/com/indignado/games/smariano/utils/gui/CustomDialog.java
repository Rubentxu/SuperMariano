package com.indignado.games.smariano.utils.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ai.fsm.StateMachine;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.indignado.games.smariano.BaseGame;
import com.indignado.games.smariano.config.constantes.Env;
import com.indignado.games.smariano.model.fms.GameState;
import com.indignado.games.smariano.model.services.Styles;

import javax.inject.Inject;

public class CustomDialog extends Window {
    @Inject
    public StateMachine<BaseGame, GameState> gameStateMachine;

    private Skin skin;

    @Inject
    public CustomDialog(String title, Styles styles) {
        super(title, styles.skin);
        this.skin = styles.skin;
        initialize();
    }

    private void initialize() {

        padTop(60);
        //getButtonTable().defaults().height(60);
        setModal(true);
        setMovable(false);
    }

    public CustomDialog addButton(String buttonText, InputListener listener) {
        TextButton button = new TextButton(buttonText, this.skin);
        button.addListener(listener);
        add(button);
        return this;
    }

    @Override
    public float getPrefWidth() {
        return 480f;
    }

    @Override
    public float getPrefHeight() {
        return 240f;
    }


    protected void result(Object object) {
        gameStateMachine.changeState(GameState.RUNNING);
        Gdx.app.log(Env.LOG, "La respuesta a la ventana de Dialogo es: " + object + "Tipo clase " + object.getClass());
        if (object instanceof Boolean && object.equals(true)) {
            Gdx.app.exit();
        } else if (object instanceof Boolean && object.equals(false)) {
            Gdx.app.log(Env.LOG, "Se pulso en continuar la partida: ");

            this.remove();
            this.setVisible(false);
            Gdx.app.log(Env.LOG, "La ventana de dialogo se cerro?... " + remove());
        }
    }

}