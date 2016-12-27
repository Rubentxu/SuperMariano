package com.indignado.games.smariano.model.services;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ai.fsm.StateMachine;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Base64Coder;
import com.badlogic.gdx.utils.Json;
import com.indignado.games.smariano.BaseGame;
import com.indignado.games.smariano.SuperMariano;
import com.indignado.games.smariano.config.constantes.Env;
import com.indignado.games.smariano.model.entities.Hero;
import com.indignado.games.smariano.model.entities.Item;
import com.indignado.games.smariano.model.entities.Profile;
import com.indignado.games.smariano.model.entities.base.Box2DPhysicsObject;
import com.indignado.games.smariano.model.entities.base.State;
import com.indignado.games.smariano.model.fms.GameState;
import com.indignado.games.smariano.model.services.interfaces.IProfileService;

import javax.inject.Inject;

public class ProfileService implements IProfileService {
    @Inject
    public StateMachine<BaseGame,GameState> gameStateMachine;
    private Profile profile;


    public ProfileService() {
        BaseGame.objectGraph.inject(this);
        this.profile = getProfile();
    }

    public Profile retrieveProfile() {
        profile = null;
        FileHandle profileDataFile = Gdx.files.local(Env.PROFILE_DATA_FILE);
        Gdx.app.log(Env.LOG, "Retrieving profile from: " + profileDataFile.path());

        Json json = new Json();

        if (profileDataFile.exists()) {
            try {
                String profileAsText = profileDataFile.readString().trim();

                if (profileAsText.matches("^[A-Za-z0-9/+=]+$")) {
                    Gdx.app.log(Env.LOG, "Persisted profile is base64 encoded");
                    profileAsText = Base64Coder.decodeString(profileAsText);
                }

                profile = json.fromJson(Profile.class, profileAsText);

            } catch (Exception e) {
                FileHandle initProfileDataFile = Gdx.files.internal(Env.INIT_PROFILE_DATA_FILE);
                Gdx.app.error(Env.LOG, "Retrieving profile from: " + initProfileDataFile.path());
                profile = json.fromJson(Profile.class, initProfileDataFile.readString().trim());
                persist(profile);
            }
        } else {
            FileHandle initProfileDataFile = Gdx.files.internal(Env.INIT_PROFILE_DATA_FILE);
            Gdx.app.log(Env.LOG, "Retrieving profile from: " + initProfileDataFile.path());
            profile = json.fromJson(Profile.class, initProfileDataFile.readString().trim());
            persist(profile);
        }

        return profile;
    }

    protected void persist(Profile profile) {

        FileHandle profileDataFile = Gdx.files.local(Env.PROFILE_DATA_FILE);
        Gdx.app.log(Env.LOG, "Persisting profile in: " + profileDataFile.path());
        Json json = new Json();
        String profileAsText = json.toJson(profile);

        if (!SuperMariano.DEBUG) {
            profileAsText = Base64Coder.encodeString(profileAsText);
        }
        profileDataFile.writeString(profileAsText, false);
    }

    public void persist() {
        if (profile != null) {
            persist(profile);
        }
    }

    public void resetToDefaultProfile() {
        FileHandle profileDataFile = Gdx.files.local(Env.PROFILE_DATA_FILE);
        if (profileDataFile.exists()) profileDataFile.delete();
    }

    public Profile getProfile() {
        if (profile == null) return retrieveProfile();
        return profile;
    }

    @Override
    public void onNotify(State state, Box2DPhysicsObject entity) {

        if (entity instanceof Item) {
            switch (((Item) entity).getType()) {
                case COIN:
                    profile.addCoinsAquired(((Item) entity).getValue());
                    break;
                case POWERUP:
                    break;
                case KEY:
                    break;
            }
        }
    }

    @Override
    public void onNotifyStateTimeLimit(State state, Box2DPhysicsObject entity, float time) {
        Gdx.app.debug(Env.LOG, "NotifyStateTimeLimit ProfileManager....");
        if (state.equals(Box2DPhysicsObject.BaseState.HURT) && entity instanceof Hero) {
            Hero hero = (Hero) entity;
            Gdx.app.log(Env.LOG, "---------------------------------------------------------------------PIERDES VIDA???");
            if (profile.removeLive()) gameStateMachine.changeState(GameState.GAME_OVER);
            hero.setState(Hero.StateHero.IDLE);
        }
    }
}