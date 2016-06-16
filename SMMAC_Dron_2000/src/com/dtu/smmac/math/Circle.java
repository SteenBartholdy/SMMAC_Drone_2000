package com.dtu.smmac.math;

import org.opencv.core.Point;

public class Circle {

	private Point centrum;
	private int radius;
	
	public Circle(Point centrum, int radius) {
		this.centrum = centrum;
		this.radius = radius;
	}

	public Point getCentrum() {
		return centrum;
	}

	public void setCentrum(Point centrum) {
		this.centrum = centrum;
	}

	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}
	
}
