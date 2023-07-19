package Logik;

import java.awt.Toolkit;
import java.util.ArrayList;

import Viualisierung.EndScreen;
import Viualisierung.Kaestchen;

public class Master extends Kaestchen {

	PacMan p;
	static ArrayList<Knotenpunkt> knotenpunkte = new ArrayList<>();
	static int felderanzahl = 29;
	String taste = null;
	ArrayList<Geister> geister = new ArrayList<Geister>();
	boolean verloren = false;
	boolean gewonnen = false;
	boolean food = false;
	int timerAufruf = 0;
	static String mapfarbe = "schwarz";
	static String borderfarbe = "blau";
	String alteTaste = "";
	float volume = 1f;
	int chaseScatterCounter = 0, frightenedCounter = 0;
	String phase = "";
	static String CHASE = "chase";
	static String FRIGHTENED = "frightened";
	static String SCATTER = "scatter";
	String altePhase = phase;
	private boolean shown = false;
	int wartenfürpowerUP = 0;
	int geisterscore = 0;
	int levelscore = 0;


	public static void main(String[] args) {
		new Master();
	}

	public Master() {
		super((Toolkit.getDefaultToolkit().getDefaultToolkit().getScreenSize().height/2),0,Toolkit.getDefaultToolkit().getDefaultToolkit().getScreenSize().height/34, Toolkit.getDefaultToolkit().getDefaultToolkit().getScreenSize().height/34, felderanzahl, 32);
		knotenpunkte = new ArrayList<Knotenpunkt>();
		System.out.println((Toolkit.getDefaultToolkit().getDefaultToolkit().getScreenSize().height/2));
//		for (int i = 1; i <= felderanzahl; i++) {
//			for (int j = 1; j < 32; j++) {
//				farbeSetzen(i, j, mapfarbe);
//			}
//		}
		new Thread(new Runnable() {
			public void run() {
				while (true) {
					try {

						Thread.sleep(35);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					repaint();
				}
			}

		}).start();

		Thread t = new Thread(new Runnable() {

			
			@Override
			public void run() {
				while(true) {
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if(shown) {
						
					}else {
						int c = 0;
						reset();
						zeichnePacManundGeister();
						createknotenpunkt();
						while (!gewonnen && !verloren) {

							try {
								Thread.sleep(40);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							wartenfürpowerUP++;
							c++;
							if (c % 8 == 0) {
								if (!food) {
									createPowerUp();
								}

								algorythmusvorbereitenundPower_UPerfassen();

								if (taste != null) {
									if (taste.equals("A") || taste.equals("Left")) {
										if (keineWand(taste, p.getXpos(), p.getYpos())) {
											alteTaste = "A";
										}
									}
									if (taste.equals("D") || taste.equals("Right")) {
										if (keineWand(taste, p.getXpos(), p.getYpos())) {
											alteTaste = "D";
										}
									}
									if (taste.equals("W") || taste.equals("Up")) {
										if (keineWand(taste, p.getXpos(), p.getYpos())) {
											alteTaste = "W";
										}
									}
									if (taste.equals("S") || taste.equals("Down")) {
										if (keineWand(taste, p.getXpos(), p.getYpos())) {
											alteTaste = "S";
										}
									}
								}
								if (alteTaste != null) {
									if (alteTaste.equals("A")) {
										if (p.getXpos() == 1 && p.getYpos() == 16) {
											p.setXpos(30);
										}
										if (keineWand(alteTaste, p.getXpos(), p.getYpos())) {
											farbeLoeschen(p.getXpos(), p.getYpos());
											p.setXpos(p.getXpos() - 1);
										}
									}
									if (alteTaste.equals("D")) {
										if (p.getXpos() == 29 && p.getYpos() == 16) {
											p.setXpos(0);
										}
										if (keineWand(alteTaste, p.getXpos(), p.getYpos())) {
											farbeLoeschen(p.getXpos(), p.getYpos());
											p.setXpos(p.getXpos() + 1);
										}
									}
									if (alteTaste.equals("W")) {
										if (keineWand(alteTaste, p.getXpos(), p.getYpos())) {
											farbeLoeschen(p.getXpos(), p.getYpos());
											p.setYpos(p.getYpos() - 1);
										}
									}
									if (alteTaste.equals("S")) {
										if (keineWand(alteTaste, p.getXpos(), p.getYpos())) {
											farbeLoeschen(p.getXpos(), p.getYpos());
											p.setYpos(p.getYpos() + 1);
										}
									}
								}
								überprüfeSieg();
								überprüfePowerUp();
								clearmap();
								zeichnePacManundGeister();
								updateTimer();
								scoreBerechnen();
							}
							if (c % 10 == 0) {
								for (Geister g : geister) {
									System.out.println(phase);

									// 180° TURN BEI PHASENWECHSEL:
									if (!altePhase.equals(phase)) {
										altePhase = phase;
										switch (g.getRichtung()) {
										case "hoch":
											g.setRichtung("runter");
											break;
										case "rechts":
											g.setRichtung("links");
											break;
										case "links":
											g.setRichtung("rechts");
											break;
										case "runter":
											g.setRichtung("hoch");
											break;
										}

									} else {

		//Phase: Eaten							
										if (g.getEatenZustand() == true) {
											g.setRichtung("");
											switch (g.getName()) {
											case "Blinky":
												g.setX(14);
												g.setY(15);
												break;
											case "Pinky":
												g.setX(14);
												g.setY(17);
												break;
											case "Inky":
												g.setX(16);
												g.setY(15);
												break;
											case "Clyde":
												g.setX(16);
												g.setY(17);
												break;
											}
											g.setCountEATEN(g.getCountEATEN() + 1);
											if (g.getCountEATEN() > 15) {
												g.setCountEATEN(0);
												g.setEatenZustand(false);
												g.setX(tpXgeist());
												g.setY(tpYgeist());
											}
		//Phase: Frightened								
										} else if (phase.equals(FRIGHTENED)) {

											boolean passt = false;
											while (passt == false) {

												int zufall = (int) (Math.random() * 4);
												switch (zufall) {
												case 0:
													if (((!(farbeGeben(g.getX(), g.getY() - 1).equals(Master.borderfarbe)))
															&& (!(g.getRichtung().equals("runter"))))) {
														g.setRichtung("hoch");
														passt = true;
													}
													break;
												case 1:
													if ((!(farbeGeben(g.getX(), g.getY() + 1).equals(Master.borderfarbe)))
															&& (!(g.getRichtung().equals("hoch")))) {
														g.setRichtung("runter");
														passt = true;
													}
													break;
												case 2:
													if ((!(farbeGeben(g.getX() + 1, g.getY()).equals(Master.borderfarbe)))
															&& (!(g.getRichtung().equals("links")))) {
														g.setRichtung("rechts");
														passt = true;
													}
													break;
												case 3:
													if ((!(farbeGeben(g.getX() - 1, g.getY()).equals(Master.borderfarbe)))
															&& (!(g.getRichtung().equals("rechts")))) {
														g.setRichtung("links");
														passt = true;
													}
													break;
												default:
													break;
												}
											}
											frightenedCounter++;
											if (frightenedCounter > 80) {
												frightenedCounter = 0;
												phase = "";
											}

		//Phase: Chase								
										} else if (chaseScatterCounter < 150) {
											phase = CHASE;
											chaseScatterCounter++;

											if (g.getName().equals("Blinky")) {
												chaseScatterCounter++;
												g.findeWeg(p.getXpos(), p.getYpos());

											} else if (g.getName().equals("Pinky")) {
												int addX = 0, addY = 0;
												switch (alteTaste) {
												case "W":
													addY = -4;
													break;
												case "A":
													addX = -4;
													break;
												case "S":
													addY = 4;
													break;
												case "D":
													addX = 4;
													break;
												default:
													break;
												}
												g.findeWeg(p.getXpos() + addX, p.getYpos() + addY);

											} else if (g.getName().equals("Inky")) {
												int addX = 0, addY = 0, vektorX = 0, vektorY = 0, zielX = 0, zielY = 0;
												switch (alteTaste) {
												case "W":
													addY = -2;
													break;
												case "A":
													addX = -2;
													break;
												case "S":
													addY = 2;
													break;
												case "D":
													addX = 2;
													break;
												default:
													break;
												}
												vektorX = (p.getXpos() + addX) - geister.get(0).getX();
												vektorY = (p.getYpos() + addY) - geister.get(0).getY();
												zielX = (p.getXpos() + addX) + vektorX;
												zielY = (p.getYpos() + addY) + vektorY;
												g.findeWeg(zielX, zielY);

											} else if (g.getName().equals("Clyde")) {
												g.findeWeg(p.getXpos(), p.getYpos());
												if (g.getKuerzesteLaenge() < 8) {
													g.findeWeg(29, 30);
												}
											}

		//Phase: Scatter								
										} else {
											phase = SCATTER;
											chaseScatterCounter++;
											if (chaseScatterCounter > 220) {
												chaseScatterCounter = 0;
											}

											if (g.getName().equals("Blinky")) {
												chaseScatterCounter++;
												g.findeWeg(3, 1);
											} else if (g.getName().equals("Pinky")) {
												g.findeWeg(1, 30);
											} else if (g.getName().equals("Inky")) {
												g.findeWeg(27, 1);
											} else if (g.getName().equals("Clyde")) {
												g.findeWeg(29, 30);
											}

										}
		//Bewegung:	
										switch (g.getRichtung()) {
										case "hoch":
											g.setY(g.getY() - 1);
											break;
										case "rechts":
											if (g.getX() == 29 && g.getY() == 16) {
												g.setX(0);
											}
											g.setX(g.getX() + 1);
											break;
										case "links":
											if (g.getX() == 1 && g.getY() == 16) {
												g.setX(30);
											}
											g.setX(g.getX() - 1);
											break;
										case "runter":
											g.setY(g.getY() + 1);
											break;
										default:
											break;
										}

									}

								}
								überprüfeSieg();
								überprüfePowerUp();
								clearmap();
								zeichnePacManundGeister();
								updateTimer();
								scoreBerechnen();
							}

						}
						System.out.println("Ok");
						gewonnen = false;
						if(!gewonnen && !verloren) {
							resetLevel2();
							phase = "chase";
							knotenpunkte = new ArrayList<Knotenpunkt>();
							createknotenpunkt();
						}
						zeichnePacManundGeister();
						while (!gewonnen && !verloren) {

							try {
								Thread.sleep(111);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							c++;
							if (c % 2 == 0) {
								for (Knotenpunkt k : knotenpunkte) {
									k.reset();
								}

								if (taste != null) {
									if (taste.equals("A") || taste.equals("Left")) {
										if (keineWand(taste, p.getXpos(), p.getYpos())) {
											alteTaste = "A";
										}
									}
									if (taste.equals("D") || taste.equals("Right")) {
										if (keineWand(taste, p.getXpos(), p.getYpos())) {
											alteTaste = "D";
										}
									}
									if (taste.equals("W") || taste.equals("Up")) {
										if (keineWand(taste, p.getXpos(), p.getYpos())) {
											alteTaste = "W";
										}
									}
									if (taste.equals("S") || taste.equals("Down")) {
										if (keineWand(taste, p.getXpos(), p.getYpos())) {
											alteTaste = "S";
										}
									}
								}
								if (alteTaste != null) {
									if (alteTaste.equals("A")) {
										if (p.getXpos() == 1 && p.getYpos() == 16) {
											p.setXpos(30);
										}
										if (keineWand(alteTaste, p.getXpos(), p.getYpos())) {
											farbeLoeschen(p.getXpos(), p.getYpos());
											p.setXpos(p.getXpos() - 1);
										}
									}
									if (alteTaste.equals("D")) {
										if (p.getXpos() == 29 && p.getYpos() == 16) {
											p.setXpos(0);
										}
										if (keineWand(alteTaste, p.getXpos(), p.getYpos())) {
											farbeLoeschen(p.getXpos(), p.getYpos());
											p.setXpos(p.getXpos() + 1);
										}
									}
									if (alteTaste.equals("W")) {
										if (keineWand(alteTaste, p.getXpos(), p.getYpos())) {
											farbeLoeschen(p.getXpos(), p.getYpos());
											p.setYpos(p.getYpos() - 1);
										}
									}
									if (alteTaste.equals("S")) {
										if (keineWand(alteTaste, p.getXpos(), p.getYpos())) {
											farbeLoeschen(p.getXpos(), p.getYpos());
											p.setYpos(p.getYpos() + 1);
										}
									}
								}

								for (Geister g : geister) {
									g.findeWeg(p.getXpos(), p.getYpos());
									switch (g.getRichtung()) {
									case "hoch":
										g.setY(g.getY() - 1);
										break;
									case "rechts":
										if (g.getX() == 29 && g.getY() == 16) {
											g.setX(0);
										}
										g.setX(g.getX() + 1);
										break;
									case "links":
										if (g.getX() == 1 && g.getY() == 16) {
											g.setX(30);
										}
										g.setX(g.getX() - 1);
										break;
									case "runter":
										g.setY(g.getY() + 1);
										break;
									default:
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
						if(verloren || gewonnen) {
							try {
								Thread.sleep(1000);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							showEndScreen();
						}
					}
				}
			}
	
		});
		t.start();
	}
	
	protected void createknotenpunkt() {
		// TODO Auto-generated method stub
		for (int i = 1; i <= felderanzahl; i++) {
			for (int j = 1; j < 32; j++) {
				if (!farbeGeben(i, j).equals(borderfarbe)) {
					if(!((i>=13 && i<=17) && (j>=15 && j<=17))) {
						Knotenpunkt k = new Knotenpunkt(i, j);
						knotenpunkte.add(k);
					}
				}
			}
		}
	}

	private void showEndScreen() {
		// TODO Auto-generated method stub
		shown = true;
		dispose();
		new EndScreen(scoreBerechnen());
	}

	protected void algorythmusvorbereitenundPower_UPerfassen() {
		// TODO Auto-generated method stub
		food = false;
		for (Knotenpunkt k : knotenpunkte) {
			if (k.power_up) {
				food = true;
			}
			k.reset();
		}
	}

	protected void überprüfePowerUp() {
		// TODO Auto-generated method stub
		for (Knotenpunkt k : knotenpunkte) {
			if (getPowerUp(k)) {
				if (p.getXpos() == k.x && p.getYpos() == k.y) {
					powerupaufsammeln(k);
				}
			}
		}

	}

	private void powerupaufsammeln(Knotenpunkt k) {
		wartenfürpowerUP = 0;
		this.sound("Pacman_Eating_Cherry_Sound_Effect.wav", volume);
		// TODO Auto-generated method stub
		k.power_up = false;
		phase = FRIGHTENED;
	}

	void createPowerUp() {
		if(!phase.equals(Geist.FRIGHTENED)&&wartenfürpowerUP>600) {
			// Wenn es nur auf bereits eingesammelten Feldern spawnt
			ArrayList<Knotenpunkt> notclaimed = new ArrayList<>();
			for (Knotenpunkt k : knotenpunkte) {
				if (!k.claimed) {
					notclaimed.add(k);
				}
			}
			if(notclaimed.size()>0) {
				Knotenpunkt k = notclaimed.get((int) (Math.random() * (notclaimed.size() - 1)));
				if (!k.claimed) {
					k.power_up = true;
				}
			}
			// Wenn es egal ist wo es spawnt
//			Knotenpunkt k = knotenpunkte.get((int) (Math.random() * (knotenpunkte.size() - 1)));
//			k.power_up = true;
		}
	}

	boolean getPowerUp(Knotenpunkt k) {
		return k.power_up;
	}

	void reset() {
		p = null;
		geister = new ArrayList<Geister>();
		p = new PacMan(8, 26, false);
		ladeMatrix("Map");
		farbeSetzen(2, 2, "rot");
		farbeSetzen(2, 29, "pink");
		farbeSetzen(28, 2, "cyan");
		farbeSetzen(28, 29, "orange");
//		farbeSetzen(28, 29, "pink");
//		farbeSetzen(2, 2, "pink");
		createGeister();
	}

	void resetLevel2() {
		p = null;
		geister = new ArrayList<Geister>();
		p = new PacMan(8, 26, false);
		ladeMatrix("Map");
		farbeSetzen(2, 2, "rot");
		createGeister2();
		ladeMatrix("Map");

	}

	void updateTimer() {
		timerAufruf++;
		textSetzen(2, 13, "Zeit " + timerAufruf);
	}

	public void clearmap() {
		for (int i = 1; i < 32; i++) {
			for (int j = 1; j < felderanzahl + 3; j++) {
				this.farbeLoeschen(i, j);
			}
		}
		ladeMatrix("Map");
	}

	// geändert
	protected void überprüfeSieg() {
		for (Geister g : geister) {

			if (g.getX() == p.getXpos() && g.getY() == p.getYpos()) {
				if (phase.equals("frightened")) {
					g.setEatenZustand(true);
					this.sound("Pacman_Eating_Ghost_Sound_Effect.wav", volume);
					geisterscore = geisterscore + 50;
				} else {
					verloren = true;
					p.setLeben(false);
					this.sound("Pacman_Dies_Sound_Effect.wav", volume);
				}
			}
		}
		for (Knotenpunkt k : knotenpunkte) {
			if (!k.claimed) {
				return;
			}
		}
		levelscore = scoreBerechnen();
		gewonnen = true;
	}

	private void createGeister() {
		for (int x = 1; x <= felderanzahl; x++) {
			for (int y = 1; y <= 32; y++) {
				if (farbeGeben(x, y).equals("rot")) {
					Geister g = new GeistSuchtFrau("Blinky", x, y, Geist.CHASE, this);
					geister.add(g);
				}
				if (farbeGeben(x, y).equals("pink")) {
					Geister g = new GeistSuchtFrau("Pinky", x, y, Geist.CHASE, this);
					geister.add(g);
				}
				if (farbeGeben(x, y).equals("cyan")) {
					Geister g = new GeistSuchtFrau("Inky", x, y, Geist.CHASE, this);
					geister.add(g);
				}
				if (farbeGeben(x, y).equals("orange")) {
					Geister g = new GeistSuchtFrau("Clyde", x, y, Geist.CHASE, this);
					geister.add(g);
				}
			}
		}
		ladeMatrix("Map");
	}

	private void createGeister2() {
		for (int x = 1; x <= felderanzahl; x++) {
			for (int y = 1; y <= 32; y++) {
				if (farbeGeben(x, y).equals("rot")) {
					Geister g = new Geist("Blinky", x, y, Geist.CHASE, this);
					geister.add(g);
				}
			}
		}
		ladeMatrix("Map");
	}

	private void zeichnePacManundGeister() {

		for (Knotenpunkt k : knotenpunkte) {
			if (p.getXpos() == k.x && p.getYpos() == k.y) {
				k.claimed = true;
			}
			bildLoeschen(k.x, k.y);
			if (k.power_up) {
				System.out.println("power_up");
				bildSetzen(k.x, k.y, "Pac-man-power_up.png", 0);
			} else if (!k.claimed) {
				bildSetzen(k.x, k.y, "Pac-man-coin.png", 0);
			}
		}
		
		for(int i = 13;i<=17;i++) {
			for(int j = 15;j<=17;j++) {
				bildLoeschen(i, j);
			}
		}

		for (Geister g : geister) {
			bildLoeschen(g.getX(), g.getY());
			// @TODO andere Gesiter
			switch (g.getName()) {
			case "Blinky":

				this.bildSetzen(g.getX(), g.getY(), "GeistRot.gif", 0);
				break;
			case "Pinky":
				this.bildSetzen(g.getX(), g.getY(), "GeistPink.gif", 0);
				break;
			case "Inky":
				this.bildSetzen(g.getX(), g.getY(), "GeistBlau.gif", 0);
				break;
			case "Clyde":
				this.bildSetzen(g.getX(), g.getY(), "GeistOrange.gif", 0);
				break;
			default:
				this.farbeSetzen(g.getX(), g.getY(), "rot");
				break;
			}
			if (phase.equals(FRIGHTENED)) {
				this.bildSetzen(g.getX(), g.getY(), "GeistFrightened.gif", 0);
			}
		}
		String img = "pacmanRechts.gif";
		double ausrichtung = 0;
		if(!p.getLeben()) {
			 img = "pacmanDies.gif";
			 ausrichtung = Math.PI/2;
		}
		switch (alteTaste) {
		case "W":
			bildSetzen(p.getXpos(), p.getYpos(), img, -Math.PI / 2 + ausrichtung);
			break;
		case "A":
			bildSetzen(p.getXpos(), p.getYpos(), img, Math.PI + ausrichtung);
			break;
		case "S":
			bildSetzen(p.getXpos(), p.getYpos(), img, Math.PI / 2 + ausrichtung);
			break;
		case "D":
			bildSetzen(p.getXpos(), p.getYpos(), img, 0 + ausrichtung);
			break;
		default:
			bildSetzen(p.getXpos(), p.getYpos(), img, 0 + ausrichtung);
			break;
		}
	}

	public boolean keineWand(String taste, int x, int y) {

		if (taste.equals("W")  || taste.equals("Up")) {
			return farbeGeben(x, y - 1).equals(mapfarbe) || farbeGeben(x, y - 1).equals("gelb");
		}
		if (taste.equals("A")  || taste.equals("Left")) {
			return farbeGeben(x - 1, y).equals(mapfarbe) || farbeGeben(x - 1, y).equals("gelb");
		}
		if (taste.equals("S")  || taste.equals("Down")) {
			return farbeGeben(x, y + 1).equals(mapfarbe) || farbeGeben(x, y + 1).equals("gelb");
		}
		if (taste.equals("D")  || taste.equals("Right")) {
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
		// speichereMatrix("Map", "blau");
	}

	@Override
	public void mausLeftClick(int x, int y) {
		// farbeSetzen(x, y, "blau");

	}

	@Override
	public void mausRightClick(int x, int y) {
		// farbeSetzen(x, y, "scharz");
	}

	public void färben(ArrayList<Knotenpunkt> weg) {
		for (int i = 0; i < weg.size(); i++) {
			bildLoeschen(weg.get(i).x, weg.get(i).y);
			farbeSetzen(weg.get(i).x, weg.get(i).y, "orange");
		}
	}

	int scoreBerechnen() {
		int counter = 0;
		for (Knotenpunkt k : knotenpunkte) {
			if (k.claimed) {
				counter++;
			}

		}
		counter = counter + geisterscore + levelscore;
		System.out.println(knotenpunkte.size() );
		setScore(counter);
		return counter;
	}

	void setScore(int score) {
		textSetzen(2, 12, "Score: " + score);
	}

	int tpXgeist() {
		return 15;
	}

	int tpYgeist() {
		return 13;
	}

	static int getKnotendelta(Knotenpunkt k, Knotenpunkt anfangsknoten) {
		int deltax = Math.abs(k.x - anfangsknoten.x);
		int deltay = Math.abs(k.y - anfangsknoten.y);
//		System.out.println(deltax + deltay);
		return deltax + deltay;
	}

	static public ArrayList<Knotenpunkt> getanknüpfendeknoten(Knotenpunkt anfangsknoten) {
		// @TODO auf used setzen,distanz setzen
		ArrayList<Knotenpunkt> anknüpfendeKnoten = new ArrayList<Knotenpunkt>();
		for (Knotenpunkt k : Master.knotenpunkte) {

			if (anfangsknoten.x == 1 && anfangsknoten.y == 16) {

				if (k.x == 29 && k.y == 16 && !k.used) {
					k.used = true;
					@SuppressWarnings("unchecked")
					ArrayList<Knotenpunkt> kopie = (ArrayList<Knotenpunkt>) anfangsknoten.weg.clone();
					kopie.add(k);
					k.setWeg(kopie);
					anknüpfendeKnoten.add(k);
				}
			}

			if (anfangsknoten.x == 29 && anfangsknoten.y == 16) {

				if (k.x == 1 && k.y == 16 && !k.used) {
					k.used = true;
					@SuppressWarnings("unchecked")
					ArrayList<Knotenpunkt> kopie = (ArrayList<Knotenpunkt>) anfangsknoten.weg.clone();
					kopie.add(k);
					k.setWeg(kopie);
					anknüpfendeKnoten.add(k);
				}

			}

			if (Master.getKnotendelta(k, anfangsknoten) == 1 && !k.used) {
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

}
