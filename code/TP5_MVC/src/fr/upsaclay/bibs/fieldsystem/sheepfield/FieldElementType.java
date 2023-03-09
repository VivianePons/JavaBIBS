package fr.upsaclay.bibs.fieldsystem.sheepfield;

public enum FieldElementType {
	GRASS(0),
	SHEEP(1),
	WOLF(2);
	
	private final int level;
	
	private FieldElementType(int level) {
		this.level = level;
	}
	
	/**
	 * Return the level of the element in the food chain
	 * @return
	 */
	public int getLevel() {
		return level;
	}
}
