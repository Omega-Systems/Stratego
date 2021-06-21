package de.omegasystems.gui;

import java.awt.Color;
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
import de.omegasystems.BoardState;

public class Renderer extends JFrame implements KeyListener, MouseListener, MouseMotionListener {

	/**
	 * One Square equals 256 pixels
	 */
	private static final long serialVersionUID = 3150993161852184366L;

	private GameStateRenderer currentGameStateRenderer;
	private WindowState currentState;

	private int width, height;

	public static void main(String[] args) {
		Board board = BoardSetup.getTestSetup();
		Renderer renderer = new Renderer("Debug", 1000, 1000, board);
		renderer.repaint();
	}

	public Renderer(String title, int width, int height, Board board) {
		super(title);
		this.width = width;
		this.height = height;

		currentState = WindowState.MAIN_MENU;

		this.currentGameStateRenderer = new StateRendererMenu();
		currentGameStateRenderer.frame = this;
		currentGameStateRenderer.board = board;

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
		currentGameStateRenderer.width = width - 16;
		currentGameStateRenderer.height = height - 39;

		BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR);
		Graphics2D g = bufferedImage.createGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, width, height);
		
		currentGameStateRenderer.render(g);

		xg.translate(8, 31);
		Graphics2D g2dComponent = (Graphics2D) xg;
		g2dComponent.drawImage(bufferedImage, null, 0, 0);

		WindowState windowState = currentGameStateRenderer.getNextWindowState();
		if (windowState != currentState)
			updateGameStateRenderer(windowState);
	}

	private void updateGameStateRenderer(WindowState state) {
		switch (state) {

		case MAIN_MENU:
			currentGameStateRenderer = new StateRendererMenu(); 
			break;
		case GAME:
			currentGameStateRenderer = new StateRendererGame();
			currentGameStateRenderer.board = BoardSetup.getTestSetup();
			currentGameStateRenderer.board.setBoardState(BoardState.INGAME);
			break;
		default: 
		}
		currentGameStateRenderer.frame = this;
		currentState = state;
		repaint();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
			System.exit(69);
		currentGameStateRenderer.keyPressed(e);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		currentGameStateRenderer.keyReleased(e);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		currentGameStateRenderer.keyTyped(e);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		currentGameStateRenderer.mouseClicked(e);
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		currentGameStateRenderer.mouseEntered(e);
	}

	@Override
	public void mouseExited(MouseEvent e) {
		currentGameStateRenderer.mouseExited(e);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		currentGameStateRenderer.mousePressed(e);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		currentGameStateRenderer.mouseReleased(e);
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		currentGameStateRenderer.mouseDragged(e);
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		currentGameStateRenderer.mouseMoved(e);
	}

}
