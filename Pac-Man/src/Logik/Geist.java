package Logik;

import java.util.ArrayList;

import Viualisierung.Kaestchen;

public class Geist {
	private String name;
	private int x;
	private int y;
	String phase = "";
	static String CHASE = "chase";
	static String FRIGHTENED = "frightened";
	static String SCATTER = "scatter";
	static String EATEN = "eaten";
	
	ArrayList<Knotenpunkt> warteschlange = new ArrayList<>();
	ArrayList<Knotenpunkt> erledigt = new ArrayList<>();
	
	public Geist(String name,int x, int y, String phase, Master m) {
		this.name = name;
		this.x = x;
		this.y = y;
		this.phase = phase;
	}
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	void findeWeg(int xStart,int yStart,int xZiel,int yZiel,Knotenpunkt anfangsknoten){
		ArrayList<Knotenpunkt> anknüpfendeKnoten = new ArrayList<>();
		boolean nichtjederpunktüberprüft = true;
		anknüpfendeKnoten = getanknüpfendeknoten(anfangsknoten);
		for(Knotenpunkt p : anknüpfendeKnoten) {
			warteschlange.add(p);
		}
		
		while(nichtjederpunktüberprüft ) {
			anknüpfendeKnoten = new ArrayList<>();
			for(Knotenpunkt p : warteschlange) {
				anknüpfendeKnoten = getanknüpfendeknoten(p);
				for(Knotenpunkt k : anknüpfendeKnoten) {
					warteschlange.add(k);
				}
				warteschlange.remove(p);
			}
			//Abruchbedingung
			int counter = 0;
			for(Knotenpunkt k : Master.knotenpunkte) {
				if(k.used) {
					counter++;
				}
			}
			if(counter == Master.knotenpunkte.size()) {
				nichtjederpunktüberprüft = false;
			}
		}
	}

	private ArrayList<Knotenpunkt> getanknüpfendeknoten(Knotenpunkt anfangsknoten) {
		//@TODO auf used setzen,distanz setzen
		ArrayList<Knotenpunkt> anknüpfendeKnoten = new ArrayList<>();
		for(Knotenpunkt k : Master.knotenpunkte) {
			if(getPosdelta(k,anfangsknoten) == 1 && !k.used) {
				Knotenpunkt[] kopie = anfangsknoten.weg;
				kopie[kopie.length] = k;
				k.setWeg(kopie);
				anknüpfendeKnoten.add(k);
			}
		}
		
		
		return anknüpfendeKnoten;
	}

	private int getPosdelta(Knotenpunkt k, Knotenpunkt anfangsknoten) {
		// TODO Auto-generated method stub
		
		return 0;
	}
}
