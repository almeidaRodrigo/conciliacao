package gui;

import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowListener;
import javax.swing.JLabel;
import javax.swing.JMenuBar;

/**
 * 
 */
public interface IWindow {
	/**
	 * 
	 */
	public void initialize(String title, JLabel subTitle, JMenuBar menu, IFrame frame);

	/**
	 * 
	 */
	public void close();

	/**
	 * @return
	 */
	public LayoutManager getLayout();

	/**
	 * @param layout
	 */
	public void setLayout(LayoutManager layout);

	/**
	 * @return
	 */
	public IWindow getWindow();
	
	public void setVisible(boolean b);
	
	public void setKeyListener(KeyListener kl);
	
	public KeyListener[] getKeyListener();
	
	public void addWindowStateListener(WindowAdapter wa);
	
	public void removeWindowListener(WindowListener wa);

	public void toFront();
	
	public void setState(int state);
	
	public void setIconImage(Image image);


}