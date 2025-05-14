package CS151final.problem2;

public class Flute implements Instrument {
    @Override
    public void playNote(Note n) {
        System.out.println("Flute playing frequency " + n.getFrequency() 
        + " for duration " + n.getDuration());
    }
}
