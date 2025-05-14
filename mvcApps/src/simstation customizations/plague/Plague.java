package plague;

import mvc.Utilities;
import simstation.*;
public class Plague extends MobileAgent{
    private boolean infected;
    private int timeInfected;
    
    public Plague() {
        super();
        infected = false;
        timeInfected = 0;
    }

    public boolean isInfected() {
        return infected;
    }
    public void setInfected(boolean infected) {
        this.infected = infected;
        if (infected) {
            timeInfected = world.getClock();
        }
    }

    // This method tries to infect an infected Agent's neighbor.
    public void infectNeighbor(Plague agent, Plague neighbor) {
        if(agent.isInfected() && !neighbor.isInfected()) {
            if (shouldInfect()) {
                neighbor.setInfected(true);
                PlagueSimulation.INFECTED++;
            }
        }
    }
    /* Method to determine if an agent is susceptible to infection
    private boolean isSusceptible(Agent agent) {
        int resistanceChance = Utilities.rng.nextInt(CHANCE);
        return resistanceChance >= RESISTANCE;
    }
    */
    
    // Method to determine infection chance
    private boolean shouldInfect() {
        int infectionChance = Utilities.rng.nextInt(PlagueSimulation.MAX_CHANCE);
        return infectionChance <  PlagueSimulation.getVirulence();
    }

    @Override
    public void update() {
        if (infected) {
            int totalTime = world.getClock() - timeInfected;
            if (totalTime >= PlagueSimulation.getInfectionLength()) {
                if (PlagueSimulation.getFatal()) {
                    this.stop(); // host dies
                } else {
                    infected = false;
                    PlagueSimulation.INFECTED--;
                }
            }
        }

        // Attempt to infect a nearby neighbor
        Plague neighbor = (Plague) world.getNeighbor(this, 10);
        if (neighbor != null) {
            this.infectNeighbor(this, neighbor);
        }

        // Move randomly
        this.turn(Heading.random());
        int steps = Utilities.rng.nextInt(10) + 1;
        move(steps);
    }
}
