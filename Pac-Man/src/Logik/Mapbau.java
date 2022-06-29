package Logik;

import Viualisierung.Kaestchen;

public class Mapbau extends Kaestchen{
	public static void main(String[] args) {
		new Mapbau();
	}
	
	Mapbau(){
		super(40, 40, 29, 32);
		ladeMatrix("Map");
		for(int i = 0;i<=29;i++) {
			for(int j = 0;j<=32;j++) {
				if(farbeGeben(i, j).equals("blau")) {
				
					farbeSetzen(i, j, "grün");
				}
			}
		}
		
	}

	@Override
	public void mausLeftClick(int x, int y) {
		speichereMatrix("Map");
		
	}

	@Override
	public void tasteClick(String taste) {
		// TODO Auto-generated method stub
		
	}
}
