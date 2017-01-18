package com.indignado.games.states.game.data;


public class KeyboardSensor extends Sensor {
    public KeyboardQuery query;
    public String target = "";

    public void reset(String target) {
        super.reset(0, false, 0);
        target = "";

    }

    public enum Type { KeyUp, keyDown }

    @FunctionalInterface
    public interface KeyboardQuery {
         boolean query(KeyboardSensor sensor, Type type, int keycode, char character);
    }

}