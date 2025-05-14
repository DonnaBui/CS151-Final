package CS151final.problem2;

public class Note {
    private double frequency;
    private double duration;

    // basic constructors 
    public Note(double frequency, double duration) {
        this.frequency = frequency;
        this.duration = duration;
    }

    public double getFrequency() {
        return frequency;
    }

    public double getDuration() {
        return duration;
    }
}

