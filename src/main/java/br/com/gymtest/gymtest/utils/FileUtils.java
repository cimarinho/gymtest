package br.com.gymtest.gymtest.utils;

import br.com.gymtest.gymtest.domain.Lap;
import br.com.gymtest.gymtest.domain.Pilot;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalTime;
import java.util.*;

@Component
public class FileUtils {

    public Map<String, Pilot> createMapPilot(String[] lines) {
        Map<String, Pilot> pilots = new HashMap<>();
        Arrays.stream(lines).skip(1).forEach(line -> {
            Map<String, String> columnName = columnName(line);
            List<Lap> laps = laps(pilots, columnName.get("pilotCode"));
            laps.add(Lap.getLap(Integer.valueOf(columnName.get("lapNumber")), getTime(columnName.get("duration")), speed(columnName.get("averageSpeed"))));
            Pilot p = Pilot.getPilot(columnName.get("pilotCode"), columnName.get("pilotName"), laps, LocalTime.parse(columnName.get("hour")));
            pilots.put(columnName.get("pilotCode"), p);
        });

        return pilots;
    }

    List<Lap> laps(Map<String, Pilot> pilots, String pilotCode) {
        if (pilots.containsKey(pilotCode)) {
            return pilots.get(pilotCode).getLaps();
        } else {
            return new ArrayList<>();
        }
    }

    Duration getTime(String time) {
        SimpleDateFormat durationFormat = new SimpleDateFormat("m:ss.SSS");
        Duration dur = null;
        try {
            dur = Duration.ofMillis(durationFormat.parse(time).getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dur;
    }

    Float speed(String value) {
        return Float.valueOf(value.replace(",", "."));
    }

    public Map<String, String> columnName(String line) {
        Map<String, String> maps = new HashMap<>();
        String column[] = line.split("\\s+");
        maps.put("hour", column[0]);
        maps.put("pilotCode", column[1]);
        maps.put("pilotName", column[3]);
        maps.put("lapNumber", column[4]);
        maps.put("duration", column[5]);
        maps.put("averageSpeed", column[6]);
        return maps;

    }

}
