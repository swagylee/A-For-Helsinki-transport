package fi.helsinki.cs.reittiopas;

import fi.helsinki.cs.tmc.edutestutils.Points;
import fi.helsinki.cs.reittiopas.logic.Stop;
import fi.helsinki.cs.reittiopas.logic.StopGraph;
import fi.helsinki.cs.reittiopas.logic.State;
import static junit.framework.Assert.*;

import org.junit.*;

@Points("AStar")
public class TravelPlannerTest {

    private TravelPlanner travelPlanner;
    private StopGraph verkko;

    @Before
    public void setUp() {

        verkko = new StopGraph("graph.json", "routes.json");
        travelPlanner = new TravelPlanner();
    }

    @Test
    public void etsiReittiPalauttaaAStarHaullaOikeinOsa1() {
        String[] oikeatPysakit = {"1130446",
            "1130442",
            "1140447",
            "1140436",
            "1140439",
            "1140440",
            "1150431",
            "1150433",
            "1150435"};
        int[] oikeatAjat = {
            23,
            19,
            17,
            13,
            12,
            10,
            8,
            7,
            4
        };
        int aika = 4;
        Stop lahto = verkko.getStop("1150435"); //Meilahdentie
        Stop maali = verkko.getStop("1130446"); //Caloniuksenkatu
        State state = travelPlanner.search(lahto, maali, aika);
        for (int i = 0; i < oikeatPysakit.length; i++) {
            if (state == null) {
                fail("Palautuksesi oli liian lyhyt. Palauttamasi listan pituus oli " + i + ", lyhimmän mahdollisen reitin pituus on " + oikeatPysakit.length);
            }

            if (state.getStop() == null) {
                fail("Palautuksesi sisälsi tilan jossa pysäkki oli null");
            }

            int pysakkiNro = oikeatPysakit.length - i;
            assertEquals("Pysäkki nro " + pysakkiNro + " väärin:", oikeatPysakit[i], state.getStop().getCode());
            assertEquals("Kellonaika pysäkillä nro " + pysakkiNro + " väärin:", oikeatAjat[i], state.getCurrentTime());
            state = state.getPrevious();
        }
        assertNull("Palauttamasi reitti oli pidempi kuin " + oikeatPysakit.length, state);

    }

    @Test
    public void etsiReittiPalauttaaAStarHaullaOikeinOsa2() {
        String[] oikeatPysakit = {"1130446",
            "1130442",
            "1140447",
            "1140436",
            "1140439",
            "1140440",
            "1150431",
            "1150433",
            "1150435"};
        int[] oikeatAjat = {
            33,
            29,
            27,
            23,
            22,
            20,
            18,
            17,
            9
        };
        int aika = 9;
        Stop lahto = verkko.getStop("1150435"); //Meilahdentie
        Stop maali = verkko.getStop("1130446"); //Caloniuksenkatu
        State state = travelPlanner.search(lahto, maali, aika);
        for (int i = 0; i < oikeatPysakit.length; i++) {
            if (state == null) {
                fail("Palautuksesi oli liian lyhyt. Palauttamasi listan pituus oli " + i + ", lyhimmän mahdollisen reitin pituus on " + oikeatPysakit.length);
            }

            if (state.getStop() == null) {
                fail("Palautuksesi sisälsi tilan jossa pysäkki oli null");
            }

            int pysakkiNro = oikeatPysakit.length - i;
            assertEquals("Pysäkki nro " + pysakkiNro + " väärin:", oikeatPysakit[i], state.getStop().getCode());
            assertEquals("Kellonaika pysäkillä nro " + pysakkiNro + " väärin:", oikeatAjat[i], state.getCurrentTime());
            state = state.getPrevious();
        }
        assertNull("Palauttamasi reitti oli pidempi kuin " + oikeatPysakit.length, state);

    }

    @Test
    public void etsiReittiPalauttaaAStarHaullaOikeinOsa3() {
        String[] oikeatPysakit = {"1121480",
            "1121438",
            "1220414",
            "1220416",
            "1220418",
            "1220420",
            "1220426",
            "1173416",
            "1173423",
            "1250425",
            "1250427",
            "1250429"};
        int[] oikeatAjat = {
            25,
            24,
            22,
            21,
            20,
            19,
            18,
            17,
            15,
            14,
            13,
            3
        };
        int aika = 3;
        Stop lahto = verkko.getStop("1250429");
        Stop maali = verkko.getStop("1121480");
        State state = travelPlanner.search(lahto, maali, aika);
        for (int i = 0; i < oikeatPysakit.length; i++) {
            if (state == null) {
                fail("Palautuksesi oli liian lyhyt. Palauttamasi listan pituus oli " + i + ", lyhimmän mahdollisen reitin pituus on " + oikeatPysakit.length);
            }

            if (state.getStop() == null) {
                fail("Palautuksesi sisälsi tilan jossa pysäkki oli null");
            }

            int pysakkiNro = oikeatPysakit.length - i;
            assertEquals("Pysäkki nro " + pysakkiNro + " väärin:", oikeatPysakit[i], state.getStop().getCode());
            assertEquals("Kellonaika pysäkillä nro " + pysakkiNro + " väärin:", oikeatAjat[i], state.getCurrentTime());
            state = state.getPrevious();
        }
        assertNull("Palauttamasi reitti oli pidempi kuin " + oikeatPysakit.length, state);

    }
}
