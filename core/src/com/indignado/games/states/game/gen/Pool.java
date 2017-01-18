package com.indignado.games.states.game.gen;

import com.ilargia.games.entitas.EntityMetaData;
import com.ilargia.games.entitas.interfaces.FactoryEntity;
import com.ilargia.games.entitas.events.EventBus;
import com.ilargia.games.entitas.exceptions.EntitasException;
import com.indignado.games.states.game.components.Background;

/**
 * ---------------------------------------------------------------------------
 * '<auto-generated>' This code was generated by CodeGeneratorApp.
 * ---------------------------------------------------------------------------
 */
public class Pool extends com.ilargia.games.entitas.BasePool<Entity, Pool> {

	public Pool(int totalComponents, int startCreationIndex,
			EntityMetaData metaData, FactoryEntity<Entity> factoryMethod,
			EventBus<Entity> bus) {
		super(totalComponents, startCreationIndex, metaData, bus, factoryMethod);
	}

	public Entity getBackgroundEntity() {
		return getGroup(SmcoreMatcher.Background()).getSingleEntity();
	}

	public Background getBackground() {
		return getBackgroundEntity().getBackground();
	}

	public boolean hasBackground() {
		return getBackgroundEntity() != null;
	}

	public Entity setBackground(Texture front, Texture middle, Texture back) {
		if (hasBackground()) {
			throw new EntitasException(
					"Could not set Background!" + this
							+ " already has an entity with Background!",
					"You should check if the pool already has a BackgroundEntity before setting it or use pool.ReplaceBackground().");
		}
		Entity entity = createEntity();
		entity.addBackground(front, middle, back);
		return entity;
	}

	public Entity replaceBackground(Texture front, Texture middle, Texture back) {
		Entity entity = getBackgroundEntity();
		if (entity == null) {
			entity = setBackground(front, middle, back);
		} else {
			entity.replaceBackground(front, middle, back);
		}
		return entity;
	}

	public Pool removeBackground() {
		destroyEntity(getBackgroundEntity());
		return this;
	}
}