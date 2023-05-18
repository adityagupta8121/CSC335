/**
 * @author adityagupta
 * 
 * Assignment 3
 * 
 * CSC 335
 * 
 * TankSet
 * 
 * Class extended by Tank and Missiles for all 
 * required basic movement related functions
 */
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class TankSet extends Rectangle implements java.io.Serializable
{

	protected boolean visible;
	//protected BufferedImage image;

	public TankSet(int x, int y) 
	{
		this.x = x;
		this.y = y;
		visible = true;
	}


	public double getX()
	{
		return x;
	}

	public double getY()
	{
		return y;
	}

	public boolean isVisible() 
	{
		return visible;
	}

	public void setVisible(Boolean visible) 
	{
		this.visible = visible;
	}
}