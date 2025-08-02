package domian.model;

import domian.model.enums.Note;
import domian.model.enums.TypeInstrument;
import domian.ports.MusicPlayer;

import java.io.File;
import java.util.List;

public class Trumpet extends InstrumentElement{

    public Trumpet(TypeInstrument type, MusicPlayer player) {
        super(type,player);
        player.setTypeInstrument(type.getMidiInstrument());
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
