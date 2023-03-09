package fr.upsaclay.bibs.fieldsystem.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import fr.upsaclay.bibs.fieldsystem.sheepfield.Field;
import fr.upsaclay.bibs.fieldsystem.sheepfield.Grass;
import fr.upsaclay.bibs.fieldsystem.sheepfield.Sheep;
import fr.upsaclay.bibs.fieldsystem.sheepfield.Wolf;
import fr.upsaclay.bibs.fieldsystem.view.FieldActionComponent;
import fr.upsaclay.bibs.fieldsystem.view.FieldView;
import fr.upsaclay.bibs.fieldsystem.view.SwingFieldView;

/**
 * The controller of the application
 * 
 * @author viviane
 *
 */
public class FieldController implements ActionListener {
	
	public static final int WIDTH = 60;
	public static final int HEIGHT = 40;
	public static final int INITIAL_DELAY = 500;

	private Field field;
	private FieldView view;
	
	private int numberOfInitialSheeps = 50;
	private int numberOfInitialWolves = 50;
	
	public FieldController() {
		view = new SwingFieldView("Sheep field", WIDTH, HEIGHT);
	}
	
	public void initialize() {
		view.initialize();
		/// BEGIN SOLUTION
		view.setFieldActionListener(this);
		view.setLoopAction(new UpdateActionListener());
		view.setLoopDelay(INITIAL_DELAY);
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
	
	@Override
	public void actionPerformed(ActionEvent e) {
		FieldActionComponent comp = (FieldActionComponent)e.getSource();
		switch(comp.getFieldAction()) {
		case INITIAL_START:
			// Write action for initial start
			/// BEGIN SOLUTION
			view.drawSimulationPlayView();
			field = new Field(WIDTH, HEIGHT);
			view.setField(field);
			field.addRandomFieldElements(numberOfInitialSheeps, () -> new Sheep());
			field.addRandomFieldElements(numberOfInitialWolves, () -> new Wolf());
			view.update();
			view.startActionLoop();
			/// END SOLUTION
			break;
		case PAUSE:
			// Write action for pause
			/// BEGIN SOLUTION
			view.drawSimulationPauseView();
			view.stopActionLoop();
			/// END SOLUTION
			break;
		case START:
			// Write action for start (when the similation was on pause)
			/// BEGIN SOLUTION
			view.drawSimulationPlayView();
			view.startActionLoop();
			/// END SOLUTION
			break;
		case QUIT:
			// Write action for quitting simulation
			/// BEGIN SOLUTION
			view.drawSimulationInitView();
			view.stopActionLoop();
			view.setField(null);
			field = null;
			view.update();
			break;
			/// END SOLUTION
		/// BEGIN SOLUTION
		case INITIAL_SHEEPS:
			numberOfInitialSheeps = Integer.valueOf(comp.getActionCommand());
			break;
		case INITIAL_WOLVES:
			numberOfInitialWolves = Integer.valueOf(comp.getActionCommand());
			break;
		case MANAGEMENT_START:
			view.drawManagementView();
			break;
		case MANAGEMENT_END:
			view.eraseManagementView();
			break;
		case GRASS_PROBA:
			Grass.setDefaultProba(Double.valueOf(comp.getActionCommand()));
			break;
		case GRASS_LIFE_SPAN:
			Grass.setDefaultLifeSpan(Integer.valueOf(comp.getActionCommand()));
			break;
		case SHEEP_LIFE_SPAN:
			Sheep.setDefaultLifeSpan(Integer.valueOf(comp.getActionCommand()));
			break;
		case SHEEP_WEAK_LEV:
			Sheep.setDefaultWeakLevel(Integer.valueOf(comp.getActionCommand()));
			break;
		case SHEEP_INCREASE:
			Sheep.setDefaultIncreasePerEat(Integer.valueOf(comp.getActionCommand()));
			break;
		case SHEEP_SPEED:
			Sheep.setDefaultSpeed(Integer.valueOf(comp.getActionCommand()));
			break;
		case SHEEP_PROBA:
			Sheep.setDefaultReproductionProba(Double.valueOf(comp.getActionCommand()));
			break;
		case WOLF_LIFE_SPAN:
			Wolf.setDefaultLifeSpan(Integer.valueOf(comp.getActionCommand()));
			break;
		case WOLF_WEAK_LEV:
			Wolf.setDefaultWeakLevel(Integer.valueOf(comp.getActionCommand()));
			break;
		case WOLF_INCREASE:
			Wolf.setDefaultIncreasePerEat(Integer.valueOf(comp.getActionCommand()));
			break;
		case WOLF_SPEED:
			Wolf.setDefaultSpeed(Integer.valueOf(comp.getActionCommand()));
			break;
		case WOLF_PROBA:
			Wolf.setDefaultReproductionProba(Double.valueOf(comp.getActionCommand()));
			break;
		case ADD_SHEEP:
			field.addRandomFieldElements(Integer.valueOf(comp.getActionCommand()), () -> new Sheep());
			view.update();
			break;
		case ADD_WOLF:
			field.addRandomFieldElements(Integer.valueOf(comp.getActionCommand()), () -> new Wolf());
			view.update();
			break;
		case SPEED_PLUS:
			int d = view.getLoopDelay();
			if(d > 5) {
				view.setLoopDelay(d-5);
			}
			break;
		case SPEED_MINUS:
			view.setLoopDelay(view.getLoopDelay()+5);
			break;
		/// END SOLUTION
		default:
			break;
		
		}
	}

	class UpdateActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			field.evolve();
			view.update();
		}
		
	}
	
}
