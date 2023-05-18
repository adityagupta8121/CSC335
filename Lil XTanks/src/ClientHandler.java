/**
 * @author Gregory Jenkins
 * 
 * CSC 335 Fall 2022
 * 
 * Handles each input and output connection for all clients.
 * 
 */

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Random;

public class ClientHandler implements Runnable {
	
	public static ArrayList<ClientHandler> clientHandlers = new ArrayList<>();
	private Socket socket;
	private BufferedReader inputFromClient;
	private ObjectOutputStream outputToClient;
	private Tank clientTank;

	
	public ClientHandler(Socket socket) {
		try {
			this.socket = socket;
			inputFromClient = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			outputToClient = new ObjectOutputStream(new BufferedOutputStream(socket.getOutputStream()));
			

			
			Random rand = new Random();
			int x = 75 + rand.nextInt(350);
			int y = 40 + rand.nextInt(200);
			if (y > 140) {
				y += 220;
			}

			clientTank = new Tank(x, y);
			Server.game.addTank(clientTank);
			
			
			clientHandlers.add(this);
			this.updateClients();
			

		} catch (IOException e) {
			//closeEverything(socket, inputFromClient, outputToClient);
		}
	}

	/**
	 * Closes all connections to the socket.
	 * 
	 * @param socket
	 * @param inputFromClient
	 * @param outputToClient
	 */
	private void closeEverything(Socket socket, BufferedReader inputFromClient, ObjectOutputStream outputToClient) {
		removeClientHandler();
		try {
			if (inputFromClient != null) {
				inputFromClient.close();
			}
			if (outputToClient != null) {
				outputToClient.close();
			}
			if (socket != null) {
				socket.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * Gets input from client and updates their tanks based on key pressed.
	 */
	@Override
	public void run() {
		
		char keyPressed;

		while (socket.isConnected()) {
			try {
				
				keyPressed = (char) inputFromClient.read();

				
				switch (keyPressed) {
				case 'w':
					if (clientTank.isVisible()) {
						clientTank.moveForward();
					}
					break;
				case 'a':
					if (clientTank.isVisible()) {
						clientTank.rotateLeft();
					}
					break;
				case 'd':
					if (clientTank.isVisible()) {
						clientTank.rotateRight();
					}
					break;
				case 's':
					if (clientTank.isVisible()) {
						clientTank.moveBackwards();
					}
					break;
				case 'f':
					if (clientTank.isVisible()) {
						clientTank.fire();
					}
					
					break;
				}
				
				
			} catch (IOException e) {
				//closeEverything(socket, inputFromClient, outputToClient);
				//break;
			}
			this.updateClients();
		}
	}
	
	/**
	 * Removes the client from the list.
	 */
	public void removeClientHandler() {
		clientHandlers.remove(this);
	}
	
	
	/**
	 * Updates all clients and sends updated game object.
	 */
	public void updateClients() {
		for (ClientHandler client : clientHandlers) {
			try {
				client.outputToClient.reset();
				client.outputToClient.writeObject(Server.game);
				
				client.outputToClient.flush();
				
			} catch (IOException e) {
				//closeEverything(socket, inputFromClient, outputToClient);
			}
		}
	}

}
