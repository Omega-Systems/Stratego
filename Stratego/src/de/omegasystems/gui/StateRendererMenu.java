package de.omegasystems.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import javax.sound.sampled.Clip;

import de.omegasystems.BoardSetup;

public class StateRendererMenu extends GameStateRenderer implements Runnable{

	boolean clicked = false;
	int curColor;
	ButtonRenderer menuButtons;
	
	static Clip selectClip, deselectClip, pressClip;

	
	private Rectangle button = new Rectangle(100, 100, 100, 20);
	private boolean isInButton = false;
	
	public StateRendererMenu() {
		menuButtons = new ButtonRenderer(100, 100, 200, 800);
		menuButtons.alignBox = true;
		menuButtons.dynamicSizeY = true;
		menuButtons.borderDistanceX = 2;
		menuButtons.borderDistanceY = 4;
		menuButtons.buttonDistanceY = 4;
		menuButtons.addButton(new Button("Start Game") {
			
			@Override
			void released() {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			void press() {
				// TODO Auto-generated method stub
				
			}
		});
		menuButtons.addButton(new Button("Quit") {
			
			@Override
			void released() {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			void press() {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	@Override
	public void mouseDragged(MouseEvent arg0) {
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		if(e.getX()-8-button.x>=0 && e.getX()-8-button.x<button.width &&
				e.getY()-31-button.y>=0 && e.getY()-31-button.y<button.height) {
			if(!isInButton) {
				isInButton = true;
				frame.repaint(button.x-8, button.y-31, button.width, button.height);
			}
		}  else if(isInButton) {
			isInButton = false;
			frame.repaint(button.x-8, button.y-31, button.width, button.height);
		}
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		if(isInButton) {
			clicked = true;
			frame.repaint();
		}
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
		renderFont("https://toastarmy.eu/merch", 20, width/2, height/2+20, g);
		menuButtons.render(g);
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
		board = BoardSetup.getStandardSetup();
		new Thread(this).start();
	}

	@Override
	public void run() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		while(true) {
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
