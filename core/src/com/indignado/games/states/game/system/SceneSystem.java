package com.indignado.games.states.game.system;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.physics.box2d.World;
import com.ilargia.games.egdx.base.interfaces.GameElementFactory;
import com.ilargia.games.entitas.api.IContext;
import com.ilargia.games.entitas.api.system.ICleanupSystem;
import com.ilargia.games.entitas.api.system.IInitializeSystem;
import com.ilargia.games.entitas.collector.Collector;
import com.ilargia.games.entitas.factories.Collections;
import com.ilargia.games.entitas.group.Group;
import com.ilargia.games.entitas.matcher.Matcher;
import com.ilargia.games.entitas.systems.ReactiveSystem;
import com.indignado.games.states.game.component.GameWorld;
import com.indignado.games.states.game.gen.Entitas;
import com.indignado.games.states.game.gen.GameEntity;
import com.indignado.games.states.game.gen.GameMatcher;
import com.indignado.games.states.game.gen.SceneContext;

import java.util.List;
import java.util.Map;


public class SceneSystem extends ReactiveSystem<GameEntity> implements IInitializeSystem, ICleanupSystem {

    public static int BOX2D_VELOCITY_ITERATIONS = 6;
    public static int BOX2D_POSITION_ITERATIONS = 10;
    private final SceneContext context;
    private final Group<GameEntity> gameElements;
    private World physics;
    private Map<String, GameElementFactory> factories;


    public SceneSystem(Entitas entitas, World world) {
        super(entitas.game);
        this.physics = world;
        this.context = entitas.scene;
        this.gameElements = entitas.game.getGroup(Matcher.AllOf(GameMatcher.GameElement(), GameMatcher.RigidBody()));
        this.factories = Collections.createMap(String.class, GameElementFactory.class);
    }

    @Override
    protected Collector<GameEntity> getTrigger(IContext<GameEntity> context) {
        return context.createCollector(GameMatcher.GameElement());
    }

    @Override
    protected boolean filter(GameEntity entity) {
        return entity.hasGameElement() && (!entity.hasTextureView() || !entity.hasRigidBody());
    }


    @Override
    public void initialize() {
        GameWorld world = context.getGameWorld();
        OrthographicCamera camera = context.getCamera().camera;
        camera.position.set(world.width / 2, world.height / 2, 0);
        camera.viewportWidth = world.width;
        camera.viewportHeight = world.height;

    }

    @Override
    protected void execute(List<GameEntity> gameEntities) {
        for (GameEntity gameEntity : gameEntities) {
            factories.get(gameEntity.getGameElement().type).generate(gameEntity);
        }
    }

    @Override
    public void cleanup() {
        physics.step(Gdx.graphics.getDeltaTime(), BOX2D_VELOCITY_ITERATIONS, BOX2D_POSITION_ITERATIONS);
    }


}
