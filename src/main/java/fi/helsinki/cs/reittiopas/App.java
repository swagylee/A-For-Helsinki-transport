package fi.helsinki.cs.reittiopas;

import fi.helsinki.cs.reittiopas.logic.Stop;
import fi.helsinki.cs.reittiopas.logic.StopGraph;
import fi.helsinki.cs.reittiopas.logic.State;

public class App {

    /**
     * A*-search
     *
     * @param args args
     */
    public static void main(String[] args) {
        StopGraph stopGraph = new StopGraph("graph.json", "routes.json");
        TravelPlanner travelPlanner = new TravelPlanner();
        int startTime = 4;
        Stop start = stopGraph.getStop("1150435"); //Meilahdentie
        Stop goal = stopGraph.getStop("1130446"); //Caloniuksenkatu
        //Stop start = stopGraph.getStop("1250429");
        //Stop goal = stopGraph.getStop("1121480");
        State state = travelPlanner.search(start, goal, startTime);
        while (state != null) {
            System.out.println(state);
            state = state.getPrevious();
        }
    }
}
