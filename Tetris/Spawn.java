/*
	File Name: Spawn.java
	Author: Teculescu Octavian
	Date: 31 iulie 2017, 18:03:49
*/
import javax.swing.JOptionPane;

public class Spawn {
	
	private Handler handler;
	
	private int WIDTH, HEIGHT;
	
	public Spawn(Handler Handler, int WIDTH, int HEIGHT) {
		this.handler = Handler;
		this.WIDTH = WIDTH;
		this.HEIGHT = HEIGHT;
	}
	
	public void tick() {
	
		boolean piece_falling = false;
		for(int i = 0; i < handler.object.size(); i++) {
			Piece tempObject = handler.object.get(i);
			if(tempObject.getID() == ID.Falling)
				piece_falling = true;
		}
		
		boolean piece_ready = false;
		for(int i = 0; i < handler.object.size(); i++) {
			Piece tempObject = handler.object.get(i);
			if(tempObject.getID() == ID.Ready)
				piece_ready = true;
		}
		
		if(!piece_falling) {
			
			if(!piece_ready) {
				Piece curr_piece = new Piece(ID.Ready,3,0);
				handler.addObject(curr_piece);
			} else {
				for(int i = 0; i < handler.object.size(); i++) {
					Piece tempObject = handler.object.get(i);
					if(tempObject.getID() == ID.Ready) {
						tempObject.setID(ID.Falling);	
						boolean[][] pBoard = tempObject.getShape();
						boolean[][] hBoard = handler.getBoard();
						int xx = tempObject.getX();
						int yy = tempObject.getY();
						for(int a = 0; a < 4; a++)
							for(int b = 0; b < 4; b++)
								if(pBoard[a][b])
									if(hBoard[xx + a][yy + b]) {
										infoBox("Your final score is: " + handler.getScore(),"YOU LOST!");
										System.exit(1); // lose
									}
					}				
				}
				Piece curr_piece = new Piece(ID.Ready,3,0);
				handler.addObject(curr_piece);
			}
		}
	}
	
	public static void infoBox(String infoMessage, String titleBar) {
        JOptionPane.showMessageDialog(null, infoMessage, titleBar, JOptionPane.INFORMATION_MESSAGE);
    }
}
