package de.omegasystems.gui;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
<<<<<<< HEAD
=======
import java.util.HashMap;
import java.util.Map;
>>>>>>> branch 'master' of https://github.com/Omega-Systems/Stratego.git

import javax.imageio.ImageIO;

import de.omegasystems.TileState;

public class ImageLoader {

	public static Map<TileState, BufferedImage> map = new HashMap<>();
	
	private static final File WORKING_DIR = new File(System.getProperty("user.dir")+"/");
	
	public static BufferedImage getImageForPiece(TileState tileState) {
<<<<<<< HEAD
		String path = System.getProperty("user.dir") + "/res/themes/theme_00" +
				tileState.name().toLowerCase() + ".png";
		
		try {
			System.out.println("Load image!");
			return ImageIO.read(new File(path));
		} catch (IOException e) {
			System.out.println("Not found: " + path);
		};
		return null;
=======
		return map.get(tileState);
>>>>>>> branch 'master' of https://github.com/Omega-Systems/Stratego.git
	}
	
	static {
		for (TileState tileState : TileState.values()) {
			try {
				map.put(tileState, loadImage(tileState.name()));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private static BufferedImage loadImage(String name) throws IOException {
		File imageFile = new File(WORKING_DIR, "/Stratego/res/themes/theme_00/"+name+".png");
		//System.out.println(imageFile.getAbsolutePath());
		BufferedImage image = ImageIO.read(imageFile);
		return image;
	}
}
