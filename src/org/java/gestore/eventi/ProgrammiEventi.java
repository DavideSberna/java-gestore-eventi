package org.java.gestore.eventi;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ProgrammiEventi {
    private String titolo;
    private List<Evento> eventi;

    public ProgrammiEventi(String titolo) {
        this.titolo = titolo;
        this.eventi = new ArrayList<>();
    }

    public void aggiungiEvento(Evento evento) {
        eventi.add(evento);
    }

    public List<Evento> eventiInData(LocalDate data) {
        List<Evento> eventiInData = new ArrayList<>();
        for (Evento evento : eventi) {
            if (evento.getData().isEqual(data)) {
                eventiInData.add(evento);
            }
        }
        return eventiInData;
    }

    public int quantitaEventi() {
        return eventi.size();
    }

    public void svuotaEventi() {
        eventi.clear();
        System.out.println("Eventi cancellati!");
    }
    
    
    @Override
    public String toString() {
        String result = "- " + titolo + " -\n";
        for (Evento evento : eventi) {
            result += "- " + evento.getData() + " - " + evento.getTitolo() + "\n";
        }
        return result;
    }
}
