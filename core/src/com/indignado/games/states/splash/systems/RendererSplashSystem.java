package com.indignado.games.states.splash.systems;

import com.ilargia.games.entitas.interfaces.IExecuteSystem;


public class RendererSplashSystem implements IExecuteSystem/*, ISetPool<Pool>*/ {
    @Override
    public void execute(float deltaTime) {

    }
//    private final BitmapFont font;
//    private Group<Entity> _group;
//    private ShapeRenderer sr;
//    private OrthographicCamera cam;
//    private Group<Entity> _groupScore;
//    private Batch batch;
//    private Group<Entity> _groupTextureView;
//
//    public RendererSplashSystem(ShapeRenderer sr, OrthographicCamera cam, Batch batch, BitmapFont font) {
//        this.sr = sr;
//        this.cam = cam;
//        this.batch = batch;
//        this.font =  font;
//    }
//
//    @Override
//    public void setPool(Pool pool) {
//        _group = pool.getGroup(CoreMatcher.View());
//        _groupScore = pool.getGroup(CoreMatcher.Score());
//        _groupTextureView = pool.getGroup(CoreMatcher.TextureView());
//    }
//
//    @Override
//    public void execute(float deltatime) {
//        Gdx.gl.glClearColor(0, 0, 0, 1);
//        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
//
//        cam.update();
//
//        sr.setProjectionMatrix(cam.combined);
//        sr.begin(ShapeRenderer.ShapeType.Filled);
//        sr.setColor(Color.WHITE);
//
//        for (Entity e : _group.getEntities()) {
//            View view = e.getView();
//
//            if(view.shape instanceof Rectangle) {
//                Rectangle ret = (Rectangle) view.shape;
//                sr.rect(ret.x, ret.y, ret.width, ret.height);
//            } else {
//                Circle circle = (Circle) view.shape;
//                sr.circle(circle.x, circle.y, circle.radius);
//            }
//
//        }
//
//        sr.end();
//
//        batch.begin();
//        for (Entity e : _groupScore.getEntities()) {
//            Score score = e.getScore();
//            font.draw(batch, score.text+ " "+ score.points, score.x, score.y);
//        }
//        for (Entity e : _groupTextureView.getEntities()) {
//            TextureView textureView = e.getTextureView();
//            float originX = textureView.width * 0.5f;
//            float originY = textureView.height * 0.5f;
//
//            batch.draw(textureView.texture, textureView.position.x , textureView.position.y ,
//                    0, 0, textureView.width, textureView.height, 1, 1, textureView.rotation);
//
//
//        }
//        batch.end();
//    }

}
