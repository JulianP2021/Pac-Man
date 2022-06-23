package Logik;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import Viualisierung.Kaestchen;

public class Master extends Kaestchen {

	PacMan p;
	static ArrayList<Knotenpunkt> knotenpunkte = new ArrayList<>();
	static int felderanzahl = 29;
	String taste = null;
	ArrayList<Geister> geister = new ArrayList<Geister>();
	boolean verloren = false;
	int timerAufruf = 0;

	public static void main(String[] args) {
		new Master();
	}

	public Master() {
		super(40, 40, felderanzahl, 32);
		reset();
		zeichnePacManundGeister();

		Thread t = new Thread(new Runnable() {

			@Override
			public void run() {
				while(true) {
					while (!verloren) {
						
						for (int i = 1; i <= felderanzahl; i++) {
							for (int j = 1; j < 32; j++) {
								if (!farbeGeben(i, j).equals("grün")) {
									Knotenpunkt k = new Knotenpunkt(i, j);
									knotenpunkte.add(k);
								}

							}
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
					}
					new JOptionPane();
				}
			}
		});
		t.start();
	}
	
	
	void updateTimer(){
		timerAufruf++;
		textSetzen(2, 13, "" + timerAufruf);
	}
	
	void reset() {
		p = null;
		geister = new ArrayList<Geister>();
		p = new PacMan(8,26, 3, false);
		ladeMatrix("Map");
		farbeSetzen(2, 3, "rot");
//		farbeSetzen(2, 2, "pink");
		createGeister();
	}
	
	public void clearmap() {
		for(int i = 1;i<32;i++) {
			for(int j = 1;j<felderanzahl + 3;j++) {
				if(!farbeGeben(i, j).equals("grün")) {
					this.farbeLoeschen(i, j);
				}
			}
		}
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
			for (int y = 1; y <= felderanzahl; y++) {
				if (farbeGeben(x, y).equals("rot")) {
					Geister g = new Geist("Blinky", x, y, Geist.CHASE, this);
					geister.add(g);
					System.out.println("Geist created bei " + x + " " + y);
				}
				if (farbeGeben(x, y).equals("pink")) {
					Geister g = new GeistSuchtFrau("Pinky", x, y, Geist.CHASE, this);
					geister.add(g);
				}
				if (farbeGeben(x, y).equals("blau")) {
					Geister g = new Geist("Inky", x, y, Geist.CHASE, this);
					geister.add(g);
				}
				if (farbeGeben(x, y).equals("orange")) {
					Geister g = new Geist("Clyde", x, y, Geist.CHASE, this);
					geister.add(g);
				}

			}
		}

	}

	private void zeichnePacManundGeister() {
		this.farbeSetzen(p.getXpos(), p.getYpos(), "gelb");

		for (Geister g : geister) {
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
			return farbeGeben(x, y - 1).equals("durchsichtig") || farbeGeben(x, y - 1).equals("gelb");
		}
		if (taste.equals("A")) {
			return farbeGeben(x - 1, y).equals("durchsichtig") || farbeGeben(x - 1, y).equals("gelb");
		}
		if (taste.equals("S")) {
			return farbeGeben(x, y + 1).equals("durchsichtig") || farbeGeben(x, y + 1).equals("gelb");
		}
		if (taste.equals("D")) {
			return farbeGeben(x + 1, y).equals("durchsichtig") || farbeGeben(x + 1, y).equals("gelb");
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
		speichereMatrix("Map", "grün");
	}

	@Override
	public void mausLeftClick(int x, int y) {
		farbeSetzen(x, y, "grün");

	}

	@Override
	public void mausRightClick(int x, int y) {
		farbeSetzen(x, y, "durchsichtig");

	}

	public void färben(ArrayList<Knotenpunkt> weg) {
		for(int i = 0;i<weg.size();i++)
			farbeSetzen(weg.get(i).x, weg.get(i).y, "orange");
		
	}

}
