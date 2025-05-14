package prisoner;

import java.io.Serializable;
import mvc.*;
import simstation.*;


public class PrisonerFactory extends SimStationFactory implements Serializable {
    public PrisonerFactory() {
        super();
    }

    @Override
    public String getTitle() {
        return "Prisoner's Dilemma";
    }

    @Override
    public String[] getHelp() {
        return new String[] {"Prisoner's Dilemma:", "Press Start to start the simulation.",
                "Press Suspend to pause the simulation while it is running.",
                "Press Resume to resume the simulation after it has been suspended.",
                "Press Stop to stop the simulation.",
                "Press Stats to view the statistics of the simulation."};
    }


    @Override
    public PrisonerView makeView(Model m) {
        return new PrisonerView(m);
    }

    @Override
    public PrisonerSim makeModel() {
        return new PrisonerSim();
    }

    @Override
    protected StatsCommand statsCommand(Model model) {
        return new PrisonerStatsCommand(model);
    }

    class PrisonerStatsCommand extends StatsCommand {
        public PrisonerStatsCommand(Model model) {
            super(model);
        }

        @Override
        protected String[] stats() {
            String[] defaultStats = super.stats();
            PrisonerSim simulation = (PrisonerSim)model;
            String[] newStats = simulation.stats();
            return new String[]{defaultStats[0], defaultStats[1], newStats[0], newStats[1], newStats[2], newStats[3]};
        }
    }
}