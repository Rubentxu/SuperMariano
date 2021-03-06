package com.indignado.games.smariano.model.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ai.fsm.StateMachine;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.joints.PrismaticJoint;
import com.indignado.games.smariano.BaseGame;
import com.indignado.games.smariano.config.constantes.Env;
import com.indignado.games.smariano.model.entities.CheckPoint;
import com.indignado.games.smariano.model.entities.Hero;
import com.indignado.games.smariano.model.entities.base.Box2DPhysicsObject;
import com.indignado.games.smariano.model.entities.base.Box2DPhysicsObject.GRUPO;
import com.indignado.games.smariano.model.fms.GameState;

import javax.inject.Inject;


public class CheckPointManager extends AbstractWorldManager {
    private StateMachine<BaseGame, GameState> gameStateMachine;


    @Inject
    public CheckPointManager(StateMachine<BaseGame, GameState> gameStateMachine) {
        this.gameStateMachine = gameStateMachine;
    }


    @Override
    public void handleBeginContact(Contact contact) {
        CheckPoint checkPoint = getCheckPoint(contact);
        Hero hero = getHero(contact);
        Gdx.app.log(Env.LOG, "Begin Contact CheckPoint");
        if (hero != null && checkPoint != null) {
            Gdx.app.log(Env.LOG, "Begin Contact CheckPoint TRUE");
            ((PrismaticJoint) checkPoint.getJoint()).enableMotor(true);
        }
    }


    @Override
    public void handleEndContact(Contact contact) {

    }


    @Override
    public void handlePostSolve(Contact contact, ContactImpulse impulse) {

    }


    @Override
    public void handlePreSolve(Contact contact, Manifold oldManifold) {

    }


    @Override
    public boolean handleShouldCollide(Fixture fixtureA, Fixture fixtureB) {
        return false;
    }


    @Override
    public void update(float delta, Box2DPhysicsObject entity) {
        CheckPoint checkPoint = (CheckPoint) entity;
        if (((PrismaticJoint) checkPoint.getJoint()).getJointTranslation() > ((PrismaticJoint) checkPoint.getJoint()).getUpperLimit()
                && !gameStateMachine.getPreviousState().equals(GameState.LEVELWIN))
            gameStateMachine.changeState(GameState.LEVELWIN);

    }


    @Override
    public void dispose() {

    }


    public Hero getHero(Contact contact) {
        Box2DPhysicsObject box2dPhysicsA = (Box2DPhysicsObject) contact.getFixtureA().getUserData();
        Box2DPhysicsObject box2dPhysicsB = (Box2DPhysicsObject) contact.getFixtureB().getUserData();

        if (box2dPhysicsA.getGrupo().equals(GRUPO.HERO)) {
            return (Hero) box2dPhysicsA;
        } else if (box2dPhysicsB.getGrupo().equals(GRUPO.HERO)) {
            return (Hero) box2dPhysicsB;
        } else {
            return null;
        }
    }


    public CheckPoint getCheckPoint(Contact contact) {
        Box2DPhysicsObject box2dPhysicsA = (Box2DPhysicsObject) contact.getFixtureA().getUserData();
        Box2DPhysicsObject box2dPhysicsB = (Box2DPhysicsObject) contact.getFixtureB().getUserData();

        if (box2dPhysicsA.getGrupo().equals(GRUPO.CHECKPOINT)) {
            return (CheckPoint) box2dPhysicsA;
        } else if (box2dPhysicsB.getGrupo().equals(GRUPO.CHECKPOINT)) {
            return (CheckPoint) box2dPhysicsB;
        } else {
            return null;
        }
    }


    private void enableMotor(CheckPoint checkPoint) {
        ((PrismaticJoint) checkPoint.getJoint()).enableMotor(true);
    }


    private void disableMotor(CheckPoint checkPoint) {
        ((PrismaticJoint) checkPoint.getJoint()).enableMotor(false);
    }

}
