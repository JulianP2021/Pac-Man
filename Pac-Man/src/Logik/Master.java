package Logik;

import java.util.ArrayList;

import Viualisierung.Kaestchen;

public class Master extends Kaestchen {

	PacMan p;
	static int felderanzahl = 20;
	String taste = null;
	ArrayList<Geist> geister = new ArrayList<Geist>();

	public static void main(String[] args) {
		new Master();
	}

	public Master() {
		super(50, 50, felderanzahl, felderanzahl);
		ladeMatrix("Map","schwarz");
		p = new PacMan(felderanzahl / 2, felderanzahl / 2, 3, false);
		farbeSetzen(10, 6, "rot");
		createGeister();
		
		zeichnePacManundGeister();

		Thread t = new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					try {
						Thread.sleep(300);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if (taste != null) {
						if (taste.equals("A")) {
							if (keineWand()) {
								farbeLoeschen(p.getXpos(), p.getYpos());
								p.setXpos(p.getXpos() - 1);
							}
						}
						if (taste.equals("D")) {
							if (keineWand()) {
								farbeLoeschen(p.getXpos(), p.getYpos());
								p.setXpos(p.getXpos() + 1);		
							}
						}
						if (taste.equals("W")) {
							if (keineWand()) {
								farbeLoeschen(p.getXpos(), p.getYpos());
								p.setYpos(p.getYpos() - 1);
							}
						}
						if (taste.equals("S")) {
							if (keineWand()) {
								farbeLoeschen(p.getXpos(), p.getYpos());
								p.setYpos(p.getYpos() + 1);
								
							}
						}
					}
					for(Geist g : geister) {
						g.findeWegZuPacMan(g.getX(), g.getY(), p.getXpos(), p.getYpos());
					}
//					for(Geist g : geister) {
//						g.setX(g.getX());
//						g.setY(g.getY()-1);
//					}
					zeichnePacManundGeister();
				}
			}

		});
		t.start();
	}
	
	private void createGeister() {
		for(int x = 1;x<=felderanzahl;x++) {
			for(int y = 1;y<=felderanzahl;y++) {
				if(farbeGeben(x, y) == "rot") {
					Geist g = new Geist(x,y,true);
					geister.add(g);
					System.out.println("geist geaddet");
				}
			}
		}
		
	}

	private void zeichnePacManundGeister() {
		this.farbeSetzen(p.getXpos(), p.getYpos(), "gelb");
		
		for(Geist g : geister) {
			this.farbeSetzen(g.getX(), g.getY(), "rot");
		}
	}

	private boolean keineWand() {
		
		if (taste.equals("W")) {
			return farbeGeben(p.getXpos(),p.getYpos()-1)=="durchsichtig";
		}
		if (taste.equals("A")) {
			return farbeGeben(p.getXpos()-1,p.getYpos())=="durchsichtig";
		}
		if (taste.equals("S")) {
			return farbeGeben(p.getXpos(),p.getYpos()+1)=="durchsichtig";
		}
		if (taste.equals("D")) {
			return farbeGeben(p.getXpos()+1,p.getYpos())=="durchsichtig";
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
		speichereMatrix("Map","schwarz");
	}

	@Override
	public void mausLeftClick(int x, int y) {
		farbeSetzen(x, y, "schwarz");
		
	}
	
	@Override
	public void mausRightClick(int x, int y) {
		farbeSetzen(x, y, "durchsichtig");
		
	}

}
