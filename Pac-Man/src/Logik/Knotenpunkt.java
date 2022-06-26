package Logik;

import java.util.ArrayList;

public class Knotenpunkt {
	
	int x,y;
	int distanz = 1000000;
	Knotenpunkt vorgänger;
	ArrayList<Knotenpunkt> weg = new ArrayList<Knotenpunkt>();;
	boolean used = false;
	boolean claimed = false;
	
	
	public Knotenpunkt(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void setWeg(ArrayList<Knotenpunkt> weg) {
		if(weg.size()<this.distanz) {
			distanz = weg.size();
		}
		this.weg = weg;
	}
	
	
}
