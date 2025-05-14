package plague;

import java.io.Serializable;
import javax.swing.JSlider;
import mvc.*;
import simstation.*;
public class PlagueFactory extends SimStationFactory implements Serializable {
    @Override
    public Simulation makeModel() {
        return new PlagueSimulation();
    }
    @Override
    public PlagueView makeView(Model m) {
        return new PlagueView(m);
    }
    @Override
    public String[] getEditCommands() {
        return new String[]{"Start", "Suspend", "Resume", "Stop", "Stats",
        "Population", "Infection Probability", "Infection Length", "Toggle Fatal", "Initial % Infected"};
    }
    @Override
    public String about() {
        return "Simstation: Plague \nNathan Tran, Donna Bui, Matilda Verdejo\n 2025";
    }
    @Override
    public String[] getHelp() {
        return new String[] {"Plague Simulation:", "Press Start to start the simulation.",
                "Press Suspend to pause the simulation while it is running.",
                "Press Resume to resume the simulation after it has been suspended.",
                "Press Stop to stop the simulation.",
                "Press Stats to view the statistics of the simulation. Returns Percent Infected.",
                "Use Edit to change the following values:", 
                "Population, Infection Probability, Infection Length, Fatality, Initial % Infected"};
    }
    public String getTitle() { return "Plague";}


    class PlagueStatsCommand extends StatsCommand {

        public PlagueStatsCommand(Model model) {
            super(model);
        }
        @Override
        protected String[] stats() {
            //String[] defaultStats = super.stats();
            PlagueSimulation simulation = (PlagueSimulation) model;
            String infectPop = simulation.stats();
            return new String[]{"alive agents = " + simulation.getAlive(), "clock = " + simulation.getClock(), infectPop};
        }
    }

    class SetPopulation extends Command {
        Integer value = null;
        public SetPopulation(Model model) { 
            super(model); 
        }
        @Override
        public void execute() {
            if (value == null) {
                String response = Utilities.ask("Population size = ?");
                value = Integer.valueOf(response);
            }
            PlagueSimulation.setPopulation(value);
        }
    }

    class SetInfectionProbability extends Command {
        Integer value = null;
        public SetInfectionProbability(Model model) { 
            super(model); 
        }
        @Override
        public void execute() {
            if (value == null) {
                String response = Utilities.ask("Infection Probability (%) = ?");
                value = Integer.valueOf(response);
            }
            PlagueSimulation.setVirulence(value);
        }
    }

    class SetInfectionLength extends Command {
        Integer value = null;
        public SetInfectionLength(Model model) { 
            super(model); 
        }
        @Override
        public void execute() {
            if (value == null) {
                String response = Utilities.ask("Infection Length = ?");
                value = Integer.valueOf(response);
            }
            PlagueSimulation.setInfectionLength(value);
        }
    }

    class SetFatal extends Command {
        public SetFatal(Model model) { 
            super(model); 
        }
        @Override
        public void execute() {
            PlagueSimulation.setFatal(!PlagueSimulation.getFatal()); // acts as a toggle switch
        }
    }

    class SetInitialInfected extends Command {
        Integer value = null;
        public SetInitialInfected(Model model) { super(model); }
        @Override
        public void execute() {
            if (value == null) {
                String response = Utilities.ask("Initial % Infected = ?");
                value = Integer.valueOf(response);
            }
            PlagueSimulation.setInitialInfected(value);
        }
    }

    @Override
    public Command makeEditCommand(Model model, String type, Object source) {
        Command cmd = super.makeEditCommand(model, type, source);
        if (cmd == null || type.equals("Stats")) {
            switch (type) {
                case "Stats":
                    cmd = new PlagueStatsCommand(model);
                    break;
                case "Population":
                     cmd = new SetPopulation(model);
                    if (source instanceof JSlider)
                        ((SetPopulation)cmd).value = ((JSlider)source).getValue();
                    break;
                case "Infection Probability":
                    cmd = new SetInfectionProbability(model);
                    if (source instanceof JSlider)
                        ((SetInfectionProbability)cmd).value = ((JSlider)source).getValue();
                    break;
                case "Infection Length":
                    cmd = new SetInfectionLength(model);
                    if (source instanceof JSlider)
                        ((SetInfectionLength)cmd).value = ((JSlider)source).getValue();
                    break;
                case "Toggle Fatal":
                    cmd = new SetFatal(model);
                    break;
                case "Initial % Infected":
                    cmd = new SetInitialInfected(model);
                    if (source instanceof JSlider)
                        ((SetInitialInfected)cmd).value = ((JSlider)source).getValue();
                    break;
                default:
                    break;
                }
            }
        return cmd;
    }
}