package de.omegasystems;

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

	public TileState getTileState(int sq) {
		if (Square.isLake(sq)) return TileState.LAKE;
		
		int redPiece = redPieces[sq];
		int bluePiece = bluePieces[sq];
		
		if (redPiece != Piece.NONE) return TileState.RED_PIECES[redPiece];
		if (bluePiece != Piece.NONE) return TileState.BLUE_PIECES[bluePiece];
		return TileState.EMPTY;
	}

	public int getPiece(Color color, int pos) {
		int[] myPieces = color == Color.RED ? redPieces : bluePieces;
		return myPieces[pos];
	}

	public void setPiece(Color color, int piece, int pos) {
		int[] myPieces = color == Color.RED ? redPieces : bluePieces;
		myPieces[pos] = piece;
	}
	
	public BoardState getBoardState() {
		return this.state;
	}
	
	public void setBoardState(BoardState boardState) {
		this.state = boardState;
	}

	public void move(int move) {
		int[] myPieces = this.curColor == Color.RED ? redPieces : bluePieces;
		int[] enemyPieces = this.curColor == Color.RED ? bluePieces : redPieces;

		if (moveCount == 1024) state = BoardState.DRAW;
		
		int fromSq = Move.getFrom(move);
		int toSq = Move.getTo(move);
		int myPiece = myPieces[fromSq];
		int enemyPiece = enemyPieces[toSq];
		
		// Find capture result
		int captureResult = 0; // -1: Defender wins; 0: Both die; 1: Attacker wins;
		if (myPiece == Piece.SPY && enemyPiece == Piece.RANK1) captureResult = 1;
		else if (myPiece == Piece.RANK8 && enemyPiece == Piece.BOMB) captureResult = 1;
		else if (myPiece > enemyPiece) captureResult = 1;
		else if (myPiece == enemyPiece) captureResult = 0;
		else if (myPiece < enemyPiece) captureResult = -1;
		
		// Execute capture
		myPieces[toSq] = captureResult == 1 ? myPiece : Piece.NONE;
		enemyPieces[toSq] = captureResult == -1 ? enemyPiece : Piece.NONE;
		myPieces[fromSq] = Piece.NONE;
		
		boolean redHasPieces = false;
		boolean blueHasPieces = false;
		for (int i = 0; i < 100; i++) {
			if (Piece.isMoveable(redPieces[i])) redHasPieces = true;
			if (Piece.isMoveable(bluePieces[i])) blueHasPieces = true;
		}
		
		if (redHasPieces && !blueHasPieces) state = BoardState.VICTORY_RED;
		if (!redHasPieces && blueHasPieces) state = BoardState.VICTORY_BLUE;
		if (!redHasPieces && !blueHasPieces) state = BoardState.DRAW;
		
		//Win if the captured piece is the enemy flag
		if (enemyPiece == Piece.FLAG) state = BoardState.getVictory(curColor);
		
		// Switch color and increment move counter
		curColor = curColor == Color.BLUE ? Color.RED : Color.BLUE;
		moveCount++;
	}

	public List<Integer> generateMoves(int sq) {
		List<Integer> moves = new ArrayList<Integer>();
		int[] enemyPieces = this.curColor == Color.RED ? bluePieces : redPieces;
		int[] myPieces = this.curColor == Color.RED ? redPieces : bluePieces;
		int piece = myPieces[sq];

		// Return the empty list if no piece or an immovable piece is on the tile
		if (piece == Piece.NONE || piece == Piece.FLAG || piece == Piece.BOMB) return moves;

		// Generate moves for the 'scout'
		if (piece == Piece.RANK9) {
			int curSq = sq;
			while (!Square.isNorthEdge(curSq)) {
				curSq += Direction.NORTH;
				if (myPieces[curSq] != Piece.NONE || Square.isLake(curSq)) break;
				moves.add(curSq);
				if (enemyPieces[curSq] != Piece.NONE) break;
			}
			
			curSq = sq;
			while (!Square.isEastEdge(curSq)) {
				curSq += Direction.EAST;
				if (myPieces[curSq] != Piece.NONE || Square.isLake(curSq)) break;
				moves.add(curSq);
				if (enemyPieces[curSq] != Piece.NONE) break;
			}
			
			curSq = sq;
			while (!Square.isSouthEdge(curSq)) {
				curSq += Direction.SOUTH;
				if (myPieces[curSq] != Piece.NONE || Square.isLake(curSq)) break;
				moves.add(curSq);
				if (enemyPieces[curSq] != Piece.NONE) break;
			}
			
			curSq = sq;
			while (!Square.isWestEdge(curSq)) {
				curSq += Direction.WEST;
				if (myPieces[curSq] != Piece.NONE || Square.isLake(curSq)) break;
				moves.add(curSq);
				if (enemyPieces[curSq] != Piece.NONE) break;
			}
			return moves;
		}
		
		// Generate moves
		int newSq = sq + Direction.NORTH;
		if (!Square.isNorthEdge(sq) && !Square.isLake(newSq) && myPieces[newSq] == Piece.NONE) moves.add(newSq);
		
		newSq = sq + Direction.EAST;
		if (!Square.isEastEdge(sq) && !Square.isLake(newSq) && myPieces[newSq] == Piece.NONE) moves.add(newSq);
		
		newSq = sq + Direction.SOUTH;
		if (!Square.isSouthEdge(sq) && !Square.isLake(newSq) && myPieces[newSq] == Piece.NONE) moves.add(newSq);
		
		newSq = sq + Direction.WEST;
		if (!Square.isWestEdge(sq) && !Square.isLake(newSq) && myPieces[newSq] == Piece.NONE) moves.add(newSq);

		return moves;
	}
}
