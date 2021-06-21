package de.omegasystems.gui;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class StateRendererResults extends GameStateRenderer{

	boolean clicked = false;
	
	@Override
	public void mouseDragged(MouseEvent arg0) {
		
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		clicked = true;
		frame.repaint();
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
	void render(Graphics2D g) {
		switch (board.getBoardState()) {
		case DRAW:
			g.setColor(Color.GRAY);
			g.drawString("Draw", width/2, height/2);
			break;

		case VICTORY_BLUE:
			g.setColor(Color.BLUE);
			g.drawString("Blue is victorious", width/2, height/2);
			break;
			
		case VICTORY_RED:
			g.setColor(Color.RED);
			g.drawString("Red is victorious", width/2, height/2);
			break;
			
		default:
			break;
		}
//		g.drawImage(ImageLoader.mainImage, 0, 0, width, height, null);
	}

	@Override
	WindowState getNextWindowState() {
		return clicked ? WindowState.MAIN_MENU : WindowState.RESULT;
	}

	@Override
	void init() {
	}

	
	
}
