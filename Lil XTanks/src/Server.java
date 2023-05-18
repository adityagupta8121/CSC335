/**
 * @author Gregory Jenkins
 * 
 * CSC 335 Fall 2022
 * 
 * Run this to start the server.
 * 
 * Stores a game instance and sends it to the clients to update their local copy every 1/30 of a second.
 * 
 * 
 */

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class Server {
	
	private ServerSocket serverSocket;
	public static Game game;
	private boolean isRunning;
	private Thread gameLoop;
	private ClientHandler clientHandler;
	

	
	public Server(ServerSocket serverSocket) {
		this.serverSocket = serverSocket;
	}
	
	/**
	 * Starts the server and the game loop. Loads the chosen map and updates the clients games.
	 */
	public void startServer() {
		Map m = new Map("src/Map2");
		Server.game = new Game(m);
		System.out.println("XTanks server started.");
		this.setupGameLoop();
		this.isRunning = true;
		this.gameLoop.start();

		try {
			while (!serverSocket.isClosed()) {
				Socket socket = serverSocket.accept();
				System.out.println("Connected");
				clientHandler = new ClientHandler(socket);
				
				Thread thread = new Thread(clientHandler);
				thread.start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
	/**
	 * Starts the game loop thread. Updates missiles position and updates all clients
	 * games every 1/30 of a second.
	 */
	private void setupGameLoop() {
		gameLoop = new Thread(() -> {
			while (isRunning) {
				Server.game.updateMissiles();
				
				if (clientHandler != null) {
					clientHandler.updateClients();
				}
				
				try {
                    Thread.sleep(33);
                } catch (InterruptedException ex) {
                }
			}
		});
		
	}
	
	public static void main(String[] args) {
		
		try {
			ServerSocket socket = new ServerSocket(1234);
			Server server = new Server(socket);
			server.startServer();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	


}
