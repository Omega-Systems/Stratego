package com.omegasystems.de;

public class BoardSetup {
	public static Board getTestSetup() {
		Board board = new Board();
		board.setPiece(Color.RED, Piece.FLAG, Square.from("a0"));
		board.setPiece(Color.RED, Piece.RANK9, Square.from("a1"));
		board.setPiece(Color.BLUE, Piece.FLAG, Square.from("j0"));
		board.setPiece(Color.BLUE, Piece.RANK9, Square.from("j1"));
		return board;
	}
}
