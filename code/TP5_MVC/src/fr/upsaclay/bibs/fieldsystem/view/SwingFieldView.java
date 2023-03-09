package fr.upsaclay.bibs.fieldsystem.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import fr.upsaclay.bibs.fieldsystem.control.FieldAction;
import fr.upsaclay.bibs.fieldsystem.sheepfield.Field;

/**
 * The implementation of FieldView with Swing JFrame
 * 
 * @author Viviane Pons
 *
 */
public class SwingFieldView extends JFrame implements FieldView {
	
	public static final int PIXELS_PER_CELLS = 20;
	
	
	DrawPanel drawPanel;
	JPanel controlPanel;
	Timer timer;
	
	final List<FieldParameter> initialParameters;
	FieldButton initialStartButton;
	
	/// BEGIN SOLUTION
	FieldButton startButton;
	FieldButton pauseButton;
	FieldButton quitButton;
	FieldButton managementButton1;
	FieldButton managementButton2;
	FieldButton resumeButton;
	FieldButton speedPlus;
	FieldButton speedMinus;
	/// END SOLUTION
	
	JPanel initialPanel;
	/// BEGIN SOLUTION
	JPanel playPanel;
	JPanel pausePanel;
	JPanel managementPanel;
	
	final List<FieldParameter> staticParameters;
	final List<FieldParameter> addButtons;
	/// END SOLUTION
	

	public SwingFieldView(String name, int widthField, int heightField) {
		super(name);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		
		// Create the drawPanel (where we draw the field)
		drawPanel = new DrawPanel(widthField, heightField);
		
		// Create the control panel
		controlPanel = new JPanel();
		
		// Create the loop timer
		timer = new Timer(1, null);
		
		// Creating the parameter lists
		initialParameters = Arrays.asList(
				new FieldParameter("Initial number of sheeps", FieldAction.INITIAL_SHEEPS),
				new FieldParameter("Initial number of wolves", FieldAction.INITIAL_WOLVES)
				);
		/// BEGIN SOLUTION
		staticParameters = Arrays.asList(
				new FieldParameter("Grass proba", FieldAction.GRASS_PROBA),
				new FieldParameter("Grass life span", FieldAction.GRASS_LIFE_SPAN),
				new FieldParameter("Sheep life span", FieldAction.SHEEP_LIFE_SPAN),
				new FieldParameter("Sheep weak level", FieldAction.SHEEP_WEAK_LEV),
				new FieldParameter("Sheep increase per eat", FieldAction.SHEEP_INCREASE),
				new FieldParameter("Sheep speed", FieldAction.SHEEP_SPEED),
				new FieldParameter("Sheep reproduction proba", FieldAction.SHEEP_PROBA),
				new FieldParameter("Wolf life span", FieldAction.WOLF_LIFE_SPAN),
				new FieldParameter("Wolf weak level", FieldAction.WOLF_WEAK_LEV),
				new FieldParameter("Wolf increase per eat", FieldAction.WOLF_INCREASE),
				new FieldParameter("Wolf speed", FieldAction.WOLF_SPEED),
				new FieldParameter("Wolf reproduction proba", FieldAction.WOLF_PROBA)
				);
		
		addButtons = Arrays.asList(
				new FieldParameter("Add sheeps", FieldAction.ADD_SHEEP),
				new FieldParameter("Add wolves", FieldAction.ADD_WOLF)
				);
		/// END SOLUTION
		
	}
	

	@Override
	public void initialize() {
		// General initialization
		drawPanel.initialize();
		add( drawPanel, BorderLayout.CENTER );
		add( controlPanel, BorderLayout.EAST);
		controlPanel.setPreferredSize(new Dimension(300, drawPanel.getPreferredSize().height));
		
		
		
		// Initializing all management panels
		// The initial panel
		initialPanel = new JPanel();
		initialPanel.setPreferredSize(new Dimension(controlPanel.getPreferredSize().width, drawPanel.getPreferredSize().height));
		initialStartButton = new FieldButton("Start");
		initialStartButton.setFieldAction(FieldAction.INITIAL_START);
		initialPanel.add(initialStartButton);
		for(FieldParameter parameter : initialParameters) {
			initialPanel.add(parameter);
		}
		/// BEGIN SOLUTION
		managementButton1 = new FieldButton("Manage options");
		managementButton1.setFieldAction(FieldAction.MANAGEMENT_START);
		initialPanel.add(managementButton1);
		/// END SOLUTION
		controlPanel.add(initialPanel);
		
		// The play panel (when the game is running)
		// Write your code here
		/// BEGIN SOLUTION
		playPanel = new JPanel();
		playPanel.setPreferredSize(new Dimension(controlPanel.getPreferredSize().width, drawPanel.getPreferredSize().height));
		pauseButton = new FieldButton("Pause");
		pauseButton.setFieldAction(FieldAction.PAUSE);
		speedPlus = new FieldButton("Speed++");
		speedPlus.setFieldAction(FieldAction.SPEED_PLUS);
		speedMinus = new FieldButton("Speed--");
		speedMinus.setFieldAction(FieldAction.SPEED_MINUS);
		playPanel.add(pauseButton);
		playPanel.add(speedPlus);
		playPanel.add(speedMinus);
		controlPanel.add(playPanel);
		/// END SOLUTION
		
		// The pause panel (when the game is on pause)
		// Write your code here
		/// BEGIN SOLUTION
		pausePanel = new JPanel();
		pausePanel.setPreferredSize(new Dimension(controlPanel.getPreferredSize().width, drawPanel.getPreferredSize().height));
		startButton = new FieldButton("Start");
		startButton.setFieldAction(FieldAction.START);
		quitButton = new FieldButton("Quit");
		quitButton.setFieldAction(FieldAction.QUIT);
		managementButton2 = new FieldButton("Manage Options");
		managementButton2.setFieldAction(FieldAction.MANAGEMENT_START);
		pausePanel.add(startButton);
		pausePanel.add(quitButton);
		for(FieldParameter parameter : addButtons) {
			pausePanel.add(parameter);
		}
		pausePanel.add(managementButton2);
		controlPanel.add(pausePanel);
		
		// Management panel
		managementPanel = new JPanel();
		JPanel insidePanel = new JPanel();
		insidePanel.setPreferredSize(new Dimension(1500, 250));
		for(FieldParameter paramter : staticParameters) {
			insidePanel.add(paramter);
		}
		resumeButton = new FieldButton("Resume");
		resumeButton.setFieldAction(FieldAction.MANAGEMENT_END);
		managementPanel.add(insidePanel);
		managementPanel.add(resumeButton);
		
		//add(managementPanel, BorderLayout.CENTER);
		/// END SOLUTION
		
		
		pack();
		this.drawSimulationInitView();
		setVisible(true);
	}

	@Override
	public void update() {
		repaint();
		
	}



	@Override
	public void setField(Field field) {
		drawPanel.setField(field);
	}
	
	@Override
	public void drawSimulationInitView() {
		initialPanel.setVisible(true);
		/// BEGIN SOLUTION
		playPanel.setVisible(false);
		pausePanel.setVisible(false);
		managementPanel.setVisible(false);
		/// END SOLUTION
		
	}
	
	@Override
	public void drawSimulationPlayView() {
		/// BEGIN SOLUTION
		initialPanel.setVisible(false);
		playPanel.setVisible(true);
		pausePanel.setVisible(false);
		/// END SOLUTION
		
	}


	@Override
	public void drawSimulationPauseView() {
		/// BEGIN SOLUTION
		playPanel.setVisible(false);
		pausePanel.setVisible(true);
		/// END SOLUTION
		
	}

	@Override
	public void drawManagementView() {
		/// BEGIN SOLUTION
		add(managementPanel, BorderLayout.CENTER);
		drawPanel.setVisible(false);
		managementPanel.setVisible(true);
		controlPanel.setVisible(false);
		repaint();
		/// END SOLUTION
		
	}
	
	@Override
	public void eraseManagementView() {
		/// BEGIN SOLUTION
		this.remove(managementPanel);
		add(drawPanel, BorderLayout.CENTER);
		drawPanel.setVisible(true);
		controlPanel.setVisible(true);
		repaint();
		/// END SOLUTION
	}
	

	@Override
	public void setLoopAction(ActionListener listener) {
		timer.addActionListener(listener);
		
	}


	@Override
	public void startActionLoop() {
		timer.start();	
	}


	@Override
	public void stopActionLoop() {
		timer.stop();
	}


	@Override
	public void setLoopDelay(int ms) {
		timer.setDelay(ms);
	}
	
	@Override
	public int getLoopDelay() {
		return timer.getDelay();
	}


	@Override
	public void setFieldActionListener(ActionListener listener) {
		/// BEGIN SOLUTION
		initialStartButton.addActionListener(listener);
		startButton.addActionListener(listener);
		pauseButton.addActionListener(listener);
		quitButton.addActionListener(listener);
		managementButton1.addActionListener(listener);
		managementButton2.addActionListener(listener);
		resumeButton.addActionListener(listener);
		for(FieldParameter parameter : initialParameters) {
			parameter.addActionListener(listener);
		}
		for(FieldParameter parameter : staticParameters ) {
			parameter.addActionListener(listener);
		}
		for(FieldParameter parameter : addButtons) {
			parameter.addActionListener(listener);
		}
		speedPlus.addActionListener(listener);
		speedMinus.addActionListener(listener);
		
		/// END SOLUTION
		
	}

}
