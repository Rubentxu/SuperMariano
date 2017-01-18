package com.indignado.games.states.game.systems;

import com.badlogic.gdx.InputAdapter;
import com.ilargia.games.entitas.Group;
import com.ilargia.games.entitas.interfaces.ISetPool;
import com.indignado.games.states.game.data.KeyboardSensor;
import com.indignado.games.states.game.gen.Entity;
import com.indignado.games.states.game.gen.Pool;
import com.indignado.games.states.game.gen.SmcoreMatcher;


public class KeyboardSensorSystem extends InputAdapter implements ISetPool<Pool> {

    private Group<Entity> _group;

    @Override
    public void setPool(Pool pool) {
        _group = pool.getGroup(SmcoreMatcher.KeyboardSensor());

    }

    public void validate(KeyboardSensor.Type type, int keycode, char character) {
        for (Entity e : _group.getEntities()) {
            KeyboardSensor sensor = e.getKeyboardSensor();
            sensor.positive = sensor.query.query(sensor, type, keycode, character);

        }
    }

    @Override
    public boolean keyDown(int keycode) {
        validate(KeyboardSensor.Type.KeyDown, keycode, '-');
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        validate(KeyboardSensor.Type.KeyUp, keycode, '-');
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        validate(KeyboardSensor.Type.KeyDown, 0, character);
        return false;
    }

}


