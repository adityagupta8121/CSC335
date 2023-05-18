/**
 * @author adityagupta
 * 
 * Assignment 3
 * 
 * CSC 335
 * 
 * Missile
 * 
 * Responsible for all Missile moving/rotating related methods
 */
import java.awt.Rectangle;

public class Missile extends TankSet 
{
    private final int boardWidth = 390;
    private final int speed = 5;
    String imageLocation = "src/missile.jpeg";
    private double rot;
    private int damage = 10;

    public Missile(int x, int y, double rot) 
    {
        super(x, y);
        this.rot = rot;
    }


    /**
     * Responsible for movement and rotation related methods
     * of the missile.
     * Collision is checked simultaneously as required in the game
     */
    public void move() 
    {  
    	if(rot == 0.0)
    	{
    		this.width = 30;
    		this.height = 15;
    		Rectangle temp = this.getBounds();
    		temp.x += speed;
    		
    		
    		if (Server.game.checkCollisionWalls(temp) || Server.game.checkCollisionTanks(temp)) {
    			this.setVisible(false);
    		} else {
    			this.x += speed;
    		}
           	
    	}
    	if(rot == 90.0)
    	{
    		this.width = 15;
    		this.height = 30;
    		Rectangle temp = this.getBounds();
    		temp.y += speed;
    		
    		if (Server.game.checkCollisionWalls(temp) || Server.game.checkCollisionTanks(temp)) {
    			this.setVisible(false);;
    		} else {
    			this.y += speed;
    		}
    		
    	}
    	if(rot == 180.0)
    	{
    		this.width = 30;
    		this.height = 15;
    		Rectangle temp = this.getBounds();
    		temp.x -= speed;
    		
    		if (Server.game.checkCollisionWalls(temp) || Server.game.checkCollisionTanks(temp)) {
    			this.setVisible(false);;
    		} else {
    			this.x -= speed;
    		}
    		

    	}
    	if(rot == 270.0)
    	{
    		this.width = 15;
    		this.height = 30;
    		Rectangle temp = this.getBounds();
    		temp.y -= speed;
    		
    		if (Server.game.checkCollisionWalls(temp) || Server.game.checkCollisionTanks(temp)) {
    			this.setVisible(false);;
    		} else {
    			this.y -= speed;
    		}
    		

    	}
    	

    }
    
    public int getDamage() {
    	return this.damage;
    }
    
    public void draw(View v) {
		v.drawMissile(this.imageLocation, this.x, this.y, this.rot);
	}
}