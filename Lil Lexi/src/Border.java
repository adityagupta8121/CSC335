import java.awt.Color;
import java.awt.Graphics;

/**
 * 
 * @author Gregory Jenkins
 * 
 * Assignment 2
 * 
 * CSC 335 Fall 2022
 * 
 * Border Glyph extends MonoGlyph
 * 
 * Draws a border around the text area, and passes all method calls to composition;
 *
 */

public class Border extends MonoGlyph {
	private Composition composition;
	
	public Border(Composition component) {
		super(component);
		this.composition = component;
	}

	/**
	 * Draws the composition and the border
	 */
	public void draw(Window w) {
		super.draw(w);
		
		drawBorder(w);
	}

	/**
	 * Draws the border
	 * @param w Window
	 */
	private void drawBorder(Window w) {
		w.drawRectangle(40, 10, 420, 500);
		
	}
	
	/**
	 * @return composition
	 */
	public Composition getComposition() {
		return this.composition;
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