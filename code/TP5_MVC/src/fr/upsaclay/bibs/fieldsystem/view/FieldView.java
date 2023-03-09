package fr.upsaclay.bibs.fieldsystem.view;

import java.awt.event.ActionListener;

import fr.upsaclay.bibs.fieldsystem.sheepfield.Field;

/**
 * The interface that should be implemented by the general view of the application
 * 
 * @author Viviane Pons
 *
 */
public interface FieldView {
	
	/**
	 * Initialize the view
	 */
	public void initialize();
	
	/**
	 * Attach the field to the view components that need to read information from the model
	 * 
	 * The field classe shouls NOT be modified by the view
	 * 
	 * @param field
	 */
	public void setField(Field field);
	
	/**
	 * Updates the view
	 */
	public void update();
	
	/**
	 * Set the listener in charge of receiving FieldActon actions
	 * 
	 * @param listener
	 */
	public void setFieldActionListener(ActionListener listener);
	
	/**
	 * Set the listener in charge of running the simulation loop
	 * 
	 * @param listener
	 */
	public void setLoopAction(ActionListener listener);
	
	/**
	 * Set the loop delay
	 * 
	 * @param ms
	 */
	public void setLoopDelay(int ms);
	
	/**
	 * Return the loop delay
	 * 
	 * @return
	 */
	public int getLoopDelay();
	
	/**
	 * Start the simulation loop
	 */
	public void startActionLoop();
	
	/**
	 * Stop the simulation loop
	 */
	public void stopActionLoop();
	
	/**
	 * Draw the initial view
	 */
	public void drawSimulationInitView();
	
	/**
	 * Draw the view when the simulation is running
	 */
	public void drawSimulationPlayView();
	
	/**
	 * Draw the view when the simulation is on pause
	 */
	public void drawSimulationPauseView();
	
	/**
	 * Draw management play view
	 */
	public void drawManagementView();
	
	/**
	 * Erase management play view
	 */
	public void eraseManagementView();
	

}
