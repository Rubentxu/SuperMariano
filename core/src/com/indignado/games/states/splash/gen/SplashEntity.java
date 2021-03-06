package com.indignado.games.states.splash.gen;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.ilargia.games.entitas.Entity;
import com.ilargia.games.entitas.api.ContextInfo;
import com.ilargia.games.entitas.api.IComponent;
import com.indignado.games.states.splash.components.Delay;
import com.indignado.games.states.splash.components.TextureView;

import java.util.Stack;

/**
 * ---------------------------------------------------------------------------
 * '<auto-generated>' This code was generated by CodeGeneratorApp.
 * ---------------------------------------------------------------------------
 */
public class SplashEntity extends Entity {

    public SplashEntity(int totalComponents,
                        Stack<IComponent>[] componentContexts, ContextInfo contextInfo) {
        super(totalComponents, componentContexts, contextInfo);
    }

    public Delay getDelay() {
        return (Delay) getComponent(SplashComponentIds.Delay);
    }

    public boolean hasDelay() {
        return hasComponent(SplashComponentIds.Delay);
    }

    public SplashEntity addDelay(float duration) {
        Delay component = (Delay) recoverComponent(SplashComponentIds.Delay);
        if (component == null) {
            component = new Delay(duration);
        } else {
            component.duration = duration;
            ;
            component.time = 0;
        }
        addComponent(SplashComponentIds.Delay, component);
        return this;
    }

    public SplashEntity replaceDelay(float duration) {
        Delay component = (Delay) recoverComponent(SplashComponentIds.Delay);
        if (component == null) {
            component = new Delay(duration);
        } else {
            component.duration = duration;
            ;
            component.time = 0;
        }
        replaceComponent(SplashComponentIds.Delay, component);
        return this;
    }

    public SplashEntity removeDelay() {
        removeComponent(SplashComponentIds.Delay);
        return this;
    }

    public TextureView getTextureView() {
        return (TextureView) getComponent(SplashComponentIds.TextureView);
    }

    public boolean hasTextureView() {
        return hasComponent(SplashComponentIds.TextureView);
    }

    public SplashEntity addTextureView(String name, TextureRegion texture,
                                       Vector2 position, float rotation, int height, int width) {
        TextureView component = (TextureView) recoverComponent(SplashComponentIds.TextureView);
        if (component == null) {
            component = new TextureView(name, texture, position, rotation,
                    height, width);
        } else {
            component.name = name;
            ;
            component.texture = texture;
            ;
            component.position = position;
            ;
            component.rotation = rotation;
            ;
            component.height = height;
            ;
            component.width = width;
        }
        addComponent(SplashComponentIds.TextureView, component);
        return this;
    }

    public SplashEntity replaceTextureView(String name, TextureRegion texture,
                                           Vector2 position, float rotation, int height, int width) {
        TextureView component = (TextureView) recoverComponent(SplashComponentIds.TextureView);
        if (component == null) {
            component = new TextureView(name, texture, position, rotation,
                    height, width);
        } else {
            component.name = name;
            ;
            component.texture = texture;
            ;
            component.position = position;
            ;
            component.rotation = rotation;
            ;
            component.height = height;
            ;
            component.width = width;
        }
        replaceComponent(SplashComponentIds.TextureView, component);
        return this;
    }

    public SplashEntity removeTextureView() {
        removeComponent(SplashComponentIds.TextureView);
        return this;
    }
}