package de.omegasystems;

import java.awt.Point;

public class Square {
	public static int from(String str) {
		if (str.length() != 2) {
			throw new IllegalArgumentException();
		}
		
		str = str.toLowerCase();
		
		int x = str.charAt(0) - 'a';
		int y = str.charAt(1) - '0';
		
		return x + y * 10;
	}
	
	public static int from(int x, int y) {
		return x + y * 10;
	}
	
	public static String toString(int sq) {
		return "TO BE IMPLEMENTED";
	}
	
	public static Point toPoint(int pos) {
		int posX = pos % 10;
		int posY = pos / 10;
		return new Point(posX, posY);
	}
	
	public static boolean isNorthEdge(int sq) { return 90 <= sq && sq < 100; }
	
	public static boolean isEastEdge(int sq) { return sq % 10 == 9; }
	
	public static boolean isSouthEdge(int sq) { return 0 <= sq && sq < 10; }
	
	public static boolean isWestEdge(int sq) { return sq % 10 == 0; }
}
