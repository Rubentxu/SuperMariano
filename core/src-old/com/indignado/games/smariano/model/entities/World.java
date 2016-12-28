package com.indignado.games.smariano.model.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import com.indignado.games.smariano.BaseGame;
import com.indignado.games.smariano.model.entities.base.Box2DPhysicsObject;
import com.indignado.games.smariano.model.factories.Box2dObjectFactory;
import com.indignado.games.smariano.model.managers.*;
import com.indignado.games.smariano.model.services.interfaces.IAudioService;
import com.indignado.games.smariano.model.services.interfaces.ILevelService;
import com.indignado.games.smariano.model.services.interfaces.IProfileService;
import com.indignado.games.smariano.model.services.interfaces.IResourcesService;
import com.indignado.games.smariano.utils.debug.GameLogger;
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

    private IResourcesService resourceService;
    private ILevelService levelService;
    private IProfileService profileService;
    private IAudioService audioService;

    private HeroManager heroManager;
    private PlatformManager platformManager;
    private WaterManager waterManager;
    private EnemyManager enemyManager;
    private ItemsManager itemsManager;
    private CheckPointManager checkPointManager;


    @Inject
    public World(BaseGame game, HeroManager heroManager, PlatformManager platformManager, WaterManager waterManager,
                 EnemyManager enemyManager, ItemsManager itemsManager, CheckPointManager checkPointManager) {
        this.heroManager = heroManager;
        this.platformManager = platformManager;
        this.waterManager = waterManager;
        this.enemyManager = enemyManager;
        this.itemsManager = itemsManager;
        this.checkPointManager = checkPointManager;

        this.profileService = game.profileService;
        this.audioService = game.audioService;

        itemsManager.addObserver(profileService);
        itemsManager.addObserver(audioService);
        heroManager.addObserver(profileService);
        heroManager.addObserver(audioService);

        this.physics = new com.badlogic.gdx.physics.box2d.World(new Vector2(0, -9.81f), true);
        this.levelService = game.levelService;
        this.resourceService = game.resourcesService;
        entities = new ArrayList<Box2DPhysicsObject>();
        createDreamsWorld();

    }


    public void createDreamsWorld() {
        Level level = levelService.getCurrentLevel();
        Collections.sort(entities, new Comparator<Box2DPhysicsObject>() {
            public int compare(Box2DPhysicsObject one, Box2DPhysicsObject two) {
                return one.getGrupo().compareTo(two.getGrupo());
            }
        });
        box2dObjectFactory = new Box2dObjectFactory(physics, this, resourceService);
        map = resourceService.getAssetManager().get(level.getMap());
        parser = new Box2DMapObjectParser(this, box2dObjectFactory);
        //System.out.println(getParser().getHierarchy(map));
        parser.load(getPhysics(), map);

        background_01 = resourceService.getAssetManager().get(level.getBackground_01());
        background_02 = resourceService.getAssetManager().get(level.getBackground_02());
        background_03 = resourceService.getAssetManager().get(level.getBackground_03());

    }


    public AbstractWorldManager getManager(Box2DPhysicsObject entity) {
        Box2DPhysicsObject.GRUPO grupo = entity.getGrupo();
        if (grupo != null) {
            switch (grupo) {
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
        } else {
            GameLogger.error("WorldController", "No existe un grupo para esta entidad: %s", entity.toString());
        }
        return null;

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
        map = null;
        parser = null;
        if (physics != null) {
            physics.dispose();
            physics = null;
        }
        background_01 = null;
        bodiesFlaggedDestroy = null;
        clearWorld();

    }


    public void clearWorld() {
        for (Box2DPhysicsObject e : entities) {
            e.dispose();
            e = null;
        }
        entities.clear();
        entities = new ArrayList<Box2DPhysicsObject>();
        hero = null;

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

    public Hero getHero() {
        return hero;
    }

    public void setHero(Hero hero) {
        this.hero = hero;
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
