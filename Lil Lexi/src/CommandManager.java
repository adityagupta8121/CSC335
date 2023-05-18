/**
 * 
 * @author Gregory Jenkins
 * 
 * Assignment 2
 * 
 * CSC 335 Fall 2022
 * 
 * Command manager
 * 
 * Executes and stores any command given
 *
 */

import java.util.Stack;

public class CommandManager {
	
	private static CommandManager instance = null;
	private Stack<Command> past;
	private Stack<Command> future;
	
	private CommandManager() {
		past = new Stack<Command>();
		future = new Stack<Command>();
	}
	
	/**
	 * Creates a singletone CommandManager instance
	 * 
	 * @return instance of CommandManager
	 */
	static CommandManager getInstance() {
		
		if (instance == null) {
			instance = new CommandManager();
		}
		return instance;
	}
	
	/**
	 * Executes the given command. If reversible, adds it to the history
	 * 
	 * @param c input command
	 */
	void execute (Command c) {
		if (c.reversible()) {
			past.push(c);
		}
		c.execute();
		
	}
	
	/**
	 * Undoes the last command
	 */
	void undo () {
		if (past.size() > 0) {
			Command temp = past.pop();
			future.push(temp);
			
			temp.unexecute();
		}
	}
	
	/**
	 * Redoes the last undone command
	 */
	void redo () {
		if (future.size() > 0) {
			Command temp = future.pop();
			past.push(temp);
			
			temp.execute();
		}
		
	}

}
