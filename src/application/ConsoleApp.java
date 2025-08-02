package application;

import domian.exceptions.NotFoundInstrumentException;
import domian.factory.InstrumentFactory;
import domian.model.InstrumentElement;
import domian.model.enums.Note;
import domian.model.enums.TypeInstrument;
import domian.ports.MusicPlayer;
import domian.ports.NotesExecute;
import domian.ports.Playable;
import infraestructure.audio.MidiPlayer;
import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ConsoleApp {
    private static MusicPlayer player;
    private static InstrumentElement currentInstrument;
    private static boolean isRunning = true;

    public static void app() {
        try (Scanner scanner = new Scanner(System.in)) {
            player = MidiPlayer.getSingletonInstance();

            selectInstrument(scanner);

            while (isRunning) {
                System.out.println("\n=== MENÚ ===");
                System.out.println("1. Tocar notas individuales");
                System.out.println("2. Reproducir secuencia de notas");
                System.out.println("3. Reproducir música predefinida");
                System.out.println("4. Cambiar instrumento");
                System.out.println("5. Detener cancion actual");
                System.out.println("6. Salir");
                System.out.print("Seleccione una opción: ");

                int option = scanner.nextInt();
                scanner.nextLine();

                switch (option) {
                    case 1 -> playSingleNotes(scanner);
                    case 2 -> playNoteSequence(scanner);
                    case 3 -> playPredefinedMusic(scanner);
                    case 4 -> selectInstrument(scanner);
                    case 5 -> stop();
                    case 6 -> isRunning = false;

                    default -> System.out.println("Opción inválida");
                }
            }


        } catch (Exception e) {
            System.err.println("error inesperado");
            app();
        }finally {
            player.close();
        }
    }

    private static void selectInstrument(Scanner scanner) throws NotFoundInstrumentException {
        System.out.println("\nInstrumentos disponibles:");
        Arrays.stream(TypeInstrument.values())
                .forEach(
                        instrument ->
                                System.out.println(
                                        "- " + instrument
                                                .getTypeName()
                                                .charAt(0)
                                                +instrument
                                                .getTypeName()
                                                .substring(1)
                                                .toLowerCase()));

        System.out.print("Seleccione un instrumento: ");
        int input = scanner
                .nextInt();

        currentInstrument = InstrumentFactory.getInstrument(
                Arrays.asList(TypeInstrument.values()).get(input-1),
                player
        );
        System.out.println("Instrumento seleccionado: " + input);
    }

    private static void playSingleNotes(Scanner scanner) {
        System.out.println("\nIngrese notas una por una (DO, RE, etc y ENTER para tocar, 'n' para salir):");

        while (true) {
            System.out.print("Nota: ");
            String input = scanner.nextLine().trim().toUpperCase();

            if (input.equals("N")) break;

            if (input.isEmpty()) continue;

            try {
                List<Note> note = List.of(Note.valueOf(input));
               playNotes(currentInstrument,note);
            } catch (IllegalArgumentException e) {
                System.out.println("Nota inválida.");
            }
        }
    }

    private static void playNoteSequence(Scanner scanner) {
        System.out.print("\nIngrese notas separadas por coma ( DO,RE,MI): ");
        String input = scanner.nextLine();

        try {
            List<Note> notes =Arrays.asList(input.split(","))
                    .stream()
                    .map(String::trim)
                    .map(String::toUpperCase)
                    .map(Note::valueOf)
                    .toList();


                playNotes(currentInstrument,notes);
            System.out.println("Secuencia reproducida");
        } catch (IllegalArgumentException e) {
            System.out.println("Error en las notas: " + e.getMessage());
        }
    }

    private static void stop(){
        player.stop();

    }
    private static void playPredefinedMusic(Scanner scanner) {
        System.out.println("\nCanciones disponibles:");
        List<File> songs = currentInstrument
                .getType()
                .getSongsFiles();


        for (int i = 0; i < songs.size(); i++) {
            System.out.println((i + 1) + ". " + songs.get(i).getName());
        }

        System.out.print("Seleccione una canción: ");
        int selection = scanner.nextInt() - 1;



        if (selection >= 0 && selection < songs.size()) {
            System.out.println("Reproduciendo..."+songs.get(selection).getName());

            Thread playbackThread = new Thread(() -> {
                try {
                   playMusic(currentInstrument,selection);
                } catch (Exception e) {
                    System.out.println("Error al reproducir: " + e.getMessage());
                }
            });

            playbackThread.start();


        } else {
            System.out.println("Selección inválida");
        }
    }

    private static void playMusic(Playable playable, int indexMusic){
        playable.play(indexMusic);
    }
    private static void playNotes(NotesExecute notesExecute,List<Note> notes){
        notesExecute.playNotes(notes);
    }
}