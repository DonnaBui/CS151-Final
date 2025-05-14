package flocking;

import simstation.*;
import mvc.*;
public class FlockingSimulation extends Simulation {
    private static final int NUM_BIRDS = 50;
    @Override
    public void populate() {
        for (int i = 0; i < NUM_BIRDS; i++) {
            addAgent(new Flocking());
        }
    }
    public static void main(String[] args) {
        AppPanel panel = new SimulationPanel(new FlockingFactory());
        panel.display();
    }
}
