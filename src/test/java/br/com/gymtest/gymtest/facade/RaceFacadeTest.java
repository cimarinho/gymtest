package br.com.gymtest.gymtest.facade;

import br.com.gymtest.gymtest.domain.Race;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RaceFacadeTest  {

    String [] file = {
            "",
            "23:49:08.277      038 – F.MASSA                           1		1:00.852                        44,275",
            "23:49:10.858      033 – R.BARRICHELLO                     1		1:01.352                        43,243",
            "23:49:11.075      002 – K.RAIKKONEN                       1        1:02.108                        43,408",
            "23:52:01.796      011 – S.VETTEL                          1        1:03.315                        43,169",
            "23:49:08.277      038 – F.MASSA                           2		1:00.852                        44,275",
            "23:49:11.075      002 – K.RAIKKONEN                       2        1:02.109                        43,408",
            "23:49:10.858      033 – R.BARRICHELLO                     2		1:01.377                        43,243",
            "23:49:08.277      038 – F.MASSA                           3		1:00.866                        44,275",
            "23:49:11.075      002 – K.RAIKKONEN                       3        1:02.155                        43,408"
    };


    @Autowired
    private RaceFacade raceFacade;

    @Test
    public void bestLap(){
        Race race = this.raceFacade.read(file);
        assertEquals(race.getBestLap().getPilot().getPilotName(), "F.MASSA");
    }

    @Test
    public void position_3(){
        Race race = this.raceFacade.read(file);
        assertEquals(race.getPilots().get(2).getPilotName(), "R.BARRICHELLO");
    }

    @Test
    public void position_4(){
        Race race = this.raceFacade.read(file);
        assertEquals(race.getPilots().get(3).getPilotName(), "S.VETTEL");
    }


}
