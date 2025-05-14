package simstation;
import mvc.*;
public class SuspendCommand extends Command {
    public SuspendCommand(Model model) {
        super(model);
    }

    public void execute() {
        Simulation simulation = (Simulation)model;
        if (!simulation.running()) {
            Utilities.inform("Simulation is not running. Start the simulation first.");
        }
        else if (simulation.suspended()) {
            Utilities.inform("Simulation is already suspended. Resume the simulation if you want to continue.");
        }
        else {
            simulation.suspend();
        }

    }
}
