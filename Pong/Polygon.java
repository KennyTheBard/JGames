import java.awt.Color;
import java.util.Arrays;

public class Polygon {
	
	private int npoints;
	
	private int[] xpoints;
	private int[] ypoints;
	
	private Color col;
	
	public Polygon() {
		this.npoints = 0;
		this.xpoints = new int[0];
		this.ypoints = new int[0];
		this.col = Color.white;
	}
	
	public Polygon(int[] x, int[] y, int n) {
		this();
		for(int i = 0; i < n; i++) {
			addPoint(x[i],y[i]);
		}
	}
	
	public Polygon(int[] x, int[] y, int n, Color col) {
		this(x, y, n);
		this.col = col;
	}
	
	public void addPoint(int x, int y) {
		this.npoints ++;
		this.xpoints = Arrays.copyOf(this.xpoints, this.xpoints.length + 1);
		this.xpoints[npoints - 1] = x;
		this.ypoints = Arrays.copyOf(this.ypoints, this.ypoints.length + 1);
		this.ypoints[npoints - 1] = y;
	}
	
	public void reset() {
		this.npoints = 0;
		this.xpoints = new int[0];
		this.ypoints = new int[0];
		this.col = Color.white;
	}
	
	public int getN() {
		 return npoints;
	}
	
	public int[] getX() {
		 return xpoints;
	}
	
	public int[] getY() {
		 return ypoints;
	}
	
	public void setColor(Color col) {
		this.col = col;
	}
	
	public void setColor(int r, int g, int b) {
		this.col = new Color(r,g,b,255);
	}
	
	public void setColor(int r, int g, int b, int a) {
		this.col = new Color(r,g,b,a);
	}
	
	public Color getColor() {
		return col;
	}
}
