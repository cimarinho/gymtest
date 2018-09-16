package br.com.gymtest.gymtest.domain;

import br.com.gymtest.gymtest.utils.TimeUtils;

import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Pilot {

    private Integer position;
    private Integer lapQuantity;
    private String pilotCode;
    private String pilotName;
    private LocalTime hour;
    private Duration raceTime;
    private String timebehindWinner;
    private Lap bestLap;
    private List<Lap> laps = new ArrayList<>();
    private Double totalAverageSpeed;

    public static Pilot getPilot(String pilotCode, String pilotName, List<Lap> laps, LocalTime hour) {
        Pilot pilot = new Pilot();
        pilot.pilotCode = pilotCode;
        pilot.pilotName = pilotName;
        pilot.laps = laps;
        pilot.hour = hour;
        return pilot;
    }

    public List<Pilot> calculatePosition(List<Pilot> pilots, Integer totalLaps) {
        Map<String, Pilot> pilotMap = pilots.stream().collect(Collectors.toMap(Pilot::getPilotCode, pil -> pil));

        List<Pilot> pilotList = listPilotComplete(pilots, totalLaps);

        int[] idx = {0};
        Pilot pilot = pilotList.get(idx[0]);

        pilotCompleteRace(pilotMap, pilotList, idx, pilot);
        pilotStop(totalLaps, pilotMap, pilotList, idx);
        return pilotList;

    }

    private List<Pilot> listPilotComplete(List<Pilot> pilots, Integer totalLaps) {
        List<Pilot> pilotList = pilots.stream().filter(i -> i.getLapQuantity() == totalLaps).collect(Collectors.toList());
        pilotList.sort(Comparator.comparing(Pilot::getRaceTime));
        return pilotList;
    }

    private void pilotStop(Integer totalLaps, Map<String, Pilot> pilotMap, List<Pilot> pilotList, int[] idx) {
        List<Pilot> pilots1 = pilotMap.values().stream().collect(Collectors.toList());
        pilots1.sort(Comparator.comparing(Pilot::getLapQuantity).reversed());
        pilots1.forEach(item -> {
            item.position = ++idx[0];
            item.timebehindWinner = (totalLaps - item.getLapQuantity()) + " Lap";
            pilotList.add(item);
        });
    }

    private void pilotCompleteRace(Map<String, Pilot> pilotMap, List<Pilot> pilotList, int[] idx, Pilot pilot) {
        pilotList.forEach(item -> {
            pilotMap.remove(item.getPilotCode());
            item.position = ++idx[0];
            if (idx[0] > 1) {
                long time = pilot.getRaceTime().toMillis() - item.getRaceTime().toMillis();
                item.timebehindWinner = TimeUtils.miliToString(time);
            }
        });
    }


    public void calculateBestLap() {
        final Comparator<Lap> comp = (p1, p2) -> p2.getDuration().compareTo(p1.getDuration());
        this.bestLap = this.laps.stream().max(comp).get();
    }

    public void calculateLapQuantity() {
        this.lapQuantity = this.laps.size();
    }

    public void calculateRaceTime() {
        long sum = this.laps.stream().mapToLong(i -> i.getDuration().toMillis()).sum();
        this.raceTime = Duration.ofMillis(sum);
    }

    public void calculateAverageSpeed() {
        Double sum = this.laps.stream().mapToDouble(Lap::getAverageSpeed).sum();
        this.totalAverageSpeed = sum / this.laps.size();
    }

    public Integer getLapQuantity() {
        return lapQuantity;
    }

    public Lap getBestLap() {
        return bestLap;
    }

    public Integer getPosition() {
        return position;
    }

    public String getPilotCode() {
        return pilotCode;
    }

    public String getTimebehindWinner() {
        return timebehindWinner;
    }

    public String getPilotName() {
        return pilotName;
    }

    public List<Lap> getLaps() {
        return laps;
    }

    public Double getTotalAverageSpeed() {
        return totalAverageSpeed;
    }

    public Duration getRaceTime() {
        return raceTime;
    }

    public LocalTime getHour() {
        return hour;
    }
}
