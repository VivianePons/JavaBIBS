package fr.upsaclay.bibs.mvc.control;

import java.awt.Color;

import fr.upsaclay.bibs.mvc.model.Rectangle;
import fr.upsaclay.bibs.mvc.view.RectangleAppFrame;
import fr.upsaclay.bibs.mvc.view.RectangleAppView;
import fr.upsaclay.bibs.mvc.view.ViewState;

public class RectangleController {

	public static final int BOUNDX = 500;
	public static final int BOUNDY = 300;

	private Rectangle rectangle;
	private RectangleAppView view;

	public RectangleController() {
		view = new RectangleAppFrame("My Rectangle app", BOUNDX, BOUNDY);
	}

	public void initialize() {
		Rectangle.setMaxx(BOUNDX);
		Rectangle.setMaxy(BOUNDY);
		view.setController(this);
		view.initialize();
		view.setViewState(ViewState.WITHOUT_RECTANGLE);
	}

	private void createRectangle() {
		rectangle = new Rectangle(250, 150, 30, 20);
		rectangle.setFillColor(Color.BLUE);
		view.setRectangle(rectangle);
		view.setViewState(ViewState.WITH_RECTANGLE);
	}

	private void deleteRectangle() {
		rectangle = null;
		view.setRectangle(null);
		view.setViewState(ViewState.WITHOUT_RECTANGLE);
	}

	public void receiveAction(RectangleAction action) {
		switch (action) {
            case CREATE: createRectangle(); break;
			case DELETE: deleteRectangle(); break;
			case DECREASE_HEIGHT:
				rectangle.tryExtendHeight(-1);
				break;
			case DECREASE_WIDTH:
				rectangle.tryExtendWidth(-1);
				break;
			case INCREASE_HEIGHT:
				rectangle.tryExtendHeight(1);
				break;
			case INCREASE_WIDTH:
				rectangle.tryExtendWidth(1);
				break;
			case MOVE_DOWN:
				rectangle.trymove(0, 1);
				break;
			case MOVE_LEFT:
				rectangle.trymove(-1, 0);
				break;
			case MOVE_RIGHT:
				rectangle.trymove(1, 0);
				break;
			case MOVE_UP:
				rectangle.trymove(0, -1);
				break;
        }
		view.update();
	}

	public void receiveAction(RectangleAction action, int diffx, int diffy) {
		switch (action) {
			case MOVE:
				rectangle.trymove(diffx, diffy);
				break;
		}
		view.update();
	}
}

