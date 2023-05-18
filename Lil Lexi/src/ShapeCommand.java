/**
 * 
 * @author Gregory Jenkins
 * 
 * Assignment 2
 * 
 * CSC 335 Fall 2022
 * 
 * Shape command
 * 
 * Draws a rectangle to the screen
 *
 */

public class ShapeCommand implements Command {
	
	private Scroller document;
	private Window w;
	private Rect rect;
	
	public ShapeCommand(Scroller document, Window w) {
		this.document = document;
		this.w = w;
		rect = new Rect(100, 100);
	}

	/**
	 * Executes the command, draws a rectangle to the screen
	 */
	@Override
	public void execute() {
		document.insert(rect, w.returnCursor());
		w.changeCursor(1);
		document.compose();
		this.w.repaint();
		
	}

	/**
	 * Removes the drawn rectangle
	 */
	@Override
	public void unexecute() {

		document.remove(rect);
		w.changeCursor(-1);
		document.compose();
		this.w.repaint();
	}

	/**
	 * Returns true, command can be undone
	 */
	@Override
	public boolean reversible() {
		return true;
	}

}
