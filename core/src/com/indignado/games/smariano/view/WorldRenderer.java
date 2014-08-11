package com.indignado.games.smariano.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.utils.Disposable;
import com.indignado.games.smariano.BaseGame;
import com.indignado.games.smariano.SMariano;
import com.indignado.games.smariano.config.constantes.Env;
import com.indignado.games.smariano.model.entities.World;
import com.indignado.games.smariano.model.services.interfaces.IResourcesService;
import com.indignado.games.smariano.utils.debug.DebugWindow;
import com.indignado.games.smariano.utils.parallax.ParallaxBackground;
import com.indignado.games.smariano.utils.parallax.ParallaxLayer;


public class WorldRenderer implements Disposable {
    private Box2DDebugRenderer debugRenderer;
    private World world;
    private OrthographicCamera cam;
    private OrthogonalTiledMapRenderer renderer;
    private SpriteBatch spriteBatch;
    private float width;
    private float height;
    private ModelsAndViews modelsAndViews;
    private ParallaxBackground background;
    private IResourcesService resourcesService;



    public WorldRenderer(BaseGame game,World world) {
        this.world=world;
        this.resourcesService=game.resourcesService;
        this.debugRenderer=new Box2DDebugRenderer();
        this.modelsAndViews=new ModelsAndViews(resourcesService,world);
        this.renderer = new OrthogonalTiledMapRenderer(world.getMap(), world.getParser().getUnitScale());
        this.world.removeParser();
        this.spriteBatch = (SpriteBatch) renderer.getSpriteBatch();
        this.cam = new OrthographicCamera();

    }


    public void resize(int w, int h) {
        this.width=w;
        this.height=h;
        cam.viewportHeight = Env.WORLD_HEIGHT;
        cam.viewportWidth = (Env.WORLD_HEIGHT / height) * width;
        Gdx.app.log(Env.LOG,"World ViewPortWidth: "+cam.viewportWidth+ " World ViewPortHeight: "+cam.viewportHeight);
        cam.update();
        background=new ParallaxBackground(Env.WORLD_WIDTH,cam);
        background.addLayer(new ParallaxLayer(world.getBackground_01(),0.4f,0,100,100));
        background.addLayer(new ParallaxLayer(world.getBackground_03(),0.6f,0,100,100));
        background.addLayer(new ParallaxLayer(world.getBackground_02(), 0.8f, 0.02f, 100, 100));

    }

    public void render() {

        background.render(cam.position,spriteBatch);
        cam.position.set(world.getHero().getBodyA().getPosition().x, cam.viewportHeight / 2 - cam.viewportHeight / 12, 0);
        cam.update();

        renderer.setView(cam);
        renderer.render();

        spriteBatch.begin();
        modelsAndViews.render(spriteBatch);

        if (SMariano.DEBUG) {
            DebugWindow.getInstance(resourcesService).setPosition(cam.position.x - 13f, cam.position.y - 5);
            DebugWindow.myLabel.setText("Modo Debug:\n\n" + world.getHero().toString());
            DebugWindow.getInstance(resourcesService).pack();
            DebugWindow.getInstance(resourcesService).draw(spriteBatch, 1f);

        }

        spriteBatch.end();

        if (SMariano.DEBUG) {
            debugRenderer.render(world.getPhysics(), cam.combined);

        }
    }

    @Override
    public void dispose() {
        renderer.dispose();
        renderer=null;
        modelsAndViews=null;
        if(debugRenderer!=null){
            debugRenderer.dispose();
            debugRenderer=null;
        }
        cam=null;
        spriteBatch=null;
        background=null;
        world=null;

    }

    public OrthographicCamera getCam() {
        return cam;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }


}
