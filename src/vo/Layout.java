/**
 * 
 */
package vo;

import java.lang.reflect.Field;
import java.util.ArrayList;

/**
 * @author rodrigo
 *
 */
public class Layout {
	
	//private Dam dam;
	private ArrayList<LayoutDam> layoutDams = new ArrayList<>();

	/**
	 * 
	 */
	public Layout() {

		Field[] fieldsDam = new Dam().getClass().getDeclaredFields();
		
		for (Field field : fieldsDam) {
			LayoutDam layoutDam = new LayoutDam(field.getName(), 0, 0);

			layoutDams.add(layoutDam);
		}

	}

	/**
	 * @return the layoutDams
	 */
	public ArrayList<LayoutDam> getLayoutDams() {
		return layoutDams;
	}

	/**
	 * @param layoutDams the layout to set
	 */
	public void setLayoutDams(ArrayList<LayoutDam> layoutDams) {
		this.layoutDams = layoutDams;
	}
	
	
	
	
	
	

}
