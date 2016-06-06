package Math;

import org.opencv.core.Point;

public class Vector {

	private Point a, b;
	
	public Vector(Point a, Point b) {
		this.a = a;
		this.b = b;
	}
	
	public double length() {
		return Math.sqrt(Math.pow(b.x - a.x, 2) + Math.pow(b.y - a.y, 2));
	}
	
	public Point getA() {
		return this.a;
	}
	
	public Point getB() {
		return this.b;
	}
	
}
