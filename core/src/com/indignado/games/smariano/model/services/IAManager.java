package com.indignado.games.smariano.model.services;

import com.indignado.games.smariano.model.managers.StateObserver;
import com.indignado.games.smariano.model.entities.base.Box2DPhysicsObject;
import com.indignado.games.smariano.model.entities.base.State;


public class IAManager implements StateObserver {
    @Override
    public void onNotify(State state, Box2DPhysicsObject entity) {

    }

    @Override
    public void onNotifyStateTimeLimit(State state, Box2DPhysicsObject entity, float time) {

    }
}
