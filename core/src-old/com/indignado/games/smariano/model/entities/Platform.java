package com.indignado.games.smariano.model.entities;


import com.badlogic.gdx.physics.box2d.Body;
import com.indignado.games.smariano.model.entities.base.Box2DPhysicsObject;

public class Platform extends Box2DPhysicsObject {


    public Platform(String nombre, GRUPO grupo, Body body) {
        super(nombre, grupo, body);
        setState(BaseState.DEFAULT);
    }


}
