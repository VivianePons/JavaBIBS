package fr.upsaclay.bibs.mvc.view;

import fr.upsaclay.bibs.mvc.control.RectangleAction;
import fr.upsaclay.bibs.mvc.control.RectangleController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonListener implements ActionListener {

    private final RectangleController controller;
    private final RectangleAction action;

    public ButtonListener(RectangleController controller, RectangleAction action) {
        this.controller = controller;
        this.action = action;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        controller.receiveAction(action);
    }
}
