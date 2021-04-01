package it.polito.tdp.metroparis.model;

public class Coppia {
	
	private Fermata Partenza;
	private Fermata Arrivo;
	private Double Peso;
	

	public Double getPeso() {
		return Peso;
	}
	public void setPeso(Double peso) {
		Peso = peso;
	}
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
	
	public Coppia(Fermata partenza, Fermata arrivo, Double peso) {
		super();
		Partenza = partenza;
		Arrivo = arrivo;
		Peso = peso;
	}
	
	
	
	

	
	
	
	

}
