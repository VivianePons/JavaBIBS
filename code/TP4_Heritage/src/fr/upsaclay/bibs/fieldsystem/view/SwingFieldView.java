package fr.upsaclay.bibs.fieldsystem.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;

import fr.upsaclay.bibs.fieldsystem.sheepfield.Field;

public class SwingFieldView extends JFrame implements FieldView {
	
	public static final int PIXELS_PER_CELLS = 20;

	public SwingFieldView(String name) {
		super(name);
	}
	

	@Override
	public void initialize(Field field) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		DrawPanel panel = new DrawPanel(field.getWidth() * PIXELS_PER_CELLS, field.getHeight() * PIXELS_PER_CELLS);
		panel.setField(field);
		add( panel, BorderLayout.CENTER );
		panel.setBackground(Color.white);
		pack();
		setVisible(true);
		
	}

	@Override
	public void update(Field field) {
		repaint();
		
	}
}
