package Logik;

public class GeistSuchtFrau implements Geister {

	private String nameGeist;
	private int xGeist;
	private int yGeist;
	String phase = "";
	static String CHASE = "chase";
	static String FRIGHTENED = "frightened";
	static String SCATTER = "scatter";
	static String EATEN = "eaten";

	int abstandX;
	int abstandY;
	int laengeOben, laengeUnten, laengeRechts, laengeLinks, kuerzesteLaenge;
	public String Richtung = "O";
	Master m;

	public GeistSuchtFrau(String name, int x, int y, String phase, Master m) {
		this.xGeist = x;
		this.yGeist = y;
		this.nameGeist = name;
		this.phase = phase;
		this.m = m;
	}

	@Override
	public String findeWeg(int xZiel, int yZiel) {
		int x,y; kuerzesteLaenge=1000;
		
		x=xGeist;y=yGeist-1;
		if((!(m.farbeGeben(x, y).equals("grün")))&&(!(Richtung.equals("runter")))) {
			
			abstandX = Math.abs(xZiel - x);
			abstandY = Math.abs(yZiel - y);
			laengeOben=abstandX*abstandX+abstandY*abstandY;
					if(kuerzesteLaenge>laengeOben) {
						kuerzesteLaenge=laengeOben;
						
					}
		}
		x=xGeist;y=yGeist+1;
		if((!(m.farbeGeben(x, y).equals("grün")))&&(!(Richtung.equals("hoch")))) {
			abstandX = Math.abs(xZiel - x);
			abstandY = Math.abs(yZiel - y);
			laengeUnten=abstandX*abstandX+abstandY*abstandY;
			if(kuerzesteLaenge>laengeUnten) {
				kuerzesteLaenge=laengeUnten;
				
			}
		}			
		x=xGeist+1;y=yGeist;
		if((!(m.farbeGeben(x, y).equals("grün")))&&(!(Richtung.equals("links")))) {
			abstandX = Math.abs(xZiel - x);
			abstandY = Math.abs(yZiel - y);
			laengeRechts=abstandX*abstandX+abstandY*abstandY;
			if(kuerzesteLaenge>laengeRechts) {
				kuerzesteLaenge=laengeRechts;
				
			}
		}
		x=xGeist-1;y=yGeist;
		if((!(m.farbeGeben(x, y).equals("grün")))&&(!(Richtung.equals("rechts")))) {
			abstandX = Math.abs(xZiel - x);
			abstandY = Math.abs(yZiel - y);
			laengeLinks=abstandX*abstandX+abstandY*abstandY;
			if(kuerzesteLaenge>laengeLinks) {
				kuerzesteLaenge=laengeLinks;
				
			}
		}
		
		if(kuerzesteLaenge==laengeOben) {
			Richtung="hoch";
		}else if(kuerzesteLaenge==laengeUnten) {
			Richtung="runter";
		}else if(kuerzesteLaenge==laengeRechts) {
			Richtung="rechts";
		}else if(kuerzesteLaenge==laengeLinks) {
			Richtung="links";
		}
		return Richtung;
	}

	// unnötig
	// unnötig
	// unnötig
	// unnötig

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
}
