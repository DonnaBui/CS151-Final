package CS151final.problem2;

public class TestProblem2 {
public static void main(String[] args) {
        
        Instrument[] instruments = {
            new Piano(), new Flute(), new Trumpet(), new OrganAdapter()
        };

        for (Instrument instrument : instruments) {
            Score score = new Score(instrument);
            score.addNote(new Note(123, 1));  
            score.addNote(new Note(456, 2)); 
            score.addNote(new Note(789, 3)); 
            score.play();
        }
    }
}

