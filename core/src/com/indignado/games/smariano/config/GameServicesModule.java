package com.indignado.games.smariano.config;

import com.indignado.games.smariano.model.services.interfaces.*;
import com.indignado.games.smariano.model.services.*;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

/**
 * Created by Rubentxu on 25/06/14.
 */
@Module(library = true)
public class GameServicesModule {

    @Provides
    @Singleton
    IResourcesService provideResourcesManager() {
        return new ResourceService();
    }


    @Provides
    @Singleton
    IAudioService provideAudioManager() {
        return new AudioService();
    }


    @Provides
    @Singleton
    ILevelService provideLevelManager() {
        return new LevelService();
    }


    @Provides
    @Singleton
    AbstractPreferencesService providePreferencesManager() {
        return new PreferencesService();
    }


    @Provides
    @Singleton
    IProfileService provideProfileManager() {
        return new ProfileService();
    }


}
