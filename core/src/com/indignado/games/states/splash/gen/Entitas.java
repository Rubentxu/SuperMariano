package com.indignado.games.states.splash.gen;

import java.util.Stack;
import com.ilargia.games.entitas.Context;
import com.ilargia.games.entitas.api.*;

/**
 * ---------------------------------------------------------------------------
 * '<auto-generated>' This code was generated by CodeGeneratorApp.
 * ---------------------------------------------------------------------------
 */
public class Entitas {

	public SplashContext splash;

	public Entitas() {
		splash = createSplashContext();
	}

	public SplashContext createSplashContext() {
		return new SplashContext(SplashComponentIds.totalComponents, 0,
				new ContextInfo("Splash", SplashComponentIds.componentNames(),
						SplashComponentIds.componentTypes()),
				factorySplashEntity());
	}

	public Context[] allContexts() {
		return new Context[]{splash};
	}

	public FactoryEntity<SplashEntity> factorySplashEntity() {
		return (int totalComponents, Stack<IComponent>[] componentContexts,
				ContextInfo contextInfo) -> {
			return new SplashEntity(totalComponents, componentContexts,
					contextInfo);
		};
	}
}