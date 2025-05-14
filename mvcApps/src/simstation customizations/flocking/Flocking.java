package flocking;

import mvc.*;
import simstation.*;

public class Flocking extends MobileAgent {
    private double speed;
    public Flocking() {
        super();
        speed = Utilities.rng.nextDouble() * 5.0 + 1.0;
        this.turn(Heading.random());
    }
    @Override
    public void update() {
        Flocking neighbor = (Flocking) world.getNeighbor(this, 10.0);
        if (neighbor != null) {
            speed = neighbor.getSpeed();
            heading = neighbor.getHeading();
        }
        move((int) Math.round(speed));
    }
    public double getSpeed() {
        return speed;
    }
}
