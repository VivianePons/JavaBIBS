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
		view.setRectanle(rectangle);
		view.setViewState(ViewState.WITH_RECTANGLE);
	}

	private void deleteRectangle() {
		rectangle = null;
		view.setRectanle(null);
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

	public void receiveAction(RectangleAction action, int x, int y) {
		switch (action) {
			case MOVE:
				rectangle.trymove(x, y);
				break;
		}
		view.update();
	}
}

/*
public class RectangleController {
	
	public static final int BOUNDX = 500;
	public static final int BOUNDY = 300;
	
	private Rectangle rectangle;
	private RectangleAppView view;
	
	private ControllerMouseMotionListener mouseListener;

	public RectangleController() {
		Rectangle.setMaxx(BOUNDX);
		Rectangle.setMaxy(BOUNDY);
		view = new RectangleAppFrame("My Rectangle app", BOUNDX, BOUNDY);
	}
	
	public void initialize() {
		view.initialize();


		view.drawNoRectangleView();
		view.setListenerOnButtons(this);


	}

	@Override
	public void actionPerformed(ActionEvent e) {
		RectangleAppActionComponent button = (RectangleAppActionComponent) e.getSource();
		if(rectangle != null) {
			view.stopKeyListener(this);
		}
		switch(button.getRectangleAction()) {
		case CREATE:
			rectangle = new Rectangle(250, 150, 30, 20);
			rectangle.setFillColor(Color.BLUE);
			view.setRectanle(rectangle);
			view.drawWithRectangleView();
			mouseListener = new ControllerMouseMotionListener();
			view.startMouseMotionListener(mouseListener);
			break;
		case DELETE:
			rectangle = null;
			view.setRectanle(null);
			view.drawNoRectangleView();
			view.stopMouseMotionListener(mouseListener);
			break;
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
		default:
			break;
		
		}
		if(rectangle != null) {
			view.startKeyListener(this);
		}
		view.update();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch(e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			rectangle.trymove(-1, 0);
			break;
		case KeyEvent.VK_RIGHT:
			rectangle.trymove(1, 0);
			break;
		case KeyEvent.VK_UP:
			rectangle.trymove(0, -1);
			break;
		case KeyEvent.VK_DOWN:
			rectangle.trymove(0, 1);
			break;
		case KeyEvent.VK_C:
			rectangle.tryExtendWidth(-1);
			break;
		case KeyEvent.VK_V:
			rectangle.tryExtendWidth(1);
			break;
		case KeyEvent.VK_B:
			rectangle.tryExtendHeight(-1);
			break;
		case KeyEvent.VK_N:
			rectangle.tryExtendHeight(1);
			break;
		}
		view.update();
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	private class ControllerMouseMotionListener implements MouseMotionListener {

		private boolean startDrag;
		private int prevx;
		private int prevy;
		
		ControllerMouseMotionListener() {
			startDrag = false;
		}
		
		@Override
		public void mouseDragged(MouseEvent e) {
			
			if(! startDrag) {
				int x = e.getX();
				int y = e.getY();
				if(rectangle.insideRectanle(x, y)) {
					startDrag = true;
					prevx = x;
					prevy = y;
				}
			} else {
				int diffx = e.getX() - prevx;
				int diffy = e.getY() - prevy;
				rectangle.trymove(diffx, diffy);
				prevx = e.getX();
				prevy = e.getY();
				view.update();
			}
			
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			startDrag = false;
			
		}
		
	}

}
*/
