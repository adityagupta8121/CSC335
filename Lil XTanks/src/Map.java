/**
 * @author Gregory Jenkins
 * 
 * CSC 335 Fall 2022
 * 
 * Loads the maps.
 * 
 * Maps are stored as a text file. First line contains the color of the
 * walls, and below that is a grid of characters. 'X' represents a wall, while 'O' is
 * empty space.
 * 
 * Example:
 * 
 * blue
 * XXXXXXXX
 * XOOOOOOX
 * XOOOOOOX
 * XOOOOOOX
 * XOOOOOOX
 * XXXXXXXX
 * 
 */

import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Map {
	private ArrayList<Wall> walls = new ArrayList<Wall>();
	private Color color;
	private int width = 25;
	
	public Map(String file) {
		this.loadMap(file);
	}
	
	/*
	 * Adds a new wall
	 */
	public void addWall(int x, int y) {
		this.walls.add(new Wall(x, y, this.color));
	}
	
	/**
	 * Returns arraylist of walls
	 */
	public ArrayList<Wall> getWalls() {
		return this.walls;
	}
	
	/**
	 * Loads the map. Maps are stored as a text file. First line contains the color of the
	 * walls, and below that is a grid of characters. 'X' represents a wall, while 'O' is
	 * empty space.
	 * @param s
	 */
	void loadMap(String s) {
		try {
			File f = new File(s);
			Scanner scan = new Scanner(f);
			int y = 0;
			String color = scan.nextLine();
			this.setColor(color);
			while (scan.hasNextLine()) {
				String line = scan.nextLine();
				for (int x = 0; x < width; x++) {
					if (line.charAt(x) == 'X') {
						this.addWall(x, y);
					}
				}
				y++;
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Sets the color of the map walls
	 * @param c
	 */
	void setColor(String c) {
		switch (c) {

		case "red":		this.color = Color.red;
						break;
		case "orange":	this.color = Color.orange;
						break;
		case "yellow":	this.color = Color.yellow;
						break;
		case "green":	this.color = Color.green;
						break;
		case "blue":	this.color = Color.blue;
						break;
		case "pink":	this.color = Color.pink;
						break;
		case "white":	this.color = Color.white;
						break;
		case "black":	this.color = Color.black;
						break;
		case "cyan":	this.color = Color.cyan;
						break;


		}
	}
	
}
