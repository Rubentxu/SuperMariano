package com.indignado.games.states.game.data;

public class DelaySensor extends Sensor {
    // Config Values
    private float delay = 0;
    private float duration = 0;
    private boolean repeat = false;

    // Signal
    public float time = 0;


    public DelaySensor(float frequency, boolean invert, float delay, float duration, boolean repeat) {
        super(frequency, invert);
        this.delay = delay;
        this.duration = duration;
        this.repeat = repeat;


    }

    public void reset() {
        super.reset();
        this.delay = 0;
        this.duration = 0;
        this.repeat = false;
        this.time = 0;

    }

}