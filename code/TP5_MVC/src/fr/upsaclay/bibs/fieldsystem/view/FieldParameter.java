package fr.upsaclay.bibs.fieldsystem.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import fr.upsaclay.bibs.fieldsystem.control.FieldAction;
import fr.upsaclay.bibs.fieldsystem.control.FieldParameterType;

/**
 * A customized JPAnel to handle field parameter updaters with label, text field and button
 * 
 * @author viviane
 *
 */
public class FieldParameter extends JPanel implements KeyListener, ActionListener, FocusListener {
	
	private JLabel label;
	private JTextField textField;
	private ParameterButton okButton;
	
	private ParameterVerifier verifier;
	String memory;

	/**
	 * Create the component using the label text and FieldAction
	 * @param text, the text to appear above the parameter
	 * @param action, the FieldAction corresponding to updating this specific parameter
	 */
	public FieldParameter(String text, FieldAction action) {
		setPreferredSize(new Dimension(300, 60));
		textField = new JTextField();
		textField.setText(action.getDefaultValue());
		textField.setPreferredSize(new Dimension(200,20));
		textField.addKeyListener(this);
		textField.addFocusListener(this);
		okButton = new ParameterButton("ok");
		okButton.setFieldAction(action);
		verifier = getVerifier(action.getType());
		memory = textField.getText();
		label = new JLabel(text);
		label.setPreferredSize(new Dimension(300,20));
		add(label);
		add(textField);
		add(okButton);
		okButton.setEnabled(false);
		addActionListener(this);
		
		
	}
	
	/**
	 * Adds the action listener to the parameter updater
	 * (actually attached to the ok button)
	 * @param listener
	 */
	public void addActionListener(ActionListener listener) {
		okButton.addActionListener(listener);
	}
	
	/**
	 * Remove the action listener from the parameter updater
	 * 
	 * @param listener
	 */
	public void removeActionListener(ActionListener listener) {
		okButton.removeActionListener(listener);
	}
	
	/** 
	 * A customized implementation of FieldButton 
	 * so that the action command is actually the text field text
	 * 
	 * @author Viviane Pons
	 *
	 */
	class ParameterButton extends FieldButton {
			
		ParameterButton(String text) {
			super(text);
		}
		
		@Override
		public String getActionCommand() {
			return textField.getText();
		}
		
		
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
	public void actionPerformed(ActionEvent e) {
		textField.setForeground(Color.BLACK);
		okButton.setEnabled(false);
		memory = textField.getText();
	}
	
	class IntInputVerifier  implements ParameterVerifier {

		@Override
		public boolean verify(String value) {
			try {
				int a = Integer.valueOf(value);
				return a >= 0;
			} catch (NumberFormatException e) {
				return false;
			}
		}
	}
	
	class ProbaInputVerifier implements ParameterVerifier {

		@Override
		public boolean verify(String value) {
			try {
				double d = Double.valueOf(value);
				return 0 <= d && d <= 1;
			} catch (NumberFormatException e) {
				return false;
			}
		}
	}
	
	ParameterVerifier getVerifier(FieldParameterType type) {
		switch(type) {
		case PROBA:
			return new ProbaInputVerifier();
		case INT:
			return new IntInputVerifier();
		default:
			return null;
		
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
