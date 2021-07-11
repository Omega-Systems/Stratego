package de.omegasystems.gui;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;

public class ButtonRenderer {

	/**
	 * This flag states whether all Button's dimensions are resized to the largest text or the text is clipped if it is outside the bounding boxes.<br>
	 * The Y coordinate-equivalent to {@link ButtonRenderer#alignBox}.
	 */
	boolean alignBox;
	/**
	 * Whether this renderer's {@link #sizeX} and {@link #sizeY} are used as each box's clips or the buttons are arranged to fit inside the container
	 */
	boolean dynamicSizeY;
	int borderDistanceX, borderDistanceY;
	Font font;
	
	private List<Button> buttons = new ArrayList<Button>();
	int offsetX, offsetY;
	
	int buttonDistanceY, sizeX, sizeY;

	public ButtonRenderer(int posX, int posY, int buttonDistanceY, int sizeX, int sizeY, boolean dynamicSizeY, boolean alignButtonXToText) {
		this.offsetX = posX;
		this.offsetY = posY;
		this.buttonDistanceY = buttonDistanceY;
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		this.dynamicSizeY = dynamicSizeY;
		this.alignBox = alignButtonXToText;
		font = new Font("Arial", Font.PLAIN, 11);
	}

	public void recalculateShape() {
		
		//Calculates the biggest possible font size if the renderer has only a limited space to work with and distribute this space to each button.
		if(!dynamicSizeY) {
			float sizePerButtonX = sizeX / ((float)buttons.size());
			sizePerButtonX -= borderDistanceY*2+buttonDistanceY;
			for(int i = 1; i<=100; i++) {
				Font nFont = font.deriveFont((float) i);
				@SuppressWarnings("deprecation")
				FontMetrics metrics = Toolkit.getDefaultToolkit().getFontMetrics(nFont);
				if(metrics.getHeight()<=sizePerButtonX)
					font = nFont;
				else
					break;
			}
		}
		
		//Calculates the x size of the largest button (longest text) if dynamic X is activated, else it crops the button's text
		if(dynamicSizeY) {
			FontMetrics metrics = Toolkit.getDefaultToolkit().getFontMetrics(font);
			for(Button b : buttons) {
				if(metrics.stringWidth(b.title) >= )
			}
		}
	}
	
	public void addButton(Button b) {
		buttons.add(b);
		recalculateShape();
	}
	
	public void removeButton(Button b) {
		buttons.remove(b);
		recalculateShape();
	}

	/**
	 * Sets whether important outline attributes of the buttons shall be ignored and the template attributes shall be used instead.<br>
	 * Combined with {@link #scaleTemplateToLargestText()} to create a uniform look of each button
	 * 
	 * @param whether to force overlay changes
	 * @see #setDefaultOverlay(Button)
	 */
	public void forceDefaultLayout(boolean force) {
		this.forceDefaulOverlay = force;
	}
	
	/**
	 * Sets the template's size properties so that it can contain the largest text (using the template font).<br>
	 * Useful for creating uniform layouts.
	 * @see #scaleButtonsToLargestText()
	 */
	public void scaleTemplateToLargestText() {
		
	}
	
	/**
	 * Instead of modifying the template, this method mofifies each button individually
	 * @see #scaleTemplateToLargestText()
	 */
	public void scaleButtonsToLargestText() {
		
	}
	
	public void draw(Graphics2D g) {
		int posX = offsetX;
		int posY = offsetY;

		if (forceDefaulOverlay) {
			for (Button button : buttons) {
				
			}
		} else {
			for (Button button : buttons) {
				
			}
		}
	}
	
	public static void alignButton(Button button) {
		String text = button.title;
		int sizeX = button.size.width;
		int sizeY = button.size.height;

		@SuppressWarnings("deprecation")
		FontMetrics metrics = Toolkit.getDefaultToolkit().getFontMetrics(button.font);

		if ((button.clippingMode & Button.ALIGN_TO_BORDER_VERTICAL) != 0) {
			for (int i = button.font.getSize() - 1; i > 0; i--) {
				button.font = button.font.deriveFont((float) i);

				if (metrics.stringWidth(text) <= button.size.width + 2 * button.borderDistanceX)
					break;
			}
		}

		if ((button.clippingMode & Button.ALIGN_TO_BORDER_HORIZONTAL) != 0) {
			for (int i = button.font.getSize() - 1; i > 0; i--) {
				button.font = button.font.deriveFont((float) i);
				if (metrics.getHeight() <= button.size.height + 2 * button.borderDistanceY)
					break;
			}
		}
		
		button.size.width = metrics.stringWidth(text) + 2 * button.borderDistanceX;
		if ((button.clippingMode & Button.ALIGN_TO_TEXT_HORIZONTAL) != 0)
			sizeX = metrics.stringWidth(text) + 2 * button.borderDistanceX;
		if ((button.clippingMode & Button.ALIGN_TO_TEXT_VERTICAL) != 0)
			button.size.height = metrics.getHeight() + 2 * button.borderDistanceY;
	}
	
	

}
