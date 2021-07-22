package de.omegasystems.gui;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import javax.sound.sampled.Clip;
import javax.swing.JPanel;

import de.omegasystems.BoardSetup;

public class StateRendererMenu extends GameStateRenderer {

	boolean clicked = false;
	int curColor;
	ButtonRenderer menuButtons;
	JPanel testPanel;
	
	static Clip selectClip, deselectClip, pressClip;

	
	public StateRendererMenu() {
		menuButtons = new ButtonRenderer(100, 100, 200, 800);

		menuButtons.alignBox = false;
		
		menuButtons.addButton(new Button("Start Game") {
			
			@Override
			void pressed() {
				clicked = true;
			}
		});
		menuButtons.addButton(new Button("Quit") {
			
			@Override
			void pressed() {
				System.exit(0);
			}
		});
		
	}
	
	@Override
	public void mouseDragged(MouseEvent arg0) {
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		menuButtons.mouseMove(e.getX(), e.getY());
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		menuButtons.mousePress(e.getX(), e.getY());
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		
	}
	
	@Override
	void render(Graphics2D g, int x, int y, int width, int height) {
		render(g);
	}

	@Override
	void render(Graphics2D g) {
		g.drawImage(ImageLoader.mainImage, 0, 0, width, height, null);
		g.setColor(Color.getHSBColor(curColor/100f, 1, 1));
		menuButtons.render(g);
	}
	
	@Override
	WindowState getNextWindowState() {
		return clicked ? WindowState.SETUP : WindowState.MAIN_MENU;
	}

	@Override
	void init() {
		board = BoardSetup.getStandardSetup();
		menuButtons.frame = frame;
	}
}
