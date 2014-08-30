package com.indignado.games.smariano.model.states.hero;

import com.badlogic.gdx.ai.fsm.DefaultStateMachine;
import com.badlogic.gdx.ai.fsm.State;
import com.badlogic.gdx.ai.fsm.StateMachine;
import com.badlogic.gdx.ai.msg.Telegram;
import com.indignado.games.smariano.controller.WorldController;
import com.indignado.games.smariano.model.entities.Hero;

import static com.indignado.games.smariano.controller.WorldController.keys;


/**
 * Created by Rubentxu on 13/08/14.
 */
public enum HeroState implements State<Hero>  {

    ONGROUND {

        private StateMachine<Hero> heroOnGroundStateMachine;

        @Override
        public void enter(Hero hero) {
            super.enter(hero);
            if (heroOnGroundStateMachine==null) heroOnGroundStateMachine=new DefaultStateMachine<Hero>(hero, HeroOnGroundState.IDLE);

            if (keys.get(WorldController.Keys.LEFT)) {
                heroOnGroundStateMachine.changeState(HeroOnGroundState.WALKING);
                hero.setFacingLeft(true);
            }
            if (keys.get(WorldController.Keys.RIGHT)) {
                heroOnGroundStateMachine.changeState(HeroOnGroundState.WALKING);
                hero.setFacingLeft(false);
            }

            if (!keys.get(WorldController.Keys.LEFT) && !keys.get(WorldController.Keys.RIGHT)) {
                heroOnGroundStateMachine.changeState(HeroOnGroundState.IDLE);

            }
            if (keys.get(WorldController.Keys.JUMP)) {
                if (hero.getStateTime() > 0.2) heroOnGroundStateMachine.changeState(HeroOnGroundState.JUMPING);
            }


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
            heroOnGroundStateMachine.update();
        }


    },
    INFLUID {
        private StateMachine<Hero> heroOnFluidStateMachine;

        @Override
        public void enter(Hero entity) {
            super.enter(entity);
            if (heroOnFluidStateMachine ==null) heroOnFluidStateMachine =new DefaultStateMachine<Hero>(entity, HeroOnAirState.FALL);
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
            heroOnFluidStateMachine.update();
        }

    },
    ONAIR {
        private StateMachine<Hero> heroOnAirStateMachine;

        @Override
        public void enter(Hero entity) {
            super.enter(entity);
            if (heroOnAirStateMachine ==null) heroOnAirStateMachine =new DefaultStateMachine<Hero>(entity, HeroOnFluidState.IDLE);
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
            heroOnAirStateMachine.update();
        }

    };

    protected float stateTime=0;
    protected float stateTimeMin=0;
    protected float stateTimeMax=0;


    HeroState() {
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
