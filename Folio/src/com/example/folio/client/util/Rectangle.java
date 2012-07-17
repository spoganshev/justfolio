package com.example.folio.client.util;

public class Rectangle {
	
	public final int x;
	public final int y;
	public final int width;
	public final int height;
	
	public Rectangle(int x, int y, int width, int height) {
		super();
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	@Override
	public String toString() {
		return "Rectangle [x=" + x + ", y=" + y + ", width=" + width + ", height=" + height + "]";
	}
	
	public Dimension getDimension() {
		return new Dimension(width, height);
	}
	
	public boolean canContain(Dimension d) {
		return (d.width <= width) && (d.height <= height);
	}
	
}
