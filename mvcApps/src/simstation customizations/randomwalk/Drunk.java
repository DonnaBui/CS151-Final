package randomwalk;
import mvc.Utilities;
import simstation.*;

public class Drunk extends MobileAgent {

    public Drunk() {
        super();
        heading = Heading.random();
    }

    @Override
    public void update() {
        this.turn(Heading.random());
        int steps = Utilities.rng.nextInt(10) + 1;
        move(steps);
    }
}
