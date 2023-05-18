/**
 * 
 * @author Gregory Jenkins
 * 
 * Assignment 2
 * 
 * CSC 335 Fall 2022
 * 
 * Scroller class
 * 
 * Draws a scroll bar to the screen.
 *
 */

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class Scroller extends MonoGlyph {
	
	Composition component;
	
	public Scroller(Composition component) {
		super(component);
		this.component = component;
	}

	/**
	 * Draws the scroller and the component
	 */
	public void draw(Window w) {
		super.draw(w);
		
		w.drawScroller();
	}
	/**
	 * Returns components child glyph at index i
	 */
	public Glyph child(int i) {
		return this.component.child(i);
	}
	
	/**
	 * @return components children
	 */
	ArrayList<Glyph> getChildren() {
		return this.component.getChildren();
		
	}
	
	/**
	 * @return unformatted glyph array
	 */
	ArrayList<Glyph> getUnformatted() {
		return this.component.getUnformatted();
		
	}
	
	/**
	 * Composes component into rows
	 */

	void compose() {
		this.component.compose();
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
