package fr.upsaclay.bibs.fieldsystem.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import fr.upsaclay.bibs.fieldsystem.sheepfield.Field;
import fr.upsaclay.bibs.fieldsystem.sheepfield.FieldElement;

public class DrawPanel extends JPanel{
	
	private final Dimension fieldDimension;
	
	private Field field;
	
	
	public DrawPanel(int width, int height) {
		super();
		this.fieldDimension = new Dimension(width, height);
		
	}
	
	public void paintGrass(Graphics g, int i, int j) {
		int size = SwingFieldView.PIXELS_PER_CELLS;
		g.setColor(Color.GREEN);
		g.fillRect(j * size, i * size, size, size);
	}
	
	public void paintAnimal(Graphics g, int i, int j, Color color, boolean weak) {
		int size = SwingFieldView.PIXELS_PER_CELLS;
		g.setColor(color);
		g.fillOval(j * size + 1, i*size +1, size-2, size-2);
		g.setColor(weak ? Color.ORANGE : Color.BLACK);
		g.drawOval(j * size + 1, i*size +1, size-2, size-2);
	}
	
	
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        for(int i = 0; i < field.getHeight(); i++) {
        	for(int j = 0; j < field.getWidth(); j++) {
        		for(FieldElement element : field.getElements(i, j)) {
        			if(element.isActive()) {
        				switch(element.getType()) {
            			case GRASS:
            				paintGrass(g, i, j);
            				break;
            			case SHEEP:
            				paintAnimal(g, i, j, Color.WHITE, element.isWeak());
            				break;
            			case WOLF:
            				paintAnimal(g, i, j, Color.BLACK, element.isWeak());
            				break;
            			}
        				
        			}
        		}
        	}
        }

    }
    @Override
    public Dimension getPreferredSize(){
        return fieldDimension;
    }


	public Field getField() {
		return field;
	}


	public void setField(Field field) {
		this.field = field;
	}

}