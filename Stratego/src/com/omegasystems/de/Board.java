package com.omegasystems.de;

import java.util.ArrayList;
import java.util.List;

public class Board {
	private Color curColor = Color.startingColor;
	private List<Integer> moveList = new ArrayList<Integer>();
	
	private List<Integer> redPieceList = new ArrayList<Integer>();
	private List<Integer> bluePieceList = new ArrayList<Integer>();
	private int[] redPieces = new int[100];
	private int[] bluePieces = new int[100];
	
	public Board() {
		
	}
	
	@Override
	public String toString() {
		String str = "  A B C D E F G H I J \n";
		for (int x = 9; x >= 0; x--) {
			str += (char) (x + '0') + " ";
			for (int y = 0; y < 10; y++) {
				int pos = x + y * 10;
				String redRepr = Piece.getRepr(redPieces[pos]);
				String blueRepr = Piece.getRepr(bluePieces[pos]);
				redRepr = redRepr == " " ? "" : "r" + redRepr;
				blueRepr = blueRepr == " " ? "" : "b" + blueRepr;
				String repr = redRepr + blueRepr;
				str += !repr.isEmpty() ? repr : ". ";;
			}
			str += "\n";
		}
		String info = String.format("Move %-6s Turn %s", curColor.toString(), moveList.size());
		str += info;
		return str;
	}
	
	public int getPiece(Color color, int pos) {
		int[] myPieces = color == Color.RED ? redPieces : bluePieces;
		return myPieces[pos];
	}
	
	public void setPiece(Color color, int piece, int pos) {
		List<Integer> myPieceList = color == Color.RED ? redPieceList : bluePieceList;
		int[] myPieces = color == Color.RED ? redPieces : bluePieces;
		
		myPieceList.add(pos);
		myPieces[pos] = piece;
	}
	
	public void removePiece(Color color, int pos) {
		List<Integer> myPieceList = color == Color.RED ? redPieceList : bluePieceList;
		int[] myPieces = color == Color.RED ? redPieces : bluePieces;
		
		myPieceList.remove((Integer) pos);
		myPieces[pos] = 0;
	}
}
