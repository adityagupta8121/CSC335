/**
 * 
 * @author Gregory Jenkins
 * 
 * Assignment 2
 * 
 * CSC 335 Fall 2022
 * 
 * Window UI class
 * 
 * Creates and displays the program frame, and handles all drawing to screen operations.
 *
 */


import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;


import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class Window extends JPanel implements KeyListener, ActionListener, MouseWheelListener {
	
	JLabel label;
	String text = "";
	Composition composition;
	Compositor compositor;
	Scroller document;
	Graphics2D g2D;
	Font font;
	FontMetrics fm;
	String fontName = "Comic Sans MS";
	int fontSize = 20;
	int x = 70;
	int y = 70;
	int cursor;
	
	int offset = 0;
	
	JFrame frame = new JFrame();
	JMenuBar mb;
	JMenu shapes;
	JMenu images;
	JMenu newFont;
	JMenu newFontSize;
	JMenu edit;
	JMenuItem car;
	JMenuItem tree;
	JMenuItem addRect;
	JMenuItem times;
	JMenuItem helvetica;
	JMenuItem wingdings;
	JMenuItem arial;
	JMenuItem comic;
	JMenuItem increase;
	JMenuItem decrease;
	JMenuItem undo;
	JMenuItem redo;
	
	CommandManager manager;

	JMenu spellcheck;
	JMenuItem spellon;

	Window(Compositor compositor) {


		manager = CommandManager.getInstance();
		
		this.compositor = compositor;
		this.composition = new Composition(compositor);
		this.document = new Scroller(composition);
		this.cursor = 0;
		this.font = new Font(fontName, Font.PLAIN, fontSize);
		

		addMenus();
		frame.setTitle("Lil Lexi");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		this.setPreferredSize(new Dimension(500, 500));
		frame.add(this);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.addKeyListener(this);
		this.addMouseWheelListener(this);

		frame.setVisible(true);
	}
	

	/**
	 * Paints the panel
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		this.g2D = (Graphics2D) g;
		fm = g2D.getFontMetrics(font);
		this.document.draw(this);
		this.drawCursor();
		
	}
	
	/**
	 * Adds all toolbars
	 */
	private void addMenus() {
		mb = new JMenuBar();
		shapes = new JMenu("Shapes");
		images = new JMenu("Images");
		newFont = new JMenu("Font");
		newFontSize = new JMenu("Size");
		edit = new JMenu("Edit");
		car = new JMenuItem("Car");
		tree = new JMenuItem("Tree");
		addRect = new JMenuItem("Rectangle");
		times = new JMenuItem("Times New Roman");
		helvetica = new JMenuItem("Helvetica");
		wingdings = new JMenuItem("Wingdings");
		arial = new JMenuItem("Arial");
		comic = new JMenuItem("Comic Sans MS");
		increase = new JMenuItem("Increase");
		decrease = new JMenuItem("Decrease");
		undo = new JMenuItem("Undo");
		redo = new JMenuItem("Redo");
		car.addActionListener(this);
		tree.addActionListener(this);
		addRect.addActionListener(this);
		times.addActionListener(this);
		helvetica.addActionListener(this);
		wingdings.addActionListener(this);
		arial.addActionListener(this);
		comic.addActionListener(this);
		increase.addActionListener(this);
		decrease.addActionListener(this);
		undo.addActionListener(this);
		redo.addActionListener(this);
		images.add(car);
		images.add(tree);
		shapes.add(addRect);
		newFont.add(times);
		newFont.add(helvetica);
		newFont.add(wingdings);
		newFont.add(arial);
		newFont.add(comic);
		newFontSize.add(increase);
		newFontSize.add(decrease);
		edit.add(undo);
		edit.add(redo);
		mb.add(newFont);
		mb.add(shapes);
		mb.add(images);
		mb.add(newFontSize);
		mb.add(edit);
		
		spellcheck = new JMenu("SpellCheck");
		spellon = new JMenuItem("On");
		spellcheck.add(spellon);
		spellon.addActionListener(this);
		mb.add(spellcheck);
		
		frame.setJMenuBar(mb);
	}
	
	/**
	 * @return FontMetrics object
	 */

	
	
	private void handleSpellChecking(){	
		SpellCheck.getInstance().LoadDictionary("testing.txt");		
		System.out.println("Called handle");
		this.repaint();
	}

	
	public FontMetrics getFontMetrics() {
		return this.fm;
	}

	/**
	 * Listens for key types
	 */
	@Override
	public void keyTyped(KeyEvent e) {
		if (e.getKeyChar() != KeyEvent.VK_BACK_SPACE && e.getKeyChar() > 31 && e.getKeyChar() < 592) {
			
		char pressed = e.getKeyChar();
		Char c = new Char(pressed, fm);
		document.insert(c, cursor);
		cursor++;
		

		repaint();
		}
	}

	/**
	 * Listens for key pressed
	 */
	@Override
	public void keyPressed(KeyEvent e) { 
		if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
			if (cursor > 0) {
				cursor--;
				
				Glyph removed = document.getUnformatted().remove(cursor);
				document.remove(removed);
				
			}
		}
		

		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			if (this.cursor > 0) {
				this.cursor--;
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			if (this.cursor < document.getUnformatted().size()) {
				this.cursor++;
			}
			
		}
		
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			offset -= 5;
		}
		if (e.getKeyCode() == KeyEvent.VK_UP && offset < 0) {
			offset += 5;
		}
		repaint();
	}


	/**
	 * Listens for key released
	 */
	@Override
	public void keyReleased(KeyEvent e) { 
		
	}
	
	/**
	 * Draws the cursor to the screen
	 */
	void drawCursor() {
		if (cursor > 0) {
			Glyph current = document.child(cursor-1);
			int cursorX = current.bounds().x;
			int cursorY = current.bounds().y;
			g2D.drawLine(cursorX + current.getWidth(), cursorY + offset - fm.getHeight(), cursorX + current.getWidth(), cursorY + offset);
		} else {
			g2D.drawLine(50, 50 + offset, 50, 50 - this.fm.getHeight() + offset);
		}
	}
	
	/**
	 * Draws a character
	 * @param c char
	 * @param x
	 * @param y 
	 */
	void drawChar(char c, int x, int y) {
		g2D.setFont(font);
		g2D.drawString(c + "", x, y + offset);
	}

	/**
	 * Draws an image
	 * @param i image
	 * @param x x location
	 * @param y y location
	 */
	void drawImage(Image i, int x, int y) {
		g2D.drawImage(i, x, y + offset, null);
	}
	
	
	/**
	 * Draws a rectangle
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	void drawRectangle(int x, int y, int width, int height) {
		g2D.drawRect(x, y + offset, width, height);
	}
	
	/**
	 * Draws the scroll bar
	 */
	void drawScroller() {
		g2D.fillRect(480, -offset, 20, 40);

	}

	
	/**
	 * Listens for then a toolbar is clicked
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == car) {

			this.manager.execute(new ImageCommand("cartoon_car.png", this.document, this));
			repaint();
		}
		if (e.getSource() == tree) {
			
			this.manager.execute(new ImageCommand("cartoon_tree.png", this.document, this));
			repaint();
		}
		if (e.getSource() == addRect) {
			
			this.manager.execute(new ShapeCommand(this.document, this));
			repaint();
		}
		if (e.getSource() == times) {
			
			this.manager.execute(new FontCommand("Times New Roman", this.fontName, this));
			document.compose();
			repaint();
		}
		if (e.getSource() == helvetica) {

			this.manager.execute(new FontCommand("Helvetica", this.fontName, this));
			document.compose();
			repaint();
		}
		if (e.getSource() == arial) {

			this.manager.execute(new FontCommand("Arial", this.fontName, this));
			document.compose();
			repaint();
		}
		if (e.getSource() == comic) {

			this.manager.execute(new FontCommand("Comic Sans MS", this.fontName, this));
			document.compose();
			repaint();
		}
		if (e.getSource() == wingdings) {

			this.manager.execute(new FontCommand("Wingdings", this.fontName, this));
			document.compose();
			repaint();
		}
		if (e.getSource() == increase) {
			
			this.manager.execute(new SizeCommand(this.fontSize+5, this.fontSize, this));
			document.compose();
			repaint();
		}
		if (e.getSource() == decrease) {

			this.manager.execute(new SizeCommand(this.fontSize-5, this.fontSize, this));
			document.compose();
			repaint();
			
		}
		if (e.getSource() == undo) {
			this.manager.undo();
			repaint();
		}
		if (e.getSource() == redo) {
			this.manager.redo();
			repaint();
		}
		if (e.getSource() == spellon) {
			
			this.handleSpellChecking();
			System.out.println("Spell Check implemented");
			document.compose();
			repaint();
		}

	}
	
	/**
	 * Changes the font
	 * @param newFont
	 */
	public void changeFont(String newFont) {
		this.fontName = newFont;
		this.font = new Font(fontName, Font.PLAIN, fontSize);
		repaint();
	}
	
	/**
	 * Changes the font size
	 * @param newSize
	 */
	public void changeFontSize(int newSize) {
		this.fontSize = newSize;
		this.font = new Font(fontName, Font.PLAIN, fontSize);
		repaint();
	}
	
	/**
	 * Adds the parameter to cursor
	 * Used when moving the cursor
	 * @param i cursor change
	 */
	public void changeCursor(int i) {
		this.cursor += i;
	}
	

	/**
	 * Returns cursor object
	 * @return cursor
	 */
	public int returnCursor() {
		return this.cursor;
	}


	/**
	 * Listens for mouse scrolling
	 */
	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		int notches = e.getWheelRotation();

		//Scrolling up
		if (notches < 0) {
			//Stops scrolling at top of page
			if (offset - (2 * notches) <= 0) {
				offset -= 2 * notches;
			}
			
		//Scrolling down
		} else if (notches > 0) {
			//Stops scrolling at bottom of page
			//Not working
			if (offset - (2 * notches) <= 200) {
				offset -= 2 * notches;
			}
			
		}
		repaint();
		
		
	}

}

