package fr.upsaclay.bibs.view;

import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseMotionListener;

import fr.upsaclay.bibs.mvc.model.Rectangle;

public interface RectangleAppView {

	public void initialize();
	
	public void setRectanle(Rectangle rectangle);
	
	public void setListenerOnButtons(ActionListener listener);
	
	public void startKeyListener(KeyListener listener);
	
	public void stopKeyListener(KeyListener listener);
	
	public void startMouseMotionListener(MouseMotionListener listener);
	
	public void stopMouseMotionListener(MouseMotionListener listener);
	
	public void update();
	
	public void drawNoRectangleView();
	
	public void drawWithRectangleView();
	
}

