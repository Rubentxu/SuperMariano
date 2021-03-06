package com.indignado.games.smariano.model.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.indignado.games.smariano.config.constantes.Env;
import com.indignado.games.smariano.model.entities.Hero;
import com.indignado.games.smariano.model.entities.Water;
import com.indignado.games.smariano.model.entities.base.Box2DPhysicsObject;
import com.indignado.games.smariano.model.entities.base.Box2DPhysicsObject.GRUPO;
import com.indignado.games.smariano.utils.physics.BuoyancyUtils;


public class WaterManager extends AbstractWorldManager {

    @Override
    public void update(float delta, Box2DPhysicsObject entity) {
        Water w = (Water) entity;
        for (int i = 0; i < w.m_bodyList.size; i++) {
            Array<Fixture> fixtureList = w.m_bodyList.get(i).getFixtureList();
            for (int j = 0; j < fixtureList.size; j++) {
                Fixture fixture = fixtureList.get(j);
                if (!fixture.isSensor()) {
                    if (isHero(fixture) && ((Hero) fixture.getUserData()).getStatePos().equals(Hero.StatePos.ONAIR))
                        ((Hero) fixture.getUserData()).setStatePos(Hero.StatePos.INWATER);
                    ApplyToFixture(fixture, w);
                }
            }
        }

    }


    @Override
    public void handleBeginContact(Contact contact) {
        Water w = getWater(contact);
        Body b = getSubmergedBody(contact).getBodyA();
        w.addBody(b);
        if (isHero(b)) {
            ((Hero) b.getUserData()).setStatePos(Hero.StatePos.INWATER);
        }

    }

    public Boolean isHero(Body b) {
        return ((Box2DPhysicsObject) b.getUserData()).getGrupo().equals(GRUPO.HERO);
    }

    public Boolean isHero(Fixture b) {
        return ((Box2DPhysicsObject) b.getUserData()).getGrupo().equals(GRUPO.HERO);
    }

    @Override
    public void handleEndContact(Contact contact) {
        Water w = getWater(contact);
        Body b = getSubmergedBody(contact).getBodyA();
        w.removeBody(b);
        if (((Box2DPhysicsObject) b.getUserData()).getGrupo().equals(GRUPO.HERO)) {
            ((Hero) b.getUserData()).setStatePos(Hero.StatePos.ONAIR);
        }

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

    public Box2DPhysicsObject getSubmergedBody(Contact contact) {
        Box2DPhysicsObject box2dPhysicsA = (Box2DPhysicsObject) contact.getFixtureA().getUserData();
        Box2DPhysicsObject box2dPhysicsB = (Box2DPhysicsObject) contact.getFixtureB().getUserData();

        if (!box2dPhysicsA.getGrupo().equals(GRUPO.FLUID)) {
            return box2dPhysicsA;
        } else {
            return box2dPhysicsB;
        }
    }

    public Water getWater(Contact contact) {
        Box2DPhysicsObject box2dPhysicsA = (Box2DPhysicsObject) contact.getFixtureA().getUserData();
        Box2DPhysicsObject box2dPhysicsB = (Box2DPhysicsObject) contact.getFixtureB().getUserData();

        if (box2dPhysicsA.getGrupo().equals(GRUPO.FLUID)) {
            return (Water) box2dPhysicsA;
        } else {
            return (Water) box2dPhysicsB;
        }
    }

    private boolean ApplyToFixture(Fixture f, Water w) {
        float shapeDensity = w.mUseDensity ? f.getDensity() : w.mFluidDensity;

        // don't bother with buoyancy on sensors or fixtures with no density
        if (f.isSensor() || (shapeDensity == 0)) {
            return false;
        }
        Body body = f.getBody();
        w.mAreac.set(Vector2.Zero);
        w.mMassc.set(Vector2.Zero);
        float area = 0;

        // Get shape for displacement area calculations
        Shape shape = f.getShape();

        w.mSC.set(Vector2.Zero);
        float sarea;
        switch (shape.getType()) {
            case Circle:
                Gdx.app.log(Env.LOG, "Apply water impulse....Circle");
                sarea = BuoyancyUtils.ComputeSubmergedArea((CircleShape) shape, w.mSurfaceNormal, w.mSurfaceHeight, body.getTransform(), w.mSC);
                break;

            case Chain:
                Gdx.app.log(Env.LOG, "Apply water impulse....Chain");
                sarea = BuoyancyUtils.ComputeSubmergedArea((ChainShape) shape, w.mSurfaceNormal, w.mSurfaceHeight, body.getTransform(), w.mSC);
                break;

            case Edge:
                Gdx.app.log(Env.LOG, "Apply water impulse....Edge");
                sarea = BuoyancyUtils.ComputeSubmergedArea((EdgeShape) shape, w.mSurfaceNormal, w.mSurfaceHeight, body.getTransform(), w.mSC);
                break;

            case Polygon:

                sarea = BuoyancyUtils.ComputeSubmergedArea((PolygonShape) shape, w.mSurfaceNormal, w.mSurfaceHeight, body.getTransform(), w.mSC);

                break;

            default:
                sarea = 2;
                break;
        }

        area += sarea;
        w.mAreac.x += sarea * w.mSC.x;
        w.mAreac.y += sarea * w.mSC.y;
        float mass = sarea * shapeDensity;
        w.mMassc.x += sarea * w.mSC.x * shapeDensity;
        w.mMassc.y += sarea * w.mSC.y * shapeDensity;

        w.mAreac.x /= area;
        w.mAreac.y /= area;
        w.mMassc.x /= mass;
        w.mMassc.y /= mass;
        if (area < Float.MIN_VALUE) {
            return false;
        }

        if (w.DEBUG_BUOYANCY) {
            // Run debug w/HCR to see the effects of different fluid densities / linear drag
            w.mFluidDensity = 0.6f;
            w.mLinearDrag = .5f;
            w.mAngularDrag = 1;
        }

        // buoyancy force.
        w.mTmp.set(w.mGravity).scl(-w.mFluidDensity * area);

        body.applyForce(w.mTmp, w.mMassc, true); // multiply by -density to invert gravity

        // linear drag.
        w.mTmp.set(body.getLinearVelocityFromWorldPoint(w.mAreac).sub(w.mFluidVelocity).scl(-w.mLinearDrag * area));

        body.applyForce(w.mTmp, w.mAreac, true);

        // angular drag.
        float bodyMass = body.getMass();
        if (bodyMass < 1) // prevent a huge torque from being generated...
        {
            bodyMass = 1;
        }
        float torque = -body.getInertia() / bodyMass * area * body.getAngularVelocity() * w.mAngularDrag;
        body.applyTorque(torque, true);
        return true;
    }

    @Override
    public void dispose() {

    }
}
