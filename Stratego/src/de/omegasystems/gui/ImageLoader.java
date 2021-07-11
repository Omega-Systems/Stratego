package de.omegasystems.gui;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

//github.com/Omega-Systems/Stratego.git

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import de.omegasystems.TileState;

public class ImageLoader {

	private static Map<TileState, BufferedImage> map = new HashMap<>(), resizedMap = new HashMap<>();

	private static int tileSizeX = 100, tileSizeY = 100;

	public static BufferedImage mainImage;

	public static final File WORKING_DIR = new File(System.getProperty("user.dir") + "/");
	private static File themeFile = new File(WORKING_DIR, "/Stratego/res/themes/theme_01/");

	public static BufferedImage getImageForPiece(TileState tileState) {
		return resizedMap.get(tileState);
	}

	public static void setDesiredTileSize(int x, int y) {
		if (x != tileSizeX || y != tileSizeY) {
			tileSizeX = x;
			tileSizeY = y;
			resizedMap.clear();
			for (Entry<TileState, BufferedImage> pair : map.entrySet()) {
				BufferedImage outputImage = new BufferedImage(x, y, BufferedImage.TYPE_INT_ARGB);
				outputImage.getGraphics().drawImage(pair.getValue().getScaledInstance(x, y, Image.SCALE_DEFAULT), 0, 0,
						null);
				resizedMap.put(pair.getKey(), outputImage);
			}
		}
	}
	
	public static int getTileSizeX() {return tileSizeX;}
	public static int getTileSizeY() {return tileSizeY;}
	
	static {
		for (TileState tileState : TileState.values()) {
			try {
				map.put(tileState, loadImage(tileState.name()));
			} catch (IOException e) {
				System.err.println("File: " + tileState.name());
				e.printStackTrace();
			}
		}
		try {
			mainImage = loadImage("barrel");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static BufferedImage loadImage(String name) throws IOException {
		File imageFile = new File(themeFile, name + ".png");
		BufferedImage image = ImageIO.read(imageFile);
		return image;
	}
	
	public static Clip LOAD_SOUND(String name) throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(themeFile, name));
        Clip clip = AudioSystem.getClip();
        clip.open(audioInputStream);
        return clip;
	}
}