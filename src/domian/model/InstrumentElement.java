package domian.model;

import domian.ports.MusicPlayer;
import domian.ports.NotesExecute;
import domian.ports.Playable;
import domian.model.enums.Note;
import domian.model.enums.TypeInstrument;

import java.util.List;

public abstract class InstrumentElement implements Playable, NotesExecute {

    protected TypeInstrument type;
    protected MusicPlayer player;

    public InstrumentElement(TypeInstrument type, MusicPlayer player) {
        this.type = type;
        this.player = player;
    }

    @Override
    public abstract void playNotes(List<Note> notes);

    @Override
    public abstract void play(int indexMusic);

    public TypeInstrument getType() {
        return type;
    }

    public void setType(TypeInstrument type) {
        this.type = type;
    }

    public MusicPlayer getPlayer() {
        return player;
    }

    public void setPlayer(MusicPlayer player) {
        this.player = player;
    }
}
