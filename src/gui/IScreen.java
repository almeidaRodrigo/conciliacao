package gui;

import javax.swing.JLabel;
import javax.swing.JMenuBar;

/**
 * 
 */
public interface IScreen {

	/**
	 * 
	 */
	public void show();

	/**
	 * @param window
	 */
	public void configure(IWindow window);

	/**
	 * 
	 */
	public void repaint();

	/**
	 * 
	 */
	public void close();

	/**
	 * @return
	 */
	public String getTitle();

	/**
	 * @param title
	 */
	public void setTitle(String title);

	/**
	 * @return
	 */
	public JLabel getSubTitle();

	/**
	 * @param subTitle
	 */
	public void setSubTitle(JLabel subTitle);

	/**
	 * @return
	 */
	public IWindow getWindow();

	/**
	 * @param window
	 */
	public void setWindow(IWindow window);

	/**
	 * @return
	 */
	public JMenuBar getMenu();

	/**
	 * @param menu
	 */
	public void setMenu(JMenuBar menu);

	/**
	 * @return
	 */
	public IFrame getFrame();

	/**
	 * @param frame
	 */
	public void setFrame(IFrame frame);

}