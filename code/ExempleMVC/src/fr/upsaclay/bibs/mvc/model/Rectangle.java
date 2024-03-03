package fr.upsaclay.bibs.mvc.model;

import java.awt.Color;

public class Rectangle {


	private static int maxx;
	private static int maxy;

	
	private int x;
	private int y;
	private int width; 
	private int height;
	
	private Color fillColor;
	
	public Rectangle(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public static int getMaxx() {
		return maxx;
	}

	public static void setMaxx(int maxx) {
		Rectangle.maxx = maxx;
	}

	public static int getMaxy() {
		return maxy;
	}

	public static void setMaxy(int maxy) {
		Rectangle.maxy = maxy;
	}
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	
	

	/**
	 * Check that the rectangle fit inside its bounds
	 * @return true if it fits
	 */
	public boolean insideBounds() {
		return x >= 0 && (x + width) <= maxx && y >= 0 && (y + height) <= maxy && width > 0 && height > 0;
	}
	
	/**
	 * Try to move the rectangle by (diffx, diffy)
	 * 
	 * If it goes outside bounds, it is not moved
	 * 
	 * @param diffx the value to add to x
	 * @param diffy the value to add to y
	 * @return true if the rectangle has been moved (while staying insinde bounds)
	 */
	public boolean trymove(int diffx, int diffy) {
		int x = this.x;
		int y = this.y;
		this.x += diffx;
		this.y += diffy;
		if(!insideBounds()) {
			this.x = x;
			this.y = y;
			return false;
		}
		return true;
	}
	
	public boolean tryExtendWidth(int diffw) {
		int width = this.width;
		this.width +=diffw;
		if(!insideBounds()) {
			this.width = width;
			return false;
		}
		return true;
	}
	
	public boolean tryExtendHeight(int diffh) {
		int height = this.height;
		this.height += diffh;
		if(!insideBounds()) {
			this.height = height;
			return false;
		}
		return true;
	}
	
	public boolean insideRectanle(int x, int y) {
		return x >= this.x && x <= (this.x + width) && y >= this.y && y <= this.y + height;
	}

	public Color getFillColor() {
		return fillColor;
	}

	public void setFillColor(Color fillColor) {
		this.fillColor = fillColor;
	}
	
	
}
