package it.polito.tdp.metroparis.model;

//javabean che definisce una fermata (nodo)
public class Fermata {

	private int idFermata;
	private String nome;
	private Double X;
	private Double Y;

	public Double getX() {
		return X;
	}

	public void setX(Double x) {
		X = x;
	}

	public Double getY() {
		return Y;
	}

	public void setY(Double y) {
		Y = y;
	}

	public Fermata(int idFermata, String nome, Double x, Double y) {
		this.idFermata = idFermata;
		this.nome = nome;
		this.X = x;
		this.Y = y;
	}

	public Fermata(int idFermata) {
		this.idFermata = idFermata;
	}


	public int getIdFermata() {
		return idFermata;
	}

	public void setIdFermata(int idFermata) {
		this.idFermata = idFermata;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public String toString() {
		return nome;
	}

	@Override
	public int hashCode() {
		return ((Integer) idFermata).hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Fermata other = (Fermata) obj;
		if (idFermata != other.idFermata)
			return false;
		return true;
	}

}
