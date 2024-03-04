package fr.upsaclay.bibs.fieldsystem.view;

import fr.upsaclay.bibs.fieldsystem.control.FieldAction;
import fr.upsaclay.bibs.fieldsystem.control.FieldController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ParameterListener implements ActionListener {

    private final FieldController controller;
    private final FieldAction action;
    private final FieldParameter param;

    public ParameterListener(FieldController controller, FieldAction action, FieldParameter param) {
        this.controller = controller;
        this.action = action;
        this.param = param;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        /// BEGIN SOLUTION
        controller.receiveAction(action, param.getValue());
        param.entrySaved();
        /// END SOLUTION
        /* BEGIN UNCOMMENT
		throw new UnsupportedOperationException("Not implemented");
		END UNCOMMENT */
    }
}
