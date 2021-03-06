package com.indignado.games.smariano.model.entities;

import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.utils.Disposable;
import com.indignado.games.smariano.model.entities.base.Box2DPhysicsObject;
import com.indignado.games.smariano.model.entities.base.State;

import java.util.HashSet;


public class Hero extends Box2DPhysicsObject implements Disposable {


    public final static float MAX_VELOCITY = 4f;
    public final static float JUMP_FORCE = 14.5f;
    boolean facingLeft = true;
    // States
    private StatePos statePos = StatePos.ONGROUND;
    private HashSet<Fixture> grounContacts;
    private Fixture heroPhysicsFixture;
    private Fixture heroSensorFixture;
    private ParticleEffect particleEffectDust;
    private ParticleEffect particleEffectContact;

    public Hero(String name, Body body) {
        super(name, GRUPO.HERO, body);
        setGrounContacts(new HashSet<Fixture>());
        setState(StateHero.IDLE);
    }

    public void velocityLimit() {
        Vector2 vel = this.getBodyA().getLinearVelocity();

        if (Math.abs(vel.x) > this.MAX_VELOCITY) {
            vel.x = Math.signum(vel.x) * this.MAX_VELOCITY;
            this.setVelocity(new Vector2(vel.x, vel.y));
        } else if (Math.abs(vel.y) > this.MAX_VELOCITY * 2) {
            vel.y = Math.signum(vel.y) * this.MAX_VELOCITY * 2;
            this.setVelocity(new Vector2(vel.x, vel.y));
        }
    }

    public boolean isFacingLeft() {
        return facingLeft;
    }

    public void setFacingLeft(boolean facingLeft) {
        this.facingLeft = facingLeft;
    }

    public Vector2 getVelocity() {
        return super.getBodyA().getLinearVelocity();
    }

    public void setVelocity(Vector2 velocity) {
        super.getBodyA().setLinearVelocity(velocity);
    }

    public Fixture getHeroPhysicsFixture() {
        return heroPhysicsFixture;
    }

    public void setHeroPhysicsFixture(Fixture heroPhysicsFixture) {
        this.heroPhysicsFixture = heroPhysicsFixture;
    }

    public Fixture getHeroSensorFixture() {
        return heroSensorFixture;
    }

    public void setHeroSensorFixture(Fixture heroSensorFixture) {
        this.heroSensorFixture = heroSensorFixture;
    }

    public HashSet<Fixture> getGrounContacts() {
        return grounContacts;
    }

    public void setGrounContacts(HashSet<Fixture> grounContacts) {
        this.grounContacts = grounContacts;
    }

    public StatePos getStatePos() {
        return statePos;
    }

    public void setStatePos(StatePos statePos) {
        this.statePos = statePos;
    }

    public ParticleEffect getParticleEffectDust() {
        return particleEffectDust;
    }

    public void setParticleEffectDust(ParticleEffect particleEffectDust) {
        this.particleEffectDust = particleEffectDust;
    }

    public ParticleEffect getParticleEffectContact() {
        return particleEffectContact;
    }

    public void setParticleEffectContact(ParticleEffect particleEffectContact) {
        this.particleEffectContact = particleEffectContact;
    }

    @Override
    public void dispose() {
        super.dispose();
        grounContacts = null;
        heroPhysicsFixture = null;
        heroSensorFixture = null;
    }

    public enum StateHero implements State {
        IDLE, WALKING, JUMPING, DYING, FALL, SWIMMING, PROPULSION, WIN;

        protected float stateTimeMin;

        StateHero() {
            this.stateTimeMin = 0.1f;
        }

        StateHero(float stateTimeMin) {
            this.stateTimeMin = stateTimeMin;
        }

        StateHero(BaseState state) {
            this.stateTimeMin = state.getStateTimeMin();
        }

        @Override
        public float getStateTimeMin() {
            return this.stateTimeMin;
        }
    }


    public enum StatePos {ONGROUND, INWATER, ONAIR}

}


