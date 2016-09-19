package gui;

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
	public String getSubTitle();

	/**
	 * @param subTitle
	 */
	public void setSubTitle(String subTitle);

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
	public IMenu getMenu();

	/**
	 * @param menu
	 */
	public void setMenu(IMenu menu);

	/**
	 * @return
	 */
	public IFrame getFrame();

	/**
	 * @param frame
	 */
	public void setFrame(IFrame frame);

}