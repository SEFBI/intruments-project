package domian.ports;

import domian.model.enums.Note;

import java.io.File;
import java.util.List;

public interface MusicPlayer {
    void executeNote(Note note);
    void executeMusic(File file);
    void close();
    void stop();
    void continuePlay();
    void setTypeInstrument(int midiInstrument);
}
