package fr.upsaclay.bibs.fieldsystem.sheepfield;

/**
 * An abstract class for all field elements
 * 
 * It implements default behavior for all the interface methods and defines
 * some abstract method for general algorithm
 * 
 *  All field elements are considered with a certain life span that reduces
 *  at each evolution if active. This is dealt with by this class using 
 *  abstract method actionZeroLifeSpan.
 *  
 *  An negative originalLifeSPan is considered infinite
 *  
 *  Subclasses should override method as needed. The super method should be
 *  called for all action action method.
 *  
 */
public abstract class AbstractFieldElement implements FieldElement {
	
	private int lifespan = -1;
	
	private final FieldElementType type;
	
	public AbstractFieldElement(FieldElementType type) {
		this.type = type;
	}
	
	/**
	 * Perform the action on the element when the 0 life span is reached
	 */
	protected abstract void actionZeroLifeSpan();
	
	/**
	 * Return the initial life span of the element
	 * @return
	 */
	public abstract int getInitialLifeSpan();
	
	protected void setLifeSpan(int lifespan) {
		this.lifespan = lifespan;
	}
	
	/**
	 * Increase the lifespan by v
	 * @param v an int
	 */
	protected void increaseLifeSpan(int v) {
		lifespan+=v;
	}
	
	@Override
	public FieldElementType getType() {
		return type;
	}
	
	@Override
	public int getLifespan() {
		return lifespan;
	}
	
	@Override
	public void activate() {
		lifespan = getInitialLifeSpan();
	}
	
	@Override
	public void evolve() {
		if(isActive()) {
			if(lifespan > 0) {
				lifespan-=1;
			}
			if (lifespan == 0) {
				lifespan = -1;
				actionZeroLifeSpan();
			}
		}
	}
	
	// State

	@Override
	public boolean isActive() {
		return false;
	}
	
	@Override
	public boolean isDead() {
		return false;
	}
	
	@Override
	public boolean isWeak() {
		return false;
	}
	
	// Eating
	
	@Override
	public boolean canEat() {
		return false;
	}
	
	@Override
	public boolean canEat(FieldElementType type) {
		return false;
	}
	
	@Override
	public void eaten() {
		// does nothing by default
	}
	
	@Override
	public void eat(FieldElement element) {
		if(!isActive()) {
			throw new IllegalStateException();
		}
		if(!element.isActive() || ! canEat(element.getType())) {
			throw new IllegalArgumentException();
		}
		element.eaten();
	}
	
	// Reproducing
	
	@Override
	public FieldElement conditionnalReproduce() {
		return null;
	}
	
	// Moving
	
	@Override
	public boolean canMove() {
		return false;
	}
	
	@Override
	public FieldMove nextMove(Position position, Position target) {
		throw new UnsupportedOperationException();
	}


}
