package Logik;

import java.util.ArrayList;
import Viualisierung.Kaestchen;

public class Master extends Kaestchen {

	PacMan p;
	static ArrayList<Knotenpunkt> knotenpunkte = new ArrayList<>();
	static int felderanzahl = 29;
	String taste = null;
	ArrayList<Geister> geister = new ArrayList<Geister>();
	boolean verloren = false;
	int timerAufruf = 0;
	static String mapfarbe = "schwarz";
	static String borderfarbe = "blau";

	public static void main(String[] args) {
		new Master();
	}

	public Master() {
		super(40, 40, felderanzahl, 32);
//		for (int i = 1; i <= felderanzahl; i++) {
//			for (int j = 1; j < 32; j++) {
//				farbeSetzen(i, j, mapfarbe);
//			}
//		}
		reset();
		zeichnePacManundGeister();

		Thread t = new Thread(new Runnable() {

			@Override
			public void run() {
				for (int i = 1; i <= felderanzahl; i++) {
					for (int j = 1; j < 32; j++) {
						if (!farbeGeben(i, j).equals(borderfarbe)) {
							Knotenpunkt k = new Knotenpunkt(i, j);
							knotenpunkte.add(k);
						}
					}
				}
				while(true) {
					while (!verloren) {
						for(Knotenpunkt k : knotenpunkte) {
							k.reset();
						}
						try {
							Thread.sleep(333);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						if (taste != null) {
							if (taste.equals("A")) {
								if (keineWand(taste, p.getXpos(), p.getYpos())) {
									farbeLoeschen(p.getXpos(), p.getYpos());
									p.setXpos(p.getXpos() - 1);
								}
							}
							if (taste.equals("D")) {
								if (keineWand(taste, p.getXpos(), p.getYpos())) {
									farbeLoeschen(p.getXpos(), p.getYpos());
									p.setXpos(p.getXpos() + 1);
								}
							}
							if (taste.equals("W")) {
								if (keineWand(taste, p.getXpos(), p.getYpos())) {
									farbeLoeschen(p.getXpos(), p.getYpos());
									p.setYpos(p.getYpos() - 1);
								}
							}
							if (taste.equals("S")) {
								if (keineWand(taste, p.getXpos(), p.getYpos())) {
									farbeLoeschen(p.getXpos(), p.getYpos());
									p.setYpos(p.getYpos() + 1);

								}
							}
						}
						for (Geister g : geister) {
//							System.out.println(g.findeWeg(p.getXpos(), p.getYpos()) + " " + g.getName());
							switch(g.findeWeg(p.getXpos(), p.getYpos())) {
							case "hoch":
								g.setY(g.getY()-1);
								break;
							case "rechts":
								g.setX(g.getX()+1);
								break;
							case "links":
								g.setX(g.getX()-1);
								break;
							case "runter":
								g.setY(g.getY()+1);
								break;	
							}
						}
						überprüfeSieg();
						clearmap();
						zeichnePacManundGeister();
						updateTimer();
						scoreBerechnen();
					}
				}
			}
		});
		t.start();
	}
	
	
	void updateTimer(){
		timerAufruf++;
		textSetzen(2, 13, "Zeit " + timerAufruf);
	}
	
	void reset() {
		p = null;
		geister = new ArrayList<Geister>();
		p = new PacMan(8,26, 3, false);
		ladeMatrix("Map");
		farbeSetzen(2, 2, "rot");
		farbeSetzen(28, 2, "rot");
		farbeSetzen(2, 29, "pink");
		farbeSetzen(28, 29, "pink");
//		farbeSetzen(2, 2, "pink");
		createGeister();
	}
	
	public void clearmap() {
		for(int i = 1;i<32;i++) {
			for(int j = 1;j<felderanzahl + 3;j++) {
				this.farbeLoeschen(i, j);
			}
		}
		ladeMatrix("Map");
	}

	protected void überprüfeSieg() {
		for(Geister g : geister) {
			if(g.getX() == p.getXpos() && g.getY() == p.getYpos()) {
				verloren = true;
			}
		}
	}

	private void createGeister() {
		for (int x = 1; x <= felderanzahl; x++) {
			for (int y = 1; y <= 32; y++) {
				if (farbeGeben(x, y).equals("rot")) {
					Geister g = new Geist("Blinky", x, y, Geist.CHASE, this);
					geister.add(g);
					System.out.println("Geist created bei " + x + " " + y);
				}
				if (farbeGeben(x, y).equals("pink")) {
					Geister g = new GeistSuchtFrau("Pinky", x, y, "", this);
					geister.add(g);
				}
//				if (farbeGeben(x, y).equals("blau")) {
//					Geister g = new Geist("Inky", x, y, Geist.CHASE, this);
//					geister.add(g);
//				}
				if (farbeGeben(x, y).equals("orange")) {
					Geister g = new Geist("Clyde", x, y, Geist.CHASE, this);
					geister.add(g);
				}

			}
		}

	}

	private void zeichnePacManundGeister() {
		
		for(Knotenpunkt k : knotenpunkte) {
			if(p.getXpos() == k.x && p.getYpos() == k.y) {
				k.claimed = true;
			}
			bildLoeschen(k.x, k.y);
			if(k.claimed){
				System.out.println(k.x + " " + k.y + " claimed");
			}else {
				bildSetzen(k.x, k.y, "Pac-man-coin.jpg");
			}
		}
		
		bildLoeschen(p.getXpos(), p.getYpos());
		this.farbeSetzen(p.getXpos(), p.getYpos(), "gelb");
		
		for (Geister g : geister) {
			bildLoeschen(g.getX(), g.getY());
			// @TODO andere Gesiter
			switch(g.getName()) {
			case "Blinky":
				this.farbeSetzen(g.getX(), g.getY(), "rot");
				break;
			case "Pinky":
				this.farbeSetzen(g.getX(), g.getY(), "pink");
				break;
			case "Inky":
				this.farbeSetzen(g.getX(), g.getY(), "blau");
				break;
			case "Clyde":
				this.farbeSetzen(g.getX(), g.getY(), "orange");
				break;
			default: 
				this.farbeSetzen(g.getX(), g.getY(), "rot");
				break;
			}
		}
	}

	public boolean keineWand(String taste, int x, int y) {

		if (taste.equals("W")) {
			return farbeGeben(x, y - 1).equals(mapfarbe) || farbeGeben(x, y - 1).equals("gelb");
		}
		if (taste.equals("A")) {
			return farbeGeben(x - 1, y).equals(mapfarbe) || farbeGeben(x - 1, y).equals("gelb");
		}
		if (taste.equals("S")) {
			return farbeGeben(x, y + 1).equals(mapfarbe) || farbeGeben(x, y + 1).equals("gelb");
		}
		if (taste.equals("D")) {
			return farbeGeben(x + 1, y).equals(mapfarbe) || farbeGeben(x + 1, y).equals("gelb");
		}
		return false;
	}

	@Override
	public void tasteClick(String taste) {
		this.taste = taste;
		System.out.println(taste);
	}

	@Override
	public void tasteClickCtrl(String taste) {
		//speichereMatrix("Map", "blau");
	}

	@Override
	public void mausLeftClick(int x, int y) {
		//farbeSetzen(x, y, "blau");

	}

	@Override
	public void mausRightClick(int x, int y) {
		//farbeSetzen(x, y, "scharz");
	}

	public void färben(ArrayList<Knotenpunkt> weg) {
		for(int i = 0;i<weg.size();i++) {
			bildLoeschen(weg.get(i).x, weg.get(i).y);
			farbeSetzen(weg.get(i).x, weg.get(i).y, "orange");
		}
	}
	
	void scoreBerechnen(){
		int counter = 0;
		for(Knotenpunkt k : knotenpunkte) {
			if(k.claimed) {
				counter++;
			}

		}
		setScore(counter);
	}
	
	void setScore(int score) {
		textSetzen(2, 12, "Score: " + score);
	}
		
}
