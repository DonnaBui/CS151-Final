package greed;

import java.io.Serializable;
import javax.swing.JSlider;
import mvc.*;
import simstation.*;

public class GreedFactory extends SimStationFactory implements Serializable {
    @Override
    public Simulation makeModel() {
        return new GreedSimulation();
    }

    @Override
    public GreedView makeView(Model m) {
        return new GreedView(m);
    }
    @Override
    public String[] getEditCommands() {
        return new String[]{"Start", "Suspend", "Resume", "Stop", "Stats",
                "Greed", "Grow Back Rate", "Move Energy"};
    }

    @Override
    public String about() {
        return "Simstation: Greed \nNathan Tran, Donna Bui, Matilda Verdejo\n 2025";
    }

    @Override
    public String[] getHelp() {
        return new String[] {
                "Greed Simulation:", "Press Start to start the simulation.",
                "Press Pause to pause the simulation while its running.",
                "Press Resume to resume the simulation after its been paused.",
                "Press Stop to stop the simulation.",
                "Press Stats to view the statistics of the simulation, returns # of alive cows and clock"
        };
    }

    @Override
    public String getTitle() {
        return "Greed";
    }

    class SetGreed extends Command {
        Integer value = null;
        public SetGreed(Model model) { super(model); }
        @Override
        public void execute() {
            if (value == null) {
                String resp = Utilities.ask("Greediness = ?");
                value = Integer.valueOf(resp);
            }
            ((GreedSimulation)model).setGreed(value);
        }
    }

    class SetGrowBackRate extends Command {
        Integer value = null;
        public SetGrowBackRate(Model model) { super(model); }
        @Override
        public void execute() {
            if (value == null) {
                String resp = Utilities.ask("Grow Back Rate = ?");
                value = Integer.valueOf(resp);
            }
            ((Meadow)model).setGrowBackRate(value);
        }
    }

    class SetMoveEnergy extends Command {
        Integer value = null;
        public SetMoveEnergy(Model model) { super(model); }
        @Override
        public void execute(){
            if (value == null) {
                String resp = Utilities.ask("Move Energy Cost = ?");
                value = Integer.valueOf(resp);
            }
            ((Meadow)model).setMoveEnergy(value);
        }
    }

    class GreedStatsCommand extends StatsCommand {
        public GreedStatsCommand(Model model) {
            super(model);
        }
        @Override
        protected String[] stats() {
            GreedSimulation simulation = (GreedSimulation) model;
            String aliveCows = simulation.getStats();
            return new String[]{aliveCows, "Clock: " + simulation.getClock()};
        }
    }

    @Override
    public Command makeEditCommand(Model model, String type, Object source) {
        Command cmd = super.makeEditCommand(model, type, source);
        if (cmd == null || type.equals("Stats")) {
            switch(type) {
                case "Stats":
                    cmd = new GreedStatsCommand(model);
                    break;
                case "Greed":
                    cmd = new SetGreed(model);
                    if (source instanceof JSlider)
                        ((SetGreed)cmd).value = ((JSlider)source).getValue();
                    break;

                case "Grow Back Rate":
                    cmd = new SetGrowBackRate(model);
                    if (source instanceof JSlider)
                        ((SetGrowBackRate)cmd).value = ((JSlider)source).getValue();
                    break;

                case "Move Energy":
                    cmd = new SetMoveEnergy(model);
                    if (source instanceof JSlider)
                        ((SetMoveEnergy)cmd).value = ((JSlider)source).getValue();
                    break;
            }
        }
        return cmd;
    }
}