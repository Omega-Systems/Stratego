package com.omegasystems.de;

import java.util.ArrayList;
import java.util.List;

public class Board {
	private Color curColor = Color.RED;
	private List<Integer> redPieceList = new ArrayList<Integer>();
	private List<Integer> bluePieceList = new ArrayList<Integer>();
	private int[] redPieces = new int[100];
	private int[] bluePieces = new int[100];
	
	public Board() {
		
	}
	
	@Override
	public String toString() {
		String str = "";
		for (int x = 0; x < 10; x++) {
			for (int y = 0; y < 10; y++) {
				str += " ";
			}
			str += "\n";
		}
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
}
