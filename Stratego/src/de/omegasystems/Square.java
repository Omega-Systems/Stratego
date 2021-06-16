package de.omegasystems;

public class Square {
	public static int from(String str) {
		if (str.length() != 2) {
			throw new IllegalArgumentException();
		}
		
		str = str.toLowerCase();
		
		int file = str.charAt(0) - 'a';
		int rank = str.charAt(1) - '0';
		
		return file + rank * 10;
	}
	
	public static int from(int rank, int file) {
		return file + rank * 10;
	}
	
	public static String toString(int sq) {
		return "TO BE IMPLEMENTED";
	}
}
