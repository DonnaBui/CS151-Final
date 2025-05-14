package prisoner;

import java.text.DecimalFormat;
import mvc.*;
import simstation.*;
public class PrisonerSim extends Simulation {
    private static final int PRISONERS_PER_STRAT = 10;

    public void populate() {
        for (int i = 0; i < PRISONERS_PER_STRAT; i++) {
            for (int j = 0; j < Strategy.COUNT; j++) {
                addAgent(createPrisoner(j));
            }
        }
    }
    
    public Prisoner createPrisoner(int strat) {
        Prisoner p = new Prisoner();
        switch (strat) {
            case 0:
                p.setStrategy(new Strategy.Cooperate(p));
                break;
            case 1:
                p.setStrategy(new Strategy.RandomlyCooperate(p));
                break;
            case 2:
                p.setStrategy(new Strategy.Cheat(p));
                break;
            case 3:
                p.setStrategy(new Strategy.Tit4Tat(p));
                break;
        }
        return p;
    }

    public synchronized String[] stats() {
        String[] msg = new String[Strategy.COUNT];
        double cooperate = 0;
        double randomlyCooperate = 0;
        double cheat = 0;
        double tit4tat = 0;

        for (Agent a : this.getAgents()) {
                if (!(a instanceof ObserverAgent)) {
                Prisoner p = (Prisoner) a;
                double avgFitness = p.getFitness() / (double)PRISONERS_PER_STRAT;
                switch (p.getStrategy().getType()) {
                    case COOPERATE:
                        cooperate += avgFitness;
                        break;
                    case RANDOMLY_COOPERATE:
                        randomlyCooperate += avgFitness;
                        break;
                    case CHEAT:
                        cheat += avgFitness;
                        break;
                    case TIT4TAT:
                        tit4tat += avgFitness;
                        break;
                }
            }
        }
        DecimalFormat df = new DecimalFormat("#.##");
        msg[0] = "Cooperate average fitness = " + Double.parseDouble(df.format(cooperate));
        msg[1] = "Randomly-Cooperate average fitness = " + Double.parseDouble(df.format(randomlyCooperate));
        msg[2] = "Cheat average fitness = " + Double.parseDouble(df.format(cheat));
        msg[3] = "Tit4Tat average fitness = " + Double.parseDouble(df.format(tit4tat));

        return msg;
    }

    public static void main(String[] args) {
        AppPanel panel = new SimulationPanel(new PrisonerFactory());
        panel.display();
    }

}