package fr.upsaclay.bibs.mvc.view;

import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseMotionListener;

import fr.upsaclay.bibs.mvc.control.RectangleController;
import fr.upsaclay.bibs.mvc.model.Rectangle;

public interface RectangleAppView {

	void initialize();
	
	void setRectanle(Rectangle rectangle);

	void setController(RectangleController controller);

	void update();

	void setViewState(ViewState state);
	
}

