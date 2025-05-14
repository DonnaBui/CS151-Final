package greed;

import java.awt.*;
import mvc.*;
import simstation.*;

public class Cow extends MobileAgent {
    private int energy = 500;
    private static int greediness = 25;
    private Color color = Color.RED;

    public Cow() {
        super();
    }

    public int getGreed() {
        return greediness;
    }

    public int getEnergy() {
        return energy;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color c) {
        this.color = c;
    }

    public void update() {
        if (energy <= 0) {
            return;
        }

        Patch patch = ((Meadow) world).getPatch(xc, yc);
        if (patch == null) {
            move();
            return;
        }
        boolean ate = patch.eatMe(this, greediness);

        if (!ate) {
            int moveCost = ((Meadow) world).getMoveEnergy();
            if (energy >= moveCost) {
                move();
                energy -= moveCost;
            } else {
                try {
                    patch.enteringWaitingRoom(this);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }

        if (energy <= 0) {
            passAway();
        }
    }

    public void reduceEnergy(int amount) {
        energy -= amount;
    }


    public void passAway() {
        energy = 0;
        setColor(Color.WHITE);
        stop();
    }

    public void applyWaitPenalty() {
        int penalty = ((Meadow) world).getWaitPenalty();
        reduceEnergy(penalty);
    }

    public void move() {
        int deltaX = Utilities.rng.nextInt(3) - 1;
        int deltaY = Utilities.rng.nextInt(3) - 1;

        xc = Math.max(0, Math.min(xc + deltaX, ((Meadow)world).getDim() - 1));
        yc = Math.max(0, Math.min(yc + deltaY, ((Meadow)world).getDim() - 1));
    }
}
