package fr.upsaclay.bibs.fieldsystem.view;

import javax.swing.JButton;

import fr.upsaclay.bibs.fieldsystem.control.FieldAction;

/**
 * An extention of JButton that implements FieldActionComponent
 * so that we can use Swing buttons to communicate with controller
 * 
 * @author Viviane Pons
 *
 */
public class FieldButton extends JButton implements FieldActionComponent {

	private FieldAction fieldAction;
	
	public FieldButton(String text) {
		super(text);
	}
	
	@Override
	public void setFieldAction(FieldAction action) {
		fieldAction = action;

	}

	@Override
	public FieldAction getFieldAction() {
		return fieldAction;
	}
	
	// Note : JButton natively implements a getActionCommand method


}
