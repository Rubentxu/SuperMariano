package com.indignado.games.states.game.gen;

import com.ilargia.games.entitas.api.*;
import com.ilargia.games.entitas.Entity;
import java.util.Stack;
import com.indignado.games.states.game.component.actuator.CameraActuator;
import com.ilargia.games.entitas.api.IComponent;
import com.ilargia.games.entitas.api.IEntity;
import com.indignado.games.states.game.component.actuator.CharacterActuator;
import com.badlogic.gdx.math.Vector2;
import com.indignado.games.states.game.data.StateCharacter;
import com.indignado.games.states.game.component.actuator.TextureActuator;
import com.badlogic.gdx.graphics.Color;
import com.indignado.games.states.game.data.Bounds;
import com.indignado.games.states.game.component.actuator.VelocityActuator;

/**
 * ---------------------------------------------------------------------------
 * '<auto-generated>' This code was generated by CodeGeneratorApp.
 * ---------------------------------------------------------------------------
 */
public class ActuatorEntity extends Entity {

	public ActuatorEntity(int totalComponents,
			Stack<IComponent>[] componentContexts, ContextInfo contextInfo) {
		super(totalComponents, componentContexts, contextInfo);
	}

	public CameraActuator getCameraActuator() {
		return (CameraActuator) getComponent(ActuatorComponentIds.CameraActuator);
	}

	public boolean hasCameraActuator() {
		return hasComponent(ActuatorComponentIds.CameraActuator);
	}

	public ActuatorEntity addCameraActuator(short height, float damping,
			String followTagEntity) {
		CameraActuator component = (CameraActuator) recoverComponent(ActuatorComponentIds.CameraActuator);
		if (component == null) {
			component = new CameraActuator();
		}
		component.height = height;
		component.damping = damping;
		component.followTagEntity = followTagEntity;
		addComponent(ActuatorComponentIds.CameraActuator, component);
		return this;
	}

	public ActuatorEntity replaceCameraActuator(short height, float damping,
			String followTagEntity) {
		CameraActuator component = (CameraActuator) recoverComponent(ActuatorComponentIds.CameraActuator);
		if (component == null) {
			component = new CameraActuator();
		}
		component.height = height;
		component.damping = damping;
		component.followTagEntity = followTagEntity;
		replaceComponent(ActuatorComponentIds.CameraActuator, component);
		return this;
	}

	public ActuatorEntity removeCameraActuator() {
		removeComponent(ActuatorComponentIds.CameraActuator);
		return this;
	}

	public CharacterActuator getCharacterActuator() {
		return (CharacterActuator) getComponent(ActuatorComponentIds.CharacterActuator);
	}

	public boolean hasCharacterActuator() {
		return hasComponent(ActuatorComponentIds.CharacterActuator);
	}

	public ActuatorEntity addCharacterActuator(String target,
			StateCharacter newState, boolean facingLeft) {
		CharacterActuator component = (CharacterActuator) recoverComponent(ActuatorComponentIds.CharacterActuator);
		if (component == null) {
			component = new CharacterActuator();
		}
		component.target = target;
		component.newState = newState;
		component.facingLeft = facingLeft;
		addComponent(ActuatorComponentIds.CharacterActuator, component);
		return this;
	}

	public ActuatorEntity replaceCharacterActuator(String target,
			StateCharacter newState, boolean facingLeft) {
		CharacterActuator component = (CharacterActuator) recoverComponent(ActuatorComponentIds.CharacterActuator);
		if (component == null) {
			component = new CharacterActuator();
		}
		component.target = target;
		component.newState = newState;
		component.facingLeft = facingLeft;
		replaceComponent(ActuatorComponentIds.CharacterActuator, component);
		return this;
	}

	public ActuatorEntity removeCharacterActuator() {
		removeComponent(ActuatorComponentIds.CharacterActuator);
		return this;
	}

	public TextureActuator getTextureActuator() {
		return (TextureActuator) getComponent(ActuatorComponentIds.TextureActuator);
	}

	public boolean hasTextureActuator() {
		return hasComponent(ActuatorComponentIds.TextureActuator);
	}

	public ActuatorEntity addTextureActuator(String target, Bounds bounds,
			int opacity, Boolean flipX, Boolean flipY, Color tint) {
		TextureActuator component = (TextureActuator) recoverComponent(ActuatorComponentIds.TextureActuator);
		if (component == null) {
			component = new TextureActuator();
		}
		component.target = target;
		component.bounds = bounds;
		component.opacity = opacity;
		component.flipX = flipX;
		component.flipY = flipY;
		component.tint = tint;
		addComponent(ActuatorComponentIds.TextureActuator, component);
		return this;
	}

	public ActuatorEntity replaceTextureActuator(String target, Bounds bounds,
			int opacity, Boolean flipX, Boolean flipY, Color tint) {
		TextureActuator component = (TextureActuator) recoverComponent(ActuatorComponentIds.TextureActuator);
		if (component == null) {
			component = new TextureActuator();
		}
		component.target = target;
		component.bounds = bounds;
		component.opacity = opacity;
		component.flipX = flipX;
		component.flipY = flipY;
		component.tint = tint;
		replaceComponent(ActuatorComponentIds.TextureActuator, component);
		return this;
	}

	public ActuatorEntity removeTextureActuator() {
		removeComponent(ActuatorComponentIds.TextureActuator);
		return this;
	}

	public VelocityActuator getVelocityActuator() {
		return (VelocityActuator) getComponent(ActuatorComponentIds.VelocityActuator);
	}

	public boolean hasVelocityActuator() {
		return hasComponent(ActuatorComponentIds.VelocityActuator);
	}

	public ActuatorEntity addVelocityActuator(String target, Vector2 velocity,
			float angularVelocity) {
		VelocityActuator component = (VelocityActuator) recoverComponent(ActuatorComponentIds.VelocityActuator);
		if (component == null) {
			component = new VelocityActuator();
		}
		component.target = target;
		component.velocity = velocity;
		component.angularVelocity = angularVelocity;
		addComponent(ActuatorComponentIds.VelocityActuator, component);
		return this;
	}

	public ActuatorEntity replaceVelocityActuator(String target,
			Vector2 velocity, float angularVelocity) {
		VelocityActuator component = (VelocityActuator) recoverComponent(ActuatorComponentIds.VelocityActuator);
		if (component == null) {
			component = new VelocityActuator();
		}
		component.target = target;
		component.velocity = velocity;
		component.angularVelocity = angularVelocity;
		replaceComponent(ActuatorComponentIds.VelocityActuator, component);
		return this;
	}

	public ActuatorEntity removeVelocityActuator() {
		removeComponent(ActuatorComponentIds.VelocityActuator);
		return this;
	}
}