package com.indignado.games.smariano.model.services;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.indignado.games.smariano.BaseGame;
import com.indignado.games.smariano.SuperMariano;
import com.indignado.games.smariano.config.constantes.Env;
import com.indignado.games.smariano.model.entities.Hero;
import com.indignado.games.smariano.model.entities.Item;
import com.indignado.games.smariano.model.entities.base.Box2DPhysicsObject;
import com.indignado.games.smariano.model.entities.base.Box2DPhysicsObject.BaseState;
import com.indignado.games.smariano.model.entities.base.State;
import com.indignado.games.smariano.model.services.interfaces.IAudioService;
import com.indignado.games.smariano.model.services.interfaces.IResourcesService;

import javax.inject.Inject;

public class AudioService implements IAudioService {
    @Inject
    IResourcesService resourcesManager;
    @Inject
    PreferencesService preferencesService;
    private SuperMariano game;
    private Music currentMusicPlaying;
    private String currentNameMusicPlaying;
    private Sound soundToPlay;


    public AudioService() {
        BaseGame.objectGraph.inject(this);
    }

    public void stopMusic() {
        if (currentMusicPlaying != null) {
            currentMusicPlaying.stop();
            String assetFileName = resourcesManager.getAssetManager().getAssetFileName(currentMusicPlaying);
            resourcesManager.getAssetManager().unload(assetFileName);
            Gdx.app.log(Env.LOG, "Stopping and unload current music " + assetFileName);
            currentMusicPlaying = null;
        }
    }

    public void playMusic(String music) {
        if (!preferencesService.music) {
            stopMusic();
            return;
        }
        stopMusic();
        currentMusicPlaying = resourcesManager.getMusic(music);
        if (currentMusicPlaying != null) {
            Gdx.app.log(Env.LOG, "Volume Music: " + preferencesService.volMusic);
            Gdx.app.log(Env.LOG, "Current Music vol: " + currentMusicPlaying.getVolume());
            currentMusicPlaying.play();
            currentMusicPlaying.setLooping(true);
            currentMusicPlaying.setVolume(preferencesService.volMusic);
        }
    }

    public void playSound(String sound) {
        if (!preferencesService.sound) {
            return;
        }

        soundToPlay = resourcesManager.getSound(sound);
        if (soundToPlay != null) {
            Gdx.app.log(Env.LOG, "Volume Music: " + preferencesService.volMusic);
            soundToPlay.play(preferencesService.volSound);
        }
    }

    @Override
    public void onNotify(State state, Box2DPhysicsObject entity) {
        if (entity instanceof Item) {
            switch (((Item) entity).getType()) {
                case COIN:
                    Gdx.app.log(Env.LOG, "Play Sound: Collect COIN.");
                    playSound(ResourceService.PICKUP_COIN_SOUND);
                    break;
                case POWERUP:
                    break;
                case KEY:
                    break;
            }
        }
        Gdx.app.log(Env.LOG, "Play Sound Notify: " + state);
        if (state.equals(Hero.StateHero.JUMPING)) playSound(ResourceService.JUMP_SOUND);
        if (state.equals(BaseState.HURT) || state.equals(BaseState.HIT)) playSound(ResourceService.HIT_SOUND);

    }

    @Override
    public void onNotifyStateTimeLimit(State state, Box2DPhysicsObject entity, float time) {

    }
}

