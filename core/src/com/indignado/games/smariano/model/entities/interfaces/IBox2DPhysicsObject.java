package com.indignado.games.smariano.model.entities.interfaces;


import com.badlogic.gdx.physics.box2d.Body;

public interface IBox2DPhysicsObject {

    public float getXBodyA();

    public void setXBodyA(float value);

    public float getYBodyA();

    public void setYBodyA(float value);

    public float getRotationBodyA();

    public Body getBodyA();

    public float getWidthBodyA();

    public void setWidthBodyA(float widthBodyA);

    public float getHeightBodyA();

    public void setHeightBodyA(float heightBodyA);

}
