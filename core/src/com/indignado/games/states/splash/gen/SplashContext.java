package com.indignado.games.states.splash.gen;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.ilargia.games.entitas.api.ContextInfo;
import com.ilargia.games.entitas.api.EntitasException;
import com.ilargia.games.entitas.api.EntityBaseFactory;
import com.indignado.games.states.splash.components.TextureView;

/**
 * ---------------------------------------------------------------------------
 * '<auto-generated>' This code was generated by CodeGeneratorApp.
 * ---------------------------------------------------------------------------
 */
public class SplashContext
        extends
        com.ilargia.games.entitas.Context<SplashEntity> {

    public SplashContext(int totalComponents, int startCreationIndex,
                         ContextInfo contextInfo, EntityBaseFactory<SplashEntity> factoryMethod) {
        super(totalComponents, startCreationIndex, contextInfo, factoryMethod);
    }

    public SplashEntity getTextureViewEntity() {
        return getGroup(SplashMatcher.TextureView()).getSingleEntity();
    }

    public TextureView getTextureView() {
        return getTextureViewEntity().getTextureView();
    }

    public boolean hasTextureView() {
        return getTextureViewEntity() != null;
    }

    public SplashEntity setTextureView(String name, TextureRegion texture,
                                       Vector2 position, float rotation, int height, int width) {
        if (hasTextureView()) {
            throw new EntitasException(
                    "Could not set TextureView!" + this
                            + " already has an entity with TextureView!",
                    "You should check if the context already has a TextureViewEntity before setting it or use context.ReplaceTextureView().");
        }
        SplashEntity entity = createEntity();
        entity.addTextureView(name, texture, position, rotation, height, width);
        return entity;
    }

    public SplashEntity replaceTextureView(String name, TextureRegion texture,
                                           Vector2 position, float rotation, int height, int width) {
        SplashEntity entity = getTextureViewEntity();
        if (entity == null) {
            entity = setTextureView(name, texture, position, rotation, height,
                    width);
        } else {
            entity.replaceTextureView(name, texture, position, rotation,
                    height, width);
        }
        return entity;
    }

    public SplashContext removeTextureView() {
        destroyEntity(getTextureViewEntity());
        return this;
    }
}