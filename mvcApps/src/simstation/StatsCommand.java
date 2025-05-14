package simstation;
import mvc.*;
public class StatsCommand extends Command{
    public StatsCommand(Model model) {
        super(model);
    }

    protected String[] stats() {
        Simulation simulation = (Simulation)model;
        System.out.println(simulation.getAlive());
        return new String[]{"alive agents = " + simulation.getAlive(), "clock = " + simulation.getClock()};
    }

    public void execute() {
        Utilities.inform(stats());
    }
}
