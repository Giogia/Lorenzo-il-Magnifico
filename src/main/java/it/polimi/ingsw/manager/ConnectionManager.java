package it.polimi.ingsw.manager;

import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;

import it.polimi.ingsw.view.CLIView;
import it.polimi.ingsw.view.ClientRMICallbackRemote;


public interface ConnectionManager extends Remote{
	public void register(ClientRMICallbackRemote client) throws RemoteException, NotBoundException;
}
