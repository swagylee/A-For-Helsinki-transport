package fi.helsinki.cs.reittiopas.logic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Danger! Don't edit this class!
 *
 * Contains the details of a stop and its neighbors
 */
public class Stop {

    /**
     * A unique identifier for the stop
     */
     private String code;

    private String address;

    private String name;

    /**
     * X-coordinate of the stop.
     */
    private int x;

    /**
     * Y-coordinate of the stop.
     */
    private int y;

    /**
     * Neighboring stops to route codes. Key: neighbor stop code, value: array of route codes.
     *
     */
    private HashMap<String, String[]> neighbors;

    private Map<Stop, Route[]> routesToNeighbors;

    private Collection<Stop> neighborStops;

    private Collection<Route> routes;

    public Stop() {
        this.code = "";
        this.address = "";
        this.name = "";
        this.x = 0;
        this.y = 0;
        this.neighbors = new HashMap<String, String[]>();
    }

    @Override
    public String toString() {
        return this.code + "(" + this.name + ")";
    }

    public String getCode() {
        return code;
    }

    public Collection<String> getNeighborStopCodes() {
        return this.neighbors.keySet();
    }

    public Collection<Stop> getNeighbors() {
        return this.neighborStops;
    }

    public void addRoute(Route route) {
        if (this.routes == null) {
            this.routes = new ArrayList<>();
        }
        routes.add(route);
    }

    public void setRoutes(Collection<Route> routes) {
        this.routes = routes;
    }

    public Collection<Route> getRoutes() {
        return this.routes;
    }


    public int getX() {
        return x;
    }


    public int getY() {
        return y;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.code);
        hash = 97 * hash + Objects.hashCode(this.address);
        hash = 97 * hash + Objects.hashCode(this.name);
        hash = 97 * hash + this.x;
        hash = 97 * hash + this.y;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Stop other = (Stop) obj;
        if (!Objects.equals(this.code, other.code)) {
            return false;
        }
        if (!Objects.equals(this.address, other.address)) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (this.x != other.x) {
            return false;
        }
        if (this.y != other.y) {
            return false;
        }
        return true;
    }

    public void setNeighbors(Collection<Stop> pysakit) {
        this.neighborStops = pysakit;
    }

    public Map<String, String[]> routesToNeighborsRouteCodes() {
        return this.neighbors;
    }

    public void setRoutesToNeighbors(Map<Stop, Route[]> linjat) {
        this.routesToNeighbors = linjat;
    }

    public Route[] getRoutesToNeighbors(Stop p) {
        return this.routesToNeighbors.get(p);
    }

    public int fastestTransition(Stop neighbor, int time) {
        
        if (!this.neighborStops.contains(neighbor)) {
            throw new IllegalArgumentException("Tried to get fastest transition for a stop that is not this stop's neighbor!");
        }
        
        int waitingTime = 1000;
        int travallingTime = 1000;

        for (Route l : this.getRoutesToNeighbors(neighbor)) {
            for (int i = 0; i < l.getStopCodes().length; i++) {

                if (l.getStops()[i].equals(this)) {
                    int wait = (l.getStoppingTimes()[i] % 10) - (time % 10);
                    wait = wait < 0 ? wait + 10 : wait;
                    int matka = l.getStoppingTimes()[i + 1] - l.getStoppingTimes()[i];
                    if (waitingTime + travallingTime > (wait + matka)) {
                        waitingTime = wait;
                        travallingTime = matka;
                    }
                }
            }
        }
        return waitingTime + travallingTime;
    }

}
