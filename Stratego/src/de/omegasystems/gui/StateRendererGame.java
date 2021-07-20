package de.omegasystems.gui;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import de.omegasystems.BoardState;
import de.omegasystems.Move;
import de.omegasystems.Piece;
import de.omegasystems.Square;

public class StateRendererGame extends GameStateRenderer {

	int boardWidth, offsetX, offsetY;
	final int lineWidth = 2;

	private Color backgroundColor = new Color(0.0f, 0.5f, 0.0f), 
			highlightedFieldColor = new Color(0.0f, 1.0f, 0.0f, 0.1f),
			blackedFieldColor = new Color(0f, 0f, 0f, 0.5f), 
			possibleMovesColor = new Color(0.0f, 1f, 1f, 0.2f),
			selectedMoveColor = new Color(0f, 1f, 0f, 0.3f), 
			captureMoveColor = new Color(1f, 0f, 1f, 0.3f);

	Point mousePos;
	int selectedField = -1;
	boolean mousePressed;

	@Override
	public void render(Graphics2D g) {

		boardWidth = Math.min(width, height);
		offsetX = Math.max((width - boardWidth) / 2, 0);
		offsetY = Math.max((height - boardWidth) / 2, 0);

		g.setColor(backgroundColor);
		g.fillRect(0, 0, width, height);

		// xg.translate(8, 32);
		g.translate(offsetX, offsetY);

		ImageLoader.setDesiredTileSize(boardWidth / 10 - lineWidth, boardWidth / 10 - lineWidth);
		
		for (int x = 0; x < 10; x++) {
			for (int y = 0; y < 10; y++) {
				int posX = (int) ((((float) boardWidth - lineWidth) / 10) * x);
				int posY = (int) ((((float) boardWidth - lineWidth) / 10) * (9 - y));
				g.drawImage(ImageLoader.getImageForPiece(board.getTileState(Square.from(x, y))), posX, posY, null);
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
				else if (board.getPiece(de.omegasystems.Color.invert(board.curColor), move) != Piece.NONE)
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
			g.drawImage(ImageLoader.getImageForPiece(board.getTileState(selectedField)),
					mousePos.x - 8 - offsetX - boardWidth / 20, mousePos.y - 32 - offsetY - boardWidth / 20, null);
	}
	

	@Override
	void render(Graphics2D g, int clipX, int clipY, int width, int height) {
		boardWidth = Math.min(width, height);
		offsetX = Math.max((width - boardWidth) / 2, 0);
		offsetY = Math.max((height - boardWidth) / 2, 0);

		// xg.translate(8, 32);
		g.translate(offsetX, offsetY);

		ImageLoader.setDesiredTileSize(boardWidth / 10 - lineWidth, boardWidth / 10 - lineWidth);
		
		for (int x = (clipX-offsetX)/10; x < (clipX-offsetX)/10+Math.ceil(width/10f); x++) {
			for (int y = (clipY-offsetY)/10; y < (clipY-offsetY)/10+Math.ceil(height/10f); x++) {
				int posX = (int) ((((float) boardWidth - lineWidth) / 10) * x);
				int posY = (int) ((((float) boardWidth - lineWidth) / 10) * (9 - y));
				g.drawImage(ImageLoader.getImageForPiece(board.getTileState(Square.from(x, y))), posX, posY, null);
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
				else if (board.getPiece(de.omegasystems.Color.invert(board.curColor), move) != Piece.NONE)
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
			g.drawImage(ImageLoader.getImageForPiece(board.getTileState(selectedField)),
					mousePos.x - 8 - offsetX - boardWidth / 20, mousePos.y - 32 - offsetY - boardWidth / 20, null);
	}

	void drawTileOverlay(Graphics2D g, Color color, int pos) {
		g.setColor(color);
		int rectWidth = (int) (boardWidth / 10.0f) - (lineWidth * 2);
		g.fillRect((int) (((pos % 10) * (boardWidth / 10.0f)) + (lineWidth / 2f)),
				(int) (((9 - (pos / 10)) * (boardWidth / 10.0f)) + (lineWidth / 2f)), rectWidth, rectWidth);
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
	public WindowState getNextWindowState() {
		return board.getBoardState() == BoardState.INGAME ? WindowState.GAME : WindowState.RESULT;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		mousePressed = true;
		mousePos = e.getPoint();
		int newField = getFieldFromPos(e.getX(), e.getY());
		if (Piece.isMoveable(board.getPiece(board.curColor, newField))) {
			selectedField = newField;
		} else if (selectedField >= 0 && board.generateMoves(selectedField).contains(newField)) {
			board.move(Move.from(selectedField, newField));
			selectedField = -1;
		} else
			selectedField = -1;
		frame.repaint();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		mousePressed = false;
		int newField = getFieldFromPos(e.getX(), e.getY());
		if (selectedField >= 0 && newField != selectedField && board.generateMoves(selectedField).contains(newField)) {
			board.move(Move.from(selectedField, newField));
			selectedField = -1;
		}
		frame.repaint();
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		int x = Math.min(mousePos.x, e.getX())-ImageLoader.getTileSizeX();
		int y = Math.min(mousePos.y, e.getY())-ImageLoader.getTileSizeY();
		int width = Math.abs(mousePos.x-e.getX())+ImageLoader.getTileSizeX()*2;
		int height = Math.abs(mousePos.y-e.getY())+ImageLoader.getTileSizeY()*2;
		mousePos = e.getPoint();
		frame.repaint(x, y, width, height);
	}

	@Override
	public void mouseMoved(MouseEvent e) {

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
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

	@Override
	void init() {
		board.setBoardState(BoardState.INGAME);
	}
}
