/*
	File Name: Handler.java
	Author: Teculescu Octavian
	Date: 24 iulie 2017, 23:32:02
*/
import java.util.LinkedList;
import java.awt.Graphics;

public class Handler {
	
	LinkedList<GameObject> object = new LinkedList<>();
	
	public void tick() {
		for(int i = 0; i < object.size(); i++) {
			GameObject tempObject = object.get(i);
			tempObject.tick(this);
		}
		
		ballCollision();
	}
	
	public void render(Graphics g) {
		for(int i = 0; i < object.size(); i++) {
			GameObject tempObject = object.get(i);
			tempObject.render(g);
		}
	}
	
	public void addObject(GameObject object) {
		this.object.add(object);
	}
	
	public void removeObject(GameObject object) {
		this.object.remove(object);
	}
	
	public void reset() {
		int len = object.size();
		GameObject[] obj = new GameObject[len];
		
		for(int i = 0; i < object.size(); i++) {
			GameObject tempObject = object.get(i);
			//removeObject(tempObject);
			obj[i] = tempObject;
		}
		
		for(int i = 0; i < len; i++) {
			removeObject(obj[i]);
		}
	}
	
	private void ballCollision() {
		for(int i = 0; i < object.size(); i++) {
			GameObject ball = object.get(i);
			if(ball.getID() == ID.Ball) {
			
				for(int j = 0; j < object.size(); j++) {
					GameObject other = object.get(j);
					if(other.getID() != ID.Ball) 
						if(ballWillCollide(ball,other)) 
							calculateNewDirection(ball,other);
				}
				
			}	
		}
	}
	
	private boolean ballWillCollide(GameObject ball, GameObject other) {
		GameObject futureBall = new Ball(
									new Rectangle(ball.getHitbox().getA().getX() + ball.getVelX(),
										ball.getHitbox().getA().getY() + ball.getVelY(),
											ball.getHitbox().getB().getX() + ball.getVelX(),
												ball.getHitbox().getB().getY() + ball.getVelY())
											, ID.Ball);
		return futureBall.getHitbox().collide(other.getHitbox());
	}
	
	private void calculateNewDirection(GameObject ball, GameObject other) {
		int mid = other.getHitbox().getCenter().getX();
		int hit = ball.getHitbox().getCenter().getX();
		
		int limit = other.getHitbox().getB().getX();
		int seg = (limit - mid)/5;
		
		int dist = hit - mid;
		int sign;
		if(dist >= 0) {
			sign = 1;
		} else {
			sign = -1;
			dist = (-1) * dist;
		}
		
		int dir;
		if(ball.getVelY() > 0)
			dir = -1;
		else
			dir = 1;
			

		
		ball.setVelX(dist / seg * sign); 
		ball.setVelY((6 - dist / seg) * dir);
		
		
		//System.out.println("Vel X: " + ball.getVelX());
		//System.out.println("Vel Y: " + ball.getVelY());
	}
	
}
