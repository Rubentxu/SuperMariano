package com.indignado.games.smariano.model.services.interfaces;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.utils.Disposable;

/**
 * Created by Rubentxu on 22/06/14.
 */
public interface IResourcesService extends Disposable {

    public void loadResourcesSplashScreen();

    public void loadResourcesMenuScreen();

    public AssetManager getAssetManager();

    public Music getMusic(String music);

    public Sound getSound(String sound);

}
