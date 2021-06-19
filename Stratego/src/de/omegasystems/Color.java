package de.omegasystems;

public enum Color {
	RED,
	BLUE;
	
	public static Color startingColor = RED;
	
	public static final Color invert(Color color) {
		return color == RED ? BLUE : RED;
	}
}
