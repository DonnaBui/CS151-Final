package simstation;

import java.io.Serializable;
import mvc.Utilities;

public abstract class Agent implements Runnable, Serializable {
    private String name;
    // protected Heading heading;
    protected Simulation world;
    public int xc, yc; // Made public
    private boolean suspended = false;
    private boolean stopped = false;
    transient protected Thread myThread;
    public abstract void update();
    public Agent(String name) {
        this();
        this.name = name;
    }
    public Agent() {
        super();
        suspended = false;
        stopped = false;
        myThread = null;
        name = "StaticAgent";
        xc = Utilities.rng.nextInt(Simulation.SIZE);
        yc = Utilities.rng.nextInt(Simulation.SIZE);
    }

    @Override
    public void run() {
        myThread = Thread.currentThread();
        suspended();
        onStart();
        while (!stopped) {
            try {
                update();
                Thread.sleep(20);
                suspended();
            } catch (InterruptedException e) {
                Utilities.error(e);
            }
        }
        onExit();
    }

    public synchronized void start() {
        stopped = false;
        suspended = false;
        //world.populate();
        myThread = new Thread(this);
        myThread.start();
    }
    public synchronized void stop() {
        stopped = true;
        //myThread.interrupt();
    }
    public synchronized void suspend() {
        suspended = true;
    }
    public synchronized void resume() {
        notify();
    }

    public synchronized void suspended() {
        try {
            while (!stopped && suspended) {
                onInterrupted();
                wait();
                suspended = false;
            }
        } catch (InterruptedException e) {
            Utilities.error(e);
        }
    }
    
    public void setXc(int xc) {
        this.xc = xc;
    }
    public void setYc(int yc) {
        this.yc = yc;
    }
    public void setSimulation(Simulation world) {
        this.world = world;
    }
    public int getXc() {
        return this.xc;
    }
    public int getYc() {
        return this.yc;
    }
    public String getName() {
        return this.name;
    }
    // Pythagorean theorem to calculate distance between two agents
    public double distance(Agent other) {
        int deltaX = this.xc - other.xc;
        int deltaY = this.yc - other.yc;
        return Math.sqrt(deltaX * deltaX + deltaY * deltaY);
    }
    public synchronized boolean isStopped() {
        return stopped;
    }
    public synchronized boolean isSuspended() {
        return stopped;
    }
    // Empty methods to be called by run() but overridden by subclasses
    public void onStart() {

    }
    public void onInterrupted() {

    }
    public void onExit() {

    }

}
