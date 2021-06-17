package de.omegasystems.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

import de.omegasystems.Board;
import de.omegasystems.BoardSetup;
import de.omegasystems.Square;

public class Renderer extends JFrame implements KeyListener, MouseListener {

	/**
	 * One Sqaure equals 256 pixels
	 */
	private static final long serialVersionUID = 3150993161852184366L;

	Board board;
	int width, height;
	final int lineWidth = 5;

	int highlightedField;
	
	public static void main(String[] args) {
		Board board = BoardSetup.getTestSetup();
		Renderer renderer = new Renderer("Debug", 1000, 1000, board);
		renderer.repaint();
	}

	public Renderer(String title, int width, int height, Board board) {
		super(title);
		this.board = board;
		this.width = width;
		this.height = height;

		setUndecorated(false);
		setSize(width, height);
		setResizable(true);
		addKeyListener(this);
		addMouseListener(this);
		
		setVisible(true);
		setLocationRelativeTo(null);
	}

	@Override
	public void paint(Graphics xg) {
	    //GraphicsEnvironment.getLocalGraphicsEnvironment().
	    
	    width = getWidth()-16;
	    height = getHeight()-39;
	    BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR);
	    Graphics2D g = bufferedImage.createGraphics();
	    
	    xg.translate(8, 31);
	    
		g.setColor(Color.GRAY);
		g.fillRect(0, 0, width, height);

		g.setColor(Color.cyan);
		g.fillRect(highlightedField%10*256, highlightedField/10*256, width/10, height/10);
		
		g.setColor(new Color(15, 8, 15));
		for (int x = 0; x < 10; x++) {
			for (int y = 0; y < 10; y++) {
				int posX = (int) ((((float) width - lineWidth) / 10) * x);
				int posY = (int) ((((float) height - lineWidth) / 10) * y);
				g.drawImage(ImageLoader.getImageForPiece(board.getTileState(Square.from(x, y))), posX, posY, width/10, height/10, null);
			}
		}

		g.setColor(new Color(15, 8, 15));

		for (int x = 0; x < 11; x++) {
			g.fillRect((int) ((((float) width - lineWidth) / 10) * x), 0, lineWidth, height);
		}
		for (int y = 0; y < 11; y++) {
			g.fillRect(0, (int) ((((float) height - lineWidth) / 10) * y), width, lineWidth);
		}
		
	    Graphics2D g2dComponent = (Graphics2D) xg;
	    g2dComponent.drawImage(bufferedImage, null, 0, 0);  
		
	}

	int getFieldFromPos(int x, int y) {
		float relX = width-lineWidth/10f;
		int fieldX = (int) (x/relX);
		float relY = width-lineWidth/10f;
		int fieldY = (int) (y/relY);
		
		System.out.println("X: "+fieldX+"; Y: "+fieldY);
		
		return fieldX + fieldY * 10;
	}
	


	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_ESCAPE:
			System.exit(69);
			break;

		default:
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		highlightedField = getFieldFromPos(e.getX(), e.getY());
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}
	
}
