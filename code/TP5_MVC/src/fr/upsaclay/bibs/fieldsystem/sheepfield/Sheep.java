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
		super(FieldElementType.SHEEP);
	}
	

	// All the static method to deal with the static parameters of the lass
	
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
		return Sheep.defaultLifeSpan;
	}

	/** In this implementation, all sheeps elements
	 * return the default weak level
	 * @return the weak levell
	 */
	@Override
	public int getWeakLevel() {
		return Sheep.defaultWeakLevel;
	}

	/** In this implementation, all sheeps elements
	 * return the default increase per eat
	 * @return the increase lifespan per eat
	 */
	@Override
	public int getIncreasePerEat() {
		return Sheep.defaultIncreasePerEat;
	}

	/** In this implementation, all sheeps elements
	 * return the default speed
	 * @return the speed
	 */
	@Override
	public int getSpeed() {
		return Sheep.defaultSpeed;
	}

	/** In this implementation, all sheeps elements
	 * return the default reproduction proba
	 * @return the reproduction proba
	 */
	@Override
	public double getReproductionProba() {
		return Sheep.defaultReproductionProba;
	}

	/**
	 * Return a new instance of Sheep
	 * @return a new sheep
	 */
	@Override
	public FieldElement newInstance() {
		return new Sheep();
	}	

}
