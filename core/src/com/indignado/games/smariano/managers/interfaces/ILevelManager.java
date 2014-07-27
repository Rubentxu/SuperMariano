package com.indignado.games.smariano.managers.interfaces;


import com.indignado.games.smariano.modelo.Level;

import java.util.List;

/**
 * Created by rubentxu on 26/06/14.
 */
public interface ILevelManager {
    void loadAssetLevel(Level level);

    void loadLevels();

    void saveState();

    Level getCurrentLevel();

    void setCurrentLevel(Level currentLevel);

    List<Level> getLevels();

    public void initialize();
}
