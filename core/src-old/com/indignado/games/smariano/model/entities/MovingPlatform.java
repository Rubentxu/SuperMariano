package com.indignado.games.smariano.model.entities;


import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.utils.Disposable;
import com.indignado.games.smariano.model.entities.base.Box2DPhysicsObject;
import com.indignado.games.smariano.model.entities.base.Path;
import com.indignado.games.smariano.model.entities.base.State;

import java.util.HashSet;

public class MovingPlatform extends Platform implements Disposable {


    public Boolean enabled = true;
    public Boolean waitForPassenger = false;
    private Vector2 pVelocity, start;
    private float distance = 0;
    private Boolean forward = false;
    private HashSet<Box2DPhysicsObject> passengers = new HashSet<Box2DPhysicsObject>();
    private Path path;

    public MovingPlatform(Body body, Vector2 pVelocity) {
        super("Platform", Box2DPhysicsObject.GRUPO.MOVING_PLATFORM, body);
        this.pVelocity = pVelocity;
        //this.maxDist=0;

    }

    public MovingPlatform(String nombre, Box2DPhysicsObject.GRUPO grupo, Body body, float dstX, float dstY, float speed) {
        super(nombre, grupo, body);
        path = new Path(speed);
        Vector2 pos = body.getPosition().cpy();
        path.addPoint(pos);
        path.addPoint(new Vector2(pos.x + dstX, pos.y + dstY));
        this.start = body.getPosition().cpy();


    }

    public Boolean getForward() {
        return forward;
    }

    public void setForward(Boolean forward) {
        this.forward = forward;
    }

    public HashSet<Box2DPhysicsObject> getPassengers() {
        return passengers;
    }

    public void setPassengers(HashSet<Box2DPhysicsObject> passengers) {
        this.passengers = passengers;
    }

    public Vector2 getpVelocity() {
        return pVelocity;
    }

    public float getDistance() {
        return distance;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }

    public Path getPath() {
        return path;
    }

    public void setPath(Path path) {
        this.path = path;
    }

    @Override
    public void dispose() {
        super.dispose();
        pVelocity = null;
        start = null;
        passengers = null;
        path = null;
    }


    public enum StateMovingPlatform implements State {
        ENABLED, DISABLED;

        protected float stateTimeMin;

        StateMovingPlatform() {
            this.stateTimeMin = 0.1f;
        }

        StateMovingPlatform(float stateTimeMin) {
            this.stateTimeMin = stateTimeMin;
        }

        StateMovingPlatform(Box2DPhysicsObject.BaseState state) {
            this.stateTimeMin = state.getStateTimeMin();
        }

        @Override
        public float getStateTimeMin() {
            return this.stateTimeMin;
        }
    }
}
