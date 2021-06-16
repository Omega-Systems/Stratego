package de.omegasystems;

public class BoardSetup {
	public static Board getTestSetup() {
		Board board = new Board();
		board.setPiece(Color.RED, Piece.FLAG, Square.from("a0"));
		board.setPiece(Color.RED, Piece.RANK9, Square.from("b3"));
		
		board.setPiece(Color.BLUE, Piece.FLAG, Square.from("a9"));
		board.setPiece(Color.BLUE, Piece.RANK9, Square.from("b6"));
		return board;
	}
}
