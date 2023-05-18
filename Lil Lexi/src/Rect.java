/**
 * 
 * @author Gregory Jenkins
 * 
 * Assignment 2
 * 
 * CSC 335 Fall 2022
 * 
 * Rectangle glyph
 *
 */

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

public class Rect implements Glyph {

	Rectangle bounds;

	public Rect(int width, int height) {
		this.bounds = new Rectangle();
		this.bounds.width = width;
		this.bounds.height = height;
	}
	
	
	/**
	 * Not implemented, cannot change rectangle size
	 */
	@Override
	public void setBounds() {
	}
	
	/**
	 * Sets rectangles location
	 */
	@Override
	public void setLocation(int x, int y) {
		bounds.x = x;
		bounds.y = y;
	}
	
	/**
	 * Returns if point intersects with rectangle
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
	 * Returns rectangles bounds
	 */

	public Rectangle bounds() {
		return this.bounds;
	}

	/**
	 * Draws the rectangle
	 */
	@Override
	public void draw(Window w) {
		w.drawRectangle(bounds.x, bounds.y, getWidth(), getHeight());
	}
	
	/**
	 * Returns rectangles height
	 */
	public int getHeight() {
		return bounds.height;
	}
	
	/**
	 * Returns rectangles width
	 */
	public int getWidth() {
		return bounds.width;
	}


	/**
	 * Not implemented
	 */
	@Override
	public void insert(Glyph glyph, int i) {

		
	}


	/**
	 * Not implemented
	 */
	@Override
	public void remove(Glyph glyph) {
		
	}

	/**
	 * Not implemented
	 */
	@Override
	public Glyph child(int i) {
		return null;
	}

	/**
	 * Not implemented
	 */
	@Override
	public Glyph parent() {
		return null;
	}

	/**
	 * Not implemented
	 */
	@Override
	public void setParent(Glyph parent) {

		
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
