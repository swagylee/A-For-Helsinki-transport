package fi.helsinki.cs.reittiopas.logic;


public class State {

    private final State previous;
    private final int currentTime;
    private final Stop stop;

    public State(Stop stop, State previous, int currentTime) {
        this.stop = stop;
        this.previous = previous;
        this.currentTime = currentTime;
    }

    public State getPrevious() {
        return previous;
    }

    public Stop getStop() {
        return stop;
    }

    @Override
    public String toString() {
        return "[" + timeAsString() + "]: " + this.stop.toString();
    }

    private String timeAsString() {
        if (this.currentTime >= 60) {
            return (this.currentTime - (this.currentTime %60))/60 + "h" + (this.currentTime %60) + "min";
        }

        return this.currentTime + "min";

    }

    public int getCurrentTime() {
        return currentTime;
    }

}
