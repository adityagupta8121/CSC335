/**
 * @author Gregory Jenkins
 * 
 * CSC 335 Fall 2022
 * 
 * Client class, sends key presses to the server and receives the game state.
 */

import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class ClientTest {
	
	private Socket socket;
	private BufferedWriter outputToServer;
	private ObjectInputStream inputFromServer;
	private View view;

	
	public ClientTest(Socket socket) {
		try {
			this.socket = socket;
			this.outputToServer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			this.inputFromServer = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));

		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Sends a character to the server
	 * 
	 * @param c Character being sent
	 */
	public void sendKey(char c) {
		try {
			outputToServer.write(c);
			outputToServer.flush();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Receives the updated game object from the server and updates local copy.
	 */
	public void getGameUpdate() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				while (socket.isConnected()) {
					try {
							Object obj = inputFromServer.readObject();
							View.game = (Game) obj;
							View.game.drawAll(view);
						//}
					} catch (IOException | ClassNotFoundException e) {
						e.printStackTrace();
					}
				}
				
			}
			
		}).start();
	}
	
	/**
	 * Sets the local view object
	 * @param v
	 */
	public void setView(View v) {
		this.view = v;
	}


}
