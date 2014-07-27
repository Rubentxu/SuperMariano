package com.indignado.games.smariano.managers.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.indignado.games.smariano.SMariano;
import com.indignado.games.smariano.constantes.Env;
import com.indignado.games.smariano.managers.StateObserver;
import com.indignado.games.smariano.managers.interfaces.IAudioManager;
import com.indignado.games.smariano.managers.interfaces.IProfileManager;
import com.indignado.games.smariano.managers.interfaces.IResourcesManager;
import com.indignado.games.smariano.modelo.Hero;
import com.indignado.games.smariano.modelo.Item;
import com.indignado.games.smariano.modelo.base.Box2DPhysicsObject;
import com.indignado.games.smariano.modelo.base.Box2DPhysicsObject.BaseState;
import com.indignado.games.smariano.modelo.base.State;

import javax.inject.Inject;

public class AudioManager implements StateObserver,IAudioManager {
    private SMariano game;
    private Music currentMusicPlaying;
    private String currentNameMusicPlaying;
    private Sound soundToPlay;
    @Inject
    IResourcesManager resourcesManager;




    public void stopMusic() {
        if (currentMusicPlaying != null) {
            currentMusicPlaying.stop();
            String assetFileName=resourcesManager.getAssetManager().getAssetFileName(currentMusicPlaying);
            resourcesManager.getAssetManager().unload(assetFileName);
            Gdx.app.log(Env.LOG, "Stopping and unload current music "+assetFileName);
            currentMusicPlaying = null;
        }
    }

    public void playMusic(String music) {
        if (!game.getPreferencesManager().music) {
            stopMusic();
            return;
        }
        stopMusic();
        currentMusicPlaying = game.getResourcesManager().getMusic(music);
        if (currentMusicPlaying != null) {
            Gdx.app.log(Env.LOG, "Volume Music: " + game.getPreferencesManager().volMusic);
            Gdx.app.log(Env.LOG, "Current Music vol: " + currentMusicPlaying.getVolume());
            currentMusicPlaying.play();
            currentMusicPlaying.setLooping(true);
            currentMusicPlaying.setVolume(game.getPreferencesManager().volMusic);
        }
    }

    public void playSound(String sound) {
        if (!game.getPreferencesManager().sound) {
            return;
        }

        soundToPlay = game.getResourcesManager().getSound(sound);
        if (soundToPlay != null) {
            Gdx.app.log(Env.LOG, "Volume Music: " + game.getPreferencesManager().volMusic);
            soundToPlay.play(game.getPreferencesManager().volSound);
        }
    }

    @Override
    public void onNotify(State state, Box2DPhysicsObject entity) {
        if (entity instanceof Item) {
            switch (((Item) entity).getType()) {
                case COIN:
                    Gdx.app.log(Env.LOG, "Play Sound: Collect COIN.");
                    playSound(ResourcesManager.PICKUP_COIN_SOUND);
                    break;
                case POWERUP:
                    break;
                case KEY:
                    break;
            }
        }
        Gdx.app.log(Env.LOG, "Play Sound Notify: "+state);
        if (state.equals(Hero.StateHero.JUMPING)) playSound(ResourcesManager.JUMP_SOUND);
        if (state.equals(BaseState.HURT) || state.equals(BaseState.HIT)) playSound(ResourcesManager.HIT_SOUND);

    }

    @Override
    public void onNotifyStateTimeLimit(State state, Box2DPhysicsObject entity, float time) {

    }
}

