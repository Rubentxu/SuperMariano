package com.indignado.games.states.game.gen;

import com.ilargia.games.entitas.api.*;
import com.ilargia.games.entitas.Entity;
import java.util.Stack;
import com.indignado.games.states.game.component.Background;
import com.badlogic.gdx.graphics.Texture;
import com.ilargia.games.entitas.api.IComponent;
import com.indignado.games.states.game.component.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.indignado.games.states.game.component.GameWorld;
import com.badlogic.gdx.graphics.Color;
import com.indignado.games.states.game.component.Tiled;
import com.badlogic.gdx.maps.tiled.TiledMap;

/**
 * ---------------------------------------------------------------------------
 * '<auto-generated>' This code was generated by CodeGeneratorApp.
 * ---------------------------------------------------------------------------
 */
public class SceneEntity extends Entity {

	public SceneEntity(int totalComponents,
			Stack<IComponent>[] componentContexts, ContextInfo contextInfo) {
		super(totalComponents, componentContexts, contextInfo);
	}

	public Background getBackground() {
		return (Background) getComponent(SceneComponentIds.Background);
	}

	public boolean hasBackground() {
		return hasComponent(SceneComponentIds.Background);
	}

	public SceneEntity addBackground(Texture front, Texture middle, Texture back) {
		Background component = (Background) recoverComponent(SceneComponentIds.Background);
		if (component == null) {
			component = new Background();
		}
		component.front = front;
		component.middle = middle;
		component.back = back;
		addComponent(SceneComponentIds.Background, component);
		return this;
	}

	public SceneEntity replaceBackground(Texture front, Texture middle,
			Texture back) {
		Background component = (Background) recoverComponent(SceneComponentIds.Background);
		if (component == null) {
			component = new Background();
		}
		component.front = front;
		component.middle = middle;
		component.back = back;
		replaceComponent(SceneComponentIds.Background, component);
		return this;
	}

	public SceneEntity removeBackground() {
		removeComponent(SceneComponentIds.Background);
		return this;
	}

	public Camera getCamera() {
		return (Camera) getComponent(SceneComponentIds.Camera);
	}

	public boolean hasCamera() {
		return hasComponent(SceneComponentIds.Camera);
	}

	public SceneEntity addCamera(OrthographicCamera camera) {
		Camera component = (Camera) recoverComponent(SceneComponentIds.Camera);
		if (component == null) {
			component = new Camera();
		}
		component.camera = camera;
		addComponent(SceneComponentIds.Camera, component);
		return this;
	}

	public SceneEntity replaceCamera(OrthographicCamera camera) {
		Camera component = (Camera) recoverComponent(SceneComponentIds.Camera);
		if (component == null) {
			component = new Camera();
		}
		component.camera = camera;
		replaceComponent(SceneComponentIds.Camera, component);
		return this;
	}

	public SceneEntity removeCamera() {
		removeComponent(SceneComponentIds.Camera);
		return this;
	}

	public GameWorld getGameWorld() {
		return (GameWorld) getComponent(SceneComponentIds.GameWorld);
	}

	public boolean hasGameWorld() {
		return hasComponent(SceneComponentIds.GameWorld);
	}

	public SceneEntity addGameWorld(float width, float height,
			float metresToPixels, float pixelsToMetres, boolean catchBack,
			boolean catchMenu, Color backGroundColor) {
		GameWorld component = (GameWorld) recoverComponent(SceneComponentIds.GameWorld);
		if (component == null) {
			component = new GameWorld();
		}
		component.width = width;
		component.height = height;
		component.metresToPixels = metresToPixels;
		component.pixelsToMetres = pixelsToMetres;
		component.catchBack = catchBack;
		component.catchMenu = catchMenu;
		component.backGroundColor = backGroundColor;
		addComponent(SceneComponentIds.GameWorld, component);
		return this;
	}

	public SceneEntity replaceGameWorld(float width, float height,
			float metresToPixels, float pixelsToMetres, boolean catchBack,
			boolean catchMenu, Color backGroundColor) {
		GameWorld component = (GameWorld) recoverComponent(SceneComponentIds.GameWorld);
		if (component == null) {
			component = new GameWorld();
		}
		component.width = width;
		component.height = height;
		component.metresToPixels = metresToPixels;
		component.pixelsToMetres = pixelsToMetres;
		component.catchBack = catchBack;
		component.catchMenu = catchMenu;
		component.backGroundColor = backGroundColor;
		replaceComponent(SceneComponentIds.GameWorld, component);
		return this;
	}

	public SceneEntity removeGameWorld() {
		removeComponent(SceneComponentIds.GameWorld);
		return this;
	}

	public Tiled getTiled() {
		return (Tiled) getComponent(SceneComponentIds.Tiled);
	}

	public boolean hasTiled() {
		return hasComponent(SceneComponentIds.Tiled);
	}

	public SceneEntity addTiled(String tileMapName, float unitScale) {
		Tiled component = (Tiled) recoverComponent(SceneComponentIds.Tiled);
		if (component == null) {
			component = new Tiled();
		}
		component.tileMapName = tileMapName;
		component.unitScale = unitScale;
		addComponent(SceneComponentIds.Tiled, component);
		return this;
	}

	public SceneEntity replaceTiled(String tileMapName, float unitScale) {
		Tiled component = (Tiled) recoverComponent(SceneComponentIds.Tiled);
		if (component == null) {
			component = new Tiled();
		}
		component.tileMapName = tileMapName;
		component.unitScale = unitScale;
		replaceComponent(SceneComponentIds.Tiled, component);
		return this;
	}

	public SceneEntity removeTiled() {
		removeComponent(SceneComponentIds.Tiled);
		return this;
	}
}