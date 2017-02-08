package com.indignado.games.states.game.system.actuator;


import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Transform;
import com.badlogic.gdx.physics.box2d.World;
import com.ilargia.games.entitas.api.IComponent;
import com.ilargia.games.entitas.api.IContext;
import com.ilargia.games.entitas.api.IGroup;
import com.ilargia.games.entitas.api.system.IInitializeSystem;
import com.ilargia.games.entitas.collector.Collector;
import com.ilargia.games.entitas.factories.Collections;
import com.ilargia.games.entitas.group.Group;
import com.ilargia.games.entitas.systems.ReactiveSystem;
import com.indignado.games.states.game.component.actuator.CameraActuator;
import com.indignado.games.states.game.component.game.RigidBody;
import com.indignado.games.states.game.gen.*;

import java.util.List;


public class MotionActuatorSystem extends ReactiveSystem<ActuatorEntity> implements IInitializeSystem {
    private final GameContext gameContext;
    private final ActuatorContext actuatorContext;
    private Camera camera;
    private GameEntity followEntity;
    private List<ActuatorEntity> actuators;


    public MotionActuatorSystem(Entitas entitas, World world) {
        super(entitas.actuator);
        this.gameContext = entitas.game;
        this.actuatorContext = entitas.actuator;
        this.actuators = Collections.createList(ActuatorEntity.class);

    }

    @Override
    public void initialize() {
        Group<ActuatorEntity> group = actuatorContext.getGroup(ActuatorMatcher.CameraActuator());
        group.OnEntityAdded((IGroup<ActuatorEntity> g, ActuatorEntity entity, int index, IComponent component)-> {
            entity.hasCameraActuator()
            actuators.add(entity);
        })
    }

    @Override
    protected Collector getTrigger(IContext context) {
        return context.createCollector(ActuatorMatcher.CameraActuator());
    }

    @Override
    protected boolean filter(ActuatorEntity entity) {
        return entity.hasCameraActuator();

    }

    @Override
    protected void execute(List<ActuatorEntity> entities) {
        for (ActuatorEntity e : entities) {
            CameraActuator actuator = e.getCameraActuator();

            if (followEntity != null) {
                RigidBody rc = followEntity.getRigidBody();
                Transform transform = rc.body.getTransform();
                Vector3 position = camera.position;
                position.x += (transform.getPosition().x - position.x) * actuator.damping;
                position.y += (transform.getPosition().y - position.y) * actuator.damping;
            }
        }

    }

}
