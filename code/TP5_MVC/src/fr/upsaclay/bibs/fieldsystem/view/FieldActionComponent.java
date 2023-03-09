package fr.upsaclay.bibs.fieldsystem.view;

import fr.upsaclay.bibs.fieldsystem.control.FieldAction;

/**
 * A field action component is used for interactions between the view and the controller
 * 
 * @author Viviane Pons
 *
 */
public interface FieldActionComponent {

	/**
	 * Attach a FieldAction to the component
	 * @param action
	 */
	public void setFieldAction(FieldAction action);
	
	/**
	 * The the FieldAction attached to the component
	 * @return a FieldAction
	 */
	public FieldAction getFieldAction();
	
	/**
	 * Return the action command that can be used to pass extra parameter
	 * 
	 * (the action command depends on the specific component implementation)
	 * @return a String
	 */
	public String getActionCommand();
}
