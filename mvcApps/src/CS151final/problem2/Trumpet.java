package CS151final.problem2;

public class Trumpet implements Instrument {
    @Override
    public void playNote(Note n) {
        System.out.println("Trumpet playing frequency " + n.getFrequency() 
        + " for duration " + n.getDuration());
    }
}
