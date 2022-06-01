package Logik;

public class Geist {
	private String name;
	private int x;
	private int y;
	String phase = "";
	static String CHASE = "chase";
	static String FRIGHTENED = "frightened";
	static String SCATTER = "scatter";
	static String EATEN = "eaten";
	
	public Geist(String name,int x, int y, String phase) {
		this.name = name;
		this.x = x;
		this.y = y;
		this.phase = phase;
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
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
