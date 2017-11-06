/*
	File Name: Piece.java
	Author: Teculescu Octavian
	Date: 21 august 2017, 18:22:23
*/
import java.awt.Graphics;
import java.util.Random;
import java.awt.Color;

public class Piece extends GameObject {
	
	private int x, y;
	
	private Color col;
	
	private boolean[][] shape;
	
	public Piece(ID id, int x, int y) {
		super(id);
		this.x = x;
		this.y = y;
		shape = generateForm();
	}
	
	public Piece(ID id, int x, int y, boolean[][] shape) {
		super(id);
		this.x = x;
		this.y = y;
		this.shape = shape;
	}
	
	public void moveTo(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	private boolean[][] generateForm() {
		Random rand = new Random();
		int k = rand.nextInt(35) + 1;
		boolean normal;
		boolean[][] aux = new boolean[4][4];
		
		
		aux = fillArray(4,4);
		if(k <= 5) {
			/*
				x x
				x x					
			*/
			aux[1][1] = true;
			aux[1][2] = true;
			aux[2][1] = true;
			aux[2][2] = true;
			col = Color.red;
		} else if(k <= 10) {
			/*
			  x x x x
			*/
			aux[2][0] = true;
			aux[2][1] = true;
			aux[2][2] = true;
			aux[2][3] = true;
			col = Color.cyan;
		} else if(k <= 15) {
			/*
				  x
				x x
				x					
			*/
			aux[1][2] = true;
			aux[2][1] = true;
			aux[2][2] = true;
			aux[3][1] = true;
			col = Color.yellow;
		} else if(k <= 20) {
			/*
				x
				x x
				  x					
			*/
			aux[1][1] = true;
			aux[2][1] = true;
			aux[2][2] = true;
			aux[3][2] = true;
			col = Color.orange;
		} else if(k <= 25) {
			/*
				x 
			  x x x
			*/
			aux[1][1] = true;
			aux[2][0] = true;
			aux[2][1] = true;
			aux[2][2] = true;
			col = Color.green;
		} else if(k <= 30) {
			/*
				x 
			    x
			    x x
			*/
			aux[0][1] = true;
			aux[1][1] = true;
			aux[2][1] = true;
			aux[2][2] = true;
			col = Color.magenta;
		} else {
			/*
				x 
			    x
			  x x
			*/
			aux[0][2] = true;
			aux[1][2] = true;
			aux[2][2] = true;
			aux[2][1] = true;
			col = Color.blue;
		}
		
		return aux;
	}
	
	private boolean[][] fillArray(int n, int m) {
		boolean[][] aux = new boolean[n][m];
		for(int i = 0; i < n; i++) 
			for(int j = 0; j < m; j++)
				aux[i][j] = false;
		return aux;
	}
	
	public void tick(Handler handler){
	
	}

	public void render(Graphics g){
	
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public int getX() {
		return x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public int getY() {
		return y;
	}
	
	public Color getColor() {
		return col;
	}
	
	public void moveRight() {
		boolean doable = true;
		for(int i = 0; i < 4; i++) 
			for(int j = 0; j < 4; j++)
				if(shape[i][j])
					if(getX() + i >= 9)
						doable = false;
		if(doable)
			setX(getX() + 1);
	}
	
	public void moveLeft() {
		boolean doable = true;
		for(int i = 0; i < 4; i++) 
			for(int j = 0; j < 4; j++)
				if(shape[i][j])
					if(getX() + i <= 0)
						doable = false;
		if(doable)
			setX(getX() - 1);
	}
	
	public void moveDown() {
		setY(getY() + 1);
	}
	
	public boolean[][] getShape() {
		return shape;
	}
	
	public void rotate() {
		boolean[][] aux = fillArray(4,4);;
		for(int i = 0; i < 4; i++)
			for(int j = 0; j < 4; j++) 
				aux[3 - j][i] = shape[i][j];
		
		this.shape = aux;
		
		for(int i = 0; i < 4; i++) 
			for(int j = 0; j < 4; j++)
				if(shape[i][j]) {
					if(getX() + i < 0) {
						moveRight();
						if(getX() + i < 0)
							moveRight();
						break;
					}
					if(getX() + i > 9) {
						moveLeft();
						if(getX() + i > 9)
							moveLeft();
						break;
					}
				}
	}
	
	
}
