package it.polimi.ingsw.manager;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import it.polimi.ingsw.view.CliRmi;

public class ConnectionManagerRmiServerImpl extends UnicastRemoteObject implements ConnectionManagerRmiServer{
	private boolean isAvailable = false;
	private String stringReceived;
	private CliRmi cliRmi = null; //it's the right user
	
	public ConnectionManagerRmiServerImpl() throws RemoteException{
	}
	
	@Override
	public void getAnswer(String stringReceived, CliRmi cliRmi) throws RemoteException{
		if(cliRmi.equals(this.cliRmi)){ //if the user that talks is the right user
			this.stringReceived = stringReceived;
			isAvailable = true;
			synchronized (this) {
				notifyAll();
			}
		}else{
			cliRmi.isNotYourTurn(); //tell to the user it's not his turn
		}
	}
	
	public synchronized String getStringReceived() {
		isAvailable = false;
		cliRmi = null;
		notifyAll();
		return stringReceived;
	}
	
	public boolean getIsAvailable(){
		return isAvailable;
	}
	
	public void setCliRmi(CliRmi cliRmi) {
		this.cliRmi = cliRmi;
	}
	
	public CliRmi getCliRmi() {
		return cliRmi;
	}
}
