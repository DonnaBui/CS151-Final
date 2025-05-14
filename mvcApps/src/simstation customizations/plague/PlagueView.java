package plague;

import java.awt.*;
import mvc.*;
import simstation.*;

public class PlagueView extends SimulationView {
    public PlagueView(Model m) {
        super(m);
        this.setBackground(Color.GRAY);
    }
    @Override
    protected void drawAgents(Graphics g) {
        PlagueSimulation simulation = (PlagueSimulation) model;
        int offset = AGENT_SIZE / 2;
        g.setColor(Color.WHITE);
        for (Agent a : simulation.getAgents()) {
            if (!(a instanceof ObserverAgent)) {
                Plague p = (Plague) a;
                if (p.isInfected()) {
                    g.setColor(Color.RED);
                } else {
                    g.setColor(Color.GREEN);
                }
                g.fillOval(p.getXc() - offset, p.getYc() - offset, AGENT_SIZE, AGENT_SIZE);
            }
        }
    }
}
