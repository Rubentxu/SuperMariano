package com.indignado.games.states.game.gen;

import com.ilargia.games.entitas.api.*;
import com.indignado.games.states.game.component.gui.Score;

/**
 * ---------------------------------------------------------------------------
 * '<auto-generated>' This code was generated by CodeGeneratorApp.
 * ---------------------------------------------------------------------------
 */
public class GuiContext extends com.ilargia.games.entitas.Context<GuiEntity> {

	public GuiContext(int totalComponents, int startCreationIndex,
			ContextInfo contextInfo, EntityBaseFactory<GuiEntity> factoryMethod) {
		super(totalComponents, startCreationIndex, contextInfo, factoryMethod);
	}

	public GuiEntity getScoreEntity() {
		return getGroup(GuiMatcher.Score()).getSingleEntity();
	}

	public Score getScore() {
		return getScoreEntity().getScore();
	}

	public boolean hasScore() {
		return getScoreEntity() != null;
	}

	public GuiEntity setScore(int value) {
		if (hasScore()) {
			throw new EntitasException(
					"Could not set Score!" + this
							+ " already has an entity with Score!",
					"You should check if the context already has a ScoreEntity before setting it or use context.ReplaceScore().");
		}
		GuiEntity entity = createEntity();
		entity.addScore(value);
		return entity;
	}

	public GuiEntity replaceScore(int value) {
		GuiEntity entity = getScoreEntity();
		if (entity == null) {
			entity = setScore(value);
		} else {
			entity.replaceScore(value);
		}
		return entity;
	}

	public GuiContext removeScore() {
		destroyEntity(getScoreEntity());
		return this;
	}
}