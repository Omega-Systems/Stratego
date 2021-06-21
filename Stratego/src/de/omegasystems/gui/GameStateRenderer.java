package de.omegasystems.gui;

import java.awt.Graphics2D;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;

import de.omegasystems.Board;

public abstract class GameStateRenderer implements MouseMotionListener, MouseListener, KeyListener{

	Board board;
	int width, height;
	JFrame frame;
	
	/**
	 * Renders the desired gamestate onto the canvas
	 * @param g the graphics to draw on
	 */
	abstract void render(Graphics2D g);
	
	/**
	 * Returns the windowState that should be rendered next.<br><br>
	 * This method is used to communicate state changes to the renderer by returning the desired State at a certain condition 
	 * or keeping the state by returning the current state. 
	 * This method is called after each {@link #render(Graphics2D)} call and determines which {@link GameStateRenderer} is desired for the next I/O.
	 * <br>If the state is not the current state, the next StateRenderer gets called directly.
	 * @return
	 */
	abstract WindowState getNextWindowState();
	
}
