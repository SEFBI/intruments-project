package domian.model.enums;

public enum Note {
    DO("Do",60),
    RE("Re",62),
    MI("Mi",64),
    FA("Fa",65),
    SOL("Sol",67),
    LA("La",70),
    SI("Si",72);

    private final String name;
    private final int valueMIDI;//valor en midi

    Note(String name,int value){
        this.name=name;
        this.valueMIDI=value;
    }

    public String getName() {
        return name;
    }

    public int getValueMIDI() {
        return  valueMIDI;
    }

    @Override
    public String toString() {
        return Double.toString(this.valueMIDI);
    }
}
