package it.polito.tdp.metroparis;

//javabean che definisce come sarà fatta la classe che andrò a stampare nelle righe della tabella
public class StampType {
	
	private Double I;
	private Double V;
	private String L;
	private String X;
	private String Y;
	private String F;
	private String C;
	
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
	public String getX() {
		return X;
	}
	public void setX(String x) {
		X = x;
	}
	public String getY() {
		return Y;
	}
	public void setY(String y) {
		Y = y;
	}
	public String getF() {
		return F;
	}
	public void setF(String f) {
		F = f;
	}
	
	public StampType(Double i, Double v, String l, String x, String y, String f, String c) {
		super();
		I = i;
		V = v;
		L = l;
		X = x;
		Y = y;
		F = f;
		C = c;
	}
	public String getC() {
		return C;
	}
	public void setC(String c) {
		C = c;
	}
	
	
	
	

}
