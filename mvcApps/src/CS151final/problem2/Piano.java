package CS151final.problem2;

public class Piano implements Instrument {
    @Override
    public void playNote(Note n) {
        System.out.println("Piano playing frequency " + n.getFrequency() 
        + " for duration " + n.getDuration());
    }
}
