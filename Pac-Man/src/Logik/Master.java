package Logik;

import java.util.ArrayList;

import Viualisierung.Kaestchen;

public class Master extends Kaestchen {

	PacMan p;
	static ArrayList<Knotenpunkt> knotenpunkte = new ArrayList<>();
	static int felderanzahl = 29;
	String taste = null;
	ArrayList<Geist> geister = new ArrayList<Geist>();

	public static void main(String[] args) {
		new Master();
	}

	public Master() {
		super(40, 40, felderanzahl, 32);
		p = new PacMan(15, 16, 3, false);
		ladeMatrix("Map");
		farbeSetzen(15, 14, "rot");
		createGeister();
		zeichnePacManundGeister();

		for (int i = 1; i < felderanzahl; i++) {
			for (int j = 1; j < felderanzahl; j++) {
				if (!farbeGeben(i, j).equals("grün")) {
					Knotenpunkt k = new Knotenpunkt(i, j);
					knotenpunkte.add(k);
				}

			}
		}

		Thread t = new Thread(new Runnable() {

			@Override
			public void run() {
//				while (true) {
					try {
						Thread.sleep(300);
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

					for (Geist g : geister) {
						g.findeWeg(g.getX(), g.getY(), p.getXpos(), p.getYpos());
					}
					zeichnePacManundGeister();
				}
//			}

		});
		t.start();
	}

	private void createGeister() {
		for (int x = 1; x <= felderanzahl; x++) {
			for (int y = 1; y <= felderanzahl; y++) {
				if (farbeGeben(x, y).equals("rot")) {
					Geist g = new Geist("Blinky", x, y, Geist.CHASE, this);
					geister.add(g);
					System.out.println("Geist created bei " + x + " " + y);
				}
				if (farbeGeben(x, y).equals("pink")) {
					Geist g = new Geist("Pinky", x, y, Geist.CHASE, this);
					geister.add(g);
				}
				if (farbeGeben(x, y).equals("blau")) {
					Geist g = new Geist("Inky", x, y, Geist.CHASE, this);
					geister.add(g);
				}
				if (farbeGeben(x, y).equals("orange")) {
					Geist g = new Geist("Clyde", x, y, Geist.CHASE, this);
					geister.add(g);
				}

			}
		}

	}

	private void zeichnePacManundGeister() {
		this.farbeSetzen(p.getXpos(), p.getYpos(), "gelb");

		for (Geist g : geister) {
			// @TODO andere Gesiter
			this.farbeSetzen(g.getX(), g.getY(), "rot");
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

}
