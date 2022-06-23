package Logik;

import java.util.ArrayList;

public class Geist implements Geister {

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
	Master m;
	String richtung = null;

	public Geist(String name, int x, int y, String phase, Master m) {
		this.name = name;
		this.x = x;
		this.y = y;
		this.phase = phase;
		this.m = m;
	}
	
	@Override
	public int getX() {
		// TODO Auto-generated method stub
		return x;
	}

	@Override
	public int getY() {
		// TODO Auto-generated method stub
		return y;
	}

	@Override
	public void setX(int x) {
		// TODO Auto-generated method stub
		this.x = x;
	}

	@Override
	public void setY(int y) {
		this.y = y;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String findeWeg(int xZiel, int yZiel) {
		Knotenpunkt kn = null;
		Knotenpunkt kl = null;
		for (Knotenpunkt k : Master.knotenpunkte) {
			if (k.x == xZiel && k.y == yZiel) {
				kn = k;
			}
			if (k.x == x && k.y == y) {
				kl = k;
			}
		}
		if (kn != null && kl != null) {
			return findeWegK(x, y, xZiel, yZiel, kn, kl);
		} else {
			System.out.println("Unlucky");
		}
		return null;
	}

	String findeWegK(int xStart, int yStart, int xZiel, int yZiel, Knotenpunkt anfangsknoten, Knotenpunkt Ziel) {
//		System.out.println("xZiel: " + xZiel + " yZiel: " + yZiel);
		warteschlange.clear();
		warteschlange.addAll(getanknüpfendeknoten(anfangsknoten));
		int i = 0;
		while (i < (32 * 28)) {
//		while(Master.knotenpunkte.get(Master.knotenpunkte.indexOf(Ziel)).weg!=null) {
			i++;
			@SuppressWarnings("unchecked")
			ArrayList<Knotenpunkt> warteschlange2 = (ArrayList<Knotenpunkt>) warteschlange.clone();
			warteschlange.clear();
			for (Knotenpunkt k : warteschlange2) {
				warteschlange.addAll(getanknüpfendeknoten(k));
			}
		}
		return WegAblaufen(Master.knotenpunkte.get(Master.knotenpunkte.indexOf(Ziel)).weg);
	}

	private String WegAblaufen(ArrayList<Knotenpunkt> weg) {
		int i = 1;
		if(weg.size() - 2>=0) {
			i = 2;
		}	
		int deltax = Math.abs(weg.get(weg.size() - i).x - x);
		int deltay = Math.abs(weg.get(weg.size() - i).y - y);
		if (deltax + deltay == 1) {
			if (weg.get(weg.size() - i).x - x == 1) {
				richtung = "rechts";
			}else if (weg.get(weg.size() - i).x - x == -1) {
				richtung = "links";
			}else if (weg.get(weg.size() - i).y - y == 1) {
				richtung = "runter";
			}else if (weg.get(weg.size() - i).y - y == -1) {
				richtung = "hoch";
			}
		}
//		this.m.färben(weg);
		return richtung;
	}

	private ArrayList<Knotenpunkt> getanknüpfendeknoten(Knotenpunkt anfangsknoten) {
		// @TODO auf used setzen,distanz setzen
		ArrayList<Knotenpunkt> anknüpfendeKnoten = new ArrayList<Knotenpunkt>();
		for (Knotenpunkt k : Master.knotenpunkte) {
			if (getKnotendelta(k, anfangsknoten) == 1 && !k.used) {
				k.used = true;
				@SuppressWarnings("unchecked")
				ArrayList<Knotenpunkt> kopie = (ArrayList<Knotenpunkt>) anfangsknoten.weg.clone();
				kopie.add(k);
				k.setWeg(kopie);
				anknüpfendeKnoten.add(k);
			}
		}
		return anknüpfendeKnoten;
	}

	private int getKnotendelta(Knotenpunkt k, Knotenpunkt anfangsknoten) {
		int deltax = Math.abs(k.x - anfangsknoten.x);
		int deltay = Math.abs(k.y - anfangsknoten.y);
//		System.out.println(deltax + deltay);
		return deltax + deltay;
	}
}
