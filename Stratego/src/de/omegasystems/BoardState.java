package de.omegasystems;

public enum BoardState {
	SETUP,
	INGAME,
	VICTORY_RED,
	VICTORY_BLUE,
	DRAW;
	
	public static BoardState getVictory(Color color) {
		return color == Color.RED ? VICTORY_RED : VICTORY_BLUE;
	}
}
