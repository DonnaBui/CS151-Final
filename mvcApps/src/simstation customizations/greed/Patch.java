package greed;

import simstation.*;

public class Patch extends Agent {
    private int energy = 100;
    private static int growBackRate = 1;
    private int patchSize = 10;

    public Patch() {
        super();
    }

    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int e) {
        energy = Math.max(0, Math.min(100, e));
    }

    public int getPatchSize() {
        return patchSize;
    }

    public void setPatchSize(int size) {
        patchSize = size;
    }

    public synchronized boolean eatMe(Cow cow, int amt) {
        if (energy >= amt) {
            energy -= amt;
            return true;
        } else {
            return false;
        }
    }

    public synchronized void enteringWaitingRoom(Cow cow) throws InterruptedException {
        while (energy < cow.getGreed()) {
            cow.applyWaitPenalty();
            if (cow.getEnergy() <= 0) {
                cow.passAway();
                return;
            }
            wait();
        }
        energy -= cow.getGreed();
    }

    @Override
    public synchronized void update() {
        if (energy < 100) {
            energy = Math.min(100, energy + growBackRate);
        }
        notifyAll();
    }
}
