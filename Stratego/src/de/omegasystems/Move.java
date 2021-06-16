package de.omegasystems;

public class Move {
	public static int from(String fromTo) {
		if (fromTo.length() != 4) {
			throw new IllegalArgumentException();
		}
		
		int fromSq = Square.from(fromTo.substring(0, 1));
		int toSq = Square.from(fromTo.substring(0, 1));
		
		return from(fromSq, toSq);
	}
	
	public static int from(int fromSq, int toSq) {
		return fromSq | (toSq << 8);
	}
	
	public static int getFrom(int move) {
		return move & 0xFF;
	}
	
	public static int getTo(int move) {
		return move >> 8;
	}
}
