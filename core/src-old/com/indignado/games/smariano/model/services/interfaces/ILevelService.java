package com.indignado.games.smariano.model.services.interfaces;


import com.indignado.games.smariano.model.entities.Level;

import java.util.List;

/**
 * Created by rubentxu on 26/06/14.
 */
public interface ILevelService {
    void loadAssetLevel(Level level);

    void loadLevels();

    void saveState();

    Level getCurrentLevel();

    void setCurrentLevel(Level currentLevel);

    List<Level> getLevels();

    public void initialize();
}
