/**
 * 
 * @author Gregory Jenkins
 * 
 * Assignment 2
 * 
 * CSC 335 Fall 2022
 * 
 * Row Glyph
 * 
 * Holds an arraylist of Glyphs, its parent, and the whole row's bounds.
 *
 */

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

public class Row implements Glyph {

	ArrayList<Glyph> children;
	Glyph parent;
	Rectangle bounds;
	private List<glyphCheck> gGlyphs;

	public Row() {
		children = new ArrayList<Glyph>();
		bounds = new Rectangle();
		

	}
	
	
	/**
	 * Checks if the given points intersects the row
	 */

	public boolean intersects(Point point) {
		if (point.x >= bounds.x && point.x <= bounds.x + bounds.width) {
			if (point.y >= bounds.y && point.y <= bounds.y + bounds.height) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Returns the rows bounds
	 */

	public Rectangle bounds() {
		return this.bounds;
	}
	
	/**
	 * Sets the location
	 */

	public void setLocation(int x, int y) {
		bounds.x = x;
		bounds.y = y;
	}

	
	/**
	 * Appends glyph to the row
	 * @param glyph
	 */
	public void append(Glyph glyph) {
		children.add(glyph);
		bounds.width += glyph.getWidth();
		
		if (glyph.getHeight() > bounds.height) {
			bounds.height = bounds.height + glyph.getHeight();
		}
	}
	
	/**
	 * Increases height by i
	 * @param i
	 */
	public void increaseHeight(int i) {
		this.bounds.height += i;
	}
	
	/**
	 *Inserts glyph at index i
	 */
	@Override
	public void insert(Glyph glyph, int i) {
		children.add(i, glyph);
	};

	/**
	 * Removes glyph from the row
	 */
	@Override
	public void remove(Glyph glyph) {
		children.remove(glyph);
		bounds.width -= glyph.bounds.width;
	}

	/**
	 * Returns the child glyph at index i
	 */
	@Override
	public Glyph child(int i) {
		return children.get(i);
	}

	/**
	 * Returns parent glyph
	 */
	@Override
	public Glyph parent() {
		return parent;
	}

	/**
	 * Draws every glyph in row to screen
	 */
	@Override
	public void draw(Window w) {
		for (Glyph g : children) {
			g.draw(w);
		}

	}
	
	/**
	 * Returns height of row
	 */
	public int getHeight() {
		return bounds.height;
	}
	
	/**
	 * Returns width of row
	 */
	public int getWidth() {
		return bounds.width;
	}


	/**
	 * Not implemented in row
	 */
	@Override
	public void setParent(Glyph parent) {
		// TODO Auto-generated method stub
		
	}


	/**
	 * Not implemented in row
	 */
	@Override
	public void setBounds() {
		// TODO Auto-generated method stub
	}
		

	
	public List<glyphCheck> getgGlyphs() {
		return this.gGlyphs;
	}
	
	public void accept(iteratorVis visitor) {
		visitor.visitRow(this);
	}


	@Override
	public void accept(spellCheckVis spellCheckVis) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void select(Graphics graphics, Color red, Color white, int x, int y) {
		// TODO Auto-generated method stub
		
	}

}
