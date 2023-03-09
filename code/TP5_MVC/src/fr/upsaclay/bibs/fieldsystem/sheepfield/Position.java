package fr.upsaclay.bibs.fieldsystem.sheepfield;

import java.util.Arrays;


public class Position {
	
	private final int xf;
	private final int yf;
	
	public Position(int x, int y) {
		this.xf = x;
		this.yf = y;
	}
	
	public int x() {
		return xf;
	}
	
	public int y() {
		return yf;
	}
	
	public String toString() {
		return "(" + x() + ", " + y() + ")";
	}
	
	public boolean equals(Object o) {
		if(o == null) {
			return false;
		}
		if(!Position.class.isAssignableFrom(o.getClass())) {
			return false;
		}
		Position p = (Position)o;
		return (p.x() == x() && p.y() == y());
	}
	
	public int hashCode() {
		return toString().hashCode();
	}
	
	
	public Iterable<Position> neighbors() {
		return Arrays.asList(new Position(xf-1,yf), new Position(xf,yf-1), new Position(xf+1, yf), new Position(xf, yf+1));
	}
	
};
