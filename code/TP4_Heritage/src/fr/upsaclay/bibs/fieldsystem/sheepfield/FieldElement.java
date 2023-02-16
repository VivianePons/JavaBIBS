package fr.upsaclay.bibs.fieldsystem.sheepfield;

/**
 * Describe a generic field element 
 * 
 * @author Viviane Pons
 *
 */
public interface FieldElement {

	/**
	 * Return the element type of the element
	 * @return the element type
	 */
	public FieldElementType getType();
	
	/**
	 * Activation of the element
	 * (either iniial or after a period of innactivity)
	 */
	public void activate();
	
	/**
	 * Perform a field evolution of the element
	 * This could be diminishing life span, getting inactive / active, dying...
	 */
	public void evolve();
	

	
	// State
	
	/**
	 * Returns if the current element is active in the field
	 * Inactive elements stay on the field can still evolve into active state
	 * but are not shown anc cannot interact with other elements
	 * (Note that some elements can be innactive but not dead)
	 * @return True if active
	 */
	public boolean isActive();
	
	/**
	 * Returns if the current element is considered weak
	 * @return True if weak
	 */
	public boolean isWeak();
	
	/**
	 * Return if the current element is dead
	 * if an element is dead, it is in particular inactive 
	 * Dead elements cannot become active again and should be removed
	 * from the field
	 * @return
	 */
	public boolean isDead();
	
	/**
	 * Return the lifespan of the element
	 * @return
	 */
	public int getLifespan();
	
	// Eating
	
	/**
	 * Returns if the element has the ability to eat
	 * @return true if the element has the ability to eat
	 */
	public boolean canEat();
	
	/**
	 * Return if the element eats this specific type of element
	 * @param type a field element type
	 * @return true if it can be eaten
	 */
	public boolean canEat(FieldElementType type);
	
	/**
	 * Perform the action of being eaten
	 * This might change the internal state of the element
	 */
	public void eaten();
	
	/**
	 * Perform the action of eating the given element
	 * This might change the internal state of the element
	 * and calls the eaten() method on eaten element
	 * @param element
	 */
	public void eat(FieldElement element);
	
	// Reproducing
	
	/**
	 * Depending on the element type, the element internal state
	 * and random decision, the current element might chose to reproduce
	 * if so, it should return a new field element as a result
	 * if it does not reproduce, it should return null
	 * @return null (no reproduction) or a new field element
	 */
	public FieldElement conditionnalReproduce();
	
	// Moving
	
	/**
	 * Return if the element has the capacity to move
	 * This might depend on element type and internal state
	 * @return true if it can move, false otherwise
	 */
	public boolean canMove();
	
	/**
	 * Return the next move of the element considering its curent position
	 * and the target position on the field.
	 * The next position of the element is determined by its way of walking to the target
	 * @param position the current position of the element
	 * @param target the position of the target on the field
	 * @return a FieldMove of the element from its current position to a new position toward the target
	 */
	public FieldMove nextMove(Position position, Position target);
	
	
	
	
}
