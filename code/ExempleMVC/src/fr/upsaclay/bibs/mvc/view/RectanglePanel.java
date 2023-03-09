package fr.upsaclay.bibs.mvc.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import fr.upsaclay.bibs.mvc.model.Rectangle;

public class RectanglePanel extends JPanel {
	
	private Rectangle rectangle;

	public RectanglePanel(int width, int height) {
		super();
		setPreferredSize(new Dimension(width, height));
		setBackground(Color.WHITE);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if(rectangle != null) {
			g.setColor(rectangle.getFillColor());
			g.fillRect(rectangle.getX(), rectangle.getY(), rectangle.getWidth(), rectangle.getHeight());
		}
	}
	
	public void initialize() {
		
	}

	public Rectangle getRectangle() {
		return rectangle;
	}

	public void setRectangle(Rectangle rectangle) {
		this.rectangle = rectangle;
	}
	
	
}
