package gui;

/**
 * 
 */
public class DialogScreen implements IScreen {

	/**
	 * Default constructor
	 */
	public DialogScreen() {
	}
	
	/**
	 * @param window
	 */
	public DialogScreen(IWindow window) {
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