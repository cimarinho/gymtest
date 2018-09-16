package br.com.gymtest.gymtest.domain;

import java.time.Duration;

public class BestLap {

    private Duration time;
    private Pilot pilot;

    public static BestLap getBestLap(Duration time, Pilot pilot) {
        BestLap bestLap = new BestLap();
        bestLap.time = time;
        bestLap.pilot = pilot;
        return bestLap;
    }

    public Duration getTime() {
        return time;
    }

    public Pilot getPilot() {
        return pilot;
    }
}
