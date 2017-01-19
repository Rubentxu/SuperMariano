package com.indignado.games.states.game.gen;

import com.ilargia.games.entitas.EntityMetaData;
import com.ilargia.games.entitas.interfaces.IComponent;
import java.util.Stack;
import com.ilargia.games.entitas.events.EventBus;
import com.indignado.games.states.game.component.AnimationView;
import com.indignado.games.states.game.component.Background;
import com.badlogic.gdx.graphics.Texture;
import com.indignado.games.states.game.component.Delay;
import com.indignado.games.states.game.sensor.KeyboardSensor;
import com.indignado.games.states.game.component.Label;
import com.indignado.games.states.game.component.Motion;
import com.badlogic.gdx.math.Vector2;
import com.indignado.games.states.game.component.TransformCollection;
import com.indignado.games.states.game.component.ViewCollection;

/**
 * ---------------------------------------------------------------------------
 * '<auto-generated>' This code was generated by CodeGeneratorApp.
 * ---------------------------------------------------------------------------
 */
public class Entity extends com.ilargia.games.entitas.Entity {

	public Entity(int totalComponents, Stack<IComponent>[] componentPools,
			EntityMetaData entityMetaData, EventBus<Entity> bus) {
		super(totalComponents, componentPools, entityMetaData, bus);
	}

	public AnimationView getAnimationView() {
		return (AnimationView) getComponent(SmcoreComponentIds.AnimationView);
	}

	public boolean hasAnimationView() {
		return hasComponent(SmcoreComponentIds.AnimationView);
	}

	public Entity addAnimationView(Map<Integer, Animation> animations) {
		AnimationView component = (AnimationView) recoverComponent(SmcoreComponentIds.AnimationView);
		if (component == null) {
			component = new AnimationView(animations);
		} else {
			component.animations = animations;
		}
		addComponent(SmcoreComponentIds.AnimationView, component);
		return this;
	}

	public Entity replaceAnimationView(Map<Integer, Animation> animations) {
		AnimationView component = (AnimationView) recoverComponent(SmcoreComponentIds.AnimationView);
		if (component == null) {
			component = new AnimationView(animations);
		} else {
			component.animations = animations;
		}
		replaceComponent(SmcoreComponentIds.AnimationView, component);
		return this;
	}

	public Entity removeAnimationView() {
		removeComponent(SmcoreComponentIds.AnimationView);
		return this;
	}

	public Background getBackground() {
		return (Background) getComponent(SmcoreComponentIds.Background);
	}

	public boolean hasBackground() {
		return hasComponent(SmcoreComponentIds.Background);
	}

	public Entity addBackground(Texture front, Texture middle, Texture back) {
		Background component = (Background) recoverComponent(SmcoreComponentIds.Background);
		if (component == null) {
			component = new Background();
		}
		component.front = front;
		component.middle = middle;
		component.back = back;
		addComponent(SmcoreComponentIds.Background, component);
		return this;
	}

	public Entity replaceBackground(Texture front, Texture middle, Texture back) {
		Background component = (Background) recoverComponent(SmcoreComponentIds.Background);
		if (component == null) {
			component = new Background();
		}
		component.front = front;
		component.middle = middle;
		component.back = back;
		removeComponent(SmcoreComponentIds.Background);
		return this;
	}

	public Entity removeBackground() {
		removeComponent(SmcoreComponentIds.Background);
		return this;
	}

	public Delay getDelay() {
		return (Delay) getComponent(SmcoreComponentIds.Delay);
	}

	public boolean hasDelay() {
		return hasComponent(SmcoreComponentIds.Delay);
	}

	public Entity addDelay(float duration) {
		Delay component = (Delay) recoverComponent(SmcoreComponentIds.Delay);
		if (component == null) {
			component = new Delay(duration);
		} else {
			component.duration = duration;;
			component.time = 0;
		}
		addComponent(SmcoreComponentIds.Delay, component);
		return this;
	}

	public Entity replaceDelay(float duration) {
		Delay component = (Delay) recoverComponent(SmcoreComponentIds.Delay);
		if (component == null) {
			component = new Delay(duration);
		} else {
			component.duration = duration;;
			component.time = 0;
		}
		replaceComponent(SmcoreComponentIds.Delay, component);
		return this;
	}

	public Entity removeDelay() {
		removeComponent(SmcoreComponentIds.Delay);
		return this;
	}

	public KeyboardSensor getKeyboardSensor() {
		return (KeyboardSensor) getComponent(SmcoreComponentIds.KeyboardSensor);
	}

	public boolean hasKeyboardSensor() {
		return hasComponent(SmcoreComponentIds.KeyboardSensor);
	}

	public Entity addKeyboardSensor(float frequency, boolean invert) {
		KeyboardSensor component = (KeyboardSensor) recoverComponent(SmcoreComponentIds.KeyboardSensor);
		if (component == null) {
			component = new KeyboardSensor(frequency, invert);
		} else {
			super(frequency, invert);
		}
		addComponent(SmcoreComponentIds.KeyboardSensor, component);
		return this;
	}

	public Entity replaceKeyboardSensor(float frequency, boolean invert) {
		KeyboardSensor component = (KeyboardSensor) recoverComponent(SmcoreComponentIds.KeyboardSensor);
		if (component == null) {
			component = new KeyboardSensor(frequency, invert);
		} else {
			super(frequency, invert);
		}
		replaceComponent(SmcoreComponentIds.KeyboardSensor, component);
		return this;
	}

	public Entity removeKeyboardSensor() {
		removeComponent(SmcoreComponentIds.KeyboardSensor);
		return this;
	}

	public Label getLabel() {
		return (Label) getComponent(SmcoreComponentIds.Label);
	}

	public boolean hasLabel() {
		return hasComponent(SmcoreComponentIds.Label);
	}

	public Entity addLabel(String text, String font) {
		Label component = (Label) recoverComponent(SmcoreComponentIds.Label);
		if (component == null) {
			component = new Label(text, font);
		} else {
			component.text = text;;
			component.font = font;
		}
		addComponent(SmcoreComponentIds.Label, component);
		return this;
	}

	public Entity replaceLabel(String text, String font) {
		Label component = (Label) recoverComponent(SmcoreComponentIds.Label);
		if (component == null) {
			component = new Label(text, font);
		} else {
			component.text = text;;
			component.font = font;
		}
		replaceComponent(SmcoreComponentIds.Label, component);
		return this;
	}

	public Entity removeLabel() {
		removeComponent(SmcoreComponentIds.Label);
		return this;
	}

	public Motion getMotion() {
		return (Motion) getComponent(SmcoreComponentIds.Motion);
	}

	public boolean hasMotion() {
		return hasComponent(SmcoreComponentIds.Motion);
	}

	public Entity addMotion(float x, float y) {
		Motion component = (Motion) recoverComponent(SmcoreComponentIds.Motion);
		if (component == null) {
			component = new Motion(x, y);
		} else {
			component.velocity = new Vector2(x, y);
		}
		addComponent(SmcoreComponentIds.Motion, component);
		return this;
	}

	public Entity replaceMotion(float x, float y) {
		Motion component = (Motion) recoverComponent(SmcoreComponentIds.Motion);
		if (component == null) {
			component = new Motion(x, y);
		} else {
			component.velocity = new Vector2(x, y);
		}
		replaceComponent(SmcoreComponentIds.Motion, component);
		return this;
	}

	public Entity removeMotion() {
		removeComponent(SmcoreComponentIds.Motion);
		return this;
	}

	public TransformCollection getTransformCollection() {
		return (TransformCollection) getComponent(SmcoreComponentIds.TransformCollection);
	}

	public boolean hasTransformCollection() {
		return hasComponent(SmcoreComponentIds.TransformCollection);
	}

	public Entity addTransformCollection(Map<String, Transform> transform) {
		TransformCollection component = (TransformCollection) recoverComponent(SmcoreComponentIds.TransformCollection);
		if (component == null) {
			component = new TransformCollection();
		}
		component.transform = transform;
		addComponent(SmcoreComponentIds.TransformCollection, component);
		return this;
	}

	public Entity replaceTransformCollection(Map<String, Transform> transform) {
		TransformCollection component = (TransformCollection) recoverComponent(SmcoreComponentIds.TransformCollection);
		if (component == null) {
			component = new TransformCollection();
		}
		component.transform = transform;
		removeComponent(SmcoreComponentIds.TransformCollection);
		return this;
	}

	public Entity removeTransformCollection() {
		removeComponent(SmcoreComponentIds.TransformCollection);
		return this;
	}

	public ViewCollection getViewCollection() {
		return (ViewCollection) getComponent(SmcoreComponentIds.ViewCollection);
	}

	public boolean hasViewCollection() {
		return hasComponent(SmcoreComponentIds.ViewCollection);
	}

	public Entity addViewCollection(Map<String, TextureView> views) {
		ViewCollection component = (ViewCollection) recoverComponent(SmcoreComponentIds.ViewCollection);
		if (component == null) {
			component = new ViewCollection();
		}
		component.views = views;
		addComponent(SmcoreComponentIds.ViewCollection, component);
		return this;
	}

	public Entity replaceViewCollection(Map<String, TextureView> views) {
		ViewCollection component = (ViewCollection) recoverComponent(SmcoreComponentIds.ViewCollection);
		if (component == null) {
			component = new ViewCollection();
		}
		component.views = views;
		removeComponent(SmcoreComponentIds.ViewCollection);
		return this;
	}

	public Entity removeViewCollection() {
		removeComponent(SmcoreComponentIds.ViewCollection);
		return this;
	}
}