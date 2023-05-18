/**
 * @author Gregory Jenkins
 * 
 * CSC 335 Fall 2022
 * 
 * Wall object
 */

import java.awt.Color;
import java.awt.Rectangle;

public class Wall extends Rectangle implements java.io.Serializable {
	private Color color;
	
	public Wall(int x, int y, Color color) {
		this.x = x * 20;
		this.y = y * 20;
		this.width = 20;
		this.height = 20;
		this.color = color;
	}
	
	/**
	 * Draws the wall
	 * @param v View object
	 */
	public void draw(View v) {
		v.drawWall(this.x, this.y, this.width, this.height, this.color);
	}
	

}
