package com.indignado.games.smariano.model.managers;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.utils.Disposable;
import com.indignado.games.smariano.config.constantes.Env;
import com.indignado.games.smariano.model.entities.base.Box2DPhysicsObject;
import com.indignado.games.smariano.model.entities.base.State;

import java.util.ArrayList;
import java.util.List;


public abstract class AbstractWorldManager implements Disposable {


    private List<StateObserver> observers;

    public AbstractWorldManager() {
        observers = new ArrayList<StateObserver>();
    }

    public abstract void handleBeginContact(Contact contact);

    public abstract void handleEndContact(Contact contact);

    public abstract void handlePostSolve(Contact contact, ContactImpulse impulse);

    public abstract void handlePreSolve(Contact contact, Manifold oldManifold);

    public abstract boolean handleShouldCollide(Fixture fixtureA, Fixture fixtureB);

    public abstract void update(float delta, Box2DPhysicsObject entity);

    public void addObserver(StateObserver o) {
        if (o == null)
            throw new NullPointerException();
        if (!observers.contains(o)) {
            observers.add(o);
        }
    }


    public void deleteObserver(StateObserver o) {
        observers.remove(o);
    }

    public void notifyObservers(State state, Box2DPhysicsObject entity) {
        for (StateObserver observer : observers) {
            observer.onNotify(state, entity);
        }
    }

    public void notifyObservers(State state, Box2DPhysicsObject entity, float stateTime) {
        Gdx.app.debug(Env.LOG, "NotifyStateTimeLimit Character " + state + " SplashEntity " + entity.getGrupo());
        for (StateObserver observer : observers) {
            observer.onNotifyStateTimeLimit(state, entity, stateTime);
        }
    }


    public synchronized void deleteObservers() {
        observers.clear();
    }

    public int countObservers() {
        return observers.size();
    }


}
