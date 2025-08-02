package domian.factory;

import domian.exceptions.NotFoundInstrumentException;
import domian.model.*;
import domian.model.enums.TypeInstrument;
import domian.ports.MusicPlayer;

public class InstrumentFactory {

    public static InstrumentElement getInstrument(TypeInstrument type, MusicPlayer player) throws NotFoundInstrumentException {
        return switch (type){
            case PIANO -> new Piano(type, player);
            case GUITAR -> new Guitar(type, player);
            case ELECTRIC_GUITAR -> new ElectricGuitar(type, player);
            case VIOLIN -> new Violin(type, player);
            case TRUMPET -> new Trumpet(type, player);
            default -> throw new NotFoundInstrumentException("No se encontro ningun instrumento");
        };
    }
}
