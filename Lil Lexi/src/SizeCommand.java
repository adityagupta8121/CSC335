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

public class SizeCommand implements Command {
	
	
	int oldSize;
	int newSize;
	Window w;
	
	SizeCommand(int newSize, int oldSize, Window w) {
		this.oldSize = oldSize;
		this.newSize = newSize;
		this.w = w;
	}

	/**
	 * Changes the font size to newSize
	 */
	@Override
	public void execute() {
		this.w.changeFontSize(this.newSize);
		this.w.repaint();

	}

	/**
	 * Changes the font size to oldSize
	 */
	@Override
	public void unexecute() {
		this.w.changeFontSize(this.oldSize);
		this.w.repaint();
	}

	/**
	 * Returns true, font size change can be undone
	 */
	@Override
	public boolean reversible() {
		return true;
	}

}
