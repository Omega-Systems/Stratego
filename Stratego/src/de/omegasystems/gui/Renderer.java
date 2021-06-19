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
import de.omegasystems.Piece;
import de.omegasystems.Square;

public class Renderer extends JFrame implements KeyListener, MouseListener, MouseMotionListener {

	/**
	 * One Square equals 256 pixels
	 */
	private static final long serialVersionUID = 3150993161852184366L;

	Board board;
	int width, height, boardWidth, offsetX, offsetY;
	final int lineWidth = 2;

	private Color backgroundColor = Color.BLACK, 
			highlightedFieldColor = new Color(0.0f, 1.0f, 0.0f, 0.1f),
			blackedFieldColor = new Color(0f, 0f, 0f, 0.5f),
			possibleMovesColor = new Color(0.0f, 1f, 1f, 0.2f),
			selectedMoveColor = new Color(0f, 1f, 0f, 0.3f),
			captureMoveColor = new Color(1f, 0f, 1f, 0.3f);
	
	Point mousePos;
	int selectedField = -1;
	boolean mousePressed;
	
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
		
		boardWidth = Math.min(width, height);
		offsetX = Math.max((width-boardWidth)/2, 0);
		offsetY = Math.max((height-boardWidth)/2, 0);
		
		BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR);
		Graphics2D g = bufferedImage.createGraphics();
		
		g.setColor(backgroundColor);
		g.fillRect(0, 0, width, height);
		
		
		xg.setColor(backgroundColor);
		xg.fillRect(0, 0, width, height);
		xg.translate(8, 32);
		xg.translate(offsetX, offsetY);

		
		
		
		for (int x = 0; x < 10; x++) {
			for (int y = 0; y < 10; y++) {
				int posX = (int) ((((float) boardWidth - lineWidth) / 10) * x);
				int posY = (int) ((((float) boardWidth - lineWidth) / 10) * (9 - y));
					g.drawImage(ImageLoader.getImageForPiece(board.getTileState(Square.from(x, y))), posX, posY,
							boardWidth / 10 - lineWidth, boardWidth / 10 - lineWidth, null);
					if (mousePressed && selectedField == Square.from(x, y))
						drawTileOverlay(g, blackedFieldColor, selectedField);
			}
		}
		// overlay highlighted Tile
		if (selectedField >= 0) {
			drawTileOverlay(g, highlightedFieldColor, selectedField);
			int targetField = getFieldFromPos(mousePos.x, mousePos.y);
				// drawTileOverlay(g, new Color(1f, 0f, 0f, 0.2f), targetField);
				for (Integer move : board.generateMoves(selectedField)) {
					if (move == targetField)
						drawTileOverlay(g, selectedMoveColor, move);
					else if(board.getPiece(de.omegasystems.Color.invert(board.curColor), move) != Piece.NONE)
						drawTileOverlay(g, captureMoveColor, move);
					else 
						drawTileOverlay(g, possibleMovesColor, move);
				}
		}

		g.setColor(backgroundColor);

		float factor = (((float) boardWidth - lineWidth) / 10);
		for (int x = 0; x < 11; x++) {
			g.fillRect((int) (factor * x), 0, lineWidth, boardWidth);
		}
		factor = (((float) boardWidth - lineWidth) / 10);
		for (int y = 0; y < 11; y++) {
			g.fillRect(0, (int) (factor * y), boardWidth, lineWidth);
		}

		if (mousePressed && selectedField >= 0)
			g.drawImage(ImageLoader.getImageForPiece(board.getTileState(selectedField)), mousePos.x - 8 - offsetX - boardWidth / 20,
					mousePos.y - 32 - offsetY - boardWidth / 20, boardWidth / 10 - lineWidth, boardWidth / 10 - lineWidth, null);

		Graphics2D g2dComponent = (Graphics2D) xg;
		g2dComponent.drawImage(bufferedImage, null, 0, 0);
	}

	void drawTileOverlay(Graphics2D g, Color color, int pos) {
		g.setColor(color);
		int rectWidth = (int) (boardWidth / 10.0f)-lineWidth;
		g.fillRect((pos % 10) * (boardWidth / 10), (9 - (pos / 10)) * (boardWidth / 10), rectWidth, rectWidth);
	}

	int getFieldFromPos(int x, int y) {
		int fieldX = (int) ((((float) x) - 8f - offsetX) / boardWidth * 10);
		int fieldY = (int) ((((float) y) - 31f - offsetY) / boardWidth * 10);

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
		mousePressed = true;
		mousePos = e.getPoint();
		int newField = getFieldFromPos(e.getX(), e.getY());
		if (board.getPiece(board.curColor, newField) != Piece.NONE) {
			selectedField = newField;
		} else if (selectedField >= 0 && board.generateMoves(selectedField).contains(newField)) {
			board.move(Move.from(selectedField, newField));
			selectedField = -1;
		}
		else selectedField = -1;
		repaint();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		mousePressed = false;
		int newField = getFieldFromPos(e.getX(), e.getY());
		if(selectedField >= 0 && newField != selectedField && board.generateMoves(selectedField).contains(newField)) {
			board.move(Move.from(selectedField, newField));
			selectedField = -1;
		}
		repaint();
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		mousePos = e.getPoint();
		repaint();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
	}

}
