package com.indignado.games.smariano.model.entities.interfaces;


import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Joint;

public interface IBox2DPhysicsCompoundObject {

    public float getXBodyB();

    public void setXBodyB(float value);

    public float getYBodyB();

    public void setYBodyB(float value);

    public float getRotationB();

    public Body getBodyB();

    public Joint getJoint();

    public float getWidthBodyB();

    public void setWidthBodyB(float widthBodyB);

    public float getHeightBodyB();

    public void setHeightBodyB(float heightBodyB);


}
