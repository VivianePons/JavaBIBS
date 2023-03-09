package fr.upsaclay.bibs.mvc.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import fr.upsaclay.bibs.mvc.control.RectangleAction;
import fr.upsaclay.bibs.mvc.model.Rectangle;

public class RectangleAppFrame extends JFrame implements RectangleAppView {

	
	RectanglePanel rectanglePanel;
	JPanel buttonPanel;
	RectangleAppButton makeRectangleBtn;
	RectangleAppButton deleteRectangleBtn;
	RectangleAppButton moveLeft;
	RectangleAppButton moveRight;
	RectangleAppButton moveUp;
	RectangleAppButton moveDown;
	RectangleAppButton moreWidth;
	RectangleAppButton lessWidth;
	RectangleAppButton moreHeight;
	RectangleAppButton lessHeight;
	
	public RectangleAppFrame(String name, int boundx, int boundy) {
		super();
		setTitle(name);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setFocusable(true);
		this.setResizable(false);
		rectanglePanel = new RectanglePanel(boundx, boundy);
		buttonPanel = new JPanel();
		buttonPanel.setPreferredSize(new Dimension(300, boundy));
		add(rectanglePanel, BorderLayout.WEST);
		add(buttonPanel, BorderLayout.EAST);
		pack();
		
	}
	
	
	@Override
	public void initialize() {
		makeRectangleBtn = new RectangleAppButton("Create rectangle");
		makeRectangleBtn.setRectangleAction(RectangleAction.CREATE);
		deleteRectangleBtn = new RectangleAppButton("Delete rectangle");
		deleteRectangleBtn.setRectangleAction(RectangleAction.DELETE);
		moveLeft = new RectangleAppButton("<<");
		moveLeft.setRectangleAction(RectangleAction.MOVE_LEFT);
		moveRight = new RectangleAppButton(">>");
		moveRight.setRectangleAction(RectangleAction.MOVE_RIGHT);
		moveUp = new RectangleAppButton("up");
		moveUp.setRectangleAction(RectangleAction.MOVE_UP);
		moveDown = new RectangleAppButton("down");
		moveDown.setRectangleAction(RectangleAction.MOVE_DOWN);
		moreWidth = new RectangleAppButton("Width++");
		moreWidth.setRectangleAction(RectangleAction.INCREASE_WIDTH);
		lessWidth = new RectangleAppButton("Width--");
		lessWidth.setRectangleAction(RectangleAction.DECREASE_WIDTH);
		moreHeight = new RectangleAppButton("Height++");
		moreHeight.setRectangleAction(RectangleAction.INCREASE_HEIGHT);
		lessHeight = new RectangleAppButton("Height--");
		lessHeight.setRectangleAction(RectangleAction.DECREASE_HEIGHT);
		
		
		
		buttonPanel.add(makeRectangleBtn);
		buttonPanel.add(deleteRectangleBtn);
		buttonPanel.add(moveLeft);
		buttonPanel.add(moveRight);
		buttonPanel.add(moveUp);
		buttonPanel.add(moveDown);
		buttonPanel.add(moreWidth);
		buttonPanel.add(lessWidth);
		buttonPanel.add(moreHeight);
		buttonPanel.add(lessHeight);
		rectanglePanel.initialize();
		setVisible(true);
	}

	@Override
	public void setRectanle(Rectangle rectangle) {
		rectanglePanel.setRectangle(rectangle);
	}


	@Override
	public void setListenerOnButtons(ActionListener listener) {
		makeRectangleBtn.addActionListener(listener);
		deleteRectangleBtn.addActionListener(listener);
		moveLeft.addActionListener(listener);
		moveRight.addActionListener(listener);
		moveUp.addActionListener(listener);
		moveDown.addActionListener(listener);
		moreWidth.addActionListener(listener);
		lessWidth.addActionListener(listener);
		moreHeight.addActionListener(listener);
		lessHeight.addActionListener(listener);
		
	}


	@Override
	public void update() {
		rectanglePanel.repaint();
	}


	@Override
	public void drawNoRectangleView() {
		makeRectangleBtn.setVisible(true);
		deleteRectangleBtn.setVisible(false);
		moveLeft.setVisible(false);
		moveRight.setVisible(false);
		moveUp.setVisible(false);
		moveDown.setVisible(false);
		moreWidth.setVisible(false);
		lessWidth.setVisible(false);
		moreHeight.setVisible(false);
		lessHeight.setVisible(false);
	}


	@Override
	public void drawWithRectangleView() {
		makeRectangleBtn.setVisible(false);
		deleteRectangleBtn.setVisible(true);
		moveLeft.setVisible(true);
		moveRight.setVisible(true);
		moveUp.setVisible(true);
		moveDown.setVisible(true);
		moreWidth.setVisible(true);
		lessWidth.setVisible(true);
		moreHeight.setVisible(true);
		lessHeight.setVisible(true);
	}


	@Override
	public void startKeyListener(KeyListener listener) {
		addKeyListener(listener);
		requestFocus();
	}
	
	@Override
	public void stopKeyListener(KeyListener listener) {
		removeKeyListener(listener);
		
	}


	@Override
	public void startMouseMotionListener(MouseMotionListener listener) {
		rectanglePanel.addMouseMotionListener(listener);
		
	}

	@Override
	public void stopMouseMotionListener(MouseMotionListener listener) {
		rectanglePanel.removeMouseMotionListener(listener);
		
	}

}
