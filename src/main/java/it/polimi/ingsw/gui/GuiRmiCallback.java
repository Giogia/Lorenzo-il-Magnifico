package it.polimi.ingsw.gui;

import java.rmi.RemoteException;
import java.util.Scanner;

import it.polimi.ingsw.manager.ConnectionManagerRmiServer;

public class GuiRmiCallback implements Runnable{

	
	ConnectionManagerRmiServer rmiServer;
	private GuiRmiView guiRmiView;
	
	public GuiRmiCallback(ConnectionManagerRmiServer rmiServer, GuiRmiView guiRmiView){
		this.rmiServer = rmiServer;
		this.guiRmiView = guiRmiView;
	}
	
	public void answer(String answer) throws RemoteException {
		rmiServer.getAnswer(answer, guiRmiView);
		
	}

	@Override
	public void run() {
	}
}
