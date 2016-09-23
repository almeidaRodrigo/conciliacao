/**
 * 
 */
package gui;

import java.awt.BorderLayout;

/**
 * @author rodrigo
 *
 */
public class WindowGenerator {

	/**
	 * 
	 */
	private WindowGenerator() {
		
	}
	
	private static IWindow window = null;

	/**
	 * @return the window
	 */
	public static IWindow getWindow() {
		if(window == null){
			window = new Window(new BorderLayout());
		}
		
		return window;
	}

	/**
	 * @param window the window to set
	 */
	public static void setWindow(IWindow window) {
		WindowGenerator.window = window;
	}
	
}
