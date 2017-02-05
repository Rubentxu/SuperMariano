package com.indignado.games.states.game.gen;

import com.indignado.games.states.game.component.Background;
import com.indignado.games.states.game.component.Camera;
import com.indignado.games.states.game.component.GameWorld;
import com.indignado.games.states.game.component.Tiled;

/**
 * ---------------------------------------------------------------------------
 * '<auto-generated>' This code was generated by CodeGeneratorApp.
 * ---------------------------------------------------------------------------
 */
public class SceneComponentIds {

	public static final int Background = 0;
	public static final int Camera = 1;
	public static final int GameWorld = 2;
	public static final int Tiled = 3;
	public static final int totalComponents = 4;

	public static String[] componentNames() {
		return new String[]{"Background", "Camera", "GameWorld", "Tiled"};
	}

	public static Class[] componentTypes() {
		return new Class[]{Background.class, Camera.class, GameWorld.class,
				Tiled.class};
	}
}