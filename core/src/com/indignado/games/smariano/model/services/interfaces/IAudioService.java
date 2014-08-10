package com.indignado.games.smariano.model.services.interfaces;

import com.indignado.games.smariano.model.managers.StateObserver;

/**
 * Created by Rubentxu on 26/06/14.
 */
public interface IAudioService extends StateObserver {
    void stopMusic();

    void playMusic(String music);

    void playSound(String sound);
}
