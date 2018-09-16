package br.com.gymtest.gymtest.utils;

import br.com.gymtest.gymtest.domain.Pilot;
import org.junit.Before;
import org.junit.Test;
import java.util.Map;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class FileUtilsTest {

    String[] file = {
            "",
            "23:49:11.075      002 – K.RAIKKONEN                       3        1:02.155                        43,408"
    };
    FileUtils fileUtils;

    @Before
    public void setup() {
        fileUtils = new FileUtils();
    }

    @Test
    public void createMapPilot() {
        Map<String, Pilot> map = fileUtils.createMapPilot(file);
        Pilot p = map.get("002");
        assertEquals(p.getPilotName(),"K.RAIKKONEN");
        assertEquals(p.getPilotCode(),"002");
        assertFalse(p.getLaps().isEmpty());
    }


}
