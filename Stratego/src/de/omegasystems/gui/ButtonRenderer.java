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
	
	public void render(Graphics2D g) {
		if(dynamicSizeY) {
			int posX = offsetX;
			for (Button button : buttons) {
				g.fillRect(posX, offsetY, sizeX, height);
			}
		}
	}

	@SuppressWarnings("deprecation")
	public void recalculateShape() {
		
		//Calculates the biggest possible font height if the renderer has only a limited space to work with and distribute this space to each button.
		//This applies a uniform
		if(!dynamicSizeY) {
			float sizePerButtonX = sizeX / ((float)buttons.size());
			sizePerButtonX -= borderDistanceY*2+buttonDistanceY;
			for(int i = 1; i<=100; i++) {
				Font nFont = font.deriveFont((float) i);
				FontMetrics metrics = Toolkit.getDefaultToolkit().getFontMetrics(nFont);
				if(metrics.getHeight()<=sizePerButtonX)
					font = nFont;
				else
					break;
			}
		}
		
		//Calculates the x size of the largest button (longest text) if dynamic X aka alignBox is activated, else it crops the button's text
		if(alignBox) {
			FontMetrics metrics = Toolkit.getDefaultToolkit().getFontMetrics(font);
			for(Button b : buttons) {
				if(metrics.stringWidth(b.title) > sizeX-2*borderDistanceX)
					sizeX = metrics.stringWidth(b.title)+2*borderDistanceX;
				b.display = b.title;
			}
		} else {
			FontMetrics metrics = Toolkit.getDefaultToolkit().getFontMetrics(font);
			for(Button b : buttons) {
				b.display = b.title;
				if(metrics.stringWidth(b.title) > sizeX-2*borderDistanceX) {
					for (int i = 0; i < b.title.length(); i++) {
						b.display = b.title+"...";
						if(metrics.stringWidth(b.display)<=sizeX-2*borderDistanceX) break;
					}
				}
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

}
