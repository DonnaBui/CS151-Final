package prisoner;

import mvc.Utilities;
import simstation.*;

public class Prisoner extends MobileAgent {
    private int fitness;
    private boolean partnerCheated;
    private Strategy strategy;
    private static final int STEP = 5;
    private static final double SEARCH_RADIUS = 10;
    public Prisoner() {
        super();
        fitness = 0;
        partnerCheated = false;
    }

    private synchronized boolean cooperate() {
        return strategy.cooperate();
    }

    private synchronized void updateFitness(int amt) {
        fitness += amt;
    }
    public int getFitness() {
        return fitness;
    }

    public void setFitness(int fitness) {
        this.fitness = fitness;
    }

    public boolean isPartnerCheated() {
        return partnerCheated;
    }

    public void setPartnerCheated(boolean cheated) {
        this.partnerCheated = cheated;
    }

    public Strategy getStrategy() {
        return strategy;
    }

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    @Override
    public void update() {
        this.turn(Heading.random());
        int steps = Utilities.rng.nextInt(STEP) + 1;
        move(steps);
        Prisoner partner = (Prisoner) world.getNeighbor(this, SEARCH_RADIUS);
        if (partner != null) {
            this.play(partner);
        }
    }

    public void play(Prisoner partner) {
        boolean selfCooperate = cooperate();
        boolean partnerCooperates = partner.cooperate();
        if (selfCooperate) {
            if (partnerCooperates) {
                updateFitness(3);
                partner.updateFitness(3);
            } else {
                updateFitness(0);
                partner.updateFitness(5);
            }
        } else {
            if (partnerCooperates) {
                updateFitness(5);
                partner.updateFitness(0);
            } else {
                updateFitness(1);
                partner.updateFitness(1);
            }
        }
        this.partnerCheated = !partnerCooperates;
        partner.partnerCheated = !selfCooperate;
    }
}