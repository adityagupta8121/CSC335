/**
 * 
 * @author Gregory Jenkins
 * 
 * Assignment 2
 * 
 * CSC 335 Fall 2022
 * 
 * Concrete line compositor
 * 
 * Stores an instance of a composition object, and formats the Glyphs into rows.
 *
 */

import java.awt.FontMetrics;
import java.util.ArrayList;

public class LineCompositor extends Compositor {
	
	ArrayList<Glyph> children;
	ArrayList<Glyph> unformatted;
	FontMetrics fm;
	int maxWidth = 400;
	int y;
	int x;

	/**
	 * Composes the compositor
	 * 
	 * Breaks the glyphs in compositor into rows
	 */
	@Override
	public ArrayList<Glyph> Compose() {
		children = this.composition.getChildren();
		unformatted = this.composition.getUnformatted();
		Row row = new Row();
		children.add(row);
		x = 50;
		y = 50;
		
		for (Glyph g : unformatted) {
			g.setBounds();
			
			if (row.getWidth() + g.getWidth() <= maxWidth) {
				g.setLocation(row.getWidth() + x, y);
				row.append(g);
			} else {
				y += row.getHeight();
				row = new Row();
				row.increaseHeight(g.getHeight());
				row.setLocation(x, y);
				g.setLocation(row.getWidth() + x, y);
				row.append(g);
				children.add(row);

			}
			
		}
		
		
		
		return children;

	}
	
	public void setFontMetrics(FontMetrics fm) {
		this.fm = fm;
	}


}
