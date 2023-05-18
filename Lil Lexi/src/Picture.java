/**
 * 
 * @author Gregory Jenkins
 * 
 * Assignment 2
 * 
 * CSC 335 Fall 2022
 * 
 * Picture Glyph
 *
 */

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;

public class Picture implements Glyph {


	Image im;
	Rectangle bounds;

	public Picture(Image im) {
		this.im = im;
		this.bounds = new Rectangle();
		this.setBounds();
	}
	
	
	/**
	 * Sets bounds based off of the image
	 */
	@Override
	public void setBounds() {
		bounds.width = im.getWidth(null);
		bounds.height = im.getHeight(null);
	}
	
	/**
	 * Sets the location
	 */
	@Override
	public void setLocation(int x, int y) {
		bounds.x = x;
		bounds.y = y;
	}
	
	/**
	 * Returns if the given points intersects with the image
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
	 * Returns image bounds
	 */
	public Rectangle bounds() {
		return this.bounds;
	}

	/**
	 * Draws the image to the screen
	 */
	@Override
	public void draw(Window w) {
		w.drawImage(im, bounds.x, bounds.y);

	}
	
	/**
	 * Returns the image height
	 */
	public int getHeight() {
		return bounds.height;
	}
	
	/**
	 * Returns the image width
	 */
	public int getWidth() {
		return bounds.width;
	}


	/**
	 * Not implemented
	 */
	@Override
	public void insert(Glyph glyph, int i) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Not implemented
	 */
	@Override
	public void remove(Glyph glyph) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Not implemented
	 */
	@Override
	public Glyph child(int i) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Not implemented
	 */
	@Override
	public Glyph parent() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Not implemented
	 */

	@Override
	public void setParent(Glyph parent) {
		// TODO Auto-generated method stub
		
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
