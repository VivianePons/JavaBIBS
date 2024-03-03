package fr.upsaclay.bibs.mvc.view;

import fr.upsaclay.bibs.mvc.control.RectangleController;
import fr.upsaclay.bibs.mvc.model.Rectangle;

public interface RectangleAppView {

	/**
	 * Initialize the view
	 */
	void initialize();

	/**
	 * Sets the model
	 * @param rectangle, the rectangle to show
	 */
	void setRectangle(Rectangle rectangle);

	/**
	 * Sets the controller
	 * @param controller, the controller of the view
	 */
	void setController(RectangleController controller);

	/**
	 * Update the view
	 */
	void update();

	/**
	 * Sets the current state
	 * @param state, the view state to show
	 */
	void setViewState(ViewState state);
	
}

