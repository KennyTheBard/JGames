/*
	File Name: KeyInput.java
	Author: Teculescu Octavian
	Date: 26 iulie 2017, 14:17:37
*/
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {
	
	private Handler handler;
	
	// ATENTION: Change the number of element if you add up some keys!
	private boolean[] keyDown = new boolean[4];
	
	private Game game;
	
	public KeyInput(Handler handler, Game game) {
		this.handler = handler;
		this.game = game;
		
		for(int i = 0; i < 4; i++) {
			keyDown[i] = false;
		}
	}
	
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
		for(int i = 0; i < handler.object.size(); i++) {
			Piece tempObject = handler.object.get(i);
			
			if(tempObject.getID() == ID.Falling) {
				
				boolean[][] shape = tempObject.getShape();
				int xx = tempObject.getX();
				int yy = tempObject.getY();
				
				boolean[][] board = handler.getBoard();
				
				if(key == KeyEvent.VK_KP_RIGHT || key == KeyEvent.VK_D) {
					boolean doable = true;
					for(int a = 0; a < 4; a++)
						for(int b = 0; b < 4; b++)
							if(shape[a][b])
								if(xx + a + 1 > 0 && xx + a + 1 < 9)
									if(board[xx + a + 1][yy + b])
										doable = false;
					if(doable)
						tempObject.moveRight();
					keyDown[0] = true; 	// RIGHT ARROW key
				}
				if(key == KeyEvent.VK_KP_LEFT || key == KeyEvent.VK_A) {
					boolean doable = true;
					for(int a = 0; a < 4; a++)
						for(int b = 0; b < 4; b++)
							if(shape[a][b])
								if(xx + a - 1 > 0 && xx + a - 1 < 9)
									if(board[xx + a - 1][yy + b])
										doable = false;
					if(doable)
						tempObject.moveLeft();
					keyDown[1] = true;	// LEFT ARROW key
				}
				if(key == KeyEvent.VK_SPACE) {
					if(!collideAfterRotate(tempObject))
						tempObject.rotate();
					keyDown[2] = true;	// SPACE ARROW key
				}
				if(key == KeyEvent.VK_KP_DOWN || key == KeyEvent.VK_S) {
					handler.setDelta(50);
					keyDown[3] = true;	// DOWN ARROW key
				}
			}
		}
		
		/*  Nu sterge piesa in asteptare
		if(key == KeyEvent.VK_ESCAPE) {
			if(game.gameState == Game.STATE.Game) {
				game.gameState = Game.STATE.Menu;
				for(int i = 0; i < handler.object.size(); i++) {
					Piece tempObject = handler.object.get(i);
					handler.removeObject(tempObject);
				}
			}
		}
		*/
	}
	
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		
		for(int i = 0; i < handler.object.size(); i++) {
			Piece tempObject = handler.object.get(i);
			
			if(tempObject.getID() == ID.Falling) {
			
				if(key == KeyEvent.VK_KP_RIGHT || key == KeyEvent.VK_D) {
					keyDown[0] = false;	// RIGHT ARROW key
				}
				
				if(key == KeyEvent.VK_KP_LEFT || key == KeyEvent.VK_A) {
					keyDown[1] = false;	// LEFT ARROW key
				}
				
				if(key == KeyEvent.VK_SPACE) {
					keyDown[2] = false;	// SPACE ARROW key
				}
				
				if(key == KeyEvent.VK_KP_DOWN || key == KeyEvent.VK_S) {
					keyDown[3] = false;	// DOWN ARROW key
				}
				
				if(!keyDown[3]) handler.setDelta(300);
				
			}
		}
	}
	
	private boolean collideAfterRotate(Piece piece) {
		
		Piece aux = new Piece(piece.getID(), piece.getX(), piece.getY(), piece.getShape());
		aux.rotate();
		boolean[][] shape = aux.getShape();
		boolean[][] board = handler.getBoard();
		int xx = aux.getX();
		int yy = aux.getY();
		
		boolean collide = false;
		for(int a = 0; a < 4; a++)
			for(int b = 0; b < 4; b++)
				if(shape[a][b])
					if(board[xx + a][yy + b])
						collide = true;
		
		return collide;
	}
	
}
