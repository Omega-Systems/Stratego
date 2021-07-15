package de.omegasystems.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

public abstract class Button {
	String title;
	String display;
	int textSize;
	Color textColor, backgroundColor, highlightedColor, pressedColor;
	Dimension size;
	int borderDistanceX, borderDistanceY;

	public Button(String text, int width, int height, int textSize) {
		this.title = text;
		this.size = new Dimension(width, height);
		this.textSize = textSize;
	}

	/**
	 * Called when the button is pressed (mouse button down)
	 */
	abstract void press();

	/**
	 * Called when the button is released.<br>
	 * If the mouse is pressed inside the bounding box but moved out before
	 * released, this method isn't called. <br>
	 * Should be used for most actions.
	 */
	abstract void released();

}
