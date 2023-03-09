package fr.upsaclay.bibs.fieldsystem.sheepfield;

public class Grass extends AbstractFieldElement {

	/** Static parameters of the glass Grass
	 * These are static parameters defining the behavior of
	 * the Grass elements
	 */
	private static double defaultProba = .05;
	private static int defaultLifeSpan = 3;

	
	private boolean active = false;
	
	/**
	 * Creates a grass element with given active state 
	 * @param active
	 */
	public Grass(boolean active) {
		super(FieldElementType.GRASS);
		if(active) {
			activate();
		}
	}

	
	/**
	 * Creates a grass element
	 * the active state is set randomly using grassProba
	 * @param grassProba
	 */
	public Grass() {
		this(false);
		randomActivate();
	}
	
	

	public static void setDefaultProba(double defaultProba) {
		if(defaultProba < 0 || defaultProba > 1) {
			throw new IllegalArgumentException();
		}
		Grass.defaultProba = defaultProba;
	}
	
	public static double getDefaultProba() {
		return defaultProba;
	}

	public static void setDefaultLifeSpan(int defaultLifeSpan) {
		Grass.defaultLifeSpan = defaultLifeSpan;
	}
	
	public static int getDefaultLifeSpan() {
		return defaultLifeSpan;
	}
	
	public double getGrassProba() {
		return defaultProba;
	}
	
	@Override
	public void activate() {
		super.activate();
		this.active = true;
	}
	
	/**
	 * Activates randomly using grassProba
	 */
	public void randomActivate() {
		if(Math.random() < getGrassProba()) {
			activate();
		}
	}
	
	@Override
	public void evolve() {
		super.evolve();
		if(!active) {
			randomActivate();
		}
	}

	@Override
	public boolean isActive() {
		return active;
	}
	
	@Override
	public void actionZeroLifeSpan() {
		active = false;
	}
	
	@Override
	public void eaten() {
		super.eaten();
		this.active = false;
	}

	/**
	 * In this implementation, all grass elements
	 * return the default initial life span
	 */
	@Override
	public int getInitialLifeSpan() {
		return Grass.defaultLifeSpan;
	}


}
