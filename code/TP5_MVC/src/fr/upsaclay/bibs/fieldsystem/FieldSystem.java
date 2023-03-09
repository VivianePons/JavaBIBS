package fr.upsaclay.bibs.fieldsystem;


import javax.swing.SwingUtilities;

import fr.upsaclay.bibs.fieldsystem.control.FieldController;
import fr.upsaclay.bibs.fieldsystem.sheepfield.Field;
import fr.upsaclay.bibs.fieldsystem.sheepfield.Sheep;
import fr.upsaclay.bibs.fieldsystem.sheepfield.Wolf;
import fr.upsaclay.bibs.fieldsystem.view.FieldView;
import fr.upsaclay.bibs.fieldsystem.view.SwingFieldView;


public class FieldSystem {

	public static void main(String[] args) {
		
		SwingUtilities.invokeLater(() -> new FieldController().initialize());

	}

}
