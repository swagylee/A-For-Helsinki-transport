package fi.helsinki.cs.reittiopas.logic;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * A class that represents a stop graph and can read its information from a JSON-file
 */
public class StopGraph {

    /**
     * All stops as an array
     *
     * Note that the graph is directed
     *
     */
    private final Stop[] stops;

    /**
     * Contains the same objects as the stops array.
     * You can use this to look up the stops by their code
     *
     */
    private HashMap<String, Stop> stopMap;


    private Route[] routes;

    private HashMap<String, Route> routeMap;


    public StopGraph(String graphPath, String routePath) {
        JsonArray psArr = readJSON(graphPath);
        Gson gson = new Gson();
        this.stops = new Stop[psArr.size()];
        for (int i = 0; i < psArr.size(); i++) {
            this.stops[i] = gson.fromJson(psArr.get(i), Stop.class);
        }
        stopMap = new HashMap<String, Stop>();
        for (Stop p : stops) {
            this.stopMap.put(p.getCode(), p);
        }

        for (Stop p : stops) {
            Collection<Stop> neighbors = new ArrayList<>();
            for (String s : p.getNeighborStopCodes()) {
                neighbors.add(stopMap.get(s));
            }
            p.setNeighbors(neighbors);
        }

        // Let's read routes from routes.json
        JsonArray lnArr = readJSON(routePath);
        this.routes = new Route[lnArr.size()];

        for (int i = 0; i < lnArr.size(); i++) {
            this.routes[i] = gson.fromJson(lnArr.get(i), Route.class);
        }
        this.routeMap = new HashMap<String, Route>();
        for (Route l : this.routes) {
            this.routeMap.put(l.getCode(), l);
        }
        for (Route l : routes) {
            Stop[] pysakit = new Stop[l.getStopCodes().length];
            for (int i = 0; i < pysakit.length; i++) {
                pysakit[i] = stopMap.get(l.getStopCodes()[i]);
                pysakit[i].addRoute(l);
            }
            l.setStops(pysakit);
        }
        
        for (Stop p : stops) {
            Map<String, String[]> routeCodesToNeighbors = p.routesToNeighborsRouteCodes();
            Map<Stop, Route[]> routesToNeighbors = new HashMap<>();
            for (String s : routeCodesToNeighbors.keySet()) {
                String[] routeCodes = routeCodesToNeighbors.get(s);
                Route[] neighborRoutes = new Route[routeCodes.length];
                for (int i = 0; i < routeCodesToNeighbors.get(s).length; i++) {
                    neighborRoutes[i] = routeMap.get(routeCodesToNeighbors.get(s)[i]);
                }
                routesToNeighbors.put(stopMap.get(s), neighborRoutes);
            }
            p.setRoutesToNeighbors(routesToNeighbors);
        }
    }

    public Stop getStop(String code) {
        return stopMap.get(code);
    }

    public Stop[] getStopList() {
        return stops.clone();
    }

    private String readFileAsString(String filePath) {
        File file = new File(this.getClass().getClassLoader().getResource(filePath).getFile());
        byte[] buffer;
        try {
            buffer = Files.readAllBytes(file.toPath());
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }

        return new String(buffer);
    }

    private JsonArray readJSON(String filePath) {
        JsonParser parser = new JsonParser();
        String json = "";

        json = readFileAsString(filePath);
        JsonArray arr = parser.parse(json).getAsJsonArray();
        return arr;
    }
}
