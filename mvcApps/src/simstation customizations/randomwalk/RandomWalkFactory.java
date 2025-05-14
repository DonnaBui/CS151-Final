package randomwalk;

import java.io.Serializable;
import mvc.Model;
import simstation.SimStationFactory;

class RandomWalkFactory extends SimStationFactory implements Serializable {
    @Override
    public Model makeModel() { return new RandomWalkSimulation(); }
    @Override
    public String getTitle() { return "Random Walks";}
}
