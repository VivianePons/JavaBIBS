package fr.upsaclay.bibs.fieldsystem.sheepfield;

import java.util.Arrays;


public record Position(int x, int y) {
	
	public Iterable<Position> neighbors() {
		return Arrays.asList(new Position(x-1,y), new Position(x,y-1), new Position(x+1, y), new Position(x, y+1));
	}
	
};
