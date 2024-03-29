package Logik;

public class GeistSuchtFrau implements Geister {

	private String nameGeist;
	private int xGeist;
	private int yGeist;
	int abstandX;
	int abstandY;
	public int laengeOben, laengeUnten, laengeRechts, laengeLinks, kuerzesteLaenge;
	public String Richtung = "O";
	Master m;
	int eatenCounter=0;
	boolean eatenZustand = false;

	public GeistSuchtFrau(String name, int x, int y, String phase, Master m) {
		this.xGeist = x;
		this.yGeist = y;
		this.nameGeist = name;
		this.m = m;
	}

	@Override
	public String findeWeg(int xZiel, int yZiel) {
		int x, y;
		kuerzesteLaenge = 10000;
		laengeOben = 10000;
		laengeLinks = 10000;
		laengeRechts = 10000;
		laengeUnten = 10000;
		x = xGeist;
		y = yGeist - 1;
		if ((!(m.farbeGeben(x, y).equals(Master.borderfarbe))) && (!(Richtung.equals("runter")))) {

			abstandX = Math.abs(xZiel - x);
			abstandY = Math.abs(yZiel - y);
			laengeOben = abstandX * abstandX + abstandY * abstandY;
			if (kuerzesteLaenge > laengeOben) {
				kuerzesteLaenge = laengeOben;

			}
		}
		x = xGeist;
		y = yGeist + 1;
		if ((!(m.farbeGeben(x, y).equals(Master.borderfarbe))) && (!(Richtung.equals("hoch")))) {
			abstandX = Math.abs(xZiel - x);
			abstandY = Math.abs(yZiel - y);
			laengeUnten = abstandX * abstandX + abstandY * abstandY;
			if (kuerzesteLaenge > laengeUnten) {
				kuerzesteLaenge = laengeUnten;

			}
		}
		x = xGeist + 1;
		y = yGeist;
		if ((!(m.farbeGeben(x, y).equals(Master.borderfarbe))) && (!(Richtung.equals("links")))) {
			abstandX = Math.abs(xZiel - x);
			abstandY = Math.abs(yZiel - y);
			laengeRechts = abstandX * abstandX + abstandY * abstandY;
			if (kuerzesteLaenge > laengeRechts) {
				kuerzesteLaenge = laengeRechts;

			}
		}
		x = xGeist - 1;
		y = yGeist;
		if ((!(m.farbeGeben(x, y).equals(Master.borderfarbe))) && (!(Richtung.equals("rechts")))) {
			abstandX = Math.abs(xZiel - x);
			abstandY = Math.abs(yZiel - y);
			laengeLinks = abstandX * abstandX + abstandY * abstandY;
			if (kuerzesteLaenge > laengeLinks) {
				kuerzesteLaenge = laengeLinks;

			}
		}

		if (kuerzesteLaenge == laengeOben) {
			Richtung = "hoch";
		} else if (kuerzesteLaenge == laengeUnten) {
			Richtung = "runter";
		} else if (kuerzesteLaenge == laengeRechts) {
			Richtung = "rechts";
		} else if (kuerzesteLaenge == laengeLinks) {
			Richtung = "links";
		}
		return Richtung;
	}

	// unn�tig
	// unn�tig
	// unn�tig
	// unn�tig

	public int getX() {
		return xGeist;
	}

	public void setX(int x) {
		this.xGeist = x;
	}

	public int getY() {
		return yGeist;
	}

	public void setY(int y) {
		this.yGeist = y;
	}

	public String getName() {
		return nameGeist;
	}

	public void setName(String name) {
		this.nameGeist = name;
	}

	@Override
	public String getRichtung() {
		// TODO Auto-generated method stub
		return Richtung;
	}

	@Override
	public void setRichtung(String r) {
		// TODO Auto-generated method stub
		Richtung = r;
	}

	@Override
	public int getKuerzesteLaenge() {
		// TODO Auto-generated method stub
		return kuerzesteLaenge;
	}

	@Override
	public int getCountEATEN() {
		// TODO Auto-generated method stub
		return eatenCounter;
	}

	@Override
	public void setCountEATEN(int eatenCounter) {
		// TODO Auto-generated method stub
		this.eatenCounter = eatenCounter;
	}

	@Override
	public boolean getEatenZustand() {
		// TODO Auto-generated method stub
		return eatenZustand;
	}

	@Override
	public void setEatenZustand(boolean eaten) {
		// TODO Auto-generated method stub
		this.eatenZustand = eaten;
	}

}
