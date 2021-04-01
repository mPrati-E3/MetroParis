package it.polito.tdp.metroparis.model;

public class Coppia {
	
	private Fermata Partenza;
	private Fermata Arrivo;
	
	public Fermata getPartenza() {
		return Partenza;
	}
	public void setPartenza(Fermata partenza) {
		Partenza = partenza;
	}
	public Fermata getArrivo() {
		return Arrivo;
	}
	public void setArrivo(Fermata arrivo) {
		Arrivo = arrivo;
	}
	
	public Coppia(Fermata partenza, Fermata arrivo) {
		super();
		Partenza = partenza;
		Arrivo = arrivo;
	}
	
	
	
	

	
	
	
	

}
