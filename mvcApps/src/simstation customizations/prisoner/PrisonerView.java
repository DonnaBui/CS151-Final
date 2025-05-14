package prisoner;

import mvc.*;
import simstation.*;
import java.awt.*;

public class PrisonerView extends SimulationView {
    private final Color COLOR_COOPERATE = Color.GREEN;
    private final Color COLOR_RANDOMLY_COOPERATE = Color.YELLOW;
    private final Color COLOR_CHEAT = Color.RED;
    private final Color COLOR_TIT4TAT = Color.BLUE;
    public PrisonerView(Model m) {
        super(m);
        this.setBackground(Color.GRAY);
    }

    @Override
    protected void drawAgents(Graphics g) {
        PrisonerSim simulation = (PrisonerSim)model;
        int offset = AGENT_SIZE / 2;
        g.setColor(Color.WHITE);
        for (Agent a : simulation.getAgents()) {
            if (!(a instanceof ObserverAgent)) {
                Prisoner p = (Prisoner) a;
                switch (p.getStrategy().getType()) {
                    case COOPERATE:
                        g.setColor(COLOR_COOPERATE);
                        break;
                    case RANDOMLY_COOPERATE:
                        g.setColor(COLOR_RANDOMLY_COOPERATE);
                        break;
                    case CHEAT:
                        g.setColor(COLOR_CHEAT);
                        break;
                    case TIT4TAT:
                        g.setColor(COLOR_TIT4TAT);
                        break;
                }
                g.fillOval(p.getXc() - offset, p.getYc() - offset, AGENT_SIZE, AGENT_SIZE);
            }
        }
    }

}