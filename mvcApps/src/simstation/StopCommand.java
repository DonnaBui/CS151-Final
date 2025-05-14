package simstation;
import mvc.*;
public class StopCommand extends Command {
    public StopCommand(Model model) {
        super(model);
    }

    public void execute() {
        Simulation simulation = (Simulation)model;
        if (!simulation.running()) {
            Utilities.inform("Simulation is already stopped. Start the simulation again if you want to continue.");
        }
        else {
            simulation.stop();
        }
    }
}
