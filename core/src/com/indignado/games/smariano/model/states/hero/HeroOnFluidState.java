package com.indignado.games.smariano.model.states.hero;

import com.badlogic.gdx.ai.fsm.State;
import com.badlogic.gdx.ai.msg.Telegram;
import com.indignado.games.smariano.model.entities.Hero;


/**
 * Created by Rubentxu on 13/08/14.
 */
public enum HeroOnFluidState implements State<Hero>  {

    SWIMMING {

        @Override
        public void enter(Hero entity) {
            super.enter(entity);

        }

        @Override
        public void exit(Hero entity) {
            super.exit(entity);
        }

        @Override
        public boolean onMessage(Hero entity, Telegram telegram) {
            return super.onMessage(entity, telegram);
        }

        @Override
        public void update(Hero entity) {

        }


    },
    IDLE {
        @Override
        public void update(Hero entity) {

        }
    },
    PROPULSION {
        @Override
        public void update(Hero entity) {

        }
    };

    HeroOnFluidState() {
    }

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
