package domian.ports;

import domian.model.enums.Note;

import java.util.List;

public interface NotesExecute {
    void playNotes(List<Note> notes);
}
