package com.indignado.games.states.game.data;


import static com.indignado.games.states.game.data.Sensor.Pulse.PM_IDLE;

public abstract class Sensor {
    // config Values
    protected float frequency = 0;
    protected boolean invert = false;
    protected int pulse = PM_IDLE.value;

    // values
    public float tick = 0;
    public boolean positive = false;
    public boolean firstExec = true;
    public boolean initialized = false;


     public Sensor(float frequency, boolean invert) {
        this.frequency = frequency;
        this.invert = invert;
        this.pulse = PM_IDLE.getValue();;
        tick = 0;
        positive = false;
        firstExec = true;
        initialized = false;

    }

    public void reset() {
        frequency = 60;
        invert = false;
        pulse = PM_IDLE.getValue();
        tick = 0;
        positive = false;
        firstExec = true;
        initialized = false;

    }

    public enum Pulse {
        PM_IDLE,
        PM_TRUE,
        PM_FALSE;

        private int value;

        Pulse() {
            this.value = 1 << ordinal();

        }

        public static boolean isPositivePulseMode(Sensor sensor) {
            return (sensor.pulse & Pulse.PM_TRUE.value) != 0;

        }

        public static boolean isNegativePulseMode(Sensor sensor) {
            return (sensor.pulse & Pulse.PM_FALSE.value) != 0;

        }

        public int getValue() {
            return value;

        }

    }

}
