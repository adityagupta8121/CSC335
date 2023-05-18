/**
 * @author adityagupta
 * Spell Check Visitor
 */

import java.util.ArrayList;
import java.util.List;

public class spellCheckVis extends iteratorVis{

	private StringBuffer currentWord;
	private List<Glyph> currentGlyphs;
	private List<glyphCheck> gGlyphs;
	private ErrorHandlerSpelling ErrorHandler;
	
	public spellCheckVis() {
		this.currentWord = new StringBuffer();
		this.currentGlyphs = new ArrayList<Glyph>();
		this.gGlyphs = new ArrayList<glyphCheck>();
	}
	
	public spellCheckVis(ErrorHandlerSpelling ErrorHandler) {
		this();
		this.ErrorHandler = ErrorHandler;
	}
	@Override
	public void visitChar(Char character) {
		if (Character.isAlphabetic(character.getCharacterCode())|| Character.isDigit(character.getCharacterCode())) {
			this.currentWord.append(character.getChar());
			this.currentGlyphs.add(character);
		} 
		else {
			this.gGlyphs.remove(this.gGlyphs.size() - 1);
			this.spellCheck();
			this.gGlyphs.clear();
		}
	}

	
	private void spellCheck() {
		String word = this.currentWord.toString();
		if (!word.equals("") && SpellCheck.getInstance().isMisspelled(word)) {			
			if (this.ErrorHandler != null) {
				this.ErrorHandler.handleSpellingError(this.currentWord.toString(),this.gGlyphs.toArray(new glyphCheck[this.gGlyphs.size()]));
			}
		}

		this.currentWord = new StringBuffer();
		this.currentGlyphs.clear();
	}

	@Override
	public void visitRow(Row row) {
		List<glyphCheck> glyphs = row.getgGlyphs();
		for (glyphCheck gGlyph : glyphs) {
			this.gGlyphs.add(gGlyph);
			gGlyph.getGlyph().accept(this);
		}
		if (this.currentWord.length() > 0) {
			this.spellCheck();
		}
	}

	@Override
	public void visitPicture(Picture picture) {
		// TODO Auto-generated method stub
		
	}	
}
