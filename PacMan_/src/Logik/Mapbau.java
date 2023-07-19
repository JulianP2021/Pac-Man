package Logik;

import Viualisierung.Kaestchen;

public class Mapbau extends Kaestchen{
	public static void main(String[] args) {
		new Mapbau();
	}
	
	Mapbau(){
		super(0,0,40, 40, 29, 32);
		ladeMatrix("Map");
		for(int i = 0;i<=29;i++) {
			for(int j = 0;j<=32;j++) {
				if(farbeGeben(i, j).equals("durchsichtig")) {
				
					farbeSetzen(i, j, "schwarz");
				}
			}
		}
		
	}

	@Override
	public void mausLeftClick(int x, int y) {
		farbeSetzen(x, y, "blau");
		
	}

	@Override
	public void tasteClick(String taste) {
		// TODO Auto-generated method stub
		speichereMatrix("Map");
	}
}
