package domian.model.enums;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public enum TypeInstrument {
    GUITAR("Guitar", Arrays.asList(
            new File("assets/guitar/smells-like.mid")
    ),24),
    PIANO("Piano", Arrays.asList(
            new File("assets/piano/debussy-clair-de-lune.mid"),
            new File("assets/piano/thunderstruck.mid"),
            new File("assets/piano/Pirates of the Caribbean - He's a Pirate (3).mid"),
            new File("assets/piano/oge.midi")),0),
    VIOLIN("Violin", Arrays.asList(
            new File("assets/violin/bach_emajor_violin.mid"),
            new File("assets/violin/Bwv1041 Violin Concerto n1 1mov.mid")),40),
    TRUMPET("Trumpet", Arrays.asList(
            new File("assets/trumpet/trumpet-call-from-beethoven-overture-leonore-no3.mid")
    ),56),
    ELECTRIC_GUITAR("Electric Guitar", Arrays.asList(
            new File("assets/guitar_e/come-as-you.mid")
    ),26);

    private final String typeName;
    private final List<File> songsFiles;
    private final int MidiInstrument;//tipo de instrumento en midi

    TypeInstrument(String name, List<File> songs,int midiInstrument){
        this.typeName=name;
        this.songsFiles=songs;
        this.MidiInstrument=midiInstrument;
    }

    public String getTypeName() {
        return typeName;
    }

    public List<File> getSongsFiles() {
        return songsFiles;
    }

    public int getMidiInstrument() {
        return MidiInstrument;
    }
}
