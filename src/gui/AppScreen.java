package gui;

/**
 * 
 */
public class AppScreen implements IScreen {

	/**
	 * Default constructor
	 */
	public AppScreen() {
	}

	/**
	 * @param window
	 */
	public AppScreen(IWindow window) {
		this.window = window;
	}

	/**
	 * 
	 */
	public IWindow window;

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
	 * 
	 */
	public void show() {
		// TODO implement here
		this.window.initialize();
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