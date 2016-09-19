package gui;

import java.awt.LayoutManager;

/**
 * 
 */
public interface IWindow {
	/**
	 * 
	 */
	public void initialize();

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

}