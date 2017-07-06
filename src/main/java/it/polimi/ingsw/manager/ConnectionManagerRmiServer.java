package it.polimi.ingsw.manager;

import java.rmi.Remote;
import java.rmi.RemoteException;

import it.polimi.ingsw.Cli.CliRmi;

public interface ConnectionManagerRmiServer extends Remote{
	public void getAnswer(String received, CliRmi cliRmi) throws RemoteException;
}
