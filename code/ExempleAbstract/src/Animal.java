
public interface Animal {

	/**
	 * Moves the animal toward the given target
	 * @param target where the animal wants to go
	 * @return true if the animal has moved
	 */
	public boolean movesToward(Position target);
	
	/**
	 * Return the current position of animal
	 * @return the current position
	 */
	public Position getPosition();
	
	/**
	 * Return the type of animal
	 * @return an AnimalType
	 */
	public AnimalType getType();
	
	/**
	 * Return if object can eat a given a animal
	 * @param animal, the animal to be eaten
	 * @return true if it can be eaten, false otherwise
	 */
	public boolean canEat(Animal animal);
}
