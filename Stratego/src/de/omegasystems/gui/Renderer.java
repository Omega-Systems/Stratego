package de.omegasystems.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

import de.omegasystems.Board;

public class Renderer extends JFrame{

	/**
	 * One Sqaure equals 256 pixels
	 */
	private static final long serialVersionUID = 3150993161852184366L;

	Board board;
	int width, height;
	final int lineWidth = 5;
	
	public static void main(String[] args) {
		new Renderer("Debug", 1000, 1000, null);
	}
	
	public Renderer(String title, int width, int height, Board board) {
		super(title);
		this.board = board;
		this.width = width;
		this.height = height;
		
		setUndecorated(true);
		setSize(width, height);
		setResizable(false);
		setBackground(Color.LIGHT_GRAY);
		setForeground(Color.LIGHT_GRAY);
		setVisible(true);
		setLocationRelativeTo(null);
	}

	
	@Override
	public void paint(Graphics xg) {
		//super.paint(xg);
		Graphics2D g = (Graphics2D) xg;
		
		/*
		int height = getContentPane().getHeight();
		int width = getContentPane().getWidth();
		*/
		
		g.setColor(new Color(15, 8, 15));
		
		for (int x = 0; x < 11; x++) {
			g.fillRect((int) ((((float)width-lineWidth)/10)*x), 0, lineWidth, height);
		}
		for (int y = 0; y < 11; y++) {
			g.fillRect(0, (int) ((((float)height-lineWidth)/10)*y), width, lineWidth);
		}
		
		for (int x = 0; x < 10; x++) {
			for (int y = 0; y < 10; y++) {
				int posX = (int) ((((float)width-lineWidth)/10)*x);
				int posY = (int) ((((float)height-lineWidth)/10)*y);
				g.drawImage(getImageForPiece(0), null, posX, posY);
			}
		}
		
		
	}
	
	BufferedImage getImageForPiece(int piece) {
		return null;
	}
	
}
