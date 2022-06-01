package Logik;

public class PacMan {
	private int xpos;
	private int ypos;
	private int leben;
	private boolean chase = false;
	
	public PacMan(int xpos, int ypos, int leben, boolean chase) {
		this.xpos = xpos;
		this.ypos = ypos;
		this.leben = leben;
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
	public int getLeben() {
		return leben;
	}
	public void setLeben(int leben) {
		this.leben = leben;
	}
	public boolean isChase() {
		return chase;
	}
	public void setChase(boolean chase) {
		this.chase = chase;
	}
	
	
	

}
