package fr.upsaclay.bibs.fieldsystem.sheepfield;

public class Sheep extends AbstractAnimal {

	
	/** Static parameters of the class Sheep
	 * These are static parameters defining the behavior of
	 * the Sheep elements
	 */
	private static int defaultLifeSpan = 5;
	private static int defaultWeakLevel = 2;
	private static int defaultIncreasePerEat = 3;
	private static int defaultSpeed = 1;
	private static double defaultReproductionProba = 0.1;
	
	public Sheep() {
		/// BEGIN SOLUTION
		super(FieldElementType.SHEEP);
		/// END SOLUTION
		/* BEGIN UNCOMMENT
		// Change the following line to what is needed
		super(null);
		END UNCOMMENT */
	}
	

	// All the static method to deal with the static parameters of thec lass
	
	public static int getDefaultLifeSpan() {
		return Sheep.defaultLifeSpan;
	}
	
	public static void setDefaultLifeSpan(int defaultLifeSpan) {
		Sheep.defaultLifeSpan = defaultLifeSpan;
	}
	
	public static int getDefaultWeakLevel() {
		return defaultWeakLevel;
	}

	public static void setDefaultWeakLevel(int defaultWeakLevel) {
		Sheep.defaultWeakLevel = defaultWeakLevel;
	}
	
	public static int getDefaultIncreasePerEat() {
		return defaultIncreasePerEat;
	}

	public static void setDefaultIncreasePerEat(int defaultIncreasePerEat) {
		Sheep.defaultIncreasePerEat = defaultIncreasePerEat;
	}
	
	public static int getDefaultSpeed() {
		return defaultSpeed;
	}

	public static void setDefaultSpeed(int defaultSpeed) {
		Sheep.defaultSpeed = defaultSpeed;
	}
	
	public static double getDefaultReproductionProba() {
		return defaultReproductionProba;
	}

	public static void setDefaultReproductionProba(double defaultReproductionProba) {
		if(defaultReproductionProba > 1 || defaultReproductionProba < 0) {
			throw new IllegalArgumentException();
		}
		Sheep.defaultReproductionProba = defaultReproductionProba;
	}

	/**
	 * In this implementation, all sheep elements
	 * return the default initial life span
	 * @return the initial life span
	 */
	@Override
	public int getInitialLifeSpan() {
		/// BEGIN SOLUTION
		return Sheep.defaultLifeSpan;
		/// END SOLUTION
		/* BEGIN UNCOMMENT
		throw new UnsupportedOperationException("Not implemented yet");
		END UNCOMMENT */
	}

	/** In this implementation, all sheeps elements
	 * return the default weak level
	 * @return the weak levell
	 */
	@Override
	public int getWeakLevel() {
		/// BEGIN SOLUTION
		return Sheep.defaultWeakLevel;
		/// END SOLUTION
		/* BEGIN UNCOMMENT
		throw new UnsupportedOperationException("Not implemented yet");
		END UNCOMMENT */
	}

	/** In this implementation, all sheeps elements
	 * return the default increase per eat
	 * @return the increase lifespan per eat
	 */
	@Override
	public int getIncreasePerEat() {
		/// BEGIN SOLUTION
		return Sheep.defaultIncreasePerEat;
		/// END SOLUTION
		/* BEGIN UNCOMMENT
		throw new UnsupportedOperationException("Not implemented yet");
		END UNCOMMENT */
	}

	/** In this implementation, all sheeps elements
	 * return the default speed
	 * @return the speed
	 */
	@Override
	public int getSpeed() {
		/// BEGIN SOLUTION
		return Sheep.defaultSpeed;
		/// END SOLUTION
		/* BEGIN UNCOMMENT
		throw new UnsupportedOperationException("Not implemented yet");
		END UNCOMMENT */
	}

	/** In this implementation, all sheeps elements
	 * return the default reproduction proba
	 * @return the reproduction proba
	 */
	@Override
	public double getReproductionProba() {
		/// BEGIN SOLUTION
		return Sheep.defaultReproductionProba;
		/// END SOLUTION
		/* BEGIN UNCOMMENT
		throw new UnsupportedOperationException("Not implemented yet");
		END UNCOMMENT */
	}

	/**
	 * Return a new instance of Sheep
	 * @return a new sheep
	 */
	@Override
	public FieldElement newInstance() {
		/// BEGIN SOLUTION
		return new Sheep();
		/// END SOLUTION
		/* BEGIN UNCOMMENT
		throw new UnsupportedOperationException("Not implemented yet");
		END UNCOMMENT */
	}	

}
