/**
 * 
 */
package gui;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import javax.imageio.ImageIO;

/**
 * @author rodrigo
 *
 */
public class GetterImageApp {

	/**
	 * 
	 */
	private GetterImageApp() {
		
	}
	
	private static Image image = null;

	/**
	 * @return the image
	 * @throws IOException 
	 */
	public static Image getImage() throws IOException {
		if(image == null){
			setImage(ImageIO.read(FileSystems.getDefault().getPath(
				System.getProperty("user.dir")
				+ File.separator
				+ "img" 
				+ File.separator
				+"financial-icon.png").toFile()
			));
		}

		return image;
	}

	/**
	 * @param image the image to set
	 */
	public static void setImage(Image image) {
		GetterImageApp.image = image;
	}

}
