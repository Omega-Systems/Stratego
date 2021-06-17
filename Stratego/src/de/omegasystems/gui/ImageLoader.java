package de.omegasystems.gui;

import java.awt.image.BufferedImage;

import de.omegasystems.TileState;

public class ImageLoader {

	public static BufferedImage	EMPTY,
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
	
	public static BufferedImage getImageForPiece(TileState tileState) {
		switch (tileState) {
		case EMPTY: return EMPTY;
		case LAKE: return LAKE;
		case RED_UNKNOWN: return RED_UNKNOWN;
		case BLUE_UNKNOWN: return BLUE_UNKNOWN;
		case RED_FLAG: return RED_FLAG;
		case RED_SPY: return RED_SPY;
		case RED_RANK9: return RED_RANK9;
		case RED_RANK8: return RED_RANK8;
		case RED_RANK7: return RED_RANK7;
		case RED_RANK6: return RED_RANK6;
		case RED_RANK5: return RED_RANK5;
		case RED_RANK4: return RED_RANK4;
		case RED_RANK3: return RED_RANK3;
		case RED_RANK2: return RED_RANK2;
		case RED_RANK1: return RED_RANK1;
		case RED_RANK8: return RED_RANK8;
		case BLUE_BOMB: return BLUE_BOMB;
		
			break;

		default:
			break;
		}
	}
	
	static {
		
	}
	
}
