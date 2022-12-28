package fi.helsinki.cs.reittiopas.logic;

public class Route {

    private String code;
    private String codeShort;
    private String name;
    private int[] x;
    private int[] y;
    private String[] stopCodes;
    private int[] stopTimes;
    
    private Stop[] stops;

    public Route() {
        this.code = "";
        this.codeShort = "";
        this.name = "";
        this.x = null;
        this.y = null;
        this.stopCodes = null;
        this.stopTimes = null;
    }

    /**
     * Short stop code e.g. 4 or 3T. Same in both directions.
     */
    public String getCodeShort() {
        return this.codeShort;
    }

    /**
     * Long code
     */
    public String getCode() {
        return this.code;
    }

    public String getName() {
        return this.name;
    }

    public String[] getStopCodes() {
        return this.stopCodes;
    }

    /**
     * All x coordinates for stops along this route. The order is same as in getStopCodes
     */
    public int[] getXCoordinates() {

        return this.x;
    }

    /**
     * All y coordinates for stops along this route. The order is same as in getStopCodes
     */
    public int[] getYCoordinates() {
        return this.y;
    }

    /**
     * Stopping times for each stop along this route. The order is same as in getStopCodes.
     */
    public int[] getStoppingTimes() {
        return this.stopTimes;
    }
    
    public void setStops(Stop[] stops) {
        this.stops = stops;
    }
    
    public Stop[] getStops() {
        return this.stops;
    }
    
}

