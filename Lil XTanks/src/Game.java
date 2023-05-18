/**
 * @author Gregory Jenkins
 * 
 * CSC 335 Fall 2022
 * 
 * Holds instances of every object in the game. Stored by both the server and the player.
 * The server will be updated first and then sent to the players.
 */

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.util.ArrayList;


public class Game implements java.io.Serializable {
	private ArrayList<Wall> walls = new ArrayList<Wall>();
	protected  ArrayList<Tank> tanks = new ArrayList<Tank>();
	protected ArrayList<Missile> missiles = new ArrayList<>();
	
	
	
	
	public Game(Map map) {
		this.walls = map.getWalls();
		Tank.game = this;
		

	}
	
	/**
	 * Updates missiles whenever an action is performed.
	 * 
	 */
	public void ActionPerformed(ActionEvent e) {
		this.updateMissiles();
	}
	
	/**
	 * Adds a tank to the game array. Only called when a new player joins.
	 * 
	 * @param t Tanks being added
	 */
	public void addTank(Tank t) {
		tanks.add(t);
	}
	
	/**
	 * Adds a missile to the same array. Called whenever a player shoots.
	 * 
	 * @param m Missile being shot
	 */
	public void addMissile (Missile m) {
		missiles.add(m);
	}
	
	/**
	 * Checks if there is a collision with a tank. Must be sent a rectangle
	 * before being checked. Used to detect if missile hits tank.
	 * 
	 * @param t Rectangle being checked
	 * @return true if collision, false otherwise
	 */
	boolean checkCollisionTanks(Rectangle t) {
		Rectangle temp;
		for( Tank tCheck : tanks) {
			
			temp = tCheck.getBounds();

			if (tCheck.isVisible() && t.intersects(temp)) {
				System.out.println("HIT TANK");
				tCheck.changeHealth(-10);
				if (tCheck.getHealth() <= 0) {
					tCheck.setVisible(false);
				}
				return true;
			}
			
		}
		return false;
	}
	
	/**
	 * Checks if there is a collision with any wall. Must be sent as a rectangle
	 * before being checked.
	 * 
	 * @param t Rectangle being checked
	 * @return true if collision, false otherwise
	 */
	boolean checkCollisionWalls(Rectangle t) {
		Rectangle temp;
		for (Wall w : this.walls) {
			
			temp = w.getBounds();
			
			if (t.intersects(temp)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Updates the position of all missiles. If a missile was set to invisible, it is
	 * removed from the arraylist. Missiles are removed when it hits either a tank or
	 * a wall.
	 */
    public void updateMissiles()
    {
        
        for (int i = 0; i < missiles.size(); i++)
        {
            Missile missile = missiles.get(i);

            if (missile.isVisible()) 
            {
                missile.move();
            }
            else 
            {
                missiles.remove(i);
            }
        }
    }
	
    /**
     * Draws all walls.
     * 
     * @param v View object
     */
	void drawWalls(View v) {
		for (Wall w : walls) {
			w.draw(v);
		}
	}
	
	/**
	 * Draws all tanks.
	 * 
	 * @param v View object
	 */
	void drawTanks(View v) {
		for (Tank t : tanks) {
			if (t.isVisible()) {
				t.draw(v);
			}
		}
	}
	
	/**
	 * Draws all missiles.
	 * 
	 * @param v View object
	 */
	void drawMissiles(View v) {
		for (Missile m : missiles) {
			m.draw(v);
		}
	}
	
	/**
	 * Draws all walls, tanks, and missiles.
	 * 
	 * @param v View object
	 */
	void drawAll(View v) {
		drawWalls(v);
		drawTanks(v);
		drawMissiles(v);
	}


}
