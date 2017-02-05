package com.indignado.games.states.game.system;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.ilargia.games.entitas.api.system.IRenderSystem;
import com.ilargia.games.entitas.group.Group;
import com.indignado.games.states.game.component.TextureView;
import com.indignado.games.states.game.gen.Entitas;
import com.indignado.games.states.game.gen.GameEntity;
import com.indignado.games.states.game.gen.GameMatcher;


public class RendererSystem implements IRenderSystem {

    // Debug   
    public static boolean TESTING = true;
    public static String DEBUG_ENTITY;
    public static boolean DRAW_BOX2D_BODIES = true;
    public static boolean DRAW_BOX2D_JOINTS = true;
    public static boolean DRAW_BOX2D_ABBs = true;
    public static boolean DRAW_BOX2D_INACTIVE_BODIES = true;
    public static boolean DRAW_BOX2D_VELOCITIES = true;
    public static boolean DRAW_BOX2D_CONTACTS = true;

    private World physics;
    private Entitas entitas;
    private ShapeRenderer debugShapeRenderer;
    private Box2DDebugRenderer debugRenderer;
    private OrthographicCamera cam;
    private OrthogonalTiledMapRenderer renderer;
    private Batch batch;
    private Group<GameEntity> _groupTextureView;

    public RendererSystem(Entitas entitas, Batch batch, World world) {
        this.physics = world;
        this.batch = batch;
        this.entitas = entitas;
        this.cam = entitas.scene.getCamera().camera;
        // this.renderer = new OrthogonalTiledMapRenderer(entitas.scene.);
        _groupTextureView = entitas.game.getGroup(GameMatcher.TextureView());


        this.debugShapeRenderer = new ShapeRenderer();
        this.debugRenderer = new Box2DDebugRenderer(DRAW_BOX2D_BODIES, DRAW_BOX2D_JOINTS, DRAW_BOX2D_ABBs,
                DRAW_BOX2D_INACTIVE_BODIES, DRAW_BOX2D_VELOCITIES, DRAW_BOX2D_CONTACTS);
        debugRenderer.setDrawAABBs(DRAW_BOX2D_ABBs);
        debugRenderer.setDrawBodies(DRAW_BOX2D_BODIES);
        debugRenderer.setDrawContacts(DRAW_BOX2D_CONTACTS);
        debugRenderer.setDrawInactiveBodies(DRAW_BOX2D_INACTIVE_BODIES);
        debugRenderer.setDrawJoints(DRAW_BOX2D_JOINTS);
        debugRenderer.setDrawVelocities(DRAW_BOX2D_VELOCITIES);

    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        cam.update();
        batch.setProjectionMatrix(cam.combined);


        batch.begin();
        for (GameEntity e : _groupTextureView.getEntities()) {
            TextureView view = e.getTextureView();
            Body body = e.getRigidBody().body;

            batch.draw(view.texture, body.getPosition().x - view.bounds.extentsX, body.getPosition().y - view.bounds.extentsY,
                    body.getPosition().x, body.getPosition().y, view.bounds.extentsX * 2, view.bounds.extentsY * 2, 1, 1, 0);

        }

        batch.end();

        debugShapeRenderer.setProjectionMatrix(cam.combined);
//        debugShapeRenderer.setColor(1.0f, 0.0f, 0.0f, 1.0f);
////        for (InputEntity e : inputs.getEntities()) {
////            Input input = e.getInput();
////
////            debugShapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
////            debugShapeRenderer.circle( input.x, input.y,  0.3f);
////        }
//        debugShapeRenderer.end();

        debugRenderer.render(physics, cam.combined);
    }


}
