package com.indignado.games.smariano.config;

import com.indignado.games.smariano.managers.game.*;
import com.indignado.games.smariano.managers.interfaces.*;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

/**
 * Created by Rubentxu on 25/06/14.
 */
@Module(library = true)
public class ManagersModule {

    @Provides
    @Singleton
    IResourcesManager provideResourcesManager() {
        return new ResourcesManager();
    }


    @Provides
    @Singleton
    IAudioManager provideAudioManager() {
        return new AudioManager();
    }


    @Provides
    @Singleton
    ILevelManager provideLevelManager() {
        return new LevelManager();
    }


    @Provides
    @Singleton
    AbstractPreferencesManager providePreferencesManager() {
        return new PreferencesManager();
    }


    @Provides
    @Singleton
    IProfileManager provideProfileManager() {
        return new ProfileManager();
    }


}
