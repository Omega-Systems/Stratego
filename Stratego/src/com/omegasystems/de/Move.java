package com.omegasystems.de;

public class Move {
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
