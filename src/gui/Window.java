package gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.LayoutManager2;
import java.awt.Toolkit;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * 
 */
public class Window extends JFrame implements IWindow {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor
	 */
	public Window(LayoutManager2 layout) {
		this.setLayout(layout);
	}

	/**
	 * 
	 */
	@Override
	public void initialize(String title, JLabel subTitle, JMenuBar menu, IFrame frame) {
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		JPanel pMenu = new JPanel();

		this.setSize(800, 600);
		this.setLocation((dimension.width - this.getSize().width)/2, (dimension.height - this.getSize().height)/2);
		
		this.setTitle(title);

		subTitle.setHorizontalAlignment(SwingConstants.CENTER);

		this.add(BorderLayout.NORTH, subTitle);
		
		menu.setLayout(new GridLayout(0, 1));
		
		pMenu.add(menu);
		pMenu.setBorder(BorderFactory.createEtchedBorder());
		
		this.add(BorderLayout.WEST, pMenu);
		this.add(BorderLayout.CENTER, (Component) frame);
		
		this.setResizable(false);
		this.setDefaultCloseOperation(Window.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				if(JOptionPane.showConfirmDialog(
						null, 
						"Você deseja encerrar a aplicação?", 
						"CONCILIAÇÃO - Encerrar", 
						JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION){
					System.exit(0);
				}
			}
		});
		
		this.setVisible(true);
	}

	/**
	 * 
	 */
	@Override
	public void close() {
		System.exit(0);
	}

	/**
	 * 
	 */
	@Override
	public IWindow getWindow() {
		return this;
	}
	
	public void setKeyListener(KeyListener kl){
		this.addKeyListener(kl);
	}
	
	public KeyListener[] getKeyListener(){
		return this.getKeyListener();
	}
	
	public void addWindowStateListener(WindowAdapter wa){
		super.addWindowStateListener(wa);
	}
	
	public void removeWindowListener(WindowListener wa){
		super.removeWindowListener(wa);
	}
	
	public void toFront(){
		super.toFront();
	}
	
	public void setState(int state){
		super.setState(state);
	}
	
	public void setIconImage(Image image){
		super.setIconImage(image);
	}

	
	


}