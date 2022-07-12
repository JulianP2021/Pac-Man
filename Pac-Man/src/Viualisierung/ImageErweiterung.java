package Viualisierung;

import java.awt.Image;

public class ImageErweiterung {
	private Image img;
	private double ausrichtung;

	public ImageErweiterung(Image img, double ausrichtung) {
		super();
		this.img = img;
		this.ausrichtung = ausrichtung;
	}

	public Image getImg() {
		return img;
	}

	public void setImg(Image img) {
		this.img = img;
	}

	public double getAusrichtung() {
		return ausrichtung;
	}

	public void setAusrichtung(double ausrichtung) {
		this.ausrichtung = ausrichtung;
	}
}
