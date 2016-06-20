/**
 * 
 */
package vo;

/**
 * @author rodrigo
 *
 */
public class LayoutDam {
	private String attributeName;
	private int colNumberStart;
	private int colNumberEnd;
	
	/**
	 * @return the attributeName
	 */
	public String getAttributeName() {
		return attributeName;
	}
	
	/**
	 * @param attributeName the attributeName to set
	 */
	public void setAttributeName(String attributeName) {
		this.attributeName = attributeName;
	}
	
	/**
	 * @return the colNumberStart
	 */
	public int getColNumberStart() {
		return colNumberStart;
	}

	/**
	 * @param colNumber the colNumber to set
	 */
	public void setColNumberStart(int colNumberStart) {
		this.colNumberStart = colNumberStart;
	}

	/**
	 * @return the colNumberEnd
	 */
	public int getColNumberEnd() {
		return colNumberEnd;
	}

	/**
	 * @param colNumberEnd the colNumberEnd to set
	 */
	public void setColNumberEnd(int colNumberEnd) {
		this.colNumberEnd = colNumberEnd;
	}

	/**
	 * @param attributeName
	 * @param colNumber
	 */
	public LayoutDam(String attributeName, int colNumberStart, int colNumberEnd) {
		this.attributeName = attributeName;
		this.colNumberStart = colNumberStart;
		this.colNumberEnd = colNumberEnd;
	}
	
	
	
	

}
