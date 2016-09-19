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

	/**
	 * 
	 */
	public void configure(IWindow window) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getTitle() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setTitle(String title) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getSubTitle() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setSubTitle(String subTitle) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public IMenu getMenu() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setMenu(IMenu menu) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public IFrame getFrame() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setFrame(IFrame frame) {
		// TODO Auto-generated method stub
		
	}

}