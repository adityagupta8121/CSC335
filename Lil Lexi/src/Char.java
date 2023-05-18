/**
 * 
 * @author Gregory Jenkins
 * 
 * Assignment 2
 * 
 * CSC 335 Fall 2022
 * 
 * Character Glyph
 * 
 * Stores a char, its bounds, and FontMetrics to set the bounds.
 *
 */

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

public class Char implements Glyph {

	char character;
	Rectangle bounds;
	FontMetrics fm;

	public Char(char c, FontMetrics fm) {
		this.character = c;
		this.bounds = new Rectangle();
		this.fm = fm;
		setBounds();
	}
	
	/**
	 * Sets bounds
	 */
	@Override
	public void setBounds() {
		bounds.width = fm.charWidth(character);
		bounds.height = fm.getHeight();
	}
	
	/**
	 * Sets location
	 */
	@Override
	public void setLocation(int x, int y) {
		bounds.x = x;
		bounds.y = y;
	}
	
	/**
	 * Returns if point intersects with rectangle
	 */
	@Override
	public boolean intersects(Point point) {
		if (point.x >= bounds.x && point.x <= bounds.x + bounds.width) {
			if (point.y >= bounds.y && point.y <= bounds.y + bounds.height) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Returns char's bounds
	 */
	@Override
	public Rectangle bounds() {
		return this.bounds;
	}

	/**
	 * Draws the char
	 */
	@Override
	public void draw(Window w) {
		this.fm = w.getFontMetrics();
		setBounds();
		w.drawChar(character, bounds.x, bounds.y);

	}
	
	/**
	 * Returns char's height
	 */
	@Override
	public int getHeight() {
		return bounds.height;
	}
	
	/**
	 * Returns char's width
	 */
	@Override
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


	public char getChar() {
		return this.character;
	}
	
	public int getCharacterCode() {
		return (int) this.character;
	}

	public void accept(iteratorVis visitor) {
		// TODO Auto-generated method stubdwd
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
