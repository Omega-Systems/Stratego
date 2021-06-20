package de.omegasystems.gui;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

import de.omegasystems.Board;
import de.omegasystems.BoardSetup;

public class Renderer extends JFrame implements KeyListener, MouseListener, MouseMotionListener {

	/**
	 * One Square equals 256 pixels
	 */
	private static final long serialVersionUID = 3150993161852184366L;

	Board board;

	private GameStateRenderer gameStateRenderer;
	private int width, height;
	
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

		this.gameStateRenderer = new GameStateRendererGame();
		gameStateRenderer.frame = this;
		gameStateRenderer.board = board;
		
		setUndecorated(false);
		setSize(width, height);
		setResizable(true);

		addKeyListener(this);
		addMouseListener(this);
		addMouseMotionListener(this);

		setVisible(true);
		setLocationRelativeTo(null);
	}

	@Override
	public void paint(Graphics xg) {
		width = getWidth();
		height = getHeight();
		gameStateRenderer.width = width - 16;
		gameStateRenderer.height = height - 39;
		
		BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR);
		Graphics2D g = bufferedImage.createGraphics();
		
		gameStateRenderer.render(g);
		
		xg.translate(8, 31);
		Graphics2D g2dComponent = (Graphics2D) xg;
		g2dComponent.drawImage(bufferedImage, null, 0, 0);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ESCAPE)
			System.exit(69);
		gameStateRenderer.keyPressed(e);
	}

	@Override
	public void keyReleased(KeyEvent e) {gameStateRenderer.keyReleased(e);}

	@Override
	public void keyTyped(KeyEvent e) {gameStateRenderer.keyTyped(e);}

	@Override
	public void mouseClicked(MouseEvent e) {gameStateRenderer.mouseClicked(e);}

	@Override
	public void mouseEntered(MouseEvent e) {gameStateRenderer.mouseEntered(e);}

	@Override
	public void mouseExited(MouseEvent e) {gameStateRenderer.mouseExited(e);}

	@Override
	public void mousePressed(MouseEvent e) {gameStateRenderer.mousePressed(e);}

	@Override
	public void mouseReleased(MouseEvent e) {gameStateRenderer.mouseReleased(e);}

	@Override
	public void mouseDragged(MouseEvent e) {gameStateRenderer.mouseDragged(e);}

	@Override
	public void mouseMoved(MouseEvent e) {gameStateRenderer.mouseMoved(e);}

}
