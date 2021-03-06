package com.indignado.games.states.game.gen.input;

import com.ilargia.games.entitas.api.*;
import com.indignado.games.states.game.component.input.PlayerInputController;

/**
 * ---------------------------------------------------------------------------
 * '<auto-generated>' This code was generated by CodeGeneratorApp.
 * ---------------------------------------------------------------------------
 */
public class InputContext
		extends
			com.ilargia.games.entitas.Context<InputEntity> {

	public InputContext(int totalComponents, int startCreationIndex,
			ContextInfo contextInfo,
			EntityBaseFactory<InputEntity> factoryMethod) {
		super(totalComponents, startCreationIndex, contextInfo, factoryMethod);
	}

	public InputEntity getPadButtonsEntity() {
		return getGroup(InputMatcher.PadButtons()).getSingleEntity();
	}

	public boolean isPadButtons() {
		return getPadButtonsEntity() != null;
	}

	public InputContext setPadButtons(boolean value) {
		InputEntity entity = getPadButtonsEntity();
		if (value != (entity != null)) {
			if (value) {
				entity.setPadButtons(true);
			} else {
				destroyEntity(entity);
			}
		}
		return this;
	}

	public InputEntity getPlayerInputControllerEntity() {
		return getGroup(InputMatcher.PlayerInputController()).getSingleEntity();
	}

	public PlayerInputController getPlayerInputController() {
		return getPlayerInputControllerEntity().getPlayerInputController();
	}

	public boolean hasPlayerInputController() {
		return getPlayerInputControllerEntity() != null;
	}

	public InputEntity setPlayerInputController(boolean leftPressed,
			boolean rightPressed, boolean jumpPressed) {
		if (hasPlayerInputController()) {
			throw new EntitasException(
					"Could not set PlayerInputController!"
							+ this
							+ " already has an entity with PlayerInputController!",
					"You should check if the context already has a PlayerInputControllerEntity before setting it or use context.ReplacePlayerInputController().");
		}
		InputEntity entity = createEntity();
		entity.addPlayerInputController(leftPressed, rightPressed, jumpPressed);
		return entity;
	}

	public InputEntity replacePlayerInputController(boolean leftPressed,
			boolean rightPressed, boolean jumpPressed) {
		InputEntity entity = getPlayerInputControllerEntity();
		if (entity == null) {
			entity = setPlayerInputController(leftPressed, rightPressed,
					jumpPressed);
		} else {
			entity.replacePlayerInputController(leftPressed, rightPressed,
					jumpPressed);
		}
		return entity;
	}

	public InputContext removePlayerInputController() {
		destroyEntity(getPlayerInputControllerEntity());
		return this;
	}

	public InputEntity getTouchPadEntity() {
		return getGroup(InputMatcher.TouchPad()).getSingleEntity();
	}

	public boolean isTouchPad() {
		return getTouchPadEntity() != null;
	}

	public InputContext setTouchPad(boolean value) {
		InputEntity entity = getTouchPadEntity();
		if (value != (entity != null)) {
			if (value) {
				entity.setTouchPad(true);
			} else {
				destroyEntity(entity);
			}
		}
		return this;
	}
}