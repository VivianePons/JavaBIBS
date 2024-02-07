package fr.upsaclay.bibs.fieldsystem;


import fr.upsaclay.bibs.fieldsystem.sheepfield.Field;
import fr.upsaclay.bibs.fieldsystem.sheepfield.Sheep;
import fr.upsaclay.bibs.fieldsystem.sheepfield.Wolf;
import fr.upsaclay.bibs.fieldsystem.view.FieldView;
import fr.upsaclay.bibs.fieldsystem.view.SwingFieldView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class FieldSystem implements ActionListener {

	Field field;
	FieldView view;
	Timer timer;

	public static void main(String[] args) {
		FieldSystem system = new FieldSystem();
		system.start();
	}

	public FieldSystem() {
		field = new Field(60,40);
		/// BEGIN COMMENTEE
		field.addRandomFieldElements(50, () -> new Sheep());
		field.addRandomFieldElements(50, () -> new Wolf());
		/// END COMMENTEE
		view = new SwingFieldView("My Field");
		view.initialize(field);
		timer = new Timer(200, this);
	}

	public void start() {
		timer.start();
	}

	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		field.evolve();
		view.update(field);
	}
}
