package fr.upsaclay.bibs.fieldsystem.view;

import fr.upsaclay.bibs.fieldsystem.control.FieldAction;
import fr.upsaclay.bibs.fieldsystem.control.FieldController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Implementations of the app Action Listener for button
 * A different listener is created for each action to be sent to the controller
 */
public class ButtonListener implements ActionListener {

    private final FieldController controller;
    private final FieldAction action;

    public ButtonListener(FieldController controller, FieldAction action) {
        this.controller = controller;
        this.action = action;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        /* Write your code here */
        /// BEGIN SOLUTION
        controller.receiveAction(action);
        /// END SOLUTION
    }
}
