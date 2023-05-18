/**
 * @author adityagupta
 * Spell Check Controller
 * Controls spelling errors and highlights the word when misspelled word is found
 */

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class SpellCheckController implements ErrorHandlerSpelling{

	
	Graphics graphics;
	Composition document;
	private int index;
	private Boolean spellCheckEnabled;
	private List<glyphCheck[]> misspelledGlyphs;
	private Document Docu;
	
	public SpellCheckController(Composition document){
		this.index = 0;		
		this.document = document;
		this.spellCheckEnabled = true;
	}	

	@Override
	public void handleSpellingError(String word, glyphCheck[] glyphs) {
		// TODO Auto-generated method stub
		for (glyphCheck gGlyph : glyphs){
			gGlyph.getGlyph().select(this.graphics, Color.RED, Color.WHITE, gGlyph.getPosition().x, gGlyph.getPosition().y);
		}
	}

	
	public void handleDrawing(List<Row> rows, MonoGlyph args){
		this.Docu.draw(rows);
		this.update(args);
		if(this.spellCheckEnabled) {
			iteratorVis visitor = new spellCheckVis(this);			
			for(Row row : rows){
				row.accept(visitor);
			}
		}
	}

	public int getIndex(){
		return this.index;
	}
	
	public void setGraphics(Graphics graphics){
		this.graphics = graphics;	
	}
	
	public void update(MonoGlyph args){
		int i, j;
		Point temp = new Point(Integer.MIN_VALUE, Integer.MIN_VALUE);
		
		for (i = 0; i < this.index; i++){
			Row currentRow = this.Docu.getRows().get(i);
			currentRow.setLocation(Integer.MIN_VALUE,Integer.MIN_VALUE);
			for (glyphCheck gGlyph : currentRow.getgGlyphs()){
				gGlyph.setPosition(temp);
			}
		}
				
		int currentTop = args.getHeight();
		int currentLeft = args.getWidth();
		
		for (j = i; j < this.Docu.getRows().size(); j++){
			Row currentRow = this.Docu.getRows().get(j);
			currentRow.setLocation(currentTop,currentLeft);
			for (glyphCheck uiGlyph : currentRow.getgGlyphs()){
				Point position = new Point(currentLeft, currentTop);
				uiGlyph.setPosition(position);
				currentLeft += uiGlyph.getGlyph().getWidth() + 2;
			}	
			currentTop += currentRow.getHeight();
			currentLeft = args.getWidth();
		}
	}
}