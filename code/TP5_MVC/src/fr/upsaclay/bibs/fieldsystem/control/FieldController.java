package fr.upsaclay.bibs.fieldsystem.control;


import fr.upsaclay.bibs.fieldsystem.sheepfield.Field;
import fr.upsaclay.bibs.fieldsystem.sheepfield.Grass;
import fr.upsaclay.bibs.fieldsystem.sheepfield.Sheep;
import fr.upsaclay.bibs.fieldsystem.sheepfield.Wolf;
import fr.upsaclay.bibs.fieldsystem.view.FieldView;
import fr.upsaclay.bibs.fieldsystem.view.SwingFieldView;
import fr.upsaclay.bibs.fieldsystem.view.ViewState;

/**
 * The controller of the application
 * 
 * @author viviane
 *
 */
public class FieldController  {
	
	public static final int WIDTH = 60;
	public static final int HEIGHT = 40;
	public static final int INITIAL_DELAY = 500;

	private Field field;
	private FieldView view;
	
	private int numberOfInitialSheeps = 50;
	private int numberOfInitialWolves = 50;

	/// BEGIN SOLUTION
	private int current_delay;
	private boolean simulationStarted;
	/// END SOLUTION
	
	public FieldController() {
		view = new SwingFieldView("Sheep field", WIDTH, HEIGHT);
	}
	
	public void initialize() {
		view.setController(this);
		view.initialize();
		view.setViewState(ViewState.SIMULATION_INIT);
		/// BEGIN SOLUTION
		current_delay = INITIAL_DELAY;
		view.setLoopDelay(current_delay);
		simulationStarted = false;
		view.update();
		/// END SOLUTION
	}

	public int getNumberOfIntialSheeps() {
		return numberOfInitialSheeps;
	}

	public void setNumberOfInitialSheeps(int numberOfInitialSheeps) {
		this.numberOfInitialSheeps = numberOfInitialSheeps;
	}

	public int getNumberOfInitialWolves() {
		return numberOfInitialWolves;
	}

	public void setNumberOfInitialWolves(int numberOfInitialWolves) {
		this.numberOfInitialWolves = numberOfInitialWolves;
	}

	/**
	 * Launches the simulation :
	 *  * create the field
	 *  * adds the sheep and wolves
	 *  * initiate the view
	 */
	private void initial_start() {
		/* Write your code here */
		/// BEGIN SOLUTION
		field = new Field(WIDTH, HEIGHT);
		view.setField(field);
		field.addRandomFieldElements(numberOfInitialSheeps, () -> new Sheep());
		field.addRandomFieldElements(numberOfInitialWolves, () -> new Wolf());
		view.setViewState(ViewState.SIMULATION_PLAY);
		simulationStarted = true;
		/// END SOLUTION
	}

	/**
	 * Quit the simulation
	 */
	private void quit() {
		/* Write your code here */
		/// BEGIN SOLUTION
		view.setViewState(ViewState.SIMULATION_INIT);
		view.setField(null);
		field = null;
		simulationStarted = false;
		/// END SOLUTION
	}

	/**
	 * Launches the option management panel
	 */
	private void startManagement() {
		/* Write your code here */
		/// BEGIN SOLUTION
		view.setViewState(ViewState.MANAGEMENT);
		/// END SOLUTION
	}

	/**
	 * Erase the management panel and goes back either to:
	 * - initial panel (if the simulation has not started)
	 * - pause panel otherwise
	 */
	private void stopManagement() {
		/// BEGIN SOLUTION
		view.setViewState(simulationStarted? ViewState.SIMULATION_PAUSE : ViewState.SIMULATION_INIT);
		/// END SOLUTION
		/* Write your code here */
	}


	/**
	 * Receives an action with no extra parameter
	 * @param action, the action to be performed
	 */
	public void receiveAction(FieldAction action) {
		switch (action) {
			case INITIAL_START: initial_start(); break;
			/// BEGIN SOLUTION
			case EVOLVE: field.evolve();break;
			case PAUSE: view.setViewState(ViewState.SIMULATION_PAUSE);break;
			case START: view.setViewState(ViewState.SIMULATION_PLAY);break;
			case QUIT: quit();break;
			case MANAGEMENT_START: startManagement();break;
			case MANAGEMENT_END: stopManagement();break;
			case SPEED_PLUS:
				if(current_delay > 5) {
					current_delay-=5;
					view.setLoopDelay(current_delay);
				}
				break;
			case SPEED_MINUS:
				current_delay+=5;
				view.setLoopDelay(current_delay);
				break;
			/// END SOLUTION
		}
		view.update();
	}

	/**
	 * Receives an action with an extra string parameter
	 * The string must be a verified string which will be directly converted into the wanted parameter value
	 * @param action, the action to be performed
	 * @param v, the sent value
	 */
	public void receiveAction(FieldAction action, String v) {
		/* Write your code here */
		/// BEGIN SOLUTION
		switch (action) {
			case INITIAL_SHEEPS: setNumberOfInitialSheeps(Integer.parseInt(v));break;
			case INITIAL_WOLVES: setNumberOfInitialWolves(Integer.parseInt(v));break;
			case GRASS_PROBA: Grass.setDefaultProba(Double.parseDouble(v)); break;
			case GRASS_LIFE_SPAN: Grass.setDefaultLifeSpan(Integer.parseInt(v));break;
			case SHEEP_LIFE_SPAN: Sheep.setDefaultLifeSpan(Integer.parseInt(v));break;
			case SHEEP_WEAK_LEV: Sheep.setDefaultWeakLevel(Integer.parseInt(v));break;
			case SHEEP_INCREASE: Sheep.setDefaultIncreasePerEat(Integer.parseInt(v));break;
			case SHEEP_SPEED: Sheep.setDefaultSpeed(Integer.parseInt(v));break;
			case SHEEP_PROBA: Sheep.setDefaultReproductionProba(Double.parseDouble(v));break;
			case WOLF_LIFE_SPAN: Wolf.setDefaultLifeSpan(Integer.parseInt(v));break;
			case WOLF_WEAK_LEV: Wolf.setDefaultWeakLevel(Integer.parseInt(v));break;
			case WOLF_INCREASE: Wolf.setDefaultIncreasePerEat(Integer.parseInt(v));break;
			case WOLF_SPEED: Wolf.setDefaultSpeed(Integer.parseInt(v));break;
			case WOLF_PROBA: Wolf.setDefaultReproductionProba(Double.parseDouble(v));break;
			case ADD_SHEEP: field.addRandomFieldElements(Integer.parseInt(v), () -> new Sheep());break;
			case ADD_WOLF: field.addRandomFieldElements(Integer.parseInt(v), () -> new Wolf()); break;
		}
		view.update();
		/// END SOLUTION
	}
}
