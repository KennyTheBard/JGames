/*
	File Name: Bot.java
	Author: Teculescu Octavian
	Date: 26 iulie 2017, 14:05:27
*/
import java.awt.Color;
import java.awt.Graphics;

public class Bot extends GameObject {
	
	private Ball ball;
	
	public Bot(int xA, int yA, int xB, int yB, ID id) {
		super(xA, yA, xB, yB, id);
	}
	
	public Bot(Point A, Point B, ID id) {
		super(A, B, id);
	}
	
	public Bot(Rectangle hitbox, ID id) {
		super(hitbox, id);
	}
	
	public void setBall(Ball ball) {
		this.ball = ball;
	}
	
	public void tick(Handler handler) {
		
		if(ball.getX() < getX())
			setVelX(-4);
		else if(ball.getX() > getX())
			setVelX(4);
		else
			setVelX(0);
		
		move(getVelX(),getVelY());
		Point pct;
		
		// Border work for A
		pct = hitbox.getA();
		pct.setX(Game.clamp(pct.getX(),0,Game.WIDTH - hitbox.getWidth()));
		pct.setY(Game.clamp(pct.getY(),0,Game.HEIGHT - hitbox.getHeight()));
		
		// Border work for B
		pct = hitbox.getB();
		pct.setX(Game.clamp(pct.getX(),hitbox.getWidth(),Game.WIDTH));
		pct.setY(Game.clamp(pct.getY(),hitbox.getHeight(),Game.HEIGHT));
		
	}
	
	public void render(Graphics g) {
		g.setColor(Color.white);
		g.fillRect(hitbox.getA().getX(), hitbox.getA().getY(), hitbox.getWidth(), hitbox.getHeight());
			
	}
	
}
