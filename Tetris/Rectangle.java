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
	
	public boolean collide(Rectangle other) {
	/*
		A  -			C  -
		-  B 			-  D
	*/
		ArrayList<Point> points = new ArrayList<>();
		points.add(other.getA());
		points.add(new Point(other.getB().getX(),other.getA().getY()));
		points.add(new Point(other.getA().getX(),other.getB().getY()));
		points.add(other.getB());
		
		boolean check = false;
		
		for(Point p : points) {
			if(A.getX() <= p.getX() && B.getX() >= p.getX() && A.getY() <= p.getY() && B.getY() >= p.getY())
				check = true;
		}
		
		Point C = other.getA();
		Point D = other.getB();
		
		if(C.getX() <= A.getX() && D.getX() >= A.getX() && C.getY() <= A.getY() && D.getY() >= A.getY())
			check = true;
		
		return check;
	}
	
	public String printRectangle() {
		String s = "(" + A.getX() + "," + A.getY() + ")-(" + B.getX() + "," + B.getY() + ")";
		return s;
	}
}
