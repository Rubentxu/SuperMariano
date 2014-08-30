package com.indignado.games.smariano.model.states.hero;

import com.badlogic.gdx.ai.fsm.State;
import com.badlogic.gdx.ai.msg.Telegram;
import com.indignado.games.smariano.controller.WorldController;
import com.indignado.games.smariano.model.entities.Hero;

import static com.indignado.games.smariano.controller.WorldController.keys;


/**
 * Created by Rubentxu on 13/08/14.
 */
public enum HeroOnAirState implements State<Hero>  {

    JUMPING {
        @Override
        public void update(Hero hero) {
            if (keys.get(WorldController.Keys.LEFT) || keys.get(WorldController.Keys.RIGHT)) {
                hero.applyPhysicMovingImpulse();
            }
        }


    },
    FALL {
        @Override
        public void update(Hero hero) {
            if (keys.get(WorldController.Keys.LEFT) || keys.get(WorldController.Keys.RIGHT)) {
                hero.applyPhysicMovingImpulse();
            }
        }
    };

    @Override
    public void enter(Hero entity) {
        
    }

    @Override
    public void update(Hero entity) {

    }

    @Override
    public void exit(Hero entity) {

    }

    @Override
    public boolean onMessage(Hero entity, Telegram telegram) {
        return false;
    }
}
