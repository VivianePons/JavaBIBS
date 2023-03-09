package fr.upsaclay.bibs.fieldsystem.sheepfield;


public class Wolf extends AbstractAnimal {
	
	/** Static parameters of the class Wolf
	 * These are static parameters defining the behavior of
	 * the Wolf elements
	 */
	private static int defaultLifeSpan = 10;
	private static int defaultWeakLevel = 2;
	private static int defaultIncreasePerEat = 4;
	private static int defaultSpeed = 1;
	private static double defaultReproductionProba = 0.1;
	
	public Wolf() {
		super(FieldElementType.WOLF);
	}
	

	public static int getDefaultLifeSpan() {
		return Wolf.defaultLifeSpan;
	}
	
	public static void setDefaultLifeSpan(int defaultLifeSpan) {
		Wolf.defaultLifeSpan = defaultLifeSpan;
	}
	
	public static int getDefaultWeakLevel() {
		return defaultWeakLevel;
	}

	public static void setDefaultWeakLevel(int defaultWeakLevel) {
		Wolf.defaultWeakLevel = defaultWeakLevel;
	}
	
	public static int getDefaultIncreasePerEat() {
		return Wolf.defaultIncreasePerEat;
	}

	public static void setDefaultIncreasePerEat(int defaultIncreasePerEat) {
		Wolf.defaultIncreasePerEat = defaultIncreasePerEat;
	}
	
	public static int getDefaultSpeed() {
		return Wolf.defaultSpeed;
	}

	public static void setDefaultSpeed(int defaultSpeed) {
		Wolf.defaultSpeed = defaultSpeed;
	}
	
	public static double getDefaultReproductionProba() {
		return Wolf.defaultReproductionProba;
	}

	public static void setDefaultReproductionProba(double defaultReproductionProba) {
		if(defaultReproductionProba > 1 || defaultReproductionProba < 0) {
			throw new IllegalArgumentException();
		}
		Wolf.defaultReproductionProba = defaultReproductionProba;
	}
	
	
	/**
	 * In this implementation, all sheep elements
	 * return the default initial life span
	 */
	@Override
	public int getInitialLifeSpan() {
		return Wolf.defaultLifeSpan;
	}

	@Override
	public int getWeakLevel() {
		return Wolf.defaultWeakLevel;
	}

	@Override
	public int getIncreasePerEat() {
		return Wolf.defaultIncreasePerEat;
	}

	@Override
	public int getSpeed() {
		return Wolf.defaultSpeed;
	}

	@Override
	public double getReproductionProba() {
		return Wolf.defaultReproductionProba;
	}

	@Override
	public FieldElement newInstance() {
		return new Wolf();
	}

}
