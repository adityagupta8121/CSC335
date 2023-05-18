/**
 * 
 * @author Gregory Jenkins
 * 
 * Assignment 2
 * 
 * CSC 335 Fall 2022
 * 
 * Compositor abstract class
 *
 */

import java.util.ArrayList;

public abstract class Compositor {

	protected Composition composition;

	void SetComposition(Composition composition) {
		this.composition = composition;
	}

	ArrayList<Glyph> Compose() {
		return null;

	}
}
