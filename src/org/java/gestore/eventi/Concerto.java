package org.java.gestore.eventi;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

class Concerto extends Evento {
    private LocalTime ora;
    private BigDecimal prezzo;

    public Concerto(String titolo, LocalDate data, int postiTotali, LocalTime ora, BigDecimal prezzo) {
        super(titolo, data, postiTotali);
        setOra(ora);
        setPrezzo(prezzo);
    }

    public LocalTime getOra() {
        return ora;
    }

    public void setOra(LocalTime ora) {
        this.ora = ora;
    }

    public BigDecimal getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(BigDecimal prezzo) {
        this.prezzo = prezzo;
    }
    
    
    public String getDataOraFormattata() {
        return getData().toString() + " " + ora.toString();
    }

    public String getPrezzoFormattato() {
        return String.format("%.2f€", prezzo);
    }

    @Override
    public String dettagliEvento() {
    	
        return "\nTitolo: " + getTitolo() +
        		"\nData: " + getData() +
        		"\nOra: " + getOra() +
        		"\nPrezzo: " + getPrezzo() +
        		"\nPosti rimanenti: " + (getPostiTotali() - getPostiPrenotati()) +
                "\nPosti Prenotati: " + (getPostiPrenotati() == 0 ? "ancora nessuna prenotazione" : getPostiPrenotati());
    }

    @Override
    public String toStringDettagli() {
        return dettagliEvento();
    }
    
    @Override
    public String toString() {
    	return getDataOraFormattata() + " - " + getTitolo() + " - " + getPrezzoFormattato();
    }
}
