package gui;

import java.awt.Dimension;
import java.awt.LayoutManager2;
import java.awt.Toolkit;
import javax.swing.JFrame;

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
	public void initialize() {
		// TODO implement here
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		
		this.setSize(800, 600);
		this.setLocation((dimension.width - this.getSize().width)/2, (dimension.height - this.getSize().height)/2);
		
		this.setTitle("Conciliação Bancária - Junta Comercial do Estado da Bahia");
		this.setDefaultCloseOperation(Window.EXIT_ON_CLOSE);

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


}