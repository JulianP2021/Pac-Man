package Logik;

import Viualisierung.Kaestchen;

public class Master extends Kaestchen {

	PacMan p;
	static int felderanzahl = 20;
	String taste = null;

	public static void main(String[] args) {
		new Master();
	}

	public Master() {
		super(50, 50, felderanzahl, felderanzahl);
		ladeMatrix("Map","schwarz");
		p = new PacMan(felderanzahl / 2, felderanzahl / 2, 3, false);
		zeichnePacMan();

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
								zeichnePacMan();
							}

						}
						if (taste.equals("D")) {
							if (keineWand()) {
								farbeLoeschen(p.getXpos(), p.getYpos());
								p.setXpos(p.getXpos() + 1);
								zeichnePacMan();
							}

						}
						if (taste.equals("W")) {
							if (keineWand()) {
								farbeLoeschen(p.getXpos(), p.getYpos());
								p.setYpos(p.getYpos() - 1);
								zeichnePacMan();
							}

						}
						if (taste.equals("S")) {
							if (keineWand()) {
								farbeLoeschen(p.getXpos(), p.getYpos());
								p.setYpos(p.getYpos() + 1);
								zeichnePacMan();
							}
						}
					}
				}
			}

		});
		t.start();
	}
	
	private void zeichnePacMan() {
		this.farbeSetzen(p.getXpos(), p.getYpos(), "gelb");
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