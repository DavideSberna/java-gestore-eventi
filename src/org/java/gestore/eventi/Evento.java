package org.java.gestore.eventi;

import java.time.LocalDate;

 

public abstract class Evento {
    private String titolo;
    private LocalDate data;
    private int postiTotali;
    private int postiPrenotati;

    public Evento(String titolo, String data, int postiTotali) {
        setTitolo(titolo);
        setData(data);
        setPostiTotali(postiTotali);
        setPostiPrenotati(0);
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(String data) {
        String[] partiData = data.split("/");
        int anno = Integer.parseInt(partiData[0]);
        int mese = Integer.parseInt(partiData[1]);
        int giorno = Integer.parseInt(partiData[2]);
        this.data = LocalDate.of(anno, mese, giorno);

        if (this.data.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("La data non può essere passata");
        }
    }

    public int getPostiTotali() {
        return postiTotali;
    }

    public int getPostiPrenotati() {
        return postiPrenotati;
    }

    private void setPostiTotali(int postiTotali) {
        if (postiTotali < 0) {
            throw new IllegalArgumentException("Il numero di posti totali non può essere negativo");
        }
        this.postiTotali = postiTotali;
    }

    private void setPostiPrenotati(int postiPrenotati) {
        this.postiPrenotati = postiPrenotati;
    }

    public void prenota(int incremento) {
        if (data.isBefore(LocalDate.now()) || postiPrenotati + incremento < 0 || postiPrenotati + incremento > postiTotali) {
            throw new IllegalArgumentException("Impossibile effettuare la modifica delle prenotazioni.");
        }
        postiPrenotati += incremento;
    }

    public void disdici(int incremento) {
        if (data.isBefore(LocalDate.now()) || postiPrenotati <= 0) {
            throw new IllegalArgumentException("Impossibile effettuare la modifica delle prenotazioni.");
        }
        postiPrenotati -= incremento;
    }

    
    public abstract String dettagliEvento();

    
    public abstract String toStringDettagli();
}
