package Logik;

public interface Geister {
	
	
	void setX(int x);
	void setY(int y);
	int getX();
	int getY();
	String getPhase();
	void setPhase(String phase);
	String getRichtung();
	void setRichtung(String r);
	public String findeWeg(int x, int y);
	String getName();
	String getaltePhase();
	void setaltePhase(String r);
}
