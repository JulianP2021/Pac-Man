package Logik;

import java.util.ArrayList;

public class Geist implements Geister {

	ArrayList<Knotenpunkt> warteschlange = new ArrayList<Knotenpunkt>();
	ArrayList<Knotenpunkt> erledigt = new ArrayList<Knotenpunkt>();
	private String name;
	private int x;
	private int y;
	String phase = "chase";
	static String CHASE = "chase";
	static String FRIGHTENED = "frightened";
	static String SCATTER = "scatter";
	static String EATEN = "eaten";
	Master m;
	String richtung = null;
	String altePhase = "chase";

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
		warteschlange.addAll(Master.getanknüpfendeknoten(anfangsknoten));
		int i = 0;
		while (i < (32 * 28)) {
//		while(Master.knotenpunkte.get(Master.knotenpunkte.indexOf(Ziel)).weg!=null) {
			i++;
			@SuppressWarnings("unchecked")
			ArrayList<Knotenpunkt> warteschlange2 = (ArrayList<Knotenpunkt>) warteschlange.clone();
			warteschlange.clear();
			for (Knotenpunkt k : warteschlange2) {
				warteschlange.addAll(Master.getanknüpfendeknoten(k));
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
		this.m.färben(weg);
		return richtung;
	}

	

	

	@Override
	public String getRichtung() {
		// TODO Auto-generated method stub
		return richtung;
	}

	@Override
	public void setRichtung(String r) {
		// TODO Auto-generated method stub
		richtung = r;
	}

	
	@Override
	public int getKuerzesteLaenge() {
		// TODO Auto-generated method stub
		return 0;
	}

	
	@Override
	public int getCountEATEN() {
		// TODO Auto-generated method stub
		return 0;
	}

	
	@Override
	public void setCountEATEN(int eatenCounter) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean getEatenZustand() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setEatenZustand(boolean eaten) {
		// TODO Auto-generated method stub
		
	}
}
