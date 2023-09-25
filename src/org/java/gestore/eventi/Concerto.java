package org.java.gestore.eventi;

import java.math.BigDecimal;
import java.time.LocalTime;

public class Concerto extends Evento {

	private LocalTime ora;
	private BigDecimal prezzo;
	
	
	public Concerto(String titolo, String data, int postiTotali, LocalTime ora, BigDecimal prezzo) {
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
	
	@Override
	public String toString() {
		 
		return super.toString() + "\n" +
		       "Data --> " + super.getData() + "\n" +
			   "Ora --> " + getOra() + "\n" + 
		       "Prezzo --> " + getPrezzo() + " euro";
	}


	 

}
