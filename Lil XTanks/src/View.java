/**
 * @author Gregory Jenkins
 * 
 * CSC 335 Fall 2022
 * 
 * Draws the walls, tanks, and missiles every 1/30 of a second based off of a game loop.
 */

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.Socket;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class View extends JPanel implements ActionListener 
{
	private Graphics2D g2d;
	protected static Game game;

    
	private boolean isRunning;
	private Thread gameLoop;

	ClientTest client;
	Socket socket;


    public View() 
    {
    	addKeyListener(new TAdapter());
        setBackground(Color.white);
        setFocusable(true);
        
        try {
			socket = new Socket("localhost", 1234);
			client = new ClientTest(socket);
			client.setView(this);
			
			client.getGameUpdate();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        this.setupGameLoop();
		this.isRunning = true;
		this.gameLoop.start();
    }

	/**
	 * Draws all game objects
	 */
	@Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.g2d = (Graphics2D) g;
        if (View.game != null && this.g2d != null) {
        	this.game.drawAll(this);
        }
    }
	
	

    /**
     * Repaints whenever an action is performed
     */
    public void actionPerformed(ActionEvent e)
    {

        repaint();
    }

    
    /**
     * Starts the game loop the draw all game objects every 1/30 of a second.
     */
	private void setupGameLoop() {
		gameLoop = new Thread(() -> {
			while (isRunning) {
				if (View.game != null) {
					View.game.drawAll(this);
				}
				
				try {
                    Thread.sleep(33);
                } catch (InterruptedException ex) {
                }
			}
		});
		
	}


	/**
	 * 
	 * Listens for any key pressed and sends it to the server.
	 *
	 */
    private class TAdapter extends KeyAdapter
    {
        @Override
        public void keyPressed(KeyEvent e)
        {
        	
        	int key = e.getKeyCode();

            if (key == KeyEvent.VK_LEFT) 
            {
            	client.sendKey('a');
    		}

            if (key == KeyEvent.VK_RIGHT) 
            {
            	client.sendKey('d');

            }
            
            if (key == KeyEvent.VK_UP) 
            {
            	client.sendKey('w');

            }
            
            if (key == KeyEvent.VK_SPACE) {
            	client.sendKey('f');
            }

            
            if (key == KeyEvent.VK_DOWN) 
            {
            	client.sendKey('s');
            }
            
        	
            repaint();
        }


    }


	/**
	 * Draws a wall
	 * 
	 * @param x	location
	 * @param y location
	 * @param width
	 * @param height
	 * @param color
	 */
	void drawWall(int x, int y, int width, int height, Color color) {
		if (this.g2d != null) {
			this.g2d.setColor(color);
			this.g2d.fillRect(x, y, width, height);
			repaint();
		}
	}

	/**
	 * Draws a tank, rotates it based on rot parameter.
	 * 
	 * @param imageLocation
	 * @param x
	 * @param y
	 * @param rot Degrees of rotation
	 */
	public void drawTank(String imageLocation, int x, int y, double rot) {
		double rotationRequired = Math.toRadians (rot);
		BufferedImage im = null;
		try {
			im = ImageIO.read(new File(imageLocation));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		double locationX = x + (im.getWidth() / 2);
		double locationY = y + (im.getHeight() / 2);
		AffineTransform at = AffineTransform.getRotateInstance(rotationRequired, locationX, locationY);
		at.translate(x, y);
		
		if (this.g2d != null) {
			g2d.drawImage(im, at, null);
			repaint();
		}
		
	}
	
	/**
	 * Draws a missile, rotates it based on rot parameter
	 * 
	 * @param imageLocation
	 * @param x
	 * @param y
	 * @param rot Degrees of rotation
	 */
	public void drawMissile(String imageLocation, int x, int y, double rot) {
		double rotationRequired = Math.toRadians (rot);
		BufferedImage im = null;
		try {
			im = ImageIO.read(new File(imageLocation));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		double locationX = x + (im.getWidth() / 2);
		double locationY = y + (im.getHeight() / 2);
		AffineTransform at = AffineTransform.getRotateInstance(rotationRequired, locationX, locationY);
		at.translate(x, y);
		
		g2d.drawImage(im, at, null);
		repaint();
	}

	
}
