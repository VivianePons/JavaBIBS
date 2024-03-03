package fr.upsaclay.bibs.mvc.view;

import fr.upsaclay.bibs.mvc.control.RectangleAction;
import fr.upsaclay.bibs.mvc.control.RectangleController;
import fr.upsaclay.bibs.mvc.model.Rectangle;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class RectangleMouseListener implements MouseMotionListener {

    private final RectangleController controller;

    private Rectangle rectangle;
    private boolean startDrag;
    int prevx;
    int prevy;

    public RectangleMouseListener(RectangleController controller) {
        this.controller = controller;
        startDrag = false;
    }

    @Override
    public void mouseDragged(MouseEvent mouseEvent) {
        if(! startDrag) {
            int x = mouseEvent.getX();
            int y = mouseEvent.getY();
            if(rectangle.insideRectanle(x, y)) {
                startDrag = true;
                prevx = x;
                prevy = y;
            }
        } else {
            int diffx = mouseEvent.getX() - prevx;
            int diffy = mouseEvent.getY() - prevy;
            controller.receiveAction(RectangleAction.MOVE, diffx, diffy);
            prevx = mouseEvent.getX();
            prevy = mouseEvent.getY();
        }
    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {
        startDrag = false;
    }

    public void setRectangle(Rectangle rectangle) {
        this.rectangle = rectangle;
    }
}
