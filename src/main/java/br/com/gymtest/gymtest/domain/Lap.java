package br.com.gymtest.gymtest.domain;

import java.time.Duration;

public class Lap {

    private Integer lapNumber;
    private Duration duration;
    private float averageSpeed;

    public static Lap getLap(Integer lapNumber, Duration duration, float averageSpeed) {
        Lap lap = new Lap();
        lap.lapNumber = lapNumber;
        lap.duration = duration;
        lap.averageSpeed = averageSpeed;
        return lap;
    }

    public Integer getLapNumber() {
        return lapNumber;
    }

    public Duration getDuration() {
        return duration;
    }

    public float getAverageSpeed() {
        return averageSpeed;
    }
}
