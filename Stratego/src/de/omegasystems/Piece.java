package de.omegasystems;

public class Piece {
	public static final int NONE	= 0;
	public static final int FLAG	= 1;
	public static final int SPY		= 2;
	public static final int RANK9	= 3;
	public static final int RANK8	= 4;
	public static final int RANK7	= 5;
	public static final int RANK6	= 6;
	public static final int RANK5	= 7;
	public static final int RANK4	= 8;
	public static final int RANK3	= 9;
	public static final int RANK2	= 10;
	public static final int RANK1	= 11;
	public static final int BOMB	= 12;
	
	
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
			"bomb"
	};
	
	private static final String[] REPRS = {
			" ", "F", "S", "9", "8", "7", "6", "5", "4", "3", "2", "1", "B"
	};
	
	public static String getName(int piece) {
		return NAMES[Math.abs(piece)];
	}
	
	public static String getRepr(int piece) {
		return REPRS[Math.abs(piece)];
	}
}
