package com.indignado.games.states.game.gen;

import com.ilargia.games.entitas.Context;
import com.ilargia.games.entitas.api.*;
import com.indignado.games.states.game.gen.input.InputEntity;
import com.indignado.games.states.game.gen.game.GameEntity;
import com.indignado.games.states.game.gen.gui.GuiEntity;
import com.indignado.games.states.game.gen.scene.SceneEntity;
import com.indignado.games.states.game.gen.actuator.ActuatorEntity;
import com.indignado.games.states.game.gen.input.InputComponentsLookup;
import com.indignado.games.states.game.gen.game.GameComponentsLookup;
import com.indignado.games.states.game.gen.gui.GuiComponentsLookup;
import com.indignado.games.states.game.gen.scene.SceneComponentsLookup;
import com.indignado.games.states.game.gen.actuator.ActuatorComponentsLookup;
import com.indignado.games.states.game.gen.input.InputContext;
import com.indignado.games.states.game.gen.game.GameContext;
import com.indignado.games.states.game.gen.gui.GuiContext;
import com.indignado.games.states.game.gen.scene.SceneContext;
import com.indignado.games.states.game.gen.actuator.ActuatorContext;

/**
 * ---------------------------------------------------------------------------
 * '<auto-generated>' This code was generated by CodeGeneratorApp.
 * ---------------------------------------------------------------------------
 */
public class Entitas implements IContexts {

	public InputContext input;
	public GameContext game;
	public GuiContext gui;
	public SceneContext scene;
	public ActuatorContext actuator;

	public Entitas() {
		input = createInputContext();
		game = createGameContext();
		gui = createGuiContext();
		scene = createSceneContext();
		actuator = createActuatorContext();
	}

	public InputContext createInputContext() {
		return new InputContext(InputComponentsLookup.totalComponents, 0,
				new ContextInfo("Input",
						InputComponentsLookup.componentNames(),
						InputComponentsLookup.componentTypes()),
				factoryInputEntity());
	}

	public GameContext createGameContext() {
		return new GameContext(GameComponentsLookup.totalComponents, 0,
				new ContextInfo("Game", GameComponentsLookup.componentNames(),
						GameComponentsLookup.componentTypes()),
				factoryGameEntity());
	}

	public GuiContext createGuiContext() {
		return new GuiContext(GuiComponentsLookup.totalComponents, 0,
				new ContextInfo("Gui", GuiComponentsLookup.componentNames(),
						GuiComponentsLookup.componentTypes()),
				factoryGuiEntity());
	}

	public SceneContext createSceneContext() {
		return new SceneContext(SceneComponentsLookup.totalComponents, 0,
				new ContextInfo("Scene",
						SceneComponentsLookup.componentNames(),
						SceneComponentsLookup.componentTypes()),
				factorySceneEntity());
	}

	public ActuatorContext createActuatorContext() {
		return new ActuatorContext(ActuatorComponentsLookup.totalComponents, 0,
				new ContextInfo("Actuator", ActuatorComponentsLookup
						.componentNames(), ActuatorComponentsLookup
						.componentTypes()),
				factoryActuatorEntity());
	}

	@Override
	public Context[] allContexts() {
		return new Context[]{input, game, gui, scene, actuator};
	}

	public EntityBaseFactory<InputEntity> factoryInputEntity() {
		return () -> {
			return new InputEntity();
		};
	}

	public EntityBaseFactory<GameEntity> factoryGameEntity() {
		return () -> {
			return new GameEntity();
		};
	}

	public EntityBaseFactory<GuiEntity> factoryGuiEntity() {
		return () -> {
			return new GuiEntity();
		};
	}

	public EntityBaseFactory<SceneEntity> factorySceneEntity() {
		return () -> {
			return new SceneEntity();
		};
	}

	public EntityBaseFactory<ActuatorEntity> factoryActuatorEntity() {
		return () -> {
			return new ActuatorEntity();
		};
	}
}