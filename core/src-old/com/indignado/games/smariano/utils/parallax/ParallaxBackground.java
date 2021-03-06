package com.indignado.games.smariano.utils.parallax;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

import java.util.ArrayList;
import java.util.List;

public class ParallaxBackground {

    private List<ParallaxLayer> layers;
    private float width;
    private OrthographicCamera cam;

    public ParallaxBackground(float width, OrthographicCamera cam) {
        this.layers = new ArrayList<ParallaxLayer>();
        this.width = width;
        this.cam = cam;

    }

    public void render(Vector3 position, SpriteBatch batch) {
        for (ParallaxLayer layer : layers) {
            float layerOffsetX = (position.x * layer.getxRatio() % width);
            float layerOffsetY = (position.y * layer.getyRatio() % cam.viewportHeight);
            layer.render(position.x - width / 2f - layerOffsetX, position.y - cam.viewportHeight / 2 + cam.viewportHeight / 12 - layerOffsetY, width, cam.viewportHeight, batch);
            layer.render(position.x + width / 2f - layerOffsetX, position.y - cam.viewportHeight / 2 + cam.viewportHeight / 12 - layerOffsetY, width, cam.viewportHeight, batch);
        }
    }

    public List<ParallaxLayer> getLayers() {
        return layers;
    }

    public float getWidth() {
        return width;
    }

    public void addLayer(ParallaxLayer parallaxLayer) {
        layers.add(parallaxLayer);
    }

}