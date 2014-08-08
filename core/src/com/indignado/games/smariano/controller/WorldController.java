package com.indignado.games.smariano.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Disposable;
import com.indignado.games.smariano.BaseGame;
import com.indignado.games.smariano.config.constantes.Env;
import com.indignado.games.smariano.model.entities.World;
import com.indignado.games.smariano.model.entities.base.Box2DPhysicsObject;
import com.indignado.games.smariano.model.entities.base.Box2DPhysicsObject.GRUPO;
import com.indignado.games.smariano.model.managers.*;
import com.indignado.games.smariano.model.services.AudioService;
import com.indignado.games.smariano.model.services.interfaces.IProfileService;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;


public class WorldController implements ContactListener, ContactFilter,Disposable {

    @Inject
    protected World world;
    @Inject
    protected HeroManager heroManager;
    @Inject
    protected PlatformManager platformManager;
    @Inject
    protected WaterManager waterManager;
    @Inject
    protected EnemyManager enemyManager;
    @Inject
    protected ItemsManager itemsManager;
    @Inject
    protected CheckPointManager checkPointManager;
    protected List<Box2DPhysicsObject> destroy=new ArrayList<Box2DPhysicsObject>();
    @Inject
    IProfileService profileService;
    @Inject
    AudioService audioService;

    public static java.util.Map<Keys, Boolean> keys = new java.util.HashMap<Keys, Boolean>();

    static {
        keys.put(WorldController.Keys.LEFT, false);
        keys.put(WorldController.Keys.RIGHT, false);
        keys.put(WorldController.Keys.JUMP, false);
        keys.put(WorldController.Keys.FIRE, false);
    }

    public static enum Keys {
        LEFT, RIGHT, JUMP, FIRE
    }


    public WorldController() {
        BaseGame.objectGraph.inject(this);
        world.getPhysics().setContactListener(this);
        heroManager = new HeroManager();
        platformManager = new PlatformManager();
        waterManager = new WaterManager();
        enemyManager = new EnemyManager();
        itemsManager= new ItemsManager();
        checkPointManager = new CheckPointManager();

        itemsManager.addObserver(profileService);
        itemsManager.addObserver(audioService);
        heroManager.addObserver(profileService);
        heroManager.addObserver(audioService);

    }

    public void leftPressed() {
        keys.get(keys.put(WorldController.Keys.LEFT, true));
    }

    public void rightPressed() {
        keys.get(keys.put(WorldController.Keys.RIGHT, true));
    }

    public void jumpPressed() {
        keys.get(keys.put(WorldController.Keys.JUMP, true));
    }

    public void firePressed() {
        keys.get(keys.put(WorldController.Keys.FIRE, true));
    }

    public void leftReleased() {
        keys.get(keys.put(WorldController.Keys.LEFT, false));
    }

    public void rightReleased() {
        keys.get(keys.put(WorldController.Keys.RIGHT, false));
    }

    public void jumpReleased() {
        keys.get(keys.put(WorldController.Keys.JUMP, false));
        //jumpingPressed = false;
    }

    public void fireReleased() {
        keys.get(keys.put(WorldController.Keys.FIRE, false));
    }

    /**
     * The main update method *
     */
    public void update(float delta) {
        for(Box2DPhysicsObject e: world.getEntities()) {
            if (e.getState().equals(Box2DPhysicsObject.BaseState.DESTROY)){
                destroy.add(e);
            } else {
                AbstractWorldManager manager =getManager(e.getGrupo());
                if(manager!=null) manager.update(delta,e);
            }
        }
        for(Box2DPhysicsObject d:destroy){
            Gdx.app.log(Env.LOG,"Destroy entity :" + d.getGrupo());
            world.destroyEntity(d);
        }
        destroy.clear();

        world.getPhysics().step(delta, Env.VELOCITY_ITERATIONS, Env.POSITION_ITERATIONS);
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {
        contact.resetFriction();
        AbstractWorldManager managerA=getManager(((Box2DPhysicsObject) contact.getFixtureA().getUserData()).getGrupo());
        AbstractWorldManager managerB=getManager(((Box2DPhysicsObject) contact.getFixtureB().getUserData()).getGrupo());

        if(managerA!=null) managerA.handlePreSolve(contact, oldManifold);
        if(managerB!=null) managerB.handlePreSolve(contact, oldManifold);
    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {
        AbstractWorldManager managerA=getManager(((Box2DPhysicsObject) contact.getFixtureA().getUserData()).getGrupo());
        AbstractWorldManager managerB=getManager(((Box2DPhysicsObject) contact.getFixtureB().getUserData()).getGrupo());

        if(managerA!=null) managerA.handlePostSolve(contact, impulse);
        if(managerB!=null) managerB.handlePostSolve(contact, impulse);

    }

    public AbstractWorldManager getManager(GRUPO grupo){
        switch (grupo){
            case ENEMY:
                return enemyManager;

            case ITEMS:
                return itemsManager;
            case FLUID:
                return waterManager;
            case HERO:
                return heroManager;
            case PLATFORM:
                break;
            case MOVING_PLATFORM:
                return platformManager;
            case CHECKPOINT:
                return checkPointManager;
            case SENSOR:
                break;
            case STATIC:
                break;

            default:
                break;
        }
        return null;
    }

    @Override
    public void beginContact(Contact contact) {
        AbstractWorldManager managerA=getManager(((Box2DPhysicsObject) contact.getFixtureA().getUserData()).getGrupo());
        AbstractWorldManager managerB=getManager(((Box2DPhysicsObject) contact.getFixtureB().getUserData()).getGrupo());

        if(managerA!=null) managerA.handleBeginContact(contact);
        if(managerB!=null) managerB.handleBeginContact(contact);

    }

    @Override
    public void endContact(Contact contact) {
        if (contact.getFixtureA() == null || contact.getFixtureB()==null ) return;

        AbstractWorldManager managerA=getManager(((Box2DPhysicsObject) contact.getFixtureA().getUserData()).getGrupo());
        AbstractWorldManager managerB=getManager(((Box2DPhysicsObject) contact.getFixtureB().getUserData()).getGrupo());

        if(managerA!=null) managerA.handleEndContact(contact);
        if(managerB!=null) managerB.handleEndContact(contact);

    }

    @Override
    public boolean shouldCollide(Fixture fixtureA, Fixture fixtureB) {
        return true;
    }


    @Override
    public void dispose() {
        heroManager=null;
        platformManager=null;
        enemyManager=null;
        waterManager=null;
        checkPointManager=null;
    }
}
