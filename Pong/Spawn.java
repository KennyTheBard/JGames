/*
	File Name: Spawn.java
	Author: Teculescu Octavian
	Date: 31 iulie 2017, 18:03:49
*/

public class Spawn {
	
	private Handler handler;
	
	private int WIDTH, HEIGHT;
	
	public Spawn(Handler Handler, int WIDTH, int HEIGHT) {
		this.handler = Handler;
		this.WIDTH = WIDTH;
		this.HEIGHT = HEIGHT;
	}
	
	public void tick() {
	
		// Asigura existenta playerului
		boolean player_exist = false;
		for(int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			if(tempObject.getID() == ID.Player)
				player_exist = true;
		}
		if(!player_exist) {
			Player player = new Player(new Rectangle(WIDTH/2,HEIGHT*11/12,WIDTH/2+80,HEIGHT*11/12+15),ID.Player);
			handler.addObject(player);
		}
		
		// Asigura existenta adversarului
		boolean bot_exist = false;
		for(int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			if(tempObject.getID() == ID.Bot)
				bot_exist = true;
		}
		if(!bot_exist) {
			Bot bot = new Bot(new Rectangle(WIDTH/2,HEIGHT/12-15,WIDTH/2+80,HEIGHT/12),ID.Bot);
			handler.addObject(bot);
			
			// Asigura existenta mingiei
			boolean ball_exist = false;
			for(int i = 0; i < handler.object.size(); i++) {
				GameObject tempObject = handler.object.get(i);
				if(tempObject.getID() == ID.Ball)
					ball_exist = true;
			}
			if(!ball_exist) {
				Ball ball = new Ball(new Rectangle(WIDTH/2-10,HEIGHT/2-10,WIDTH/2+10,HEIGHT/2+10),ID.Ball);
				handler.addObject(ball);
				bot.setBall(ball);
			}
			
		}
		
		/*
		// Asigura existenta mingiei
		boolean ball_exist = false;
		for(int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			if(tempObject.getID() == ID.Ball)
				ball_exist = true;
		}
		if(!ball_exist) {
			Ball ball = new Ball(new Rectangle(WIDTH/2-10,HEIGHT/2-10,WIDTH/2+10,HEIGHT/2+10),ID.Ball);
			handler.addObject(ball);
		}
		*/
	}
}
