package fr.upsaclay.bibs.fieldsystem.view;

import fr.upsaclay.bibs.fieldsystem.control.FieldController;
import fr.upsaclay.bibs.fieldsystem.sheepfield.Field;

/**
 * The interface that should be implemented by the general view of the application
 * 
 * @author Viviane Pons
 *
 */
public interface FieldView {
	
	/**
	 * Initialize the view
	 */
	void initialize();
	
	/**
	 * Attach the field to the view components that need to read information from the model
	 * The field classe shouls NOT be modified by the view
	 * @param field, a Field to be shown
	 */
	void setField(Field field);

	/**
	 * Attach the controller to the view
	 * The controller should only be used to receive action
	 * @param controller, a FieldController
	 */
	void setController(FieldController controller);
	
	/**
	 * Update the view
	 */
	void update();
	
	/**
	 * Set the loop delay
	 * @param ms, the delay in ms
	 */
	void setLoopDelay(int ms);

	/**
	 * Sets the current state
	 * @param state, the view state to show
	 */
	void setViewState(ViewState state);

}
