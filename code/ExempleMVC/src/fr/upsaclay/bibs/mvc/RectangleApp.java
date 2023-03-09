package fr.upsaclay.bibs.mvc;

import javax.swing.SwingUtilities;

import fr.upsaclay.bibs.mvc.control.RectangleController;

public class RectangleApp {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> new RectangleController().initialize());
	}
}
