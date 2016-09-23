/**
 * 
 */
package gui;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import principal.InitializerGUI;

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
			setImage(ImageIO.read(new File(InitializerGUI.class.getResource("/img/financial-icon.png").getPath())));
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
