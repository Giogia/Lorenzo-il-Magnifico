package it.polimi.ingsw.manager;

import java.io.IOException;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

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
		//TODO
	}

	public static void main(String[] args) throws IOException, AlreadyBoundException {
		Server server = new Server();
		System.out.println("START RMI");
		server.startRMI();
		System.out.println("START SOCKET----------TODO");
		//TODO: to implement
		server.startSocket();
	}
}