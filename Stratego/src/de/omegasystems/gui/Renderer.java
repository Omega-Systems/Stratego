package de.omegasystems.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

import de.omegasystems.Board;
import de.omegasystems.BoardSetup;
import de.omegasystems.Move;
import de.omegasystems.Square;
import de.omegasystems.TileState;

public class Renderer extends JFrame implements KeyListener, MouseListener, MouseMotionListener {

	/**
	 * One Square equals 256 pixels
	 */
	private static final long serialVersionUID = 3150993161852184366L;

	Board board;
	int width, height;
	final int lineWidth = 5;

	Point mousePos;
	int highlightedField = -1, targetField = -1;

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
		addMouseMotionListener(this);

		setVisible(true);
		setLocationRelativeTo(null);
	}

	@Override
	public void paint(Graphics xg) {
		// GraphicsEnvironment.getLocalGraphicsEnvironment().

		width = getWidth() - 16;
		height = getHeight() - 39;
		BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR);
		Graphics2D g = bufferedImage.createGraphics();

		xg.translate(8, 31);

		for (int x = 0; x < 10; x++) {
			for (int y = 0; y < 10; y++) {
				int posX = (int) ((((float) width - lineWidth) / 10) * x);
				int posY = (int) ((((float) height - lineWidth) / 10) * (9 - y));
				if (highlightedField == Square.from(x, y) && targetField >= 0)
					g.drawImage(ImageLoader.getImageForPiece(TileState.EMPTY), posX, posY, width / 10, height / 10,
							null);
				else
					g.drawImage(ImageLoader.getImageForPiece(board.getTileState(Square.from(x, y))), posX, posY,
							width / 10, height / 10, null);
			}
		}
		// overlay highlighted Tile
		if (highlightedField >= 0) {
			drawTileOverlay(g, new Color(0.0f, 0.0f, 1.0f, 0.2f), highlightedField);
			if (targetField >= 0) {
				drawTileOverlay(g, new Color(1f, 0f, 0f, 0.2f), targetField);
				Color color = new Color(0.0f, 1f, 1f, 0.2f);
				for (Integer move : board.generateMoves(highlightedField)) {
					drawTileOverlay(g, color, move);
				}
			}
		}

		g.setColor(new Color(15, 8, 15));

		float factor = (((float) width - lineWidth) / 10);
		for (int x = 0; x < 11; x++) {
			g.fillRect((int) (factor * x), 0, lineWidth, height);
		}
		factor = (((float) height - lineWidth) / 10);
		for (int y = 0; y < 11; y++) {
			g.fillRect(0, (int) (factor * y), width, lineWidth);
		}

		if (highlightedField >= 0 && targetField >= 00)
			g.drawImage(ImageLoader.getImageForPiece(board.getTileState(highlightedField)), mousePos.x - 8 - width / 20,
					mousePos.y - 31 - height / 20, width / 10, height / 10, null);

		Graphics2D g2dComponent = (Graphics2D) xg;
		g2dComponent.drawImage(bufferedImage, null, 0, 0);

	}

	void drawTileOverlay(Graphics2D g, Color color, int pos) {
		g.setColor(color);
		g.fillRect((pos % 10) * (width / 10), (9 - (pos / 10)) * (height / 10), (int) (width / 10.0f),
				(int) (height / 10.0f));
	}

	int getFieldFromPos(int x, int y) {
		int fieldX = (int) ((((float) x) - 8f) / width * 10);
		int fieldY = (int) ((((float) y) - 31f) / height * 10);

		return fieldX + (9 - fieldY) * 10;
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

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {
		int newField = getFieldFromPos(e.getX(), e.getY());
		if (highlightedField == newField)
			highlightedField = -1;
		else
			highlightedField = newField;
		repaint();
		/*
		 * mousePos = e.getPoint(); int newField = getFieldFromPos(e.getX(), e.getY());
		 * if (newField == highlightedField) highlightedField = -1; else if
		 * (highlightedField >= 0 && board.getTileState(highlightedField) !=
		 * TileState.EMPTY) { if
		 * (board.generateMoves(highlightedField).contains(newField)) {
		 * board.move(Move.from(highlightedField, newField)); highlightedField = -1; }
		 * else { highlightedField = newField; } } else
		 * if(!board.generateMoves(newField).isEmpty()){ highlightedField = newField; }
		 * repaint();
		 */
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (highlightedField >= 0) {
			if (targetField >= 0 && board.generateMoves(highlightedField).contains(targetField))
				board.move(Move.from(highlightedField, targetField));
			highlightedField = -1;
			targetField = -1;
			repaint();
		}
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		targetField = getFieldFromPos(e.getX(), e.getY());
		if (highlightedField < 0 && !board.generateMoves(targetField).isEmpty())
			highlightedField = getFieldFromPos(e.getX(), e.getY());
		mousePos = e.getPoint();

		repaint();
	}

	@Override
	public void mouseMoved(MouseEvent e) {

	}

}
