package org.java.gestore.eventi;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Inserisci il titolo del programma: ");
        String titoloProgramma = sc.nextLine();
        List<Evento> listaEventi = new ArrayList<>();
        ProgrammiEventi programma = new ProgrammiEventi(titoloProgramma);

        while (true) {
        	
            System.out.println("Menu:");
            System.out.println("1. Aggiungi evento");
            System.out.println("2. Visualizza eventi in una data");
            System.out.println("3. Visualizza numero di eventi");
            System.out.println("4. Svuota lista eventi");
            System.out.println("5. Esci");
            System.out.print("Scelta: ");
            
            int scelta = sc.nextInt();
            sc.nextLine();

            
            if(scelta == 1) {
            	
            	 
                int numeroEventi = 0;
                
                while (true) {
                    System.out.print("Quanti eventi vuoi registrare? ");
                    String input = sc.nextLine();
                    
                    try {
                        numeroEventi = Integer.parseInt(input);
                        break;
                    } catch (NumberFormatException e) {
                        System.err.println("Inserisci un valore numerico valido.");
                    }
                }
                
                for (int i = 0; i < numeroEventi; i++) {
                	Concerto concerto = null;
                	try {
                		System.out.println("\n-----------------------------------");
                        System.out.println("Inserisci i dettagli dell'evento n." + (i + 1));
                        System.out.println("-----------------------------------\n");
                        
                        
                        System.out.print("Nome evento: ");
                        String titolo = sc.nextLine();
                        if (titolo.isEmpty()) {
                            throw new IllegalArgumentException("Il nome dell'evento non può essere vuoto.");
                        }

                        System.out.print("Data evento (anno-mese-giorno): ");
                        String dataInput = sc.nextLine();
                        if (dataInput.isEmpty() || dataInput.length() < 10 || dataInput.length() > 10) {
                            throw new IllegalArgumentException("La data dell'evento non è corretta.");
                        }
                        LocalDate data = LocalDate.parse(dataInput);
                        
                        
                        System.out.print("Numero posti totali: ");
                        int postiInput = Integer.parseInt(sc.nextLine());
                        if (postiInput <= 0) {
                            throw new IllegalArgumentException("Il numero di posti totali deve essere maggiore di 0.");
                        }
                        int postiTotali = postiInput;
                        
                        

                        System.out.print("Ora del concerto (ore:minuti): ");
                        String oraInputString = sc.nextLine();

                        if (oraInputString.isEmpty()) {
                            throw new IllegalArgumentException("L'orario dell'evento non può essere vuoto.");
                        }

                        LocalTime oraConcerto = null;
                        try {
                            oraConcerto = LocalTime.parse(oraInputString, DateTimeFormatter.ofPattern("HH:mm"));
                        } catch (DateTimeParseException e) {
                            throw new IllegalArgumentException("Formato dell'orario non valido. Utilizza il formato HH:mm.");
                        }
                        
                        
                        System.out.print("Prezzo del concerto (##.##): ");
                        BigDecimal prezzoConcerto = new BigDecimal(sc.nextLine());
                        if (prezzoConcerto.toString().isEmpty()) {
                            throw new IllegalArgumentException("Il prezzo dell'evento non può essere vuota.");
                        }

                        
                        concerto = new Concerto(titolo, data, postiTotali, oraConcerto, prezzoConcerto);
                       

                    } catch (IllegalArgumentException e) {
                        System.err.println("Errore di compilazione: " + e.getMessage());
                        i--;
                    }
                	
                    listaEventi.add(concerto);
                    programma.aggiungiEvento(concerto);
                }
                
                System.out.println("\n-----------------------------------");
                System.out.println("Ecco tutti i tuoi eventi: ");
                System.out.println("-----------------------------------");
                
                
                for (int x = 0; x < listaEventi.size(); x++) {
                	System.out.println("\n-----------");
                    System.out.println("Evento n." + (x + 1));
                    System.out.println("-------------");
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
                                        String sumOrDif = sc.nextLine();

                                        if (sumOrDif.equals("1")) {
                                            incremento = Integer.parseInt(input);
                                            listaEventi.get(indexEvento).prenota(incremento);
                                            break;
                                        } else if (sumOrDif.equals("0")) {
                                            incremento = Integer.parseInt(input);
                                            listaEventi.get(indexEvento).disdici(incremento);
                                            break;
                                        } else {
                                            System.err.println("Input non valido. Seleziona '1' per sommare, '0' per sottrarre.");
                                        }
                                    }
                                }
                                
                                System.out.println("-------------------------------------");
                                System.out.println("Aggiornamento dell'evento:");
                                System.out.println("-------------------------------------");
                                System.out.println(listaEventi.get(indexEvento).toStringDettagli());
                                
                            } catch (IllegalArgumentException e) {
                                System.err.println("Errore: " + e.getMessage());
                            }
                        }

                  
                    } else {
                        System.err.println("Numero evento non esiste");
                    }
                }
            	
            }
			if(scelta == 2) {
				
				while(true) {
					
					System.out.print("Inserisci la data (AAAA-MM-GG): ");
					LocalDate dataRicerca = LocalDate.parse(sc.nextLine());
					List<Evento> eventiInData = programma.eventiInData(dataRicerca);
					if (eventiInData.isEmpty()) {
						System.out.println("Nessun evento trovato in questa data.");
					} else {
						System.out.println("Eventi in data " + dataRicerca.toString() + ":");
						for (Evento evento : eventiInData) {
							System.out.println(evento);
						}
					}
					break;
				}
			            	
			}
			if(scelta == 3) {

				System.out.println("Il numero di eventi totali è: " + programma.quantitaEventi());
				
				
				 
				
			}
			if(scelta == 4) {
				 
				 programma.svuotaEventi();
			}
			if(scelta == 5) {
				
				
			}
        }
    }
}
