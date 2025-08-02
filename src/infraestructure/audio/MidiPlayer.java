package infraestructure.audio;

import domian.model.enums.Note;
import domian.ports.MusicPlayer;

import javax.sound.midi.*;
import java.io.File;
import java.io.IOException;

public class MidiPlayer implements MusicPlayer {
    private Sequencer sequencer;
    private Synthesizer synthesizer;
    private MidiChannel midiChannel;
    private static MidiPlayer singletonInstance;

    private MidiPlayer() throws MidiUnavailableException {
        sequencer =  MidiSystem.getSequencer(); //Reproductor MIDI
        synthesizer = MidiSystem.getSynthesizer();//Generador MIDI

        if(sequencer == null) throw new MidiUnavailableException("Secuencia midi no disponible");

        synthesizer.open();
        sequencer.open();
        midiChannel = synthesizer.getChannels()[0];//apertura de canal

        midiChannel.programChange(0);

    }
    public static MidiPlayer getSingletonInstance() throws MidiUnavailableException {
        if (singletonInstance != null){
            return singletonInstance;
        }else {
            singletonInstance = new MidiPlayer();
            return singletonInstance;
        }
    }


    @Override
    public void executeNote(Note note) {
        midiChannel.noteOn(note.getValueMIDI(),50);

        try{
            Thread.sleep(400);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        midiChannel.noteOff(note.getValueMIDI());
    }

    public void closeNote(){
        synthesizer.close();
    }

    @Override
    public void executeMusic(File file) {
        try {
            if (!file.exists()) {
                System.err.println("Archivo no encontrado: " + file.getAbsolutePath());
                return;
            }
            Sequence sequence= MidiSystem.getSequence(file);//secuencia midi

            sequencer.setSequence(sequence);
            sequencer.getTransmitter().setReceiver(synthesizer.getReceiver());//salida de audio
            sequencer.start();
            while (sequencer.isRunning()) {
                Thread.sleep(100);
            }
        } catch (InvalidMidiDataException | IOException | InterruptedException | MidiUnavailableException e) {
            throw new RuntimeException(e);
        } finally {

        }
    }

    @Override
    public void close() {
        closeNote();
        if (sequencer != null && sequencer.isOpen()) {
            sequencer.close();
        }
    }

    @Override
    public void stop() {
        if (sequencer != null) {
            sequencer.stop();
            sequencer.setTickPosition(0);
        }
    }

    @Override
    public void continuePlay() {
        if (sequencer != null && !sequencer.isRunning()) {
            sequencer.start();
        }
    }

    @Override
    public void setTypeInstrument(int midiInstrument) {
        this.midiChannel.programChange(midiInstrument);
    }
}
