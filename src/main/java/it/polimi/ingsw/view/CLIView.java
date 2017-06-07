package it.polimi.ingsw.view;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import it.polimi.ingsw.manager.ConnectionManager;

public class CLIView {
	private final static int RMI_PORT = 52365;
	private final static int SOCKET_PORT = 29999;
	private static final String NAME = "connectionManager";
	
	public static void main(String[] args) throws RemoteException, NotBoundException{
		Registry registry = LocateRegistry.getRegistry("localhost", RMI_PORT);
		System.out.println("preso referenza al registry");
		
		ConnectionManager connectionManager = (ConnectionManager) registry.lookup(NAME);
		System.out.println("connesso al connectionManager");
		
		ClientRMICallbackRemote client = new ClientRMICallbackImpl();

		UnicastRemoteObject.exportObject(client, 0);

		connectionManager.register(client);
	}
}
