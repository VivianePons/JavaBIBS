package fr.upsaclay.bibs.fieldsystem.sheepfield;

public abstract class AbstractAnimal extends AbstractFieldElement {
	

	private boolean dead = false;
	
	public AbstractAnimal(FieldElementType type) {
		super(type);
		activate();
	}
	
	/**
	 * Return the life span level where the animal is considered weak
	 * @return the level as an int
	 */
	public abstract int getWeakLevel();
	
	/**
	 * Return the life span increase when the animal eats
	 * @return the increase as an int
	 */
	public abstract int getIncreasePerEat();
	
	/**
	 * Return the number of steps the animal can make in one round
	 * @return the speed as an int
	 */
	public abstract int getSpeed();
	
	/**
	 * Return the probability to reproduce at each round
	 * @return a double between 0 and 1
	 */
	public abstract double getReproductionProba();
	
	/**
	 * Create a new instace
	 * @return a new instance of animal
	 */
	public abstract FieldElement newInstance();
	
	@Override
	public boolean isActive() {
		return !dead;
	}
	
	@Override
	public boolean isDead() {
		return dead;
	}
	
	@Override
	public boolean isWeak() {
		return getLifespan() <= getWeakLevel();
	}
	
	@Override
	public boolean canEat() {
		return isActive();
	}
	
	/**
	 * Return if the animal can eat another element
	 * this is directly computed using the type level 
	 * in the food chain
	 * @param the type to be eaten
	 * @return true if the eaten type is extactly one level below the level of the current type
	 *         (as an example the wolf can eat the sheep. The sheep can eat the grass. But the
	 *         wolf cannot eat the grass)
	 */
	@Override
	public boolean canEat(FieldElementType type) {
		return isActive() && type.getLevel() == getType().getLevel() - 1;
	}
	
	@Override
	public void eaten() {
		super.eaten();
		this.dead = true;
	}
	
	@Override
	public void eat(FieldElement element) {
		super.eat(element);
		increaseLifeSpan(getIncreasePerEat());
	}
	
	@Override
	public boolean canMove() {
		return isActive();
	}
	
	@Override
	public FieldMove nextMove(Position position, Position target) {
		if(position.equals(target)) {
			return new FieldMove(this, position, position);
		}
		int x = position.x();
		int y = position.y();
		int sngx = (target.x() > x) ? 1 : -1;
		int sngy = (target.y() > y) ? 1 : -1;
		for(int i =0; i < getSpeed(); i++) {
			int diffx = target.x() - x;
			int diffy = target.y() - y;
			if(diffx == 0 && diffy == 0) {
				break;
			}
			if(Math.abs(diffx) >= Math.abs(diffy) ) {
				x+= sngx;
			} else {
				y+= sngy;
			}
		}
		return new FieldMove(this, position, new Position(x,y));
	}
	
	@Override
	protected void actionZeroLifeSpan() {
		dead = true;
	}
	
	@Override
	public FieldElement conditionnalReproduce() {
		if(!isWeak() && Math.random() < getReproductionProba()) {
			return newInstance();
		}
		return null;
		
	}

}
