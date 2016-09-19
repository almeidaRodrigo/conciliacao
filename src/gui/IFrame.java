package gui;

import java.util.Set;

/**
 * 
 */
public interface IFrame {

	/**
	 * 
	 */
	public void initialize();

	/**
	 * 
	 */
	public void close();

	/**
	 * @param objetcs
	 */
	public void insertObjects(Set<Object> objetcs);

}