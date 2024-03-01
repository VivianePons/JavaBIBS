package fr.upsaclay.bibs.mvc.view;

import fr.upsaclay.bibs.mvc.control.RectangleAction;
import fr.upsaclay.bibs.mvc.control.RectangleController;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class RectangleKeyListener implements KeyListener {

    private final RectangleController controller;

    public RectangleKeyListener(RectangleController controller) {
        this.controller = controller;
    }
    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        switch(keyEvent.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                controller.receiveAction(RectangleAction.MOVE_LEFT);
                break;
            case KeyEvent.VK_RIGHT:
                controller.receiveAction(RectangleAction.MOVE_RIGHT);
                break;
            case KeyEvent.VK_UP:
                controller.receiveAction(RectangleAction.MOVE_UP);
                break;
            case KeyEvent.VK_DOWN:
                controller.receiveAction(RectangleAction.MOVE_DOWN);
                break;
            case KeyEvent.VK_C:
                controller.receiveAction(RectangleAction.DECREASE_WIDTH);
                break;
            case KeyEvent.VK_V:
                controller.receiveAction(RectangleAction.INCREASE_WIDTH);
                break;
            case KeyEvent.VK_B:
                controller.receiveAction(RectangleAction.DECREASE_HEIGHT);
                break;
            case KeyEvent.VK_N:
                controller.receiveAction(RectangleAction.INCREASE_HEIGHT);
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {

    }
}
