/**
 * 
 * @author Gregory Jenkins
 * 
 * Assignment 2
 * 
 * CSC 335 Fall 2022
 * 
 * Command interface

 *
 */

public interface Command {
	
	
	public void execute();
	
	public void unexecute();
	
	abstract boolean reversible();

}
