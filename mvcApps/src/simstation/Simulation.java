package simstation;

import java.util.*;
import mvc.Model;
import mvc.Utilities;
public class Simulation extends Model {
    protected final static int SIZE = 600;
    transient private Timer timer;
    private int clock;
    private int alive;
    private List<Agent> agents;
    private boolean running;
    private boolean suspended;
    private ObserverAgent observer;

    public Simulation() {
        super();
        running = false;
        suspended = false;
        agents = new LinkedList<Agent>();
        clock = 0;
        alive = 0;
        this.observer = new ObserverAgent("Observer");
    }

    /*
    public void startTimer() {
        timer = new Timer();
        timer.scheduleAtFixedRate(new ClockUpdater(), 1000, 1000);
    }
    private void stopTimer() {
        timer.cancel();
        timer.purge();
    }
    */

    public void start() {
        clock = 0;
        //startTimer();
        agents.clear();
        addAgent(observer);
        populate();
        for (Agent a : agents) {
            a.start();
        }
        running = true;
        suspended = false;
        changed();
    }

    public void suspend() {
        //stopTimer();
        for (Agent a : agents) {
            a.suspend();
        }
        suspended = true;
        changed();
    }

    public void resume() {
        //startTimer();
        for (Agent a : agents) {
            a.resume();
        }
        suspended = false;
        changed();
    }

    public void stop() {
        //stopTimer();
        for (Agent a : agents) {
            a.stop();
        }
        running = false;
        suspended = false;
        changed();
    }

    public void addAgent(Agent ag) {
        agents.add(ag);
        ag.setSimulation(this);
    }

    public synchronized Agent getNeighbor(Agent a, double radius) {
        int startIndex = Utilities.rng.nextInt(agents.size());
        int index = startIndex;
        while (true) {
            Agent neighbor = agents.get(index);
            if (a.distance(neighbor) < radius && !a.equals(neighbor) && !(neighbor instanceof ObserverAgent)) {
                return neighbor;
            }            
            index = (index + 1) % agents.size(); //wrap around
            if (index == startIndex) {
                break;
            }
        }
        return null;
    }


    public void populate() {
        // empty method that will be specified in subclasses
    }

    public List<Agent> getAgents() {
        return agents;
    }

    /* 
    public Timer getTimer() {
        return timer;
    }

    public void setTimer(Timer timer) {
        this.timer = timer;
    }
    */

    public int getClock() {
        return clock / 50; // Divide by 50 to get clock in seconds 
        // Use 1000 divided by time used in sleep()
    }

    public void setClock(int clock) {
        this.clock = clock;
    }

    public int getAlive() {
        return alive;
    }

    public void setAgents(ArrayList<Agent> agents) {
        this.agents = agents;
    }

    /* 
    public int getAgentsSize() {
        return agents.size();
    }
    */

    public boolean running() {
        return running;
    }

    public boolean suspended() {
        return suspended;
    }

    /* 
    private class ClockUpdater extends TimerTask {
        @Override
        public void run() {
            clock++;
        }
    }
    */

     public void updateStatistics() {
        clock++; // increments every 20 ticks or the time used in sleep()
        alive = 0;
        for (Agent a : agents) {
            if (!(a instanceof ObserverAgent) && !a.isStopped()) {
                alive++;
            }
        }
     }
}
