/*
	File Name: Menu.java
	Author: Teculescu Octavian
	Date: 1 august 2017, 15:47:49
*/
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.MouseInfo;

public class Menu extends MouseAdapter {
	
	private static int WIDTH, HEIGHT;
	
	private int delta_x = 0, delta_y = 0;
	
	private int numButtons = 0;
	
	private int space;
	
	private boolean clicked = false;
	
	Game game;
	
	private Button[] buttons = new Button[10];
 	
	public Menu(Game game, int WIDTH, int HEIGHT, int space) {
		this.game = game;
		this.WIDTH = WIDTH;
		this.HEIGHT = HEIGHT;
		this.numButtons = 0;
		
		this.space = space;
	}
	
	private void updateButtons(String name) {
	
		int startHeight = HEIGHT/4;
		int endHeight = HEIGHT*5/6;
		int startWidth = WIDTH/2 - WIDTH/4;
		int endWidth = WIDTH/2 + WIDTH/4;
		
		int buttonHeight = (endHeight - startHeight - space*(numButtons - 1)) / numButtons;
		
		String temp = "";
		
		for(int i = 0; i < numButtons; i++) {
		
			if(i < numButtons - 1) temp = buttons[i].getName();
			else temp = name;
			
			buttons[i] = new Button(new Rectangle(startWidth,
				startHeight + buttonHeight*i + space*i,
					endWidth,
						startHeight + buttonHeight*(i+1) + space*i));
			
			buttons[i].setName(temp);
		}
	}
	
	public void addButton(String name) {
		numButtons++;
		updateButtons(name);
	}
	
	public void mousePressed(MouseEvent e) {
		this.clicked = true;
		int mx = e.getX();
		int my = e.getY();
		int rx = MouseInfo.getPointerInfo().getLocation().x;
		int ry = MouseInfo.getPointerInfo().getLocation().y;
		
		delta_x = mx - rx;
		delta_y = my - ry;

		for(int i = 0; i < numButtons; i++) {
			if(mouseOver(mx, my,buttons[i].getRect())) {
				// Add
				
				if(i == 0) game.gameState = Game.STATE.Game;
				if(i == 2) System.exit(1);
			}
		}
	}
	
	public void mouseReleased(MouseEvent e) {
	
	}
	
	private boolean mouseOver(int mx, int my, Rectangle button) {
		if(mx > button.getA().getX() && mx < button.getB().getX()) {
			if(my > button.getA().getY() && my < button.getB().getY()) {
				return true;
			} else return false;
		} else return false;
	}
	
	public void tick() {
	
	}
	
	public void render(Graphics g) {
	
		g.setColor(Color.white);
		Font font;
		
		font = new Font("Courier", Font.BOLD, WIDTH/8);
		
		drawCenteredString(g, "Menu", new Rectangle(0,0,WIDTH,HEIGHT/5),font);
		
		for(int i = 0; i < numButtons; i++) {
			font = new Font("Courier", Font.BOLD, buttons[i].getRect().getHeight()/2);
			int a = buttons[i].getRect().getA().getX();
			int b = buttons[i].getRect().getA().getY();
			int c = buttons[i].getRect().getWidth();
			int d = buttons[i].getRect().getHeight();
			g.drawRect(a,b,c,d);
			
			if(mouseOver(MouseInfo.getPointerInfo().getLocation().x + delta_x, MouseInfo.getPointerInfo().getLocation().y + delta_y, new Rectangle(a,b,a+c,b+d))) {
				g.fillRect(a,b,c,d);
				g.setColor(Color.black);
			}
			
			drawCenteredString(g, buttons[i].getName(), new Rectangle(a,b,a+c,b+d), font);
			
			if(mouseOver(MouseInfo.getPointerInfo().getLocation().x + delta_x, MouseInfo.getPointerInfo().getLocation().y + delta_y, new Rectangle(a,b,a+c,b+d))) {
				g.setColor(Color.white);
			}
		}
		
		
		font = new Font("Courier", Font.BOLD, 16);
		drawCenteredString(g,"Pot of Delight v0.0.1", new Rectangle(WIDTH/2,HEIGHT*11/12,WIDTH,HEIGHT),font);
		
		if(!clicked)
			drawCenteredString(g,"Click once anywhere!", new Rectangle(0,0,WIDTH,HEIGHT/3),font);
		
	}
	
	private void drawCenteredString(Graphics g, String text, Rectangle rect, Font font) {
		FontMetrics metrics = g.getFontMetrics(font);

		int x = rect.getA().getX() + (rect.getWidth() - metrics.stringWidth(text)) / 2;
		int y = rect.getA().getY() + ((rect.getHeight() - metrics.getHeight()) / 2) + metrics.getAscent();

		g.setFont(font);

		g.drawString(text, x, y);
	}

}
