/*
	File Name: KeyInput.java
	Author: Teculescu Octavian
	Date: 26 iulie 2017, 14:17:37
*/
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {
	
	private Handler handler;
	
	private Game game;
	
	// ATENTION: Change the number of element if you add up some keys!
	private boolean[] keyDown = new boolean[3];
	
	public KeyInput(Handler handler, Game game) {
		this.handler = handler;
		this.game = game;
		
		for(int i = 0; i < 3; i++) {
			keyDown[i] = false;
		}
	}
	
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
		for(int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getID() == ID.Player) {
				if(key == KeyEvent.VK_A) {
					tempObject.setVelX(-4);
					keyDown[0] = true; 	// A key
				}
				if(key == KeyEvent.VK_D) {
					tempObject.setVelX(4);
					keyDown[1] = true;	// D key
				}
				if(key == KeyEvent.VK_SPACE) {
					// Action
					keyDown[2] = true;	// SPACE key
				}
			}
		}
		
		if(key == KeyEvent.VK_ESCAPE)
			if(game.gameState == Game.STATE.Game)
				game.gameState = Game.STATE.Pause;
			else
				game.gameState = Game.STATE.Game;

	}
	
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		
		for(int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getID() == ID.Player) {
			
				if(key == KeyEvent.VK_A) {
					keyDown[0] = false;	// A key
				}
				
				if(key == KeyEvent.VK_D) {
					keyDown[1] = false;	// D key
				}
				
				if(key == KeyEvent.VK_SPACE) {
					keyDown[2] = false;	// SPACE key
				}
				
				if(!keyDown[0] && !keyDown[1]) tempObject.setVelX(0);
			}
		}
	}
	
}
