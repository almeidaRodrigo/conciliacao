/**
 * 
 */
package conciliacao;

import gui.AppScreen;
import gui.IScreen;
import gui.IWindow;
import gui.Window;

/**
 * @author rodrigo
 *
 */
public class TestGui {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		IWindow window = new Window();
		IScreen screen = new AppScreen(window);
		screen.show();
		
	}

}
