package simstation;

import mvc.Command;
import mvc.Model;
import mvc.Utilities;

public class StartCommand extends Command {
    public StartCommand(Model model) {
        super(model);
    }

    public void execute() {
        Simulation simulation = (Simulation)model;
        if (simulation.running()) {
            Utilities.inform("Simulation is already running. Stop the simulation first if you wish to make a new instance.");
        }
        else {
            simulation.start();
        }
    }
}
