package de.omegasystems;

public class StrategoMain {
	public static void main(String[] args) {
		System.out.println("Started Stratego Main...");
		
		Board board = BoardSetup.getTestSetup();
		
		board.move(Move.from(Square.from("b3"), Square.from("b4")));
		System.out.println(board);
		
		board.move(Move.from(Square.from("b4"), Square.from("b5")));
		System.out.println(board);
		
		board.move(Move.from(Square.from("b5"), Square.from("b6")));
		System.out.println(board);
		
		board.move(Move.from(Square.from("b6"), Square.from("b7")));
		System.out.println(board);
		
		System.out.println("Finished Stratego Main!");
	}
}
