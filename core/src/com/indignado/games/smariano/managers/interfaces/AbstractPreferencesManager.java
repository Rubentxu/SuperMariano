package com.indignado.games.smariano.managers.interfaces;

/**
 * Created by Rubentxu on 26/06/14.
 */
public abstract class AbstractPreferencesManager {
    public boolean sound = false;
    public boolean music = false;
    public float volSound = 0.5f;
    public float volMusic = 0.5f;
    public boolean touchPadEnabled = true;

    protected abstract void load();

    protected abstract void save();

}
