/*
	File Name: GameObject.java
	Author: Teculescu Octavian
	Date: 24 iulie 2017, 23:41:49
*/
import java.awt.Graphics;

public abstract class GameObject {
	
	protected Rectangle hitbox;
	private ID id;
	private int velX, velY;
	
	public GameObject(Rectangle hitbox, ID id) {
		this.hitbox = hitbox;
		this.id = id;
		velX = 0;
		velY = 0;
	}
	
	public GameObject(Point A, Point B, ID id) {
		this.hitbox = new Rectangle(A,B);
		this.id = id;
		velX = 0;
		velY = 0;
	}
	
	public GameObject(int xA, int yA, int xB, int yB, ID id) {
		this.hitbox = new Rectangle(xA, yA, xB, yB);
		this.id = id;
		velX = 0;
		velY = 0;
	}
	
	public abstract void tick(Handler handler);
	public abstract void render(Graphics g);
	
	public int getX() {
		return hitbox.getCenter().getX();
	}
	
	public int getY() {
		return hitbox.getCenter().getY();
	}
	
	public void move(int distX, int distY) {
		hitbox.move(distX,distY);
	}
	
	public int getVelX() {
		return velX;
	}
	
	public int getVelY() {
		return velY;
	}
	
	public void setVelX(int newVelX) {
		velX = newVelX;
	}
	
	public void setVelY(int newVelY) {
		velY = newVelY;
	}
	
	public void setID(ID id) {
		this.id = id;
	}
	
	public ID getID() {
		return id;
	}
	
	public Rectangle getHitbox() {
		return hitbox;
	}
}
