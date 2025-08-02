package domian.model;

import domian.model.enums.Note;
import domian.model.enums.TypeInstrument;
import domian.ports.MusicPlayer;

import java.io.File;
import java.util.List;

public class ElectricGuitar extends Guitar{

    public ElectricGuitar(TypeInstrument type, MusicPlayer player) {
        super(type, player);
        player.setTypeInstrument(type.getMidiInstrument());
    }
}
