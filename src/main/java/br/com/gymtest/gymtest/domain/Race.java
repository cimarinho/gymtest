package br.com.gymtest.gymtest.domain;

import java.util.Comparator;
import java.util.List;

public class Race  {

    private List<Pilot> pilots;
    private Integer numberlaps;
    private BestLap bestLap;

    public static Race getRace(List<Pilot> pilots) {
        Race race = new Race();
        race.pilots = pilots;
        return race;
    }

    public void position() {
        final Comparator<Pilot> comp = Comparator.comparing(Pilot::getLapQuantity);
        this.numberlaps = this.pilots.stream().max(comp).get().getLapQuantity();
        this.pilots = new Pilot().calculatePosition(pilots,this.numberlaps);
    }

    public void calculateBestLap() {
        final Comparator<Pilot> comp = (p1, p2) -> p2.getBestLap().getDuration().compareTo(p1.getBestLap().getDuration());
        Pilot pilot = this.pilots.stream().max(comp).get();
        this.bestLap = BestLap.getBestLap(pilot.getBestLap().getDuration(),pilot);
    }

    public List<Pilot> getPilots() {
        return pilots;
    }

    public Integer getNumberlaps() {
        return numberlaps;
    }

    public BestLap getBestLap() {
        return bestLap;
    }
}
