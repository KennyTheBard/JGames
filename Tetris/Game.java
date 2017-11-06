/*
	File Name: Tetris.java
	Author: Teculescu Octavian
	Date: 24 iulie 2017, 18:33:40
*/
import java.awt.Canvas;
import java.awt.image.BufferStrategy;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.Font;
import java.awt.image.BufferedImage;    
    
public class Game extends Canvas implements Runnable {
	
	private static final long serialVersionUID = 2187319728479147L;
	
	public static final int WIDTH = 720, HEIGHT = WIDTH /12 * 9;
		
	private Thread thread;
	
	private boolean running = false;
	
	private boolean paused = false;
	
	private Handler handler;
	private HUD hud;
	private Spawn spawner;
	private Menu menu;
	
	public enum STATE {
		Menu,
		Game
	};
	
	public STATE gameState = STATE.Game;
	
	public Game() {
		handler = new Handler();
		this.addKeyListener(new KeyInput(handler,this));
		
		Window win = new Window(WIDTH,HEIGHT,"Tetris",this);
		
		hud = new HUD(handler,WIDTH,HEIGHT);
		spawner = new Spawn(handler,WIDTH,HEIGHT);
		
		menu = new Menu(this,WIDTH,HEIGHT,16);
		this.addMouseListener(menu);
		
		menu.addButton("Start");
		menu.addButton("Quit");
		
		/*
		if(gameState == STATE.Game) {
			// Add objects here:
			
			handler.addObject(player1);
		}
		*/
	}
	
	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
		running = true;
	}
	
	public synchronized void stop() {
		try {
			thread.join();
			running = false;
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void run() {
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while(running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >=1) {
				tick();
				delta--;
			}
			if(running)
				render();
			frames++;
			
			if(System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				// Print FPS:
				//System.out.println("FPS: "+ frames);
				frames = 0;
			}
		}
		stop();
	}
	
	private void tick() {
		if(gameState == STATE.Game) {
			handler.tick();
			hud.tick();
			spawner.tick();
		} else if (gameState == STATE.Menu) {
			menu.tick();
		}
	}
	
	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}

		Graphics g = bs.getDrawGraphics();
		
		g.setColor(Color.black);
		g.fillRect(0,0,WIDTH,HEIGHT);
		
		handler.render(g);

		if(gameState == STATE.Game) {
			hud.render(g);
		} else if (gameState == STATE.Menu) {
			menu.render(g);
		}
		
		g.dispose();
		bs.show();
		
		Toolkit.getDefaultToolkit().sync();
	}
	
	public static int clamp(int var, int min, int max) {
		if(var >= max)
			return max;
		else if(var <= min)
			return min;
		else 
			return var;
	}
	
	public static void main(String[] args) {
		new Game();
	}
}
