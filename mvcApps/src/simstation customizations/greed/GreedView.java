package greed;

import java.awt.*;
import mvc.*;
import simstation.*;

public class GreedView extends SimulationView {
    public GreedView(Model m) {
        super(m);
        this.setBackground(Color.GRAY);
    }

    @Override
    protected void drawAgents(Graphics g) {
        Meadow meadow = (Meadow) model;
        int patchSize = 10;

        for (Agent a : meadow.getAgents()) {
            if (a instanceof Patch patch) {
                int x = patch.getXc() * patchSize;
                int y = patch.getYc() * patchSize;
                int green = Math.min(255, (int) (255 * (patch.getEnergy() / 100.0)));

                g.setColor(new Color(0, green, 0));
                g.fillRect(x, y, patchSize, patchSize);

                g.setColor(Color.GRAY);
                g.drawRect(x, y, patchSize - 1, patchSize - 1);
            }
        }

        for (Agent a : meadow.getAgents()) {
            if (a instanceof Cow cow) {
                int x = cow.getXc() * patchSize;
                int y = cow.getYc() * patchSize;

                g.setColor(cow.getEnergy() > 0 ? Color.RED : Color.WHITE);
                g.fillOval(x + patchSize / 4, y + patchSize / 4, patchSize / 2, patchSize / 2);
            }
        }
    }
}
