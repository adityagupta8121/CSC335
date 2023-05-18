/**
 * 
 * @author Gregory Jenkins
 * 
 * Assignment 2
 * 
 * CSC 335 Fall 2022
 * 
 * MonoGlyph abstract class
 * 
 * Allows subclasses to add features and implementations to a Glyph.
 * Holds a component glyph and passes each method call to it.
 *
 */

import java.awt.Point;
import java.awt.Rectangle;

public abstract class MonoGlyph implements Glyph {
	protected Glyph component;
	
	public MonoGlyph(Glyph component) {
		this.component = component;
	}
	
	/**
	 * Draws component
	 */
	public void draw(Window w) {
		component.draw(w);
	}

	/**
	 * Returns components bounds
	 */
	@Override
	public Rectangle bounds() {
		return component.bounds();
	}

	/**
	 * Returns if given point intersects with component
	 */
	@Override
	public boolean intersects(Point p) {
		return component.intersects(p);
	}

	/**
	 * Inserts glyph into componenet at index i
	 */
	@Override
	public void insert(Glyph glyph, int i) {
		component.insert(glyph, i);		
	}

	/**
	 * Removes glyph from component
	 */
	@Override
	public void remove(Glyph glyph) {
		component.remove(glyph);
		
	}

	/**
	 * Returns components child glyph at index i
	 */
	@Override
	public Glyph child(int i) {
		component.child(i);
		return null;
	}

	/**
	 * Returns components parent glyph
	 */
	@Override
	public Glyph parent() {
		return component.parent();
	}

	/**
	 * Sets components parent
	 */
	@Override
	public void setParent(Glyph parent) {
		component.setParent(parent);
		
	}

	/**
	 * Sets components bounds
	 */
	@Override
	public void setBounds() {
		component.setBounds();
		
	}

	/**
	 * Sets components location
	 */
	@Override
	public void setLocation(int x, int y) {
		component.setLocation(x, y);
		
	}

	/**
	 * Returns components height
	 */
	@Override
	public int getHeight() {
		return component.getHeight();
	}

	/**
	 * Returns components width
	 */
	@Override
	public int getWidth() {
		return component.getWidth();
	}


}
