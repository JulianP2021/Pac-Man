package Logik;

public class Knotenpunkt {
	
	int x,y;
	int distanz = 1000000;
	Knotenpunkt vorgänger;
	Knotenpunkt[] weg;
	boolean used = false;
	
	
	public Knotenpunkt(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void setWeg(Knotenpunkt[] weg) {
		if(weg.length<this.distanz) {
			distanz = weg.length;
		}
		this.weg = weg;
	}
	
	
}
