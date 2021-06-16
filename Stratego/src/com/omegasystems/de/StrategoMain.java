package com.omegasystems.de;

public class StrategoMain {
	public static void main(String[] args) {
		System.out.println("Started Stratego Main...");
		
		Board board = BoardSetup.getTestSetup();
		System.out.println(board);
		
		System.out.println("Finished Stratego Main!");
	}
}
