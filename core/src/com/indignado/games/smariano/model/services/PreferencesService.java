package com.indignado.games.smariano.model.services;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.math.MathUtils;
import com.indignado.games.smariano.config.constantes.Env;
import com.indignado.games.smariano.model.services.interfaces.AbstractPreferencesService;

public class PreferencesService extends AbstractPreferencesService {
    private Preferences preferences;


    public PreferencesService() {
        preferences = Gdx.app.getPreferences(Env.PREFS_NAME);
        load();

    }

    public void load() {
        sound = preferences.getBoolean(Env.PREF_SOUND_ENABLED, true);
        music = preferences.getBoolean(Env.PREF_MUSIC_ENABLED, true);
        volSound = MathUtils.clamp(preferences.getFloat(Env.PREF_VOLUME_SOUND, 0.5f),
                0.0f, 1.0f);
        volMusic = MathUtils.clamp(preferences.getFloat(Env.PREF_VOLUME_MUSIC, 0.5f),
                0.0f, 1.0f);
        touchPadEnabled = preferences.getBoolean(Env.PREF_TOUCHPAD_ENABLED, true);
    }


    public void save() {
        preferences.putBoolean(Env.PREF_SOUND_ENABLED, sound);
        preferences.putBoolean(Env.PREF_MUSIC_ENABLED, music);
        preferences.putFloat(Env.PREF_VOLUME_SOUND, volSound);
        preferences.putFloat(Env.PREF_VOLUME_MUSIC, volMusic);
        preferences.putBoolean(Env.PREF_TOUCHPAD_ENABLED, touchPadEnabled);
        preferences.flush();
        load();
    }

}