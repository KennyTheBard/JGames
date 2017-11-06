/*
	File Name: GameObject.java
	Author: Teculescu Octavian
	Date: 24 iulie 2017, 23:41:49
*/
import java.awt.Graphics;

public abstract class GameObject {
	
	private ID id;
	
	public GameObject(ID id) {
		this.id = id;

	}
	
	public abstract void tick(Handler handler);
	public abstract void render(Graphics g);
	
	public void setID(ID id) {
		this.id = id;
	}
	
	public ID getID() {
		return id;
	}
	
}
