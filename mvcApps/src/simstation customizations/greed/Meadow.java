package greed;

import simstation.*;

public class Meadow extends Simulation {
    private int waitPenalty = 5;
    private int moveEnergy = 10;
    private int numCows = 50;
    public static final int SIZE = 600;
    private int patchSize = 10;
    private int dim = SIZE / patchSize;
    private int growBackRate = 1;
    private Patch[][] patches;

    public Meadow() {
        super();
        patches = new Patch[dim][dim];
    }

    public void setGrowBackRate(int rate) {
        growBackRate = rate;
    }

    public int getGrowBackRate() {
        return growBackRate;
    }

    public void setMoveEnergy(int energy) {
        moveEnergy = energy;
    }

    public int getMoveEnergy() {
        return moveEnergy;
    }

    public int getWaitPenalty() {
        return waitPenalty;
    }

    public Patch getPatch(int xc, int yc) {
        if (xc >= 0 && xc < dim && yc >= 0 && yc < dim) {
            return patches[xc][yc];
        }
        return null;
    }

    public int getDim() {
        return dim;
    }
    
    @Override
    public void populate() {
        for (int x = 0; x < dim; x++) {
            for (int y = 0; y < dim; y++) {
                Patch patch = new Patch();
                patch.xc = x;
                patch.yc = y;
                patches[x][y] = patch;
                addAgent(patch);
            }
        }

        for (int i = 0; i < numCows; i++) {
            Cow cow = new Cow();
            cow.xc = (int) (Math.random() * dim);
            cow.yc = (int) (Math.random() * dim);
            addAgent(cow);
        }
    }
}
