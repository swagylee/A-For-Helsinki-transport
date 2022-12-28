package fi.helsinki.cs.reittiopas;

import fi.helsinki.cs.tmc.edutestutils.Points;
import fi.helsinki.cs.reittiopas.logic.Stop;
import fi.helsinki.cs.reittiopas.logic.StopGraph;
import fi.helsinki.cs.reittiopas.logic.State;

import static junit.framework.Assert.*;
import org.junit.*;

@Points("AStar")
public class StateComparatorTest {

    private StopGraph verkko;
    private Stop stop1;
    private Stop maali;
    private Stop meilahdentie;
    private Stop caloniuksenkatu;
    private StateComparator stateComparator;

    @Before
    public void setUp() {
        verkko = new StopGraph("graph.json", "routes.json");
        stop1 = verkko.getStop("1250429");
        maali = verkko.getStop("1121480");

        meilahdentie = verkko.getStop("1150435"); //Meilahdentie
        caloniuksenkatu = verkko.getStop("1130446"); //Caloniuksenkatu
    }

    @Test
    public void heuristiikkaToimii() {
        stateComparator = new StateComparator(maali);
        assertEquals(11.9873, stateComparator.heuristic(stop1), 0.01);
    }

    @Test
    public void heuristiikkaToimii2() {
        stateComparator = new StateComparator(caloniuksenkatu);
        assertEquals(9.9933, stateComparator.heuristic(meilahdentie), 0.01);
    }

    @Test
    public void vertailuToimii() {
        stateComparator = new StateComparator(maali);
        State state1 = new State(stop1, null, 20);
        State state2 = new State(stop1, null, 10);
        assertTrue(stateComparator.compare(state1, state2) > 0);
    }

    @Test
    public void vertailuToimii2() {
        stateComparator = new StateComparator(maali);
        State state1 = new State(stop1, null, 20);
        State state2 = new State(stop1, null, 10);
        assertTrue(stateComparator.compare(state2, state1) < 0);
    }

    @Test
    public void vertailuToimii3() {
        stateComparator = new StateComparator(maali);
        State state1 = new State(stop1, null, 10);
        State state2 = new State(stop1, null, 10);
        assertEquals(0, stateComparator.compare(state1, state2));
    }

    @Test
    public void vertailuToimii4() {
        stateComparator = new StateComparator(maali);
        State state1 = new State(caloniuksenkatu, null, 10);
        State state2 = new State(stop1, null, 10);
        assertTrue(stateComparator.compare(state2, state1) > 0);
    }

    @Test
    public void vertailuToimii5() {
        stateComparator = new StateComparator(maali);
        State state1 = new State(caloniuksenkatu, null, 10);
        State state2 = new State(stop1, null, 10);
        assertTrue(stateComparator.compare(state1, state2) < 0);
    }

}
