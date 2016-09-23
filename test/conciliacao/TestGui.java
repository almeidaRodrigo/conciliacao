/**
 * 
 */
package conciliacao;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import gui.AppScreen;
import gui.Frame;
import gui.IFrame;
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
	public static void main(String[] args) throws Exception {

		IScreen screen;
		IWindow window = new Window(new BorderLayout());
		JMenuBar menu = new JMenuBar();
		Button menuConfig, menuOcultar;
		IFrame frame = new Frame();
		JLabel subTitle = new JLabel("Concilia��o - Banco do Brasil");

		menuConfig = new Button("Configura��es");
		menuConfig.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(
						null, 
						e.getActionCommand() , 
						"CONCILIA��O - "+e.getActionCommand(), 
						JOptionPane.INFORMATION_MESSAGE);
			}
		});
		
		menuOcultar = new Button("Ocultar");
		menuOcultar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(
						null, 
						e.getActionCommand() , 
						"CONCILIA��O - "+e.getActionCommand(),  
						JOptionPane.INFORMATION_MESSAGE);
			}
		});
		
		menu.add(menuConfig);
		menu.add(menuOcultar);
		
		screen = new AppScreen("Sistema de Concilia��o Banc�ria - JUCEB", subTitle, window, menu, frame);
		screen.show();

	}

}
