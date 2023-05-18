/**
 * @author adityagupta
 * 
 * Assignment 3
 * 
 * CSC 335
 * 
 * Tank
 * 
 * Responsible for all tank moving/rotating related methods
 */
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class Tank extends TankSet
{
    private int dx;
    private int dy;
    private List<Missile> missiles;
    int direct = 0;
    final int speed = 4;
    private int health;
    String imageLocation = "src/tank.png";
    protected static Game game;
    
    private double rot = 0.0;
    

    public Tank(int x, int y)
    {
        super(x, y);
        this.width = 32;
        this.height = 32;
        this.health = 20;
        getTank();
    }

    private void getTank() 
    {
        missiles = new ArrayList<>();
    }

    public void move()
    {
        x += dx;
        y += dy;
    }

    /**
     * responsible for rotating the tank to the right
     */
    public void rotateRight()
    {

    	rot += 90.0;
    	if (rot >= 360.0) {
    		rot = 0.0;
    	}
    	
    	
    }

    /**
     * responsible for rotating the tank to the left
     */
    public void rotateLeft()
    {

    	rot -= 90.0;
    	if (rot <= 0.0) {
    		rot = 360.0;
    	}

    	
    }
    
    /**
     * responsible for moving the tank forward
     * and also checking the collisions simultaneously
     */
    public void moveForward() {
    	if(rot == 0.0)
    	{
    		Rectangle temp = this.getBounds();
    		temp.x += speed;
    		
    		if (!Server.game.checkCollisionWalls(temp)) {
    			this.x += speed;
    		}
           	
    	}
    	if(rot == 90.0)
    	{
    		Rectangle temp = this.getBounds();
    		temp.y += speed;
    		
    		if (!Server.game.checkCollisionWalls(temp)) {
    			this.y += speed;
    		}
    		
    	}
    	if(rot == 180.0)
    	{
    		Rectangle temp = this.getBounds();
    		temp.x -= speed;
    		
    		if (!Server.game.checkCollisionWalls(temp)) {
    			this.x -= speed;
    		}
    		

    	}
    	if(rot == 270.0)
    	{
    		Rectangle temp = this.getBounds();
    		temp.y -= speed;
    		
    		if (!Server.game.checkCollisionWalls(temp)) {
    			this.y -= speed;
    		}
    		

    	}
    	
    }
    
    /**
     * responsible for moving the tank backwards
     * and also checking the collisions simultaneously
     */
    public void moveBackwards() {
    	
    	if(rot == 0.0)
    	{
    		Rectangle temp = this.getBounds();
    		temp.x -= speed;
    		
    		if (!Server.game.checkCollisionWalls(temp)) {
    			this.x -= speed;
    		}
           	
    	}
    	if(rot == 90.0)
    	{
    		Rectangle temp = this.getBounds();
    		temp.y -= speed;
    		
    		if (!Server.game.checkCollisionWalls(temp)) {
    			this.y -= speed;
    		}
    		
    	}
    	if(rot == 180.0)
    	{
    		Rectangle temp = this.getBounds();
    		temp.x += speed;
    		
    		if (!Server.game.checkCollisionWalls(temp)) {
    			this.x += speed;
    		}
    		

    	}
    	if(rot == 270.0)
    	{
    		Rectangle temp = this.getBounds();
    		temp.y += speed;
    		
    		if (!Server.game.checkCollisionWalls(temp)) {
    			this.y += speed;
    		}
    		

    	}
    	
    }
    
    public List<Missile> getMissiles()
    {
        return missiles;
    }

    

    /**
     * responsible for calling missiles when
     * method is called and also checking rotations
     * simultaneously
     */
    public void fire() 
    {
    	if(rot == 0.0)
    	{
    		Tank.game.addMissile(new Missile(x + width, y + height / 2, rot));
           	
    	}
    	if(rot == 90.0)
    	{
    		Tank.game.addMissile(new Missile(x, y + height, rot));
    		
    	}
    	if(rot == 180.0)
    	{
    		Tank.game.addMissile(new Missile(x - width, y + height / 2, rot));

    	}
    	if(rot == 270.0)
    	{
    		Tank.game.addMissile(new Missile(x, y - height, rot));

    	}
    }
   
    
	private String name;
	

	
	public String getName() {
		return this.name;
	}
	
	public int getHealth() {
		return this.health;
	}
	
	public void changeHealth(int h) {
		this.health += h;
	}
	
	public void draw(View v) {
		v.drawTank(this.imageLocation, this.x, this.y, this.rot);
	}
	
	
	public boolean isAlive() {
		if (health > 0) {
			return true;
		}
		return false;
	}

}