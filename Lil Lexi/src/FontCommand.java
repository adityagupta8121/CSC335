/**
 * 
 * @author Gregory Jenkins
 * 
 * Assignment 2
 * 
 * CSC 335 Fall 2022
 * 
 * Font command
 * 
 * Changes the font size
 *
 */

public class FontCommand implements Command {
	
	String oldFont;
	String newFont;
	Window w;
	
	FontCommand(String newName, String oldName, Window w) {
		this.oldFont = oldName;
		this.newFont = newName;
		this.w = w;
	}

	/**
	 * Changes the font to newFont
	 */
	@Override
	public void execute() {
		this.w.changeFont(newFont);
		this.w.repaint();

	}

	/**
	 * Changes the font to oldFont
	 */
	@Override
	public void unexecute() {

		this.w.changeFont(oldFont);
		this.w.repaint();
	}

	/**
	 * Returns true, font change can be undone
	 */
	@Override
	public boolean reversible() {
		return true;

	}

}
