package de.omegasystems;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class Board {
	public Color curColor = Color.startingColor;
	private int[] moveList = new int[1024];
	private int moveCount = 0;
	private BoardState state = BoardState.SETUP;

	private int[] redPieces = new int[10 * 10];
	private int[] bluePieces = new int[10 * 10];

	public Board() {

	}

	@Override
	public String toString() {
		String str = new String(new char[22]).replace("\0", "=") + "\n"
				+ String.format("Move %-6s Turn %s", curColor.toString(), moveCount) + "\n"
				+ new String(new char[22]).replace("\0", "-") + "\n";
		for (int y = 9; y >= 0; y--) {
			str += (char) (y + '0') + " ";
			for (int x = 0; x < 10; x++) {
				int pos = x + y * 10;
				String redRepr = Piece.getRepr(redPieces[pos]);
				String blueRepr = Piece.getRepr(bluePieces[pos]);
				redRepr = redRepr == " " ? "" : "r" + redRepr;
				blueRepr = blueRepr == " " ? "" : "b" + blueRepr;
				String repr = redRepr + blueRepr;
				str += !repr.isEmpty() ? repr : ". ";
			}
			str += "\n";
		}
		str += "  A B C D E F G H I J " + "\n" + new String(new char[22]).replace("\0", "=");
		return str;
	}

	public TileState getTileState(int pos) {

		if(redPieces[pos]!=Piece.NONE) return TileState.RED_PIECES[redPieces[pos]];
		else if(bluePieces[pos]!=Piece.NONE) return TileState.BLUE_PIECES[bluePieces[pos]];
		else return TileState.EMPTY;
		
//		if (redPieces[pos] == Piece.NONE && bluePieces[pos] == Piece.NONE) {
//			return TileState.EMPTY;
//		}
//
//		return TileState.RED_FLAG;
	}

	public int getPiece(Color color, int pos) {
		int[] myPieces = color == Color.RED ? redPieces : bluePieces;
		return myPieces[pos];
	}

	public void setPiece(Color color, int piece, int pos) {
		int[] myPieces = color == Color.RED ? redPieces : bluePieces;
		myPieces[pos] = piece;
	}

	public void move(int move) {
		int[] myPieces = this.curColor == Color.RED ? redPieces : bluePieces;
		int[] enemyPieces = this.curColor == Color.RED ? bluePieces : redPieces;

		if (moveCount == 1024) {
			state = BoardState.DRAW;
		}

		curColor = curColor == Color.BLUE ? Color.RED : Color.BLUE;
		
		int myPiece = myPieces[Move.getFrom(move)];
		int enemyPiece = enemyPieces[Move.getTo(move)];

		if (enemyPiece == Piece.FLAG)
			state = curColor == Color.RED ? BoardState.VICTORY_RED : BoardState.VICTORY_BLUE;

		if (myPiece == Piece.SPY && enemyPiece == Piece.RANK1) {
			myPieces[Move.getTo(move)] = myPieces[Move.getFrom(move)];
			enemyPieces[Move.getTo(move)] = Piece.NONE;
			myPieces[Move.getFrom(move)] = Piece.NONE;
		}

		if (myPiece == Piece.RANK8 && enemyPiece == Piece.BOMB) {
			myPieces[Move.getTo(move)] = myPieces[Move.getFrom(move)];
			enemyPieces[Move.getTo(move)] = Piece.NONE;
			myPieces[Move.getFrom(move)] = Piece.NONE;
		}

		if (myPiece == enemyPiece) { // Both pieces are the same
			enemyPieces[Move.getTo(move)] = Piece.NONE;
			myPieces[Move.getFrom(move)] = Piece.NONE;
		}

		if (myPiece > enemyPiece) { // My piece is stronger
			myPieces[Move.getTo(move)] = myPieces[Move.getFrom(move)];
			enemyPieces[Move.getTo(move)] = Piece.NONE;
			myPieces[Move.getFrom(move)] = Piece.NONE;
		}

		if (myPiece < enemyPiece) { // Enemy piece is stronger
			myPieces[Move.getFrom(move)] = Piece.NONE;
		}
	}

	public List<Integer> generateMoves(int field) {
		List<Integer> list = new ArrayList<>();

		Color color = redPieces[field]!=Piece.NONE ? Color.RED : Color.BLUE;
		
		int[] enemyPieces = this.curColor == Color.RED ? bluePieces : redPieces;
		int[] myPieces = this.curColor == Color.RED ? redPieces : bluePieces;
		int piece = myPieces[field];
		Point p = Square.toPoint(field);

		// Return the empty list if no piece is on the tile
		if (piece == Piece.NONE)
			return list;

		// Generate moves for the 'tower'
		if (piece == Piece.RANK9) {
			for (int x = p.x+1; x < 10; x++) {
				int neighbour = Square.from(x, p.y);
				if (myPieces[neighbour] != Piece.NONE)
					break;
				list.add(neighbour);
				if (enemyPieces[neighbour] != Piece.NONE)
					break;
			}
			for (int x = p.x-1; x >= 0; x--) {
				int neighbour = Square.from(x, p.y);
				if (myPieces[neighbour] != Piece.NONE)
					break;
				list.add(neighbour);
				if (enemyPieces[neighbour] != Piece.NONE)
					break;
			}
			for (int y = p.y+1; y < 10; y++) {
				int neighbour = Square.from(p.x, y);
				if (myPieces[neighbour] != Piece.NONE)
					break;
				list.add(neighbour);
				if (enemyPieces[neighbour] != Piece.NONE)
					break;
			}
			for (int y = p.y-1; y >= 0; y--) {
				int neighbour = Square.from(p.x, y);
				if (myPieces[neighbour] != Piece.NONE)
					break;
				list.add(neighbour);
				if (enemyPieces[neighbour] != Piece.NONE)
					break;
			}
		} else if (piece != Piece.FLAG && piece != Piece.BOMB) {
			int neighbour = Square.from(p.x-1, p.y);
			if(neighbour<100 && neighbour>=0 && myPieces[neighbour]==Piece.NONE) 
				list.add(neighbour);
			neighbour = Square.from(p.x+1, p.y);
			if(neighbour<100 && neighbour>=0 && myPieces[neighbour]==Piece.NONE) 
				list.add(neighbour);
			neighbour = Square.from(p.x, p.y-1);
			if(neighbour<100 && neighbour>=0 && myPieces[neighbour]==Piece.NONE) 
				list.add(neighbour);
			neighbour = Square.from(p.x, p.y+1);
			if(neighbour<100 && neighbour>=0 && myPieces[neighbour]==Piece.NONE) 
				list.add(neighbour);	
		}

		return list;
	}
	
}
