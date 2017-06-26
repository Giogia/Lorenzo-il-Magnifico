package it.polimi.ingsw.manager;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import it.polimi.ingsw.view.CliRmi;

public class ConnectionManagerRmiServerImpl extends UnicastRemoteObject implements ConnectionManagerRmiServer{
	private boolean isAvailable = false;
	private String stringReceived;
	private boolean isRightTurn = false;
	
	public ConnectionManagerRmiServerImpl() throws RemoteException{
	}
	
	@Override
	public void getAnswer(String stringReceived, CliRmi cliRmi) throws RemoteException{
		if( isRightTurn ){ //if the user that talks is the right user
			this.stringReceived = stringReceived;
			isAvailable = true;
			synchronized (this) {
				notifyAll();
			}
		}else{
			cliRmi.isNotYourTurn(); //tell to the user it's not his turn
		}
	}
	
	public String getStringReceived() {
		isAvailable = false;
		return stringReceived;
	}
	
	public boolean getIsAvailable(){
		return isAvailable;
	}
	
	public boolean getIsRightTurn(){
		return isRightTurn;
	}
	
	public void setIsRightTurn(boolean isRightTurn){
		this.isRightTurn = isRightTurn;
	}
}
