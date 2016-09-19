/**
 * 
 */
package conciliacao;

import java.awt.BorderLayout;

import gui.AppScreen;
import gui.Frame;
import gui.IFrame;
import gui.IMenu;
import gui.IScreen;
import gui.IWindow;
import gui.MenuLeft;
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
		
		IWindow window = new Window(new BorderLayout());
		IMenu menu = new MenuLeft();
		IFrame frame = new Frame();
		
		IScreen screen = new AppScreen("Sistema de Conciliação Bancária - JUCEB", "Teste de Tela", window, menu, frame);
		screen.show();
		
	}

}
