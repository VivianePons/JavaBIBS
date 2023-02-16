package fr.upsaclay.bibs.fieldsystem.sheepfield;

public class Grass extends AbstractFieldElement {

	/** Static parameters of the glass Grass
	 * These are static parameters defining the behavior of
	 * the Grass elements
	 */
	private static double defaultProba = .05;
	private static int defaultLifeSpan = 3;
	
	private double grassProba;
	
	private boolean active = false;
	
	/**
	 * Creates a grass element with given active state and grass probablity
	 * @param active
	 * @param grassProba
	 */
	public Grass(boolean active, double grassProba) {
		super(FieldElementType.GRASS);
		setGrassProba(grassProba);
		if(active) {
			activate();
		}
	}

	/**
	 * Creates a grass element with given active state
	 * The grass proba is using default parameter
	 * @param active
	 */
	public Grass(boolean active) {
		/// BEGIN SOLUTION
		this(active, defaultProba);
		/// END SOLUTION
		/* BEGIN UNCOMMENT
		// Change the following line to what is needed
		this(false, 0);
		END UNCOMMENT */
	}
	
	/**
	 * Creates a grass element with given grassProba
	 * the active state is set randomly using grassProba
	 * @param grassProba
	 */
	public Grass(double grassProba) {
		/// BEGIN SOLUTION
		this(false, grassProba);
		randomActivate();
		/// END SOLUTION
		/* BEGIN UNCOMMENT
		// Change the following line to what is needed
		this(false, 0);
		END UNCOMMENT */
	}
	
	/**
	 * Creates a Grass element with random active state
	 * using the default grass proba
	 */
	public Grass() {
		/// BEGIN SOLUTION
		this(defaultProba);
		/// END SOLUTION
		/* BEGIN UNCOMMENT
		// Change the following line to what is needed
		this(false, 0);
		END UNCOMMENT */
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
	
	public void setGrassProba(double grassProba) {
		if(grassProba < 0 || grassProba > 1) {
			throw new IllegalArgumentException();
		}
		this.grassProba = grassProba;
	}
	
	public double getGrassProba() {
		return grassProba;
	}
	
	@Override
	public void activate() {
		/// BEGIN SOLUTION
		super.activate();
		this.active = true;
		/// END SOLUTION
		/* BEGIN UNCOMMENT
		throw new UnsupportedOperationException("Not implemented yet");
		END UNCOMMENT */
	}
	
	/**
	 * Activates randomly using grassProba
	 */
	public void randomActivate() {
		if(Math.random() < grassProba) {
			activate();
		}
	}
	
	@Override
	public void evolve() {
		/// BEGIN SOLUTION
		super.evolve();
		if(!active) {
			randomActivate();
		}
		/// END SOLUTION
		/* BEGIN UNCOMMENT
		throw new UnsupportedOperationException("Not implemented yet");
		END UNCOMMENT */
	}

	@Override
	public boolean isActive() {
		return active;
	}
	
	@Override
	public void actionZeroLifeSpan() {
		/// BEGIN SOLUTION
		active = false;
		/// END SOLUTION
		/* BEGIN UNCOMMENT
		throw new UnsupportedOperationException("Not implemented yet");
		END UNCOMMENT */
	}
	
	@Override
	public void eaten() {
		/// BEGIN SOLUTION
		super.eaten();
		this.active = false;
		/// END SOLUTION
		/* BEGIN UNCOMMENT
		throw new UnsupportedOperationException("Not implemented yet");
		END UNCOMMENT */
	}

	/**
	 * In this implementation, all grass elements
	 * return the default initial life span
	 */
	@Override
	public int getInitialLifeSpan() {
		/// BEGIN SOLUTION
		return Grass.defaultLifeSpan;
		/// END SOLUTION
		/* BEGIN UNCOMMENT
		throw new UnsupportedOperationException("Not implemented yet");
		END UNCOMMENT */
	}


}
