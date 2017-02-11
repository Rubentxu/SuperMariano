package com.indignado.games.states.game.gen;

import com.ilargia.games.entitas.api.*;
import com.ilargia.games.entitas.Entity;
import java.util.Stack;
import com.indignado.games.states.game.component.game.Animations;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.ilargia.games.entitas.api.IComponent;
import java.util.Map;
import com.indignado.games.states.game.component.game.Character;
import com.indignado.games.states.game.component.game.Element;
import com.indignado.games.states.game.data.StateCharacter;
import com.indignado.games.states.game.component.game.Destroy;
import com.indignado.games.states.game.component.game.Interactive;
import com.indignado.games.states.game.component.game.Movable;
import com.indignado.games.states.game.component.game.OnGround;
import com.indignado.games.states.game.component.game.Player;
import com.indignado.games.states.game.component.game.RigidBody;
import com.badlogic.gdx.physics.box2d.Body;
import com.indignado.games.states.game.component.game.TextureView;
import com.badlogic.gdx.graphics.Color;
import com.indignado.games.states.game.data.Bounds;

/**
 * ---------------------------------------------------------------------------
 * '<auto-generated>' This code was generated by CodeGeneratorApp.
 * ---------------------------------------------------------------------------
 */
public class GameEntity extends Entity {

	public Destroy DestroyComponent = new Destroy();
	public Interactive InteractiveComponent = new Interactive();
	public OnGround OnGroundComponent = new OnGround();
	public Player PlayerComponent = new Player();

	public GameEntity(int totalComponents,
			Stack<IComponent>[] componentContexts, ContextInfo contextInfo) {
		super(totalComponents, componentContexts, contextInfo);
	}

	public Animations getAnimations() {
		return (Animations) getComponent(GameComponentIds.Animations);
	}

	public boolean hasAnimations() {
		return hasComponent(GameComponentIds.Animations);
	}

	public GameEntity addAnimations(
			Map<String, Animation<TextureRegion>> animationStates,
			Animation<TextureRegion> currentAnimation, float time) {
		Animations component = (Animations) recoverComponent(GameComponentIds.Animations);
		if (component == null) {
			component = new Animations();
		}
		component.animationStates = animationStates;
		component.currentAnimation = currentAnimation;
		component.time = time;
		addComponent(GameComponentIds.Animations, component);
		return this;
	}

	public GameEntity replaceAnimations(
			Map<String, Animation<TextureRegion>> animationStates,
			Animation<TextureRegion> currentAnimation, float time) {
		Animations component = (Animations) recoverComponent(GameComponentIds.Animations);
		if (component == null) {
			component = new Animations();
		}
		component.animationStates = animationStates;
		component.currentAnimation = currentAnimation;
		component.time = time;
		replaceComponent(GameComponentIds.Animations, component);
		return this;
	}

	public GameEntity removeAnimations() {
		removeComponent(GameComponentIds.Animations);
		return this;
	}

	public Character getCharacter() {
		return (Character) getComponent(GameComponentIds.Character);
	}

	public boolean hasCharacter() {
		return hasComponent(GameComponentIds.Character);
	}

	public GameEntity addCharacter(String tag, StateCharacter currentState,
			boolean facingLeft) {
		Character component = (Character) recoverComponent(GameComponentIds.Character);
		if (component == null) {
			component = new Character();
		}
		component.tag = tag;
		component.currentState = currentState;
		component.facingLeft = facingLeft;
		addComponent(GameComponentIds.Character, component);
		return this;
	}

	public GameEntity replaceCharacter(String tag, StateCharacter currentState,
			boolean facingLeft) {
		Character component = (Character) recoverComponent(GameComponentIds.Character);
		if (component == null) {
			component = new Character();
		}
		component.tag = tag;
		component.currentState = currentState;
		component.facingLeft = facingLeft;
		replaceComponent(GameComponentIds.Character, component);
		return this;
	}

	public GameEntity removeCharacter() {
		removeComponent(GameComponentIds.Character);
		return this;
	}

	public boolean isDestroy() {
		return hasComponent(GameComponentIds.Destroy);
	}

	public GameEntity setDestroy(boolean value) {
		if (value != hasComponent(GameComponentIds.Destroy)) {
			if (value) {
				addComponent(GameComponentIds.Destroy, DestroyComponent);
			} else {
				removeComponent(GameComponentIds.Destroy);
			}
		}
		return this;
	}

	public Element getGameElement() {
		return (Element) getComponent(GameComponentIds.GameElement);
	}

	public boolean hasGameElement() {
		return hasComponent(GameComponentIds.GameElement);
	}

	public GameEntity addGameElement(String type, String tags) {
		Element component = (Element) recoverComponent(GameComponentIds.GameElement);
		if (component == null) {
			component = new Element();
		}
		component.type = type;
		component.tags = tags;
		addComponent(GameComponentIds.GameElement, component);
		return this;
	}

	public GameEntity replaceGameElement(String type, String tags) {
		Element component = (Element) recoverComponent(GameComponentIds.GameElement);
		if (component == null) {
			component = new Element();
		}
		component.type = type;
		component.tags = tags;
		replaceComponent(GameComponentIds.GameElement, component);
		return this;
	}

	public GameEntity removeGameElement() {
		removeComponent(GameComponentIds.GameElement);
		return this;
	}

	public boolean isInteractive() {
		return hasComponent(GameComponentIds.Interactive);
	}

	public GameEntity setInteractive(boolean value) {
		if (value != hasComponent(GameComponentIds.Interactive)) {
			if (value) {
				addComponent(GameComponentIds.Interactive, InteractiveComponent);
			} else {
				removeComponent(GameComponentIds.Interactive);
			}
		}
		return this;
	}

	public Movable getMovable() {
		return (Movable) getComponent(GameComponentIds.Movable);
	}

	public boolean hasMovable() {
		return hasComponent(GameComponentIds.Movable);
	}

	public GameEntity addMovable(float maxVelocity, float jumpForce) {
		Movable component = (Movable) recoverComponent(GameComponentIds.Movable);
		if (component == null) {
			component = new Movable();
		}
		component.maxVelocity = maxVelocity;
		component.jumpForce = jumpForce;
		addComponent(GameComponentIds.Movable, component);
		return this;
	}

	public GameEntity replaceMovable(float maxVelocity, float jumpForce) {
		Movable component = (Movable) recoverComponent(GameComponentIds.Movable);
		if (component == null) {
			component = new Movable();
		}
		component.maxVelocity = maxVelocity;
		component.jumpForce = jumpForce;
		replaceComponent(GameComponentIds.Movable, component);
		return this;
	}

	public GameEntity removeMovable() {
		removeComponent(GameComponentIds.Movable);
		return this;
	}

	public boolean isOnGround() {
		return hasComponent(GameComponentIds.OnGround);
	}

	public GameEntity setOnGround(boolean value) {
		if (value != hasComponent(GameComponentIds.OnGround)) {
			if (value) {
				addComponent(GameComponentIds.OnGround, OnGroundComponent);
			} else {
				removeComponent(GameComponentIds.OnGround);
			}
		}
		return this;
	}

	public boolean isPlayer() {
		return hasComponent(GameComponentIds.Player);
	}

	public GameEntity setPlayer(boolean value) {
		if (value != hasComponent(GameComponentIds.Player)) {
			if (value) {
				addComponent(GameComponentIds.Player, PlayerComponent);
			} else {
				removeComponent(GameComponentIds.Player);
			}
		}
		return this;
	}

	public RigidBody getRigidBody() {
		return (RigidBody) getComponent(GameComponentIds.RigidBody);
	}

	public boolean hasRigidBody() {
		return hasComponent(GameComponentIds.RigidBody);
	}

	public GameEntity addRigidBody(Body body) {
		RigidBody component = (RigidBody) recoverComponent(GameComponentIds.RigidBody);
		if (component == null) {
			component = new RigidBody();
		}
		component.body = body;
		addComponent(GameComponentIds.RigidBody, component);
		return this;
	}

	public GameEntity replaceRigidBody(Body body) {
		RigidBody component = (RigidBody) recoverComponent(GameComponentIds.RigidBody);
		if (component == null) {
			component = new RigidBody();
		}
		component.body = body;
		replaceComponent(GameComponentIds.RigidBody, component);
		return this;
	}

	public GameEntity removeRigidBody() {
		removeComponent(GameComponentIds.RigidBody);
		return this;
	}

	public TextureView getTextureView() {
		return (TextureView) getComponent(GameComponentIds.TextureView);
	}

	public boolean hasTextureView() {
		return hasComponent(GameComponentIds.TextureView);
	}

	public GameEntity addTextureView(TextureRegion texture, Bounds bounds,
			boolean flipX, boolean flipY, int opacity, int layer, Color tint) {
		TextureView component = (TextureView) recoverComponent(GameComponentIds.TextureView);
		if (component == null) {
			component = new TextureView(texture, bounds, flipX, flipY, opacity,
					layer, tint);
		} else {
			component.texture = texture;;
			component.bounds = bounds;;
			component.flipX = flipX;;
			component.flipY = flipY;;
			component.opacity = opacity;;
			component.layer = layer;;
			component.tint = tint;
		}
		addComponent(GameComponentIds.TextureView, component);
		return this;
	}

	public GameEntity replaceTextureView(TextureRegion texture, Bounds bounds,
			boolean flipX, boolean flipY, int opacity, int layer, Color tint) {
		TextureView component = (TextureView) recoverComponent(GameComponentIds.TextureView);
		if (component == null) {
			component = new TextureView(texture, bounds, flipX, flipY, opacity,
					layer, tint);
		} else {
			component.texture = texture;;
			component.bounds = bounds;;
			component.flipX = flipX;;
			component.flipY = flipY;;
			component.opacity = opacity;;
			component.layer = layer;;
			component.tint = tint;
		}
		replaceComponent(GameComponentIds.TextureView, component);
		return this;
	}

	public GameEntity removeTextureView() {
		removeComponent(GameComponentIds.TextureView);
		return this;
	}
}