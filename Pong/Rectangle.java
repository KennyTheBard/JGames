/*
	File Name: Rectangle.java
	Author: Teculescu Octavian
	Date: 18 iulie 2017, 18:34:12
*/
import java.util.ArrayList;

public class Rectangle {
	
	private Point A, B;
	private int width, height;
	
	public Rectangle(Point newA, Point newB) {
		A = newA;
		B = newB;
	}
	
	public Rectangle(int xA, int yA, int xB, int yB) {
		A = new Point(xA < xB ? xA : xB, yA < yB ? yA : yB);
		B = new Point(xA > xB ? xA : xB, yA > yB ? yA : yB);
		
		width = updateWidth();
		height = updateHeight();
	}
	
	public Point getA() {
		return A;
	}
	
	public Point getB() {
		return B;
	}
	
	public void move(int distX, int distY) {
		A.setX(A.getX() + distX);
		A.setY(A.getY() + distY);
		B.setX(B.getX() + distX);
		B.setY(B.getY() + distY);
	}
	
	public int updateWidth() {
		return B.getX() - A.getX();
	}
	
	public int updateHeight() {
		return B.getY() - A.getY();
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public Point getCenter() {
		int x = (A.getX() + B.getX())/2;
		int y = (A.getY() + B.getY())/2;
		Point center = new Point(x,y);
		return center;
	}
	
	public boolean collide(Rectangle other) {
	/*
		A  -			C  -
		-  B 			-  D
	*/
		
		Point A = getA();
		Point B = getB();
		Point C = other.getA();
		Point D = other.getB();
		
		boolean check = false;
		
		if( (A.getX() <= D.getX() && A.getX() >= C.getX()) || (B.getX() <= D.getX() && B.getX() >= C.getX()) )
			if( (A.getY() <= D.getY() && A.getY() >= C.getY()) || (B.getY() <= D.getY() && B.getY() >= C.getY()) )
				check = true;
		
		if( (C.getX() <= A.getX() && C.getX() >= B.getX()) || (D.getX() <= A.getX() && D.getX() >= B.getX()) )
			if( (C.getY() <= A.getY() && C.getY() >= B.getY()) || (D.getY() <= A.getY() && D.getY() >= B.getY()) )
				check = true;
		
		return check;
	}
	
	public String printRectangle() {
		String s = "(" + A.getX() + "," + A.getY() + ")-(" + B.getX() + "," + B.getY() + ")";
		return s;
	}
}
