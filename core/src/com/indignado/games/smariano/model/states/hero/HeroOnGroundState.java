package com.indignado.games.smariano.model.states.hero;

import com.badlogic.gdx.ai.fsm.State;
import com.badlogic.gdx.ai.msg.Telegram;
import com.indignado.games.smariano.controller.WorldController;
import com.indignado.games.smariano.model.entities.Hero;

import static com.indignado.games.smariano.controller.WorldController.keys;


/**
 * Created by Rubentxu on 13/08/14.
 */
public enum HeroOnGroundState implements State<Hero>  {

    IDLE {
        @Override
        public void update(Hero hero) {
            hero.getBodyA().setLinearVelocity(hero.getVelocity().x * 0.9f, hero.getVelocity().y);
            hero.getHeroPhysicsFixture().setFriction(100f);
            hero.getHeroSensorFixture().setFriction(100f);
            hero.getParticleEffectDust().allowCompletion();

        }
    },
    WALKING {
        @Override
        public void update(Hero hero) {
            hero.applyPhysicMovingImpulse();
            hero.getHeroPhysicsFixture().setFriction(0.2f);
            hero.getHeroSensorFixture().setFriction(0.2f);
            if (hero.getParticleEffectDust().isComplete()) hero.getParticleEffectDust().reset();
        }
    },
    JUMPING {
        @Override
        public void update(Hero hero) {
            hero.applyPhysicJumpingImpulse();
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
