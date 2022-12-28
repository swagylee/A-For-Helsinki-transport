package fi.helsinki.cs.reittiopas;

import fi.helsinki.cs.reittiopas.logic.Stop;
import fi.helsinki.cs.reittiopas.logic.State;
import java.util.Comparator;

public class StateComparator implements Comparator<State> {

    private final Stop goal;

    public StateComparator(Stop goalStop) {
        this.goal = goalStop;
    }

    /**
     * Implement this
     *
     * @param stop
     * @return Estimated remaining time
     */
    public double heuristic(Stop stop) {
        double x1 = stop.getX();
        double y1 = stop.getY();
        double x2 = goal.getX();
        double y2 = goal.getY();
        double distance = Math.sqrt(Math.pow(x2-x1,2) + Math.pow(y2-y1, 2));
        double travel_time = distance/260;
        return travel_time;
    }

    /**
     * Implement this
     *
     * @param t1
     * @param t2
     * @return result of the comparison
     */
    @Override
    public int compare(State t1, State t2) {
        double result;
        int cost1 = -1;
        int cost2 = -1;
        double travel1 = this.heuristic(t1.getStop());
        double travel2 = this.heuristic(t2.getStop());
        State s1_pre = t1.getPrevious();
        State s2_pre = t2.getPrevious();
        if(s1_pre == null){
            cost1 = t1.getCurrentTime();
        }
        if(s2_pre == null){
            cost2 = t2.getCurrentTime();
        }
        if(s1_pre!= null && s2_pre!=null){
        cost1 = t1.getCurrentTime() + s1_pre.getStop().fastestTransition(t1.getStop(), s1_pre.getCurrentTime());
        cost2 = t2.getCurrentTime() + s2_pre.getStop().fastestTransition(t2.getStop(), s2_pre.getCurrentTime());
        }
        result = travel1+cost1-travel2-cost2;
        if(result > 0){
            return 1;
        }else if (result < 0){
            return -1;
        }
        return 0;
    }

}
