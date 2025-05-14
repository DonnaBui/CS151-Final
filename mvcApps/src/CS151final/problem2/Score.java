package CS151final.problem2;

import java.util.ArrayList;

public class Score {
    private ArrayList<Note> notes;
    private Instrument instrument;

    public Score(Instrument instrument) {
        this.instrument = instrument;
        this.notes = new ArrayList<Note>();
    }
    
    public void addNote(Note n) {
        notes.add(n);
    }

    public void play() {
        for (Note n : notes) {
            instrument.playNote(n);
        }
    }
}
