package fr.upsaclay.bibs.fieldsystem.view;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import fr.upsaclay.bibs.fieldsystem.control.FieldAction;
import fr.upsaclay.bibs.fieldsystem.control.FieldController;
import fr.upsaclay.bibs.fieldsystem.control.FieldParameterType;
import fr.upsaclay.bibs.fieldsystem.sheepfield.Field;

/**
 * The implementation of FieldView with Swing JFrame
 * 
 * @author Viviane Pons
 *
 */
public class SwingFieldView extends JFrame implements FieldView {
	
	public static final int PIXELS_PER_CELLS = 20;

	private FieldController controller;
	
	
	DrawPanel drawPanel;
	JPanel controlPanel;
	Timer timer;
	
	final List<FieldParameter> initialParameters;
	JButton initialStartButton;

	/// BEGIN SOLUTION

	JButton startButton;
	JButton pauseButton;
	JButton quitButton;


	JButton managementButton1;
	JButton managementButton2;
	JButton resumeButton;
	JButton speedPlus;
	JButton speedMinus;


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
		
		// Create the parameter lists
		initialParameters = new ArrayList<>();

		/// BEGIN SOLUTION
		staticParameters = new ArrayList<>();
		addButtons = new ArrayList<>();
		/// END SOLUTION
	}

	/**
	 * Create a FieldParamater with an attached ParameterListener
	 * @param label, the label of the parameter
	 * @param action, the action to be sent to the controller
	 * @return the field parameter element to be added to the view
	 */
	private FieldParameter createParameter(String label, FieldAction action) {
		/// BEGIN SOLUTION
		FieldParameter param = new FieldParameter(label, action.getDefaultValue(), FieldParameterType.getVerifier(action.getType()));
		param.addActionListener(new ParameterListener(controller, action, param));
		return param;
		/// END SOLUTION
		/* BEGIN UNCOMMENT
		throw new UnsupportedOperationException("Not implemented");
		END UNCOMMENT */
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
		initialStartButton = new JButton("Start");
		initialPanel.add(initialStartButton);

		// Add the initial parameters to the list
		/// BEGIN SOLUTION
		initialParameters.add(createParameter("Initial number of sheep", FieldAction.INITIAL_SHEEPS));
		initialParameters.add(createParameter("Initial number of wolves", FieldAction.INITIAL_WOLVES));

		for(FieldParameter parameter : initialParameters) {
			initialPanel.add(parameter);
		}
		/// END SOLUTION

		/// BEGIN SOLUTION
		initialStartButton.addActionListener(new ButtonListener(controller, FieldAction.INITIAL_START));

		managementButton1 = new JButton("Manage options");
		managementButton1.addActionListener(new ButtonListener(controller, FieldAction.MANAGEMENT_START));
		initialPanel.add(managementButton1);

		/// END SOLUTION
		controlPanel.add(initialPanel);

		// Timer initialization
		/* Write your code here */
		/// BEGIN SOLUTION
		timer.addActionListener(new ButtonListener(controller, FieldAction.EVOLVE));
		/// END SOLUTION
		
		// The play panel (when the simulation is running)
		/* Write your code here */
		/// BEGIN SOLUTION
		playPanel = new JPanel();
		playPanel.setPreferredSize(new Dimension(controlPanel.getPreferredSize().width, drawPanel.getPreferredSize().height));

		pauseButton = new JButton("Pause");
		pauseButton.addActionListener(new ButtonListener(controller, FieldAction.PAUSE));


		speedPlus = new JButton("Speed++");
		speedPlus.addActionListener(new ButtonListener(controller, FieldAction.SPEED_PLUS));
		speedMinus = new JButton("Speed--");
		speedMinus.addActionListener(new ButtonListener(controller, FieldAction.SPEED_MINUS));


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

		startButton = new JButton("Start");
		startButton.addActionListener(new ButtonListener(controller, FieldAction.START));
		quitButton = new JButton("Quit");
		quitButton.addActionListener(new ButtonListener(controller, FieldAction.QUIT));

		managementButton2 = new JButton("Manage Options");
		managementButton2.addActionListener(new ButtonListener(controller, FieldAction.MANAGEMENT_START));

		pausePanel.add(startButton);
		pausePanel.add(quitButton);
		addButtons.add(createParameter("Add sheep", FieldAction.ADD_SHEEP));
		addButtons.add(createParameter("Add wolves", FieldAction.ADD_WOLF));


		for(FieldParameter parameter : addButtons) {
			pausePanel.add(parameter);
		}
		pausePanel.add(managementButton2);


		controlPanel.add(pausePanel);
		
		// Management panel
		managementPanel = new JPanel();
		staticParameters.add(createParameter("Grass proba", FieldAction.GRASS_PROBA));
		staticParameters.add(createParameter("Grass life span", FieldAction.GRASS_LIFE_SPAN));
		staticParameters.add(createParameter("Sheep life span", FieldAction.SHEEP_LIFE_SPAN));
		staticParameters.add(createParameter("Sheep weak level", FieldAction.SHEEP_WEAK_LEV));
		staticParameters.add(createParameter("Sheep increase per eat", FieldAction.SHEEP_INCREASE));
		staticParameters.add(createParameter("Sheep speed", FieldAction.SHEEP_SPEED));
		staticParameters.add(createParameter("Sheep reproduction proba", FieldAction.SHEEP_PROBA));
		staticParameters.add(createParameter("Wolf life span", FieldAction.WOLF_LIFE_SPAN));
		staticParameters.add(createParameter("Wolf weak level", FieldAction.WOLF_WEAK_LEV));
		staticParameters.add(createParameter("Wolf increase per eat", FieldAction.WOLF_INCREASE));
		staticParameters.add(createParameter("Wolf speed", FieldAction.WOLF_SPEED));
		staticParameters.add(createParameter("Wolf reproduction proba", FieldAction.WOLF_PROBA));


		JPanel insidePanel = new JPanel();
		insidePanel.setPreferredSize(new Dimension(drawPanel.getPreferredSize().width, 300));
		for(FieldParameter parameter : staticParameters) {
			insidePanel.add(parameter);
		}
		resumeButton = new JButton("Resume");
		resumeButton.addActionListener(new ButtonListener(controller, FieldAction.MANAGEMENT_END));

		managementPanel.add(insidePanel);
		managementPanel.add(resumeButton);

		/// END SOLUTION
		pack();
		setVisible(true);
	}

	@Override
	public void update() {
		repaint();
	}

	@Override
	public void setLoopDelay(int ms) {
		/* Write your code here */
		/// BEGIN SOLUTION
		timer.setDelay(ms);
		/// END SOLUTION
	}

	private void drawSimulationInitView() {
		initialPanel.setVisible(true);
		/// BEGIN SOLUTION
		add( drawPanel, BorderLayout.CENTER );
		drawPanel.setVisible(true);
		playPanel.setVisible(false);
		pausePanel.setVisible(false);
		managementPanel.setVisible(false);
		timer.stop();
		/// END SOLUTION

	}

	/// BEGIN SOLUTION
	private void drawPlayView() {
		add( drawPanel, BorderLayout.CENTER );
		drawPanel.setVisible(true);
		initialPanel.setVisible(false);
		playPanel.setVisible(true);
		pausePanel.setVisible(false);
		managementPanel.setVisible(false);
		timer.start();
	}

	private void drawPauseView() {
		add( drawPanel, BorderLayout.CENTER );
		drawPanel.setVisible(true);
		initialPanel.setVisible(false);
		playPanel.setVisible(false);
		pausePanel.setVisible(true);
		managementPanel.setVisible(false);
		timer.stop();
	}

	private void drawManagementView() {
		add(managementPanel, BorderLayout.CENTER);
		managementPanel.setVisible(true);
		initialPanel.setVisible(false);
		playPanel.setVisible(false);
		pausePanel.setVisible(false);
		drawPanel.setVisible(false);
	}
	/// END SOLUTION

	@Override
	public void setViewState(ViewState state) {
		switch (state) {
			case SIMULATION_INIT: drawSimulationInitView();break;
			/// BEGIN SOLUTION
			case SIMULATION_PLAY: drawPlayView();break;
			case SIMULATION_PAUSE: drawPauseView();break;
			case MANAGEMENT: drawManagementView();break;
			/// END SOLUTION
		}
	}
	@Override
	public void setField(Field field) {
		drawPanel.setField(field);
	}

	@Override
	public void setController(FieldController controller) {
		this.controller = controller;
	}

}
