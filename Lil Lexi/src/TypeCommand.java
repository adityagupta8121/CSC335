/**
 * 
 * @author Gregory Jenkins
 * 
 * Assignment 2
 * 
 * CSC 335 Fall 2022
 * 
 * Type Command
 * 
 * Inserts the given character in a compositino and draws it to the screen
 *
 */

import java.awt.FontMetrics;

public class TypeCommand implements Command {
	
	Window w;
	char c;
	Scroller document;
	int cursor;
	FontMetrics fm;
	Char charGlyph;
	
	public TypeCommand(char c, Scroller document, Window w, FontMetrics fm) {
		this.w = w;
		this.c = c;
		this.document = document;
		this.cursor = w.returnCursor();
		this.fm = fm;
	}

	/**
	 * Adds the character to the composition and draws it to the screen
	 */
	@Override
	public void execute() {
		char pressed = this.c;
		charGlyph = new Char(pressed, fm);
		document.insert(charGlyph, cursor);
		w.changeCursor(1);
		w.repaint();
		
		
	}

	/**
	 * Deletes the character
	 */
	@Override
	public void unexecute() {
		if (cursor > 0) {
			w.changeCursor(-1);
			
			Glyph removed = document.getUnformatted().remove(cursor);
			document.remove(removed);
			w.repaint();
		}
		

		
	}

	/**
	 * Returns true, execution can be undone
	 */
	@Override
	public boolean reversible() {
		return true;
	}

}
