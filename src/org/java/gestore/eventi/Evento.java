package org.java.gestore.eventi;

import java.time.LocalDate;


 

public abstract class Evento {
    private String titolo;
    private LocalDate data;
    private int postiTotali;
    private int postiPrenotati;

    public Evento(String titolo, LocalDate data, int postiTotali) {
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

    public void setData(LocalDate data) {
    	
    	if (data.isBefore(LocalDate.now())) {
    		throw new IllegalArgumentException("La data non può essere passata");
    	}
    	
        this.data = data;

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
    
    @Override
    public String toString() {
    	
    	return data.toString() + " - " + titolo;
    }
}
