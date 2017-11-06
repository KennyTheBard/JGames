/*
	File Name: HUD.java
	Author: Teculescu Octavian
	Date: 27 iulie 2017, 00:25:09
*/
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;

public class HUD {
	
	private Handler handler;
	
	private static int WIDTH, HEIGHT;
	
	public HUD(Handler handler, int WIDTH, int HEIGHT) {
		this.handler = handler;
		this.WIDTH = WIDTH;
		this.HEIGHT = HEIGHT;
	}
	
	public void tick() {
		
	}
	
	public void render(Graphics g) {
		g.setColor(Color.white);
		g.drawRect(54,19,252,502);
		g.drawRect(354,19,200,200);
		
		Font font = new Font("Courier", Font.BOLD, 32);
		drawCenteredString(g,"SCORE:", new Rectangle(354,300,550,400),font);
		drawCenteredString(g,"" + handler.getScore(), new Rectangle(354,300,550,475),font);
	}
	
	private void drawCenteredString(Graphics g, String text, Rectangle rect, Font font) {
		FontMetrics metrics = g.getFontMetrics(font);

		int x = rect.getA().getX() + (rect.getWidth() - metrics.stringWidth(text)) / 2;
		int y = rect.getA().getY() + ((rect.getHeight() - metrics.getHeight()) / 2) + metrics.getAscent();

		g.setFont(font);

		g.drawString(text, x, y);
	}
	
}
