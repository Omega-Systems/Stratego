package de.omegasystems;

public class Board {
	private Color curColor = Color.startingColor;
	private int[] moveList = new int[1024];
	private int moveCount = 0;
	private BoardState state = BoardState.SETUP;
	
	private int[] redPieces = new int[10*10];
	private int[] bluePieces = new int[10*10];
	
	public Board() {
		
	}
	
	@Override
	public String toString() {
		String str =
				new String(new char[22]).replace("\0", "=") + "\n" +
				String.format("Move %-6s Turn %s", curColor.toString(), moveCount) + "\n" + 
				new String(new char[22]).replace("\0", "-") + "\n";
		for (int y = 9; y >= 0; y--) {
			str += (char) (y + '0') + " ";
			for (int x = 0; x < 10; x++) {
				int pos = x + y * 10;
				String redRepr = Piece.getRepr(redPieces[pos]);
				String blueRepr = Piece.getRepr(bluePieces[pos]);
				redRepr = redRepr == " " ? "" : "r" + redRepr;
				blueRepr = blueRepr == " " ? "" : "b" + blueRepr;
				String repr = redRepr + blueRepr;
				str += !repr.isEmpty() ? repr : ". ";
			}
			str += "\n";
		}
		str +=
				"  A B C D E F G H I J " +"\n" +
				new String(new char[22]).replace("\0", "=");
		return str;
	}
	
	public int getPiece(Color color, int pos) {
		int[] myPieces = color == Color.RED ? redPieces : bluePieces;
		return myPieces[pos];
	}
	
	public void setPiece(Color color, int piece, int pos) {
		int[] myPieces = color == Color.RED ? redPieces : bluePieces;
		myPieces[pos] = piece;
	}
	
	public void move(int move) {
		int[] myPieces = this.curColor == Color.RED ? redPieces : bluePieces;
		int[] enemyPieces = this.curColor == Color.RED ? bluePieces : redPieces;
		
		if (enemyPieces[Move.getTo(move)] == Piece.FLAG) {
			state = curColor == Color.RED ? BoardState.VICTORY_RED : BoardState.VICTORY_BLUE;
		}
		if (moveCount == 1024) {
			state = BoardState.DRAW;
		}
		
		myPieces[Move.getTo(move)] = myPieces[Move.getFrom(move)];
		enemyPieces[Move.getTo(move)] = Piece.NONE;
		myPieces[Move.getFrom(move)] = Piece.NONE;
	}
}
