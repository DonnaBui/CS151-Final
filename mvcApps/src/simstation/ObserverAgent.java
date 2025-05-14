package simstation;

public class ObserverAgent extends Agent {
    
    public ObserverAgent(String name) {
        super(name);
    }
    
    public ObserverAgent() {
        super();
    }
    
    @Override
    public void update() {
        world.updateStatistics();
        world.changed();  
    }
}
