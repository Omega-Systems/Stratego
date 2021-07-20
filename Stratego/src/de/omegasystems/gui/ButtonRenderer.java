package de.omegasystems.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

public class ButtonRenderer {

	/**
	 * This flag states whether all Button's dimensions are resized to the largest
	 * text or the text is clipped if it is outside the bounding boxes.<br>
	 * The X coordinate-equivalent to {@link ButtonRenderer#dynamicSizeY}.
	 */
	boolean alignBox;
	/**
	 * Whether this renderer's {@link #sizeX} and {@link #sizeY} are used as each
	 * box's clips or the buttons are arranged to fit inside the container
	 */
	boolean dynamicSizeY;
	
	boolean visible;
	
	int borderDistanceX, borderDistanceY, textAscend;
	Font font;

	private List<Button> buttons = new ArrayList<Button>();
	int offsetX, offsetY;

	int buttonDistanceY, sizeX, sizeY, buttonSizeY;
	
	Color backGroundColor = Color.LIGHT_GRAY, 
			textColor = Color.BLACK,
			highlightedBackgrondColor = Color.DARK_GRAY,
			highlightedTextColor = Color.WHITE;
	
	JFrame frame;

	public ButtonRenderer(int posX, int posY, int buttonDistanceY, int borderDistanceX, int borderDistanceY, int sizeX, int sizeY, boolean dynamicSizeY,
			boolean alignButtonXToText) {
		this.offsetX = posX;
		this.offsetY = posY;
		this.buttonDistanceY = buttonDistanceY;
		this.borderDistanceX = borderDistanceX;
		this.borderDistanceY = borderDistanceY;
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		this.dynamicSizeY = dynamicSizeY;
		this.alignBox = alignButtonXToText;
		font = new Font("Arial", Font.PLAIN, 11);
	}
	
	public ButtonRenderer(int posX, int posY, int width, int height) {
		offsetX = posX;
		offsetY = posY;
		sizeX = width;
		sizeY = height;
		borderDistanceX = 6;
		borderDistanceY = 4;
		buttonDistanceY = 4;
		alignBox = true;
		dynamicSizeY = true;
		visible = true;
		font = new Font("Arial", Font.PLAIN, 20);
	}

	public void render(Graphics2D g) {
		if(!visible) return;
		g.setFont(font);
		//g.drawRect(400, 400, 800, Toolkit.getDefaultToolkit().getFontMetrics(font).getHeight());
		int posY = offsetY;
		for (Button b : buttons) {
			g.setColor(b.highlighted ? highlightedBackgrondColor : backGroundColor);
			g.fillRect(offsetX, posY, sizeX, buttonSizeY);
			g.setColor(b.highlighted ? highlightedTextColor : textColor);
			g.drawString(b.display, offsetX+borderDistanceX, posY+borderDistanceY+textAscend);
			posY+=buttonSizeY+buttonDistanceY;
		}
	}

	@SuppressWarnings("deprecation")
	public void recalculateShape() {

		// Calculates the biggest possible font height if the renderer has only a
		// limited space to work with and distribute this space to each button.
		// This applies a uniform
		if (!dynamicSizeY) {
			float sizePerButtonX = sizeX / ((float) buttons.size());
			sizePerButtonX -= borderDistanceY * 2 + buttonDistanceY;
			for (int i = 1; i <= 100; i++) {
				Font nFont = font.deriveFont((float) i);
				FontMetrics metrics = Toolkit.getDefaultToolkit().getFontMetrics(nFont);
				if (metrics.getHeight() <= sizePerButtonX)
					font = nFont;
				else
					break;
			}
		} else {
			buttonSizeY = Toolkit.getDefaultToolkit().getFontMetrics(font).getHeight()+2*borderDistanceY;
			sizeY = (buttonSizeY+buttonDistanceY)*buttons.size();
		}
		
		// Calculates the x size of the largest button (longest text) if dynamic X aka
		// alignBox is activated, else it crops the button's text
		
		FontMetrics metrics = Toolkit.getDefaultToolkit().getFontMetrics(font);
		if (alignBox) {
			sizeX = 0;
			for (Button b : buttons) {
				b.display = b.title;
				if (metrics.stringWidth(b.title) > sizeX - 2 * borderDistanceX)
					sizeX = metrics.stringWidth(b.title) + 2 * borderDistanceX;
			}
		} else {
			
			for (Button b : buttons) {
				b.display = b.title;
				if (metrics.stringWidth(b.title) > sizeX - 2 * borderDistanceX) {
					for (int i = 0; i < b.title.length(); i++) {
						b.display = b.title + "...";
						if (metrics.stringWidth(b.display) <= sizeX - 2 * borderDistanceX)
							break;
					}
				}
			}
		}
		textAscend = metrics.getAscent();
	}
	
	public void mouseMove(int posX, int posY) {
		Button button = getButtonFromPos(posX, posY);
		
		boolean repaint = false;
		for (Button nButton : buttons) {
			if(nButton.highlighted!=(button==nButton)) {
				repaint = true;
				nButton.highlighted = button==nButton;
			}
		}
		
		if(repaint)
			frame.repaint();
	}

	public void mousePress(int posX, int posY) {
		Button button = getButtonFromPos(posX, posY);
		if(button!=null) button.pressed();
	}
	
	public void addButton(Button b) {
		buttons.add(b);
		recalculateShape();
	}

	public void removeButton(Button b) {
		buttons.remove(b);
		recalculateShape();
	}

	
	Button getButtonFromPos(int absoluteX, int absoluteY) {
		int posRelX = absoluteX - offsetX, // Renderer.BORDER_OFFSET_X,
				posRelY = absoluteY - offsetY - Renderer.BORDER_OFFSET_Y;
		
		int buttonIndex = (int) ((posRelY / ((float)(sizeY))) * buttons.size());
		Button button = null;
		
		if(buttonIndex >= 0 && buttonIndex < buttons.size()
				&& posRelX >= 0 && posRelX <= sizeX
				&& posRelY >= 0 && posRelY <= sizeY) 
			button = buttons.get(buttonIndex);
		return button;
	}
}
