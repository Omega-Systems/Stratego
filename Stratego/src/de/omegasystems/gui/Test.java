package de.omegasystems.gui;

import java.util.Scanner;

public class Test {

	public static void main(String[] args) {
		String line = "";
		for (int i = 1; i < 13; i++) {
			for (int j = 1; j < 13; j++) {
				line+=String.format("%4d", i*j)+"  |";
			}
			System.out.println(line);
			line = "";
		}
		

	}

}
