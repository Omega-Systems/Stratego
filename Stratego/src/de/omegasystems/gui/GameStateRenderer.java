package de.omegasystems.gui;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import de.omegasystems.Board;

public abstract class GameStateRenderer implements MouseMotionListener, MouseListener, KeyListener{

	Board board;
	int width, height;
	Renderer frame;
	
	/**
	 * Renders the desired {@link WindowState} onto the canvas
	 * @param g the graphics to draw on
	 */
	abstract void render(Graphics2D g);
	
	/**
	 * Renders the desired {@link WindowState} onto the canvas.<br>This method may call {@link #render(Graphics2D)} if there is no partial rendering implementation.
	 * @param g the graphics to draw on
	 * @param x the left bound of the redraw area
	 * @param y the upper bound of the redraw area
	 * @param width the width of the area to be redrawn
	 * @param height the height of the area to be redrawn
	 */
	abstract void render(Graphics2D g, int x, int y, int width, int height);
	
	/**
	 * Called after every property is set on the StateRenderer
	 */
	abstract void init();
	
	/**
	 * Returns the windowState that should be rendered next.<br><br>
	 * This method is used to communicate state changes to the renderer by returning the desired State at a certain condition 
	 * or keeping the state by returning the current state. 
	 * This method is called after each {@link #render(Graphics2D)} call and determines which {@link GameStateRenderer} is desired for the next I/O.
	 * <br>If the state is not the current state, the next StateRenderer gets called directly.
	 * @return
	 */
	abstract WindowState getNextWindowState();
	
	public static final GameStateRenderer EMPTY_RENDERER = new GameStateRenderer() {
		
		@Override
		public void keyTyped(KeyEvent e) {
			
		}
		
		@Override
		public void keyReleased(KeyEvent e) {
			
		}
		
		@Override
		public void keyPressed(KeyEvent e) {
			
		}
		
		@Override
		public void mouseReleased(MouseEvent e) {
			
		}
		
		@Override
		public void mousePressed(MouseEvent e) {
			
		}
		
		@Override
		public void mouseExited(MouseEvent e) {
			
		}
		
		@Override
		public void mouseEntered(MouseEvent e) {
			
		}
		
		@Override
		public void mouseClicked(MouseEvent e) {
			
		}
		
		@Override
		public void mouseMoved(MouseEvent e) {
			
		}
		
		@Override
		public void mouseDragged(MouseEvent e) {
			
		}
		
		@Override
		void render(Graphics2D g) {
			
		}
		
		@Override
		void init() {
			
		}
		
		@Override
		WindowState getNextWindowState() {
			return null;
		}

		@Override
		void render(Graphics2D g, int x, int y, int width, int height) {
			// TODO Auto-generated method stub
			
		}
	};
}
