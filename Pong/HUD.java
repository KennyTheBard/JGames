/*
	File Name: HUD.java
	Author: Teculescu Octavian
	Date: 27 iulie 2017, 00:25:09
*/
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.BasicStroke;
import java.awt.Stroke;

public class HUD {
	
	private Handler handler;
	
	private static int PlayerScore, BotScore;
	
	private int WIDTH, HEIGHT;
	
	public HUD(Handler handler, int WIDTH, int HEIGHT) {
		this.handler = handler;
		this.WIDTH = WIDTH;
		this.HEIGHT = HEIGHT;
		this.PlayerScore = 0;
		this.BotScore = 0;
	}
	
	public void tick(Handler handler) {
		for(int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			if(tempObject.getID() == ID.Ball) {
				if(tempObject.getHitbox().getA().getY() < HEIGHT/12-15) {
					playerScores();
					handler.reset();
				}
				if(tempObject.getHitbox().getB().getY() > HEIGHT*11/12+15) {
					botScores();
					handler.reset();
				}
			}
		}
	}
	
	public void reset() {
		this.PlayerScore = 0;
		this.BotScore = 0;
	}
	
	public String getBotScore() {
		return BotScore + "";
	}
	
	public String getPlayerScore() {
		return PlayerScore + "";
	}
	
	public void render(Graphics g) {
		g.setColor(Color.white);
		Font font = new Font("Courier", Font.BOLD, 48);
		drawCenteredString(g, getBotScore(), new Rectangle(WIDTH*11/12, HEIGHT/2-100, WIDTH, HEIGHT/2), font);
		drawCenteredString(g, getPlayerScore(), new Rectangle(WIDTH*11/12, HEIGHT/2+100, WIDTH, HEIGHT/2), font);
		
		g.setColor(Color.gray);
		drawDashedLine(g, 0, HEIGHT/2, WIDTH, HEIGHT/2);
	}
	
	public void playerScores() {
		this.PlayerScore++;
	}
	
	public void botScores() {
		this.BotScore++;
	}
	
	private void drawCenteredString(Graphics g, String text, Rectangle rect, Font font) {
		FontMetrics metrics = g.getFontMetrics(font);

		int x = rect.getA().getX() + (rect.getWidth() - metrics.stringWidth(text)) / 2;
		int y = rect.getA().getY() + ((rect.getHeight() - metrics.getHeight()) / 2) + metrics.getAscent();

		g.setFont(font);

		g.drawString(text, x, y);
	}
	
	public void drawDashedLine(Graphics g, int x1, int y1, int x2, int y2){

        //creates a copy of the Graphics instance
        Graphics2D g2d = (Graphics2D) g.create();

        //set the stroke of the copy, not the original 
        Stroke dashed = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0);
        g2d.setStroke(dashed);
        g2d.drawLine(x1, y1, x2, y2);

        //gets rid of the copy
        g2d.dispose();
	}
}
