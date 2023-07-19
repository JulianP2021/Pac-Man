package Logik;

public interface Geister {
	
	int getKuerzesteLaenge();
	void setX(int x);
	void setY(int y);
	int getX();
	int getY();
	String getRichtung();
	void setRichtung(String r);
	public String findeWeg(int x, int y);
	String getName();		
	int getCountEATEN();
	void setCountEATEN(int eatenCounter);
	boolean getEatenZustand();
	void setEatenZustand(boolean eaten);
}
