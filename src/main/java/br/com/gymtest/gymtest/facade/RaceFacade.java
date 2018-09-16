package br.com.gymtest.gymtest.facade;

import br.com.gymtest.gymtest.domain.Pilot;
import br.com.gymtest.gymtest.domain.Race;
import br.com.gymtest.gymtest.utils.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class RaceFacade {

    @Autowired
    private FileUtils fileUtils;

    public Race read(String [] file){
        Map<String, Pilot>  pilos = fileUtils.createMapPilot(file);
        List<Pilot> pilots = new ArrayList<>();
        pilos.forEach((key, value) ->{
            value.calculateBestLap();
            value.calculateRaceTime();
            value.calculateLapQuantity();
            value.calculateAverageSpeed();
            pilots.add(value);
        });

        Race race = Race.getRace(pilots);
        race.calculateBestLap();
        race.position();
        return race;
    }
}


