/**
 * 
 * @author Gregory Jenkins
 * 
 * Assignment 2
 * 
 * CSC 335 Fall 2022
 * 
 * Glyph interface
 *
 */


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

public interface Glyph {

	Glyph parent = null;
	Rectangle bounds = new Rectangle();

	void draw(Window w);
	
	Rectangle bounds();
	
	
	boolean intersects(Point p);

	void insert(Glyph glyph, int i);

	void remove(Glyph glyph);

	Glyph child(int i);
	

	Glyph parent();
	
	void setParent(Glyph parent);
	
	public void setBounds();
	
	public void setLocation(int x, int y);
	
	public int getHeight();
	
	public int getWidth();

	void accept(spellCheckVis spellCheckVis);

	void select(Graphics graphics, Color red, Color white, int x, int y);
	
	
}
