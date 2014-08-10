package com.indignado.games.smariano.model.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import com.indignado.games.smariano.BaseGame;
import com.indignado.games.smariano.model.entities.base.Box2DPhysicsObject;
import com.indignado.games.smariano.model.factories.Box2dObjectFactory;
import com.indignado.games.smariano.model.services.interfaces.ILevelService;
import com.indignado.games.smariano.model.services.interfaces.IResourcesService;
import com.indignado.games.smariano.utils.dermetfan.box2d.Box2DMapObjectParser;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class World implements Disposable {
    private TiledMap map;
    private com.badlogic.gdx.physics.box2d.World physics;
    private ArrayList<Box2DPhysicsObject> entities;
    private Hero hero;
    private Array<Body> bodiesFlaggedDestroy = new Array<Body>();
    private Texture background_03;
    private Texture background_02;
    private Texture background_01;

    private Box2dObjectFactory box2dObjectFactory;
    private Box2DMapObjectParser parser;
    IResourcesService resourceService;
    ILevelService levelService;


    @Inject
    public World(com.badlogic.gdx.physics.box2d.World physics,BaseGame game) {
        this.physics= physics;
        this.levelService= game.levelService;
        this.resourceService= game.resourcesService;
        entities = new ArrayList<Box2DPhysicsObject>();
        createDreamsWorld();

    }


    public void createDreamsWorld() {
        Level level= levelService.getCurrentLevel();
        Collections.sort(entities, new Comparator<Box2DPhysicsObject>() {
            public int compare(Box2DPhysicsObject one, Box2DPhysicsObject two) {
                return one.getGrupo().compareTo(two.getGrupo());
            }
        });
        box2dObjectFactory= new Box2dObjectFactory(physics,this,resourceService);
        map = resourceService.getAssetManager().get(level.getMap());
        parser = new Box2DMapObjectParser(this,box2dObjectFactory);
        System.out.println(getParser().getHierarchy(map));
        parser.load(getPhysics(), map);

        background_01 = resourceService.getAssetManager().get(level.getBackground_01());
        background_02 = resourceService.getAssetManager().get(level.getBackground_02());
        background_03 = resourceService.getAssetManager().get(level.getBackground_03());

    }


    public void destroyEntity(Box2DPhysicsObject data) {

        if (data != null) {
            data.getBodyA().setUserData(null);
            physics.destroyBody(data.getBodyA());
            entities.remove(data);
            data.setBodyA(null);
            data = null;
        }

    }

    @Override
    public void dispose() {
        map.dispose();
        map=null;
        parser=null;
        physics.dispose();
        physics = null;
        background_01 = null;
        bodiesFlaggedDestroy = null;
        clearWorld();

    }


    public void clearWorld(){
        for (Box2DPhysicsObject e : entities) {
            e.dispose();
            e = null;
        }
        entities.clear();
        entities = new ArrayList<Box2DPhysicsObject>();
        hero=null;
        physics.dispose();
        physics=null;
        bodiesFlaggedDestroy.clear();


    }


    public TiledMap getMap() {
        return map;
    }

    public com.badlogic.gdx.physics.box2d.World getPhysics() {
        return physics;
    }


    public Box2DMapObjectParser getParser() {
        return parser;
    }

    public void removeParser() {
        parser = null;
    }

    public Texture getBackground_01() {
        return background_01;
    }

    public void addBodiesFlaggedDestroy(Body body) {
        bodiesFlaggedDestroy.add(body);
    }

    public Array<Body> getBodiesFlaggedDestroy() {
        return bodiesFlaggedDestroy;
    }

    public Texture getBackground_03() {
        return background_03;
    }

    public Texture getBackground_02() {
        return background_02;
    }

    public ArrayList<Box2DPhysicsObject> getEntities() {
        return entities;
    }

    public void setHero(Hero hero) {
        this.hero = hero;
    }

    public Hero getHero() {
        return hero;
    }


    @Override
    public String toString() {
        return "World{" +
                "map=" + map +
                ", physics=" + physics +
                ", parser=" + parser +
                ", entities=" + entities +
                ", hero=" + hero +
                ", bodiesFlaggedDestroy=" + bodiesFlaggedDestroy +
                ", background_03=" + background_03 +
                ", background_02=" + background_02 +
                ", background_01=" + background_01 +
                ", box2dObjectFactory=" + box2dObjectFactory +
                ", resourceService=" + resourceService +
                '}';
    }

}
