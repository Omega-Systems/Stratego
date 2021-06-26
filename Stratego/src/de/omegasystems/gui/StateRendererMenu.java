package de.omegasystems.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import de.omegasystems.BoardSetup;

public class StateRendererMenu extends GameStateRenderer implements Runnable{

	boolean clicked = false;
	int curColor;
	
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
	void render(Graphics2D g, int x, int y, int width, int height) {
		render(g);
	}

	@Override
	void render(Graphics2D g) {
		g.drawImage(ImageLoader.mainImage, 0, 0, width, height, null);
		g.setColor(Color.getHSBColor(curColor/100f, 1, 1));
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
		new Thread(this).start();
	}

	@Override
	public void run() {
		while(true) {
			if(frame.currentGameStateRenderer!=this) return;
			curColor++;
			frame.repaint();
			try {
				Thread.sleep(30);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}		
	}
	
}
