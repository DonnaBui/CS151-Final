package flocking;

import java.io.Serializable;
import mvc.*;
import simstation.*;


public class FlockingFactory extends SimStationFactory implements Serializable {
    @Override
    public Model makeModel() {
        return new FlockingSimulation();
    }
    @Override
    public String getTitle() {
        return "Flocking Simulation";
    }

    @Override
    public String[] getHelp() {
        return new String[] {"Flocking Simulation:", "Press Start to start the simulation.",
                "Press Suspend to pause the simulation while it is running.",
                "Press Resume to resume the simulation after it has been suspended.",
                "Press Stop to stop the simulation.",
                "Press Stats to view the statistics of the simulation. Returns the number of birds flying at each speed."};
    }
    @Override
    public String about() {
        return "Simstation: Flocking \nBrandon Nguyen, Charles Manaois, Hruday Prathipati\n 2024";
    }


    @Override
    public Command makeEditCommand(Model model, String type, Object source) {
        if (type.equals("Stats")) {
            return new FlockingStatsCommand(model);
        } else {
            return super.makeEditCommand(model, type, source);
        }
    }

    class FlockingStatsCommand extends StatsCommand {
        public FlockingStatsCommand(Model model) {
            super(model);
        }

        @Override
        protected String[] stats() {
            FlockingSimulation simulation = (FlockingSimulation) model;
            int[] speeds = new int[5];
            for (Agent agent : simulation.getAgents()) {
                Flocking bird = (Flocking) agent;
                speeds[(int) (bird.getSpeed() - 1)]++;
            }
            String[] stats = new String[5];
            for (int i = 0; i < 5; i++) {
                stats[i] = "#birds @ speed " + (i + 1) + "= " + speeds[i];
            }
            return stats;
        }
    }
}
