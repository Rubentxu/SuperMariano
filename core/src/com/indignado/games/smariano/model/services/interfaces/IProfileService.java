package com.indignado.games.smariano.model.services.interfaces;


import com.indignado.games.smariano.model.entities.Profile;
import com.indignado.games.smariano.model.managers.StateObserver;

/**
 * Created by Rubentxu on 26/06/14.
 */
public interface IProfileService extends StateObserver {
    Profile retrieveProfile();

    void persist();

    void resetToDefaultProfile();

    Profile getProfile();
}
