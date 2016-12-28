package com.indignado.games.states.splash.systems;

import com.ilargia.games.entitas.interfaces.IExecuteSystem;


public class DelaySystem implements IExecuteSystem/*, ISetPool<Pool>*/ {

    /*   private Group<Entity> _group;


       @Override
       public void setPool(Pool pool) {
           _group = pool.getGroup(CoreMatcher.Delay());

       }
   */
    @Override
    public void execute(float deltatime) {

      /*  for (Entity e : _group.getEntities()) {
            Delay delay = e.getDelay();
            delay.time += deltatime;
            if (delay.time > delay.duration) {
                EGGame.ebus.post(GameEvent.NEXT_STATE);
                delay.time = 0;
            }

        }
*/
    }


}


