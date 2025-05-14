package simstation;

public abstract class MobileAgent extends Agent {
    protected Heading heading;

    public MobileAgent() {
        super("MobileAgent");
        this.heading = Heading.random();
    }

    public void setHeading(Heading heading) {
        this.heading = heading;
    }
    
    public Heading getHeading() {
        return this.heading;
    }

    public void turn(Heading h) {
        this.heading = h;
    }

    public void move(int steps) {
        int offset = 3; // half of agent size, rounded up
        switch (heading) {
            case NORTH -> yc = ((yc - steps + offset + Simulation.SIZE) % Simulation.SIZE) - offset;
            case SOUTH -> yc = ((yc + steps + offset) % Simulation.SIZE) - offset;
            case EAST -> xc = ((xc + steps + offset) % Simulation.SIZE) - offset;
            case WEST -> xc = ((xc - steps + offset + Simulation.SIZE) % Simulation.SIZE) - offset;
        }
        if (world != null) {
            world.changed();
        }
    }

}