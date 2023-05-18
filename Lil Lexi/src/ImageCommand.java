/**
 * 
 * @author Gregory Jenkins
 * 
 * Assignment 2
 * 
 * CSC 335 Fall 2022
 * 
 * Image command
 * 
 * Draws an image to the screen.
 * 
 * Image location is passed in as a String called fileName in constructor
 *
 */

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageCommand implements Command {
	
	String fileName;
	Scroller c;
	Window w;
	Picture pic;
	
	public ImageCommand(String fileName, Scroller c, Window w) {
		this.fileName = fileName;
		this.c = c;
		this.w = w;
	}

	/**
	 * Executes the command, draws the image to the screen
	 */
	@Override
	public void execute() {
		BufferedImage im = null;
		try {
			im = ImageIO.read(new File(fileName));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		pic = new Picture(im);
		c.insert(pic, w.returnCursor());
		w.changeCursor(1);
		c.compose();
		w.repaint();
	}

	/**
	 * Removes the image
	 */
	@Override
	public void unexecute() {
		c.remove(pic);
		w.changeCursor(-1);
		c.compose();
		this.w.repaint();
		
	}

	/**
	 * Returns true, image can be undone
	 */
	@Override
	public boolean reversible() {
		return true;
	}

}
