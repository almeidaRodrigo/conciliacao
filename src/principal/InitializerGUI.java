/**
 * 
 */
package principal;

import java.awt.Button;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.io.IOException;
import java.util.Set;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import gui.AppScreen;
import gui.Frame;
import gui.IFrame;
import gui.IScreen;
import gui.IWindow;

/**
 * @author rodrigo
 *
 */
public class InitializerGUI {
	/**
	 * 
	 */
	private InitializerGUI() {

	}

	private static WindowAdapter windowState = null;

	/**
	 * @return the windowState
	 */
	public static WindowAdapter getWindowState() {
		return windowState;
	}

	/**
	 * @param windowState the windowState to set
	 */
	public static void setWindowState(WindowAdapter wa) {
		InitializerGUI.windowState = wa;
	}

	public static void show(Image appIcon, IWindow window, Set<Button> lButtons) throws IOException{
		IScreen screen;
		JMenuBar menu = new JMenuBar();
		IFrame frame = new Frame();
		JLabel subTitle = new JLabel("Conciliação - Banco do Brasil");
		
		window.setIconImage(appIcon);
		screen = new AppScreen();

		for (Button button : lButtons) {
			menu.add(button);
		}
		
		screen.setTitle("Sistema de Conciliação Bancária - JUCEB");
		screen.setSubTitle(subTitle);
		screen.setWindow(window);
		screen.setMenu(menu);
		screen.setFrame(frame);
		
		screen.show();
	}

}
