package de.omegasystems;

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
}
