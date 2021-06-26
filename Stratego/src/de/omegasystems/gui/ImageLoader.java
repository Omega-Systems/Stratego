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

import de.omegasystems.TileState;

public class ImageLoader {

	private static Map<TileState, BufferedImage> map = new HashMap<>(), resizedMap = new HashMap<>();

	private static int tileSizeX = 100, tileSizeY = 100;

	public static BufferedImage mainImage;

	private static final File WORKING_DIR = new File(System.getProperty("user.dir") + "/");

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
		File imageFile = new File(WORKING_DIR, "/Stratego/res/themes/theme_01/" + name + ".png");
		BufferedImage image = ImageIO.read(imageFile);
		return image;
	}
}