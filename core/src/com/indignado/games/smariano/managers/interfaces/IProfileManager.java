package com.indignado.games.smariano.managers.interfaces;


import com.indignado.games.smariano.modelo.Profile;

/**
 * Created by rubentxu on 26/06/14.
 */
public interface IProfileManager {
    Profile retrieveProfile();

    void persist();

    void resetToDefaultProfile();

    Profile getProfile();
}
