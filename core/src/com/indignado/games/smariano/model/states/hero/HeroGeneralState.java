package com.indignado.games.smariano.model.states.hero;

import com.badlogic.gdx.ai.fsm.State;
import com.badlogic.gdx.ai.msg.Telegram;
import com.indignado.games.smariano.model.entities.Hero;


/**
 * Created by Rubentxu on 13/08/14.
 */
public enum HeroGeneralState implements State<Hero>  {

    INIT {

        @Override
        public void enter(Hero hero) {
            super.enter(hero);

        }

        @Override
        public void exit(Hero hero) {
            super.exit(hero);
        }

        @Override
        public boolean onMessage(Hero hero, Telegram telegram) {
            return super.onMessage(hero, telegram);
        }

        @Override
        public void update(Hero hero) {

        }


    },
    DEFAULT {
        @Override
        public void update(Hero hero) {

        }
    },
    DESTROY {
        @Override
        public void update(Hero hero) {

        }
    },
    HURT {
        @Override
        public void update(Hero hero) {

        }
    },
    HIT {
        @Override
        public void update(Hero hero) {

        }
    },
    DEAD {
        @Override
        public void update(Hero hero) {

        }
    };


    @Override
    public void enter(Hero hero) {
        
    }

    @Override
    public void update(Hero hero) {

    }

    @Override
    public void exit(Hero hero) {

    }

    @Override
    public boolean onMessage(Hero hero, Telegram telegram) {
        return false;
    }
}
