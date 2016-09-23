/**
 * 
 */
package gui;

import java.awt.AWTException;
import java.awt.Button;
import java.awt.Image;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import principal.InitializerGUI;

/**
 * @author rodrigo
 *
 */
public class ButtonGenerator {

	/**
	 * 
	 */
	private ButtonGenerator() {

	}

	public static Set<Button> generate(IWindow window){
		Set<Button> lButtons = new HashSet<>();
		Button menuConfig, menuOcultar;
		
		menuConfig = new Button("Configurações");
		menuConfig.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(
						null, 
						e.getActionCommand() , 
						"CONCILIAÇÃO - "+e.getActionCommand(), 
						JOptionPane.INFORMATION_MESSAGE);
			}
		});
		
		menuOcultar = new Button("Ocultar");
		menuOcultar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(JOptionPane.showConfirmDialog(
						null, 
						"Você deseja "+ e.getActionCommand() +"?",
						"CONCILIAÇÃO - "+e.getActionCommand(), 
						JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION){
					if(InitializerGUI.getWindowState() == null){
						if(SystemTray.isSupported()){
							final SystemTray tray = SystemTray.getSystemTray();
							Image iconImg = null;

							try {
								iconImg = ImageIO.read(new File(getClass().getResource("/img/financial-icon.png").getPath()));
							} catch (IOException e1) {
								//TODO: Implementar solução de contorno 
							}

							final TrayIcon trayIcon = new TrayIcon((Image) iconImg);
							trayIcon.setImageAutoSize(true);
							
							InitializerGUI.setWindowState(new WindowAdapter() {
				                @Override
				                public void windowStateChanged(WindowEvent e) {
				                    if (e.getNewState() == JFrame.ICONIFIED) {
				                        trayIcon.addMouseListener(new MouseAdapter() {
				                            @Override
				                            public void mouseClicked(MouseEvent e) {
				                                window.setVisible(true);
				                                window.toFront();
				                                tray.remove(trayIcon);
				                            }
				                        });
				                        
				                        try {
				                        	tray.add(trayIcon);
				                        } catch (AWTException ex) {
				                        	window.setState(JFrame.ICONIFIED);
				                        }

				                        window.setVisible(false);
				                    }
				                }
				            });
				            
							window.addWindowStateListener(InitializerGUI.getWindowState());
						}
					}
					
					window.setState(JFrame.ICONIFIED);
				}
			}
		});
		
		lButtons.add(menuConfig);
		lButtons.add(menuOcultar);
		
		return lButtons;
	}
	
	
	
}
