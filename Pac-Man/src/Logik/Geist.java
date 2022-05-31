package Logik;

public class Geist {
	int x;
	int y;
	boolean chase = true;
	
	
	public Geist(int x, int y, boolean chase) {
		super();
		this.x = x;
		this.y = y;
		this.chase = chase;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public boolean isChase() {
		return chase;
	}
	public void setChase(boolean chase) {
		this.chase = chase;
	}
	
	
}
