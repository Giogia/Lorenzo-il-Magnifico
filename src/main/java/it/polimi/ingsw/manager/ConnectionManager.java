package it.polimi.ingsw.manager;

import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import it.polimi.ingsw.view.CliRmi;


public interface ConnectionManager extends Remote{
	public void register(CliRmi client) throws RemoteException, NotBoundException;
}
