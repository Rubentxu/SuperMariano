package com.indignado.games.smariano.model.services;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.indignado.games.smariano.BaseGame;
import com.indignado.games.smariano.config.constantes.Env;
import com.indignado.games.smariano.model.entities.Level;
import com.indignado.games.smariano.model.services.interfaces.ILevelService;
import com.indignado.games.smariano.model.services.interfaces.IProfileService;
import com.indignado.games.smariano.model.services.interfaces.IResourcesService;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class LevelService implements ILevelService {
    @Inject
    protected IProfileService profileService;
    @Inject
    protected IResourcesService resourcesManager;
    private List<Level> levels;
    private Level currentLevel;


    public LevelService() {
        BaseGame.objectGraph.inject(this);
        levels = new ArrayList<Level>();
        levels = profileService.getProfile().getLevels();
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
        profileService.persist();
    }


    public Level getCurrentLevel() {
        if (currentLevel == null) setCurrentLevel(levels.get(0));
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
