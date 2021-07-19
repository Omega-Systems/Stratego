package de.omegasystems.gui;

public abstract class Button {
	String title;
	String display;
	boolean highlighted;
	
	public Button(String text) {
		this.title = text;
	}

	/**
	 * Called when the button is pressed (mouse button down)
	 */
	abstract void pressed();

}
