package it.polimi.ingsw.manager;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
	//Main class from the server side
	private final static int SOCKET_PORT = 29999;
	private final String NAME = "connectionManager";
	private final static int RMI_PORT = 52365;
	
	public Server() throws RemoteException {}

	private void startRMI() throws RemoteException, AlreadyBoundException{

		//create the registry to publish remote objects
		LocateRegistry.createRegistry(RMI_PORT);
		Registry registry = LocateRegistry.getRegistry(RMI_PORT);
		System.out.println("Constructing the RMI registry");

		// getting connectionManagerImpl, then binding on the registry it with "connectionManager"
		ConnectionManagerImpl connectionManagerImpl = ConnectionManagerImpl.getConnectionManager();
		
		System.out.println("Binding the server implementation to the registry");
		registry.bind(NAME, connectionManagerImpl);
		
		System.out.println("Waiting for invocations from clients...");
	}
	
	private void startSocket() throws IOException {
		// creates the thread pool to handle clients
		ExecutorService executor = Executors.newCachedThreadPool();
		//creates the socket
		ServerSocket serverSocket = new ServerSocket(SOCKET_PORT);

		System.out.println("Server socket ready on port: " + SOCKET_PORT);
		
		while (true) {
			//Waits for a new client to connect
			Socket socket = serverSocket.accept();

			// creates the view (server side) associated with the new client
			ConnectionManagerImpl view = ConnectionManagerImpl.getConnectionManager();
			 
			User user = new User(socket);
			view.getUsers().add(user);
			
			// a new thread handle the connection with the view
			executor.submit(view);
		}
	}

	public static void main(String[] args) throws IOException, AlreadyBoundException {
		Server server = new Server();
		System.out.println("Starting RMI...");
		server.startRMI();
		System.out.println("Starting socket...");
		server.startSocket();
	}
}