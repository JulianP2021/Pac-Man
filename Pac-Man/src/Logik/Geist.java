package Logik;

import java.util.ArrayList;

import Viualisierung.Kaestchen;

public class Geist {
	
	ArrayList<Knotenpunkt> warteschlange = new ArrayList<Knotenpunkt>();
	ArrayList<Knotenpunkt> erledigt = new ArrayList<Knotenpunkt>();
	private String name;
	private int x;
	private int y;
	String phase = "";
	static String CHASE = "chase";
	static String FRIGHTENED = "frightened";
	static String SCATTER = "scatter";
	static String EATEN = "eaten";
	
	
	
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
	
	void findeWeg(int xStart,int yStart,int xZiel,int yZiel) {
		Knotenpunkt kn = null;
		Knotenpunkt kl = null;
		for(Knotenpunkt k : Master.knotenpunkte) {
			if(k.x == xZiel && k.y == yZiel) {
				kn = k;
			}
			if(k.x == xStart && k.y == yStart) {
				kl = k;
			}
		}
		findeWegK(xStart,yStart,xZiel,yZiel,kn,kl);
	}
	
	void findeWegK(int xStart,int yStart,int xZiel,int yZiel,Knotenpunkt anfangsknoten, Knotenpunkt zielknoten){
		ArrayList<Knotenpunkt> anknüpfendeKnoten = new ArrayList<Knotenpunkt>();
		boolean nichtjederpunktüberprüft = true;
		warteschlange.add(anfangsknoten);
		ArrayList<Knotenpunkt> warteschlange2 = new ArrayList<Knotenpunkt>();
		while(nichtjederpunktüberprüft ) {
//			System.out.println("Ka");
			anknüpfendeKnoten = new ArrayList<Knotenpunkt>();
			for(Knotenpunkt p : warteschlange) {
				anknüpfendeKnoten = getanknüpfendeknoten(p);
				for(Knotenpunkt k : anknüpfendeKnoten) {
					warteschlange2.add(k);
				}
			}
			warteschlange.clear();
			warteschlange = warteschlange2;
			warteschlange2.clear();
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
		System.out.println(zielknoten.weg);
//		WegAblaufen(kn.weg,anfangsknoten);
	}

	private void WegAblaufen(ArrayList<Knotenpunkt> weg,Knotenpunkt anfangsknoten ) {
		Knotenpunkt lastk = anfangsknoten;
		for(int i = 1;i<weg.size();i++) {
			if(getPosdelta(weg.get(i), lastk) == 1) {
				int deltax = weg.get(i).x - lastk.x;
				int deltay = weg.get(i).y - lastk.y;
				if(deltax == 1) {
					
				}
			}
		}
	}

	private ArrayList<Knotenpunkt> getanknüpfendeknoten(Knotenpunkt anfangsknoten) {
		//@TODO auf used setzen,distanz setzen
		ArrayList<Knotenpunkt> anknüpfendeKnoten = new ArrayList<Knotenpunkt>();
		for(Knotenpunkt k : Master.knotenpunkte) {
			if(getPosdelta(k,anfangsknoten) == 1 && !k.used) {
				k.used = true;
				ArrayList<Knotenpunkt> kopie = anfangsknoten.weg;
				kopie.add(k);
				k.setWeg(kopie);
				anknüpfendeKnoten.add(k);
			}
		}
//		System.out.println(anknüpfendeKnoten.size());
		return anknüpfendeKnoten;
	}

	private int getPosdelta(Knotenpunkt k, Knotenpunkt anfangsknoten) {
		int deltax = Math.abs(k.x - anfangsknoten.x);
		int deltay = Math.abs(k.y - anfangsknoten.y);
		return deltax + deltay;
	}
}
