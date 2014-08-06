package com.indignado.games.smariano.config;

import com.indignado.games.smariano.model.entities.World;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

/**
 * Created by Rubentxu on 25/06/14.
 */
@Module(library = true,complete = false)
public class GameManagersModule {

    @Provides
    @Singleton
    World provideWorld() {
        return new World();
    }


}
