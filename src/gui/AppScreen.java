package gui;

import javax.swing.JFrame;

/**
 * 
 */
public class AppScreen extends JFrame implements IScreen {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @param title
	 * @param subTitle
	 * @param window
	 * @param menu
	 * @param frame
	 */
	public AppScreen(String title, String subTitle, IWindow window, IMenu menu, IFrame frame) {
		this.title = title;
		this.subTitle = subTitle;
		this.window = window;
		this.menu = menu;
		this.frame = frame;
	}

	/**
	 * 
	 */
	private String title;

	/**
	 * 
	 */
	private String subTitle;

	/**
	 * 
	 */
	private IWindow window;

	/**
	 * 
	 */
	private IMenu menu;

	/**
	 * 
	 */
	private IFrame frame;

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the subTitle
	 */
	public String getSubTitle() {
		return subTitle;
	}

	/**
	 * @param subTitle the subTitle to set
	 */
	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}

	/**
	 * @return the window
	 */
	public IWindow getWindow() {
		return window;
	}

	/**
	 * @param window the window to set
	 */
	public void setWindow(IWindow window) {
		this.window = window;
	}

	/**
	 * @return the menu
	 */
	public IMenu getMenu() {
		return menu;
	}

	/**
	 * @param menu the menu to set
	 */
	public void setMenu(IMenu menu) {
		this.menu = menu;
	}

	/**
	 * @return the frame
	 */
	public IFrame getFrame() {
		return frame;
	}

	/**
	 * @param frame the frame to set
	 */
	public void setFrame(IFrame frame) {
		this.frame = frame;
	}

	/**
	 * 
	 */
	public void show() {
		this.window.initialize();
	}

	/**
	 * @param window
	 */
	public void configure(IWindow window) {
		// TODO implement here
	}

	/**
	 * 
	 */
	public void repaint() {
		// TODO implement here
	}

	/**
	 * 
	 */
	public void close() {
		// TODO implement here
	}



}