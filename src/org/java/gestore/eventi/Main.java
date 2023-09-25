package org.java.gestore.eventi;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("\n");
        System.out.print("Quanti eventi vuoi registrare? ");
        int NumeroEventi = Integer.parseInt(sc.nextLine());

        System.out.println("\n-----------------------------------\n");
        List<Evento> listaEventi = new ArrayList<>();

        for (int i = 0; i < NumeroEventi; i++) {
            Evento evento = richiediDettagliEvento(sc, i + 1);
            listaEventi.add(evento);
        }

        System.out.println("\n-----------------------------------\n");
        System.out.println("Ecco tutti i tuoi eventi: ");

        for (int x = 0; x < listaEventi.size(); x++) {
            System.out.println("\n-----------------------------------");
            System.out.println("Evento n." + (x + 1));
            System.out.println("-----------------------------------");
            System.out.println(listaEventi.get(x).toStringDettagli());
        }

        System.out.println("\n-----------------------------------\n");
        System.out.print("Vuoi aggiungere o eliminare delle prenotazioni? (si / no): ");

        boolean modificaPrenotazioni = sc.nextLine().equalsIgnoreCase("si");

        if (modificaPrenotazioni) {
            System.out.print("Scegli il numero dell'evento da modificare: ");
            int indexEvento = Integer.parseInt(sc.nextLine()) - 1;

            if (indexEvento >= 0 && indexEvento < listaEventi.size()) {
                System.out.println("\n-----------------------------------");
                System.out.println("Evento selezionato:");
                System.out.println("-----------------------------------");
                System.out.println(listaEventi.get(indexEvento).toStringDettagli());

                while (true) {
                    System.out.println("\nDigita '+' per aggiungere, '-' per cancellare, o inserisci un numero (o 'exit' per uscire): ");
                    System.out.print("Input: ");
                    String input = sc.nextLine();

                    if (input.equalsIgnoreCase("exit")) {
                        break;
                    }

                    try {
                        int incremento = 0;

                        if (input.equals("+")) {
                            incremento = 1;
                            listaEventi.get(indexEvento).prenota(incremento);
                        } else if (input.equals("-")) {
                            incremento = 1;
                            listaEventi.get(indexEvento).disdici(incremento);
                        } else {
                            while (true) {
                                System.out.print("Seleziona '1' se vuoi sommare, '0' se vuoi sottrarre: ");
                                String scelta = sc.nextLine();

                                if (scelta.equals("1")) {
                                    incremento = Integer.parseInt(input);
                                    listaEventi.get(indexEvento).prenota(incremento);
                                    break;
                                } else if (scelta.equals("0")) {
                                    incremento = Integer.parseInt(input);
                                    listaEventi.get(indexEvento).disdici(incremento);
                                    break;
                                } else {
                                    System.err.println("Input non valido. Seleziona '1' per sommare, '0' per sottrarre.");
                                }
                            }
                        }

                        System.out.println("Aggiornamento dell'evento:");
                        System.out.println(listaEventi.get(indexEvento).toStringDettagli());
                    } catch (IllegalArgumentException e) {
                        System.err.println("Errore: " + e.getMessage());
                    }
                }

                System.out.println("\n-------------------------------------");
                System.out.println("Ecco il tuo evento con i dati finali");
                System.out.println("-------------------------------------");
                System.out.println(listaEventi.get(indexEvento).toStringDettagli());
            } else {
                System.err.println("Numero evento non esiste");
            }
        }

        sc.close();
    }

    public static Evento richiediDettagliEvento(Scanner sc, int numeroEvento) {
        Evento evento = null;

        do {
            try {
                System.out.println("Inserisci i dettagli dell'evento n." + numeroEvento);
                System.out.print("Nome evento: ");
                String title = sc.nextLine();

                if (title.isEmpty()) {
                    throw new IllegalArgumentException("Il nome dell'evento non può essere vuoto.");
                }

                System.out.print("Data evento (anno/mese/giorno): ");
                String data = sc.nextLine();

                System.out.print("Numero posti totali: ");
                int postiTotali = Integer.parseInt(sc.nextLine());

                if (postiTotali <= 0) {
                    throw new IllegalArgumentException("Il numero di posti totali deve essere maggiore di 0.");
                }

                System.out.print("Ora del concerto (ore:minuti): ");
                String oraInput = sc.nextLine();
                LocalTime oraConcerto = LocalTime.parse(oraInput, DateTimeFormatter.ofPattern("HH:mm"));

                System.out.print("Prezzo del concerto (##.##): ");
                String prezzoInput = sc.nextLine();
                BigDecimal prezzoConcerto = new BigDecimal(prezzoInput);

                
                evento = new Concerto(title, data, postiTotali, oraConcerto, prezzoConcerto);
               

            } catch (IllegalArgumentException e) {
                System.err.println("Errore di compilazione: " + e.getMessage());
            }
        } while (evento == null);

        return evento;
    }
}
