package Logik;

public class PacMan {
	private int xpos;
	private int ypos;
	boolean lebendig = true;
	private boolean chase = false;
	
	public PacMan(int xpos, int ypos, boolean chase) {
		this.xpos = xpos;
		this.ypos = ypos;
		this.chase = chase;
		System.out.println(xpos+" "+ ypos);
	}
	public int getXpos() {
		return xpos;
	}
	public void setXpos(int xpos) {
		this.xpos = xpos;
	}
	public int getYpos() {
		return ypos;
	}
	public void setYpos(int ypos) {
		this.ypos = ypos;
	}
	public boolean getLeben() {
		return lebendig;
	}
	public void setLeben(boolean lebendig) {
		this.lebendig = lebendig;
	}
	public boolean isChase() {
		return chase;
	}
	public void setChase(boolean chase) {
		this.chase = chase;
	}
	
	
	

}
