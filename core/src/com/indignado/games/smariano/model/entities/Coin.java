package com.indignado.games.smariano.model.entities;

import com.badlogic.gdx.physics.box2d.Body;
import com.indignado.games.smariano.model.entities.base.State;

public class Coin extends Item {

    public Coin(String nombre, GRUPO grupo, TYPE tipo, Body body, int value) {
        super(nombre, grupo, tipo, body, value);
        setState(BaseState.DEFAULT);
    }

    public enum StateCoin implements State {
        COLLECTED(0.5f);
        protected float stateTimeLimit;

        StateCoin() {
            this.stateTimeLimit = 0.1f;
        }

        StateCoin(float stateTimeLimit) {
            this.stateTimeLimit = stateTimeLimit;
        }

        StateCoin(BaseState state) {
            this.stateTimeLimit = state.getStateTimeMin();
        }

        @Override
        public float getStateTimeMin() {
            return this.stateTimeLimit;
        }
    }


}
