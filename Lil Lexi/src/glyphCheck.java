/**
 * @author adityagupta
 * glyphCheck made for spellCheck
 */
import java.awt.Point;

public class glyphCheck {

	private Glyph glyph;
	private Point position;
	private int physicalIndex;
	
	public glyphCheck(Glyph glyph, Point position, int physicalIndex){
		this.glyph = glyph;
		this.position = position;
		this.physicalIndex = physicalIndex;
	}
	
	public Glyph getGlyph(){
		return this.glyph;
	}
	
	public Point getPosition(){
		return this.position;
	}
	
	public void setPosition(Point position){
		this.position = position;
	}
	
	public int getPhysicalIndex(){
		return this.physicalIndex;
	}
}

