package de.omegasystems.gui;

import java.awt.Color;

public abstract class Button {
	String title;
	String display;
	Color textColor, backgroundColor, highlightedColor, pressedColor;
	int borderDistanceX, borderDistanceY;

	public Button(String text) {
		this.title = text;
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
