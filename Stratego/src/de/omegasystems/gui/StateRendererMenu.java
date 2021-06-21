package de.omegasystems.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.Random;

import de.omegasystems.BoardSetup;

public class StateRendererMenu extends GameStateRenderer{

	boolean clicked = false;
	
	@Override
	public void mouseDragged(MouseEvent arg0) {
		
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		frame.repaint();
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
		g.drawImage(ImageLoader.mainImage, 0, 0, width, height, null);
		g.setColor(new Color(new Random().nextInt(100000000)));
		renderFont("Kauft meinen Merch!", 50, width/2, height/2-20, g);
		//g.setColor(Color.BLACK);
		renderFont("https://toastarmy.eu/merch", 20, width/2, height/2+20, g);
	}

	private void renderFont(String string, int size, int x, int y, Graphics2D g) {
		g.setFont(new Font("TimesRoman", Font.BOLD, size));
		FontMetrics metrics = g.getFontMetrics();
	    int nx = x - (metrics.stringWidth(string) / 2);
	    int ny = y - (metrics.getHeight() / 2) + metrics.getAscent();
	    
		g.drawString(string, nx, ny);
	}
	
	@Override
	WindowState getNextWindowState() {
		return clicked ? WindowState.GAME : WindowState.MAIN_MENU;
	}

	@Override
	void init() {
		board = BoardSetup.getTestSetup();
	}

	
	
}
