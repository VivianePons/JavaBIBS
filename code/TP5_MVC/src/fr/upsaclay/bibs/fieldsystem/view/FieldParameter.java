package fr.upsaclay.bibs.fieldsystem.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;

import fr.upsaclay.bibs.fieldsystem.control.ParameterVerifier;

/**
 * A customized JPAnel to handle field parameter updaters with label, text field and button
 * 
 * @author viviane
 *
 */
public class FieldParameter extends JPanel implements KeyListener, FocusListener {
	
	private final JLabel label;
	private final JTextField textField;
	private final JButton okButton;
	
	private final ParameterVerifier verifier;
	String memory;

	/**
	 * Create the component using the label text and FieldAction
	 * @param label, the text to appear above the parameter
	 * @param verifier, the verifier for the text
	 */
	public FieldParameter(String label, String defaultValue, ParameterVerifier verifier) {
		setPreferredSize(new Dimension(300, 60));
		textField = new JTextField();
		textField.setText(defaultValue);
		textField.setPreferredSize(new Dimension(200,20));
		textField.addKeyListener(this);
		textField.addFocusListener(this);
		okButton = new JButton("ok");
		this.verifier = verifier;
		memory = textField.getText();
		this.label = new JLabel(label);
		this.label.setPreferredSize(new Dimension(300,20));
		add(this.label);
		add(textField);
		add(okButton);
		okButton.setEnabled(false);
	}
	
	/**
	 * Adds the action listener to the parameter updater
	 * (actually attached to the ok button)
	 * @param listener an Action Listener
	 */
	public void addActionListener(ActionListener listener) {
		okButton.addActionListener(listener);
	}
	
	/**
	 * Remove the action listener from the parameter updater
	 * @param listener an action listener
	 */
	public void removeActionListener(ActionListener listener) {
		okButton.removeActionListener(listener);
	}

	/**
	 * Return the current value of the parameter
	 * @return the vavlue as a string
	 */
	public String getValue() {
		return textField.getText();
	}

	/**
	 * Inform the parameter box that the parameter value has been saved:
	 * - the save button can be deactivated
	 * - the color is set back to "no change"
	 */
	public void entrySaved() {
		textField.setForeground(Color.BLACK);
		okButton.setEnabled(false);
		memory = textField.getText();
	}
	
	// All the methods below are internal listeners
	// to handle the verification and general functioning
	// of the field parameter updater 

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		textField.setForeground(Color.RED);
		if(verifier.verify(textField.getText())) {
			okButton.setEnabled(true);
		} else {
			okButton.setEnabled(false);
		}
	}
	@Override
	public void focusGained(FocusEvent e) {
		
	}

	@Override
	public void focusLost(FocusEvent e) {
		if(textField.getText().equals("")) {
			textField.setText(memory);
			textField.setForeground(Color.BLACK);
		}
	}
}
