package fr.upsaclay.bibs.view;

import javax.swing.JButton;

import fr.upsaclay.bibs.mvc.control.RectangleAction;

public class RectangleAppButton extends JButton implements RectangleAppActionComponent {

	private RectangleAction action;
	
	public RectangleAppButton(String name) {
		super(name);
	}

	@Override
	public void setRectangleAction(RectangleAction action) {
		this.action = action;
		
	}

	@Override
	public RectangleAction getRectangleAction() {
		return action;
	}
}
