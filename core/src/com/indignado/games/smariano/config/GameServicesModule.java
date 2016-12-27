package com.indignado.games.smariano.config;

import com.indignado.games.smariano.model.services.*;
import com.indignado.games.smariano.model.services.interfaces.IAudioService;
import com.indignado.games.smariano.model.services.interfaces.ILevelService;
import com.indignado.games.smariano.model.services.interfaces.IProfileService;
import com.indignado.games.smariano.model.services.interfaces.IResourcesService;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

/**
 * Created by Rubentxu on 25/06/14.
 */
@Module(injects = {LevelService.class, ProfileService.class, AudioService.class}, library = true, complete = false)
public class GameServicesModule {

    @Provides
    @Singleton
    IResourcesService provideResourceService() {
        return new ResourceService();
    }


    @Provides
    @Singleton
    IAudioService provideAudioService() {
        return new AudioService();
    }


    @Provides
    @Singleton
    ILevelService provideLevelService() {
        return new LevelService();
    }


    @Provides
    @Singleton
    PreferencesService providePreferencesService() {
        return new PreferencesService();
    }


    @Provides
    @Singleton
    IProfileService provideProfileService() {
        return new ProfileService();
    }


}
