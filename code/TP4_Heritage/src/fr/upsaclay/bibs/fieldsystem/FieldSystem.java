package fr.upsaclay.bibs.fieldsystem;


import fr.upsaclay.bibs.fieldsystem.sheepfield.Field;
import fr.upsaclay.bibs.fieldsystem.sheepfield.Sheep;
import fr.upsaclay.bibs.fieldsystem.sheepfield.Wolf;
import fr.upsaclay.bibs.fieldsystem.view.FieldView;
import fr.upsaclay.bibs.fieldsystem.view.SwingFieldView;


public class FieldSystem {

	public static void main(String[] args) {
		
		
		Field field = new Field(60,40);
		/// BEGIN COMMENTEE
		field.addRandomFieldElements(50, () -> new Sheep());
		field.addRandomFieldElements(50, () -> new Wolf());
		/// END COMMENTEE
		FieldView view = new SwingFieldView("My Field");
		view.initialize(field);
		
		while (true) {
		    try {
		    	Thread.sleep(200);
		    }
		    catch (InterruptedException e) {
		    }
		    field.evolve();
		    view.update(field);
	    }

	}

}
