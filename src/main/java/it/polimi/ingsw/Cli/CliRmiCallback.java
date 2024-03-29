package it.polimi.ingsw.Cli;

import java.rmi.RemoteException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import it.polimi.ingsw.manager.ConnectionManagerRmiServer;

//class that answer the server when using cli
public class CliRmiCallback implements Runnable{
	ConnectionManagerRmiServer rmiServer;
	Scanner scanner = new  Scanner(System.in);
	CliRmiView cliRmiView; // the stub of the user
	
	private final static Logger LOGGER = Logger.getLogger(CliRmiCallback.class.getName());
	public boolean keepOn = true;
	
	public CliRmiCallback(ConnectionManagerRmiServer rmiServer, CliRmiView cliRmiView){
		this.rmiServer = rmiServer;
		this.cliRmiView = (CliRmiView) cliRmiView;
	}

	@Override
	public void run() {
		while(keepOn){
			if(scanner.hasNext()){
				try {
					rmiServer.getAnswer(scanner.nextLine(), cliRmiView);
				} catch (RemoteException e) {
					LOGGER.log(Level.SEVERE, e.getMessage(),e);
				}
			}
		}
	}
}
