package it.polimi.ingsw.gui;

import java.rmi.RemoteException;
import java.util.Scanner;

import it.polimi.ingsw.manager.ConnectionManagerRmiServer;

public class GuiRmiCallback implements Runnable{

	
	ConnectionManagerRmiServer rmiServer;
	Scanner scanner = new  Scanner(System.in);//TODO passare stringa
	ClientGui clientGui = new ClientGui(); // the stub of the user
	
	public GuiRmiCallback(ConnectionManagerRmiServer rmiServer, ClientGui clientGui){
		this.rmiServer = rmiServer;
		this.clientGui = clientGui;
	}

	@Override
	public void run() {
		while(true){
			if(scanner.hasNext()){//TODO passare stringa
				try {
					rmiServer.getAnswer(scanner.nextLine(), clientGui);
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
