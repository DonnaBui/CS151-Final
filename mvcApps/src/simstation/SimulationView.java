package simstation;

import mvc.*;

import java.awt.*;

public class SimulationView extends View {
    protected final static int AGENT_SIZE = 5;

    public SimulationView(Model model) {
        super(model);
        this.setBackground(Color.GRAY);
    }

    protected void drawAgents(Graphics g) {
        Simulation simulation = (Simulation) model;
        int offset = AGENT_SIZE / 2;
        for (Agent a : simulation.getAgents()) {
            g.setColor(Color.WHITE);
            g.fillOval(a.getXc() - offset, a.getYc() - offset, AGENT_SIZE, AGENT_SIZE);
        }
    }

    public void paintComponent(Graphics gc) {
        super.paintComponent(gc);
        drawAgents(gc);
    }
}