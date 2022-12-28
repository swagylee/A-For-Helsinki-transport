package fi.helsinki.cs.reittiopas;

import fi.helsinki.cs.reittiopas.logic.Stop;
import fi.helsinki.cs.reittiopas.logic.State;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class TravelPlanner {

    private StateComparator stateComparator;

    /**
     * Implement A*
     *
     */
    public State search(Stop start, Stop goal, int timeAtBeginning) {
        this.stateComparator = new StateComparator(goal);
        PriorityQueue<State> examinees = new PriorityQueue<>(stateComparator);
        State startState = new State(start, null, timeAtBeginning);
        // ... ... ...
        ArrayList<Stop> closinglist = new ArrayList<Stop>();
        ArrayList<State> closinglist2 = new ArrayList<State>();
        examinees.add(startState);
        while(!examinees.isEmpty() && !closinglist.contains(goal)){
            State Node = examinees.poll();
            closinglist2.add(Node);
            closinglist.add(Node.getStop());
            ArrayList<Stop> neighbor = (ArrayList<Stop>)Node.getStop().getNeighbors();
            for(int i = 0 ; i < neighbor.size() ; i ++){
                int ifexist = 0;
                Stop current = Node.getStop();
                if(closinglist.contains(neighbor.get(i))){
                    continue;
                }
                int current_time = Node.getCurrentTime();
                State station = new State(neighbor.get(i), Node, current_time + current.fastestTransition(neighbor.get(i), current_time));
                PriorityQueue<State> newQ = new PriorityQueue<>(stateComparator);
                newQ.addAll(examinees);
                while(!newQ.isEmpty()){
                    State exist = newQ.poll();
                    if(exist.getStop() == station.getStop()){
                        ifexist = 1;
                        if(exist.getCurrentTime() > station.getCurrentTime()){
                            examinees.remove(exist);
                            examinees.add(station);
                            break;
                        }
                    }
                }
                if(ifexist == 0){
                    examinees.add(station);
                }
            }
        }
        if(closinglist.contains(goal)){
            State target = closinglist2.get(closinglist2.size() - 1);
            State pre = target.getPrevious();
            ArrayList<String> messages = new ArrayList<>();
            while(pre!=null){
                messages.add(pre.getStop().getCode());
                pre = pre.getPrevious();
            }
            for(int i = messages.size() - 1; i >= 0 ; i --){
                System.out.println(messages.get(i));
            }
            return target;
        }
        return null;
    }
}
