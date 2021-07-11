package de.omegasystems.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

public abstract class Button {

	public static final int ALIGN_TO_TEXT_HORIZONTAL = 0x00000001, ALIGN_TO_TEXT_VERTICAL = 0x00000010,
			ALIGN_TO_BORDER_VERTICAL = 0x00000100, ALIGN_TO_BORDER_HORIZONTAL = 0x00001000;

	String title;
	int textSize;
	Color textColor, backgroundColor, highlightedColor, pressedColor;
	Dimension size;
	int borderDistanceX, borderDistanceY;
	/**
	 * This flag is a boolean expression of the four alignment modes.<br>
	 * If any bit related to the above bit masks is one, the button's properties are
	 * <strong>modified</strong> accordingly by the parent {@link ButtonRenderer} in
	 * the next rendering state, or manually modified by calling
	 * {@link ButtonRenderer#alignButton(Button)}
	 */
	int clippingMode;
	Font font;

	public Button(String text, int width, int height, int textSize) {
		this.title = text;
		this.size = new Dimension(width, height);
		this.textSize = textSize;
		font = new Font("Arial", Font.PLAIN, textSize);
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
