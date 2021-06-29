package de.omegasystems;

import java.util.Arrays;

public class Piece {
	public static final int NONE = 0,
			FLAG	= 1,
			SPY		= 2,
			RANK9	= 3,
			RANK8	= 4,
			RANK7	= 5,
			RANK6	= 6,
			RANK5	= 7,
			RANK4	= 8,
			RANK3	= 9,
			RANK2	= 10,
			RANK1	= 11,
			BOMB	= 12,
			UNKNOWN = 13;
	
	
	private static final String[] NAMES = {
			"none",
			"flag",
			"spy",
			"rank9",
			"rank8",
			"rank7",
			"rank6",
			"rank5",
			"rank4",
			"rank3",
			"rank2",
			"rank1",
			"bomb",
			"unknown"
	};
	
	private static final String[] REPRS = {
			" ", "F", "S", "9", "8", "7", "6", "5", "4", "3", "2", "1", "B","?"
	};
	
	public static String getName(int piece) {
		return NAMES[Math.abs(piece)];
	}
	
	public static String getRepr(int piece) {
		return REPRS[Math.abs(piece)];
	}
	
	public static int getPiece(String name) {
		return Arrays.asList(NAMES).indexOf(name);
	}
	
	public static boolean isMoveable(int piece) {
		return piece != Piece.NONE && piece != Piece.FLAG && piece != Piece.BOMB;
	}
}
