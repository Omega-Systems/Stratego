package de.omegasystems.gui;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

//github.com/Omega-Systems/Stratego.git

import javax.imageio.ImageIO;

import de.omegasystems.TileState;

public class ImageLoader {

	public static Map<TileState, BufferedImage> map = new HashMap<>();
	
	public static BufferedImage mainImage;
	
	private static final File WORKING_DIR = new File(System.getProperty("user.dir")+"/");
	
	public static BufferedImage getImageForPiece(TileState tileState) {
		return map.get(tileState);
	}
	
	static {
		for (TileState tileState : TileState.values()) {
			try {
				map.put(tileState, loadImage(tileState.name()));
			} catch (IOException e) {
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
		File imageFile = new File(WORKING_DIR, "/Stratego/res/themes/theme_00/"+name+".png");
		BufferedImage image = ImageIO.read(imageFile);
		return image;
	}
}