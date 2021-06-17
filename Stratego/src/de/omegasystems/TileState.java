package de.omegasystems;

public enum TileState {
	EMPTY,
	LAKE,
	RED_UNKNOWN,
	BLUE_UNKNOWN,
	RED_FLAG,
	RED_SPY,
	RED_RANK9,
	RED_RANK8,
	RED_RANK7,
	RED_RANK6,
	RED_RANK5,
	RED_RANK4,
	RED_RANK3,
	RED_RANK2,
	RED_RANK1,
	RED_BOMB,
	BLUE_FLAG,
	BLUE_SPY,
	BLUE_RANK9,
	BLUE_RANK8,
	BLUE_RANK7,
	BLUE_RANK6,
	BLUE_RANK5,
	BLUE_RANK4,
	BLUE_RANK3,
	BLUE_RANK2,
	BLUE_RANK1,
	BLUE_BOMB;
	
	public static TileState[] RED_PIECES;
	public static TileState[] BLUE_PIECES;
	
	static {
		
		RED_PIECES = new TileState[]{
				EMPTY,
				RED_FLAG,
				RED_SPY,
				RED_RANK9,
				RED_RANK8,
				RED_RANK7,
				RED_RANK6,
				RED_RANK5,
				RED_RANK4,
				RED_RANK3,
				RED_RANK2,
				RED_RANK1,
				RED_BOMB
		};
		
		
		BLUE_PIECES = new TileState[]{
				EMPTY,
				BLUE_FLAG,
				BLUE_SPY,
				BLUE_RANK9,
				BLUE_RANK8,
				BLUE_RANK7,
				BLUE_RANK6,
				BLUE_RANK5,
				BLUE_RANK4,
				BLUE_RANK3,
				BLUE_RANK2,
				BLUE_RANK1,
				BLUE_BOMB
		};
	}
}
