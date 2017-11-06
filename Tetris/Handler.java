/*
	File Name: Handler.java
	Author: Teculescu Octavian
	Date: 24 iulie 2017, 23:32:02
*/
import java.util.LinkedList;
import java.awt.Graphics;
import java.awt.Color;

public class Handler {
	
	LinkedList<Piece> object = new LinkedList<>();
	
	public static int score = 0;
	
	boolean[][] board = new boolean[10][20];
	
	Color[][] col_board = new Color[10][20];
	
	int x = 55, y = 20;
	
	double lastTime = System.currentTimeMillis();
	
	private int delta;
	
	public Handler() {
		board = fillArray(10, 20);
		setDelta(300);
		for(int i = 0; i < 10; i++)
			for(int j = 0; j < 20; j++)
				col_board[i][j] = Color.black;
	}
	
	public synchronized void tick() {
		
		double thisTime = System.currentTimeMillis();
		// timpul in milisecunde dintre caderi
		if(thisTime - lastTime > delta) {
			lastTime = thisTime;
			
			for(int i = 0; i < object.size(); i++) {
				Piece tempObject = object.get(i);
				if(tempObject.getID() == ID.Falling) { 
					// caderea piesei 
					boolean[][] aux = tempObject.getShape();
					boolean stop = false;
					int xx = tempObject.getX();
					int yy = tempObject.getY();
					for(int a = 0; a < 4; a++)
						for(int b = 0; b < 4; b++)
							if(aux[a][b]) {
								if(yy + b >= 19)
									stop = true;
								if(yy + b + 1 >= 20)
									stop = true;
								else if(board[xx + a][yy + b + 1])
									stop = true;
							}
					// "inghetarea" piesei cand ajunge jos
					if(stop) {
						tempObject.setID(ID.Sitting);
						for(int a = 0; a < 4; a++) {
							for(int b = 0; b < 4; b++)
								if(aux[a][b]) {
									board[xx + a][yy + b] = true;
									col_board[xx + a][yy + b] = tempObject.getColor();
								}
						}
						removeObject(tempObject);
					} else tempObject.moveDown();
				}
			}
		}
		
		// Eliminarea de linii complete
		int bonus = 0;
		for(int i = 0; i < 20; i++) {
			boolean test = true;
			for(int j = 0; j < 10; j++)
				if(!board[j][i])
					test = false;
			
			if(test) {
				for(int a = i; a >= 0; a--)
					for(int b = 0; b <= 9; b++)
						if(a == 0) {
							board[b][a] = false;
							col_board[b][a] = Color.black;
						} else {
							board[b][a] = board[b][a - 1];
							col_board[b][a] = col_board[b][a - 1];
						}
				score += 10 + bonus;
				bonus += 10;
			}
		}
	}
	
	public void render(Graphics g) {
		for(int i = 0; i < object.size(); i++) {
			Piece tempObject = object.get(i);
			
			// deseneaza piesa cazatoare
			if(tempObject.getID() == ID.Falling) {
				boolean[][] aux = tempObject.getShape();
				int xx = tempObject.getX();
				int yy = tempObject.getY();
				for(int a = 0; a < 4; a++)
					for(int b = 0; b < 4; b++)
						if(aux[a][b]) {
							g.setColor(tempObject.getColor());
							g.fillRect((xx + a)*25 + x,(yy + b)*25 + y,25,25);
						}
			}

			
			// deseneaza piesele cazute
			for(int p = 0; p < 10; p++)
				for(int t = 0; t < 20; t++)
					if(col_board[p][t] != Color.black) {
						g.setColor(col_board[p][t]);
						g.fillRect(p*25 + x, t*25 + y, 25, 25);
					}
					
			// deseneaza piesa in asteptare 
			if(tempObject.getID() == ID.Ready) {
				boolean[][] shape = tempObject.getShape();
				for(int a = 0; a < 4; a++)
					for(int b = 0; b < 4; b++)
						if(shape[a][b]) {
							g.setColor(tempObject.getColor());
							g.fillRect((14 + a)*25 + x,(2 + b)*25 + y,25,25);
						}
			}

		}
	}
	
	public void addObject(Piece object) {
		this.object.add(object);
	}
	
	public void removeObject(Piece object) {
		this.object.remove(object);
	}
	
	private boolean[][] fillArray(int n, int m) {
		boolean[][] aux = new boolean[n][m];
		for(int i = 0; i < n; i++) 
			for(int j = 0; j < m; j++)
				aux[i][j] = false;
		return aux;
	}
	
	public boolean[][] getBoard() {
		return board;
	}
	
	public int getDelta() {
		return delta;
	}
	
	public void setDelta(int delta) {
		this.delta = delta;
	}
	
	public int getScore() {
		return score;
	}
}
