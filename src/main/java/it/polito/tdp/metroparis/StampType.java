package it.polito.tdp.metroparis;

public class StampType {
	
	private Double I;
	private Double V;
	private String L;
	private Double X;
	private Double Y;
	private String F;
	
	public Double getI() {
		return I;
	}
	public void setI(Double i) {
		I = i;
	}
	public Double getV() {
		return V;
	}
	public void setV(Double v) {
		V = v;
	}
	public String getL() {
		return L;
	}
	public void setL(String l) {
		L = l;
	}
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
	public String getF() {
		return F;
	}
	public void setF(String f) {
		F = f;
	}
	
	public StampType(Double i, Double v, String l, Double x, Double y, String f) {
		super();
		I = i;
		V = v;
		L = l;
		X = x;
		Y = y;
		F = f;
	}
	
	
	
	

}
