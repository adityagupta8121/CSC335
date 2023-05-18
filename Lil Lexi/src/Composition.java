/**
 * 
 * @author Gregory Jenkins
 * 
 * Assignment 2
 * 
 * CSC 335 Fall 2022
 * 
 * Composition class
 * 
 * Stores an arraylist of unformatted glyphs, and an arraylist of formatted ones.
 * Holds a compositor object to compose the glyphs.
 *
 */

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;

public class Composition implements Glyph {
	
	private ArrayList<Glyph> children;
	private ArrayList<Glyph> unformatted;
	private Compositor compositor;

	
	public Composition(Compositor compositor) {
		this.compositor = compositor;
		this.unformatted = new ArrayList<Glyph>();
		this.children = new ArrayList<Glyph>();
		this.compositor.SetComposition(this);

	}

	
	/**
	 * Draws all glyphs in composition
	 */
	@Override
	public void draw(Window w) {

		for (Glyph g : children) {
			g.setBounds();
			g.draw(w);
		}
	}

	/**
	 * Inserts a glyph into the composition at index i
	 */
	@Override
	public void insert(Glyph glyph, int i) {
		glyph.setBounds();
		unformatted.add(i, glyph);
		
		children = new ArrayList<Glyph>();
		children = compositor.Compose();
	}

	/**
	 * Removes the given glyph
	 */
	@Override
	public void remove(Glyph glyph) {

		unformatted.remove(glyph);
		children = new ArrayList<Glyph>();
		children = compositor.Compose();
	}

	/**
	 * Returns the child at index i
	 */
	@Override
	public Glyph child(int i) {
		return unformatted.get(i);

	}
	
	/**
	 * @return children arraylist of glyphs
	 */
	ArrayList<Glyph> getChildren() {
		return children;
		
	}
	
	/**
	 * @return unformatted arraylist of glyphs
	 */
	ArrayList<Glyph> getUnformatted() {
		return unformatted;
		
	}
	
	/**
	 * Composes the glyphs into rows
	 */
	void compose() {
		children = new ArrayList<Glyph>();
		children = compositor.Compose();
	}

	/**
	 * Not implemented
	 */
	@Override
	public Rectangle bounds() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Not implemented
	 */
	@Override
	public boolean intersects(Point p) {
		// TODO Auto-generated method stub
		return false;
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

	/**
	 * Not implemented
	 */
	@Override
	public void setBounds() {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Not implemented
	 */
	@Override
	public void setLocation(int x, int y) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Not implemented
	 */
	@Override
	public int getHeight() {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * Not implemented
	 */

	@Override
	public int getWidth() {
		// TODO Auto-generated method stub
		return 0;
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
