package de.omegasystems;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class BoardSetup {
	private static final File WORKING_DIR = new File(System.getProperty("user.dir")+"/");
	
	public static Board getTestSetup() {
		Board board = new Board();
		board.setPiece(Color.RED, Piece.FLAG, Square.from("a0"));
		board.setPiece(Color.RED, Piece.RANK9, Square.from("b3"));
		board.setPiece(Color.RED, Piece.RANK8, Square.from("c3"));
		board.setPiece(Color.RED, Piece.RANK7, Square.from("d3"));
		board.setPiece(Color.RED, Piece.RANK6, Square.from("e3"));
		board.setPiece(Color.RED, Piece.RANK5, Square.from("f3"));
		board.setPiece(Color.RED, Piece.RANK4, Square.from("g3"));
		board.setPiece(Color.RED, Piece.RANK3, Square.from("h3"));
		board.setPiece(Color.RED, Piece.RANK2, Square.from("i3"));
		board.setPiece(Color.RED, Piece.RANK1, Square.from("j3"));
		board.setPiece(Color.RED, Piece.SPY, Square.from("i1"));
		
		board.setPiece(Color.BLUE, Piece.FLAG, Square.from("a9"));
		board.setPiece(Color.BLUE, Piece.RANK9, Square.from("b6"));
		board.setPiece(Color.BLUE, Piece.RANK8, Square.from("c6"));
		board.setPiece(Color.BLUE, Piece.RANK7, Square.from("d6"));
		board.setPiece(Color.BLUE, Piece.RANK6, Square.from("e6"));
		board.setPiece(Color.BLUE, Piece.RANK5, Square.from("f6"));
		board.setPiece(Color.BLUE, Piece.RANK4, Square.from("g6"));
		board.setPiece(Color.BLUE, Piece.RANK3, Square.from("h6"));
		board.setPiece(Color.BLUE, Piece.RANK2, Square.from("i6"));
		board.setPiece(Color.BLUE, Piece.RANK1, Square.from("j6"));
		board.setPiece(Color.BLUE, Piece.SPY, Square.from("i8"));
		return board;
	}
	
	public static Board getStandardSetup() {
		Board board = new Board();
		File setupFile = new File(WORKING_DIR, "/Stratego/res/setups/standard.txt");
		Scanner setupReader = null;
		try {
			setupReader = new Scanner(setupFile);
		} catch (FileNotFoundException e) { e.printStackTrace(); }
		
		for (int y = 3; y >= 0; y--) {
			String line = setupReader.nextLine();
			String[] pieces = line.split(" ");
			for (int x = 0; x < 10; x++) {
				board.setPiece(Color.RED, Piece.getPiece(pieces[x]), Square.from(x, y));
				board.setPiece(Color.BLUE, Piece.getPiece(pieces[x]), Square.from(x, 9-y));
			}
		}
		
		return board;
	}
}
