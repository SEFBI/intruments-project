package domian.model;

import domian.model.enums.Note;
import domian.model.enums.TypeInstrument;
import domian.ports.MusicPlayer;

import java.io.File;
import java.util.List;

public class Violin extends InstrumentElement{

    public Violin(TypeInstrument type, MusicPlayer player) {
        super(type,player);

    }

    @Override
    public void playNotes(List<Note> notes) {
        notes
                .stream()
                .forEach(this.player::executeNote);
    }

    @Override
    public void play(int indexMusic) {
        try{
            File music = this.type.getSongsFiles().get(indexMusic);
            this.player.executeMusic(music);
        }catch (Exception e){
            System.out.println("Not found");
        }
    }
}
