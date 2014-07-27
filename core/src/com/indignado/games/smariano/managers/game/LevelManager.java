package com.indignado.games.smariano.managers.game;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.indignado.games.smariano.constantes.Env;
import com.indignado.games.smariano.managers.interfaces.ILevelManager;
import com.indignado.games.smariano.managers.interfaces.IProfileManager;
import com.indignado.games.smariano.managers.interfaces.IResourcesManager;
import com.indignado.games.smariano.modelo.Level;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class LevelManager implements ILevelManager {
    private List<Level> levels;
    private Level currentLevel;
    @Inject
    protected IProfileManager profileManager;
    @Inject
    protected IResourcesManager resourcesManager;


    public LevelManager() {
        levels = new ArrayList<Level>();
        levels = profileManager.getProfile().getLevels();
        loadLevels();

    }


    public void loadAssetLevel(Level level) {
        resourcesManager.getAssetManager().load(level.getMap(), TiledMap.class);
        resourcesManager.getAssetManager().load(level.getBackground_03(), Texture.class);
        resourcesManager.getAssetManager().load(level.getBackground_02(), Texture.class);
        resourcesManager.getAssetManager().load(level.getBackground_01(), Texture.class);
        resourcesManager.getAssetManager().load(level.getMusic(), Music.class);
        resourcesManager.getAssetManager().finishLoading();

    }


    public void loadLevels() {

    }


    public void saveState() {
        profileManager.persist();
    }


    public Level getCurrentLevel() {
        return currentLevel;
    }


    public void setCurrentLevel(Level currentLevel) {
        loadAssetLevel(currentLevel);
        Gdx.app.log(Env.LOG, "Se cargo el mapa: " + currentLevel.getMap());
        this.currentLevel = currentLevel;
    }


    public List<Level> getLevels() {
        return levels;
    }


    @Override
    public void initialize() {

    }

}
