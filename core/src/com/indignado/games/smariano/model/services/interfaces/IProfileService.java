package com.indignado.games.smariano.model.services.interfaces;


import com.indignado.games.smariano.model.entities.Profile;

/**
 * Created by Rubentxu on 26/06/14.
 */
public interface IProfileService {
    Profile retrieveProfile();

    void persist();

    void resetToDefaultProfile();

    Profile getProfile();
}
