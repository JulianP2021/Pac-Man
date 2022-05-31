package Logik;

import java.util.ArrayList;

public class Geist {
	int x;
	int y;
	boolean chase = true;
	String bewegungsrichtung = null;
	ArrayList<String> möglicheRichtungen = new ArrayList<>();
	
	public Geist(int x, int y, boolean chase) {
		this.x = x;
		this.y = y;
		this.chase = chase;
		this.bewegungsrichtung = null;
		möglicheRichtungen.add("W");
		möglicheRichtungen.add("A");
		möglicheRichtungen.add("S");
		möglicheRichtungen.add("D");
	}
	public String getBewegungsrichtung() {
		return bewegungsrichtung;
	}
	public void setBewegungsrichtung(String bewegungsrichtung) {
		this.bewegungsrichtung = bewegungsrichtung;
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
	public boolean isChase() {
		return chase;
	}
	public void setChase(boolean chase) {
		this.chase = chase;
	}
	
	public void findeWegZuPacMan(int xStart, int yStart, int xZiel, int yZiel) {
		if (xStart == xZiel && yStart == yZiel) {
			System.out.println("Pac-man gefangen");
			System.out.println(" xStart " + xStart +" yStart " + yStart + " xZiel " + xZiel + " yZiel " + yZiel);
		}else {
			ArrayList<String> neuerWeg = new ArrayList<String>();
			for (String s : möglicheRichtungen) {
				
				if(s.equals("S")) {
					neuerWeg.add(s);
					findeWegrek(xStart,yStart+1, xZiel, yZiel, neuerWeg);
				}
				
			}
		}
		
	}
	private void findeWegrek(int xStart, int yStart, int xZiel, int yZiel, ArrayList<String> Weg) {
		System.out.println("xStart: " + xStart + " yStart: " + yStart + "," + xZiel + yZiel);
		if (xStart == xZiel && yStart == yZiel) {
			System.out.println("Pac-man gefangenWeg, " + Weg);
			return;
		}
		ArrayList<String> neuerWeg = Weg;
		for(String s : möglicheRichtungen) {
//			if(!(s.equals(Weg.get(Weg.size()-1)))) {
				if(s.equals("S")) {
					neuerWeg.add(s);
					findeWegrek(xStart,yStart+1, xZiel, yZiel, neuerWeg);
				}
//			}
		}
		
		
	}
	
}
