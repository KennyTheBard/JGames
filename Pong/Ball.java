/*
	File Name: Ball.java
	Author: Teculescu Octavian
	Date: 26 iulie 2017, 14:05:27
*/
import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Ball extends GameObject {
	
	public Ball(int xA, int yA, int xB, int yB, ID id) {
		super(xA, yA, xB, yB, id);
	}
	
	public Ball(Point A, Point B, ID id) {
		super(A, B, id);
	}
	
	public Ball(Rectangle hitbox, ID id) {
		super(hitbox, id);
	}
	
	private void startRound() {
		Random rand = new Random();
		int x = rand.nextInt(50);
		
		/*
		long firstTime = System.currentTimeMillis();
		long thisTime = System.currentTimeMillis();
		while(thisTime - firstTime < 3000)
			thisTime = System.currentTimeMillis();
		*/
		
		if(x % 2 == 0)
			setVelY(6);
		else
			setVelY(-6);
	}
	
	public void tick(Handler handler) {
		
		if(getVelX() == 0 && getVelY() == 0)
			startRound();
		
		move(getVelX(),getVelY());
		
		if(getHitbox().getA().getX() <= 0)
			setVelX(getVelX() * (-1));
		
		if(getHitbox().getB().getX() >= Game.WIDTH)
			setVelX(getVelX() * (-1));
	}
	
	public void render(Graphics g) {
		g.setColor(Color.white);
		g.fillOval(hitbox.getA().getX(), hitbox.getA().getY(), hitbox.getWidth(), hitbox.getHeight());
			
	}
	
}
