package CS151final.problem2;

public class OrganAdapter implements Instrument {
    private final Organ organ;

    public OrganAdapter() {
        this.organ = new Organ();
    }
    
    public OrganAdapter(Organ organ) {
        this.organ = organ;
    }

    @Override
    public void playNote(Note n) {
        organ.playOrganNote(n);
    }
}
