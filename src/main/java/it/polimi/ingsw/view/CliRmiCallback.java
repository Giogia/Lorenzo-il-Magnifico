package it.polimi.ingsw.view;

import java.rmi.RemoteException;
import java.util.Scanner;

import it.polimi.ingsw.manager.ConnectionManagerRmiServer;

public class CliRmiCallback implements Runnable{
	ConnectionManagerRmiServer rmiServer;
	Scanner scanner = new  Scanner(System.in);
	CliRmiView cliRmiView; // the stub of the user
	
	public CliRmiCallback(ConnectionManagerRmiServer rmiServer, CliRmiView cliRmiView){
		this.rmiServer = rmiServer;
		this.cliRmiView = cliRmiView;
	}

	@Override
	public void run() {
		while(true){
			if(scanner.hasNext()){
				try {
					rmiServer.getAnswer(scanner.nextLine(), cliRmiView);
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
