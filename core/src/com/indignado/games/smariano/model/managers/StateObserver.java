package com.indignado.games.smariano.model.managers;


import com.indignado.games.smariano.model.entities.base.Box2DPhysicsObject;
import com.indignado.games.smariano.model.entities.base.State;

public interface StateObserver {
    public void onNotify(State state, Box2DPhysicsObject entity);
    public void onNotifyStateTimeLimit(State state, Box2DPhysicsObject entity, float time);
}
