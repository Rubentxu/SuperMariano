package com.indignado.games.manager;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.ilargia.games.egdx.api.EntityFactory;
import com.ilargia.games.egdx.managers.EGSceneManager;
import com.ilargia.games.entitas.api.IEntity;
import com.indignado.games.SMEngine;


public class SMSSceneManger extends EGSceneManager {

    public SMSSceneManger(SMEngine engine) {
        super(engine);
    }

    @Override
    public <TEntity extends IEntity> TEntity createEntity(String name, float posX, float posY) {
        EntityFactory factory = factories.get(name);
        TEntity entity = null;
        if (factory != null) {
            entity = (TEntity) factory.create(egEngine, posX, posY);
        }
        return entity;

    }

    @Override
    public void createScene(TiledMap map) {
        for (MapObject object : map.getLayers().get(3).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set((rect.getX() + rect.getWidth() / 2) / MarioBros.PPM, (rect.getY() + rect.getHeight() / 2) / MarioBros.PPM);

            body = world.createBody(bdef);

            shape.setAsBox(rect.getWidth() / 2 / MarioBros.PPM, rect.getHeight() / 2 / MarioBros.PPM);
            fdef.shape = shape;
            fdef.filter.categoryBits = MarioBros.OBJECT_BIT;
            body.createFixture(fdef);
        }
    }
}
