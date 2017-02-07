package com.indignado.games.states.game.gen;

import com.ilargia.games.entitas.api.*;
import com.ilargia.games.entitas.Entity;
import java.util.Stack;
import com.indignado.games.states.game.component.gui.Label;
import com.ilargia.games.entitas.api.IComponent;
import com.indignado.games.states.game.component.gui.Score;
import com.indignado.games.states.game.component.gui.TouchPad;

/**
 * ---------------------------------------------------------------------------
 * '<auto-generated>' This code was generated by CodeGeneratorApp.
 * ---------------------------------------------------------------------------
 */
public class GuiEntity extends Entity {

	public GuiEntity(int totalComponents,
			Stack<IComponent>[] componentContexts, ContextInfo contextInfo) {
		super(totalComponents, componentContexts, contextInfo);
	}

	public Label getLabel() {
		return (Label) getComponent(GuiComponentIds.Label);
	}

	public boolean hasLabel() {
		return hasComponent(GuiComponentIds.Label);
	}

	public GuiEntity addLabel(String text, String font) {
		Label component = (Label) recoverComponent(GuiComponentIds.Label);
		if (component == null) {
			component = new Label(text, font);
		} else {
			component.text = text;;
			component.font = font;
		}
		addComponent(GuiComponentIds.Label, component);
		return this;
	}

	public GuiEntity replaceLabel(String text, String font) {
		Label component = (Label) recoverComponent(GuiComponentIds.Label);
		if (component == null) {
			component = new Label(text, font);
		} else {
			component.text = text;;
			component.font = font;
		}
		replaceComponent(GuiComponentIds.Label, component);
		return this;
	}

	public GuiEntity removeLabel() {
		removeComponent(GuiComponentIds.Label);
		return this;
	}

	public Score getScore() {
		return (Score) getComponent(GuiComponentIds.Score);
	}

	public boolean hasScore() {
		return hasComponent(GuiComponentIds.Score);
	}

	public GuiEntity addScore(int value) {
		Score component = (Score) recoverComponent(GuiComponentIds.Score);
		if (component == null) {
			component = new Score();
		}
		component.value = value;
		addComponent(GuiComponentIds.Score, component);
		return this;
	}

	public GuiEntity replaceScore(int value) {
		Score component = (Score) recoverComponent(GuiComponentIds.Score);
		if (component == null) {
			component = new Score();
		}
		component.value = value;
		replaceComponent(GuiComponentIds.Score, component);
		return this;
	}

	public GuiEntity removeScore() {
		removeComponent(GuiComponentIds.Score);
		return this;
	}

	public TouchPad getTouchPad() {
		return (TouchPad) getComponent(GuiComponentIds.TouchPad);
	}

	public boolean hasTouchPad() {
		return hasComponent(GuiComponentIds.TouchPad);
	}

	public GuiEntity addTouchPad(int value) {
		TouchPad component = (TouchPad) recoverComponent(GuiComponentIds.TouchPad);
		if (component == null) {
			component = new TouchPad();
		}
		component.value = value;
		addComponent(GuiComponentIds.TouchPad, component);
		return this;
	}

	public GuiEntity replaceTouchPad(int value) {
		TouchPad component = (TouchPad) recoverComponent(GuiComponentIds.TouchPad);
		if (component == null) {
			component = new TouchPad();
		}
		component.value = value;
		replaceComponent(GuiComponentIds.TouchPad, component);
		return this;
	}

	public GuiEntity removeTouchPad() {
		removeComponent(GuiComponentIds.TouchPad);
		return this;
	}
}