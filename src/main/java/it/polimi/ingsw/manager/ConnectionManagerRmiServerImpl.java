package it.polimi.ingsw.manager;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Timer;
import java.util.TimerTask;

import it.polimi.ingsw.view.CliRmi;

public class ConnectionManagerRmiServerImpl extends UnicastRemoteObject implements ConnectionManagerRmiServer{
	private Timer timer;
	private boolean isAvailable = false;
	private String stringReceived;
	private boolean isRightTurn = false;
	private boolean timeExpired = false;
	
	public ConnectionManagerRmiServerImpl() throws RemoteException{
	}
	
	@Override
	public void getAnswer(String stringReceived, CliRmi cliRmi) throws RemoteException{
		if( isRightTurn ){ //if the user that talks is the right user
			this.stringReceived = stringReceived;
			System.out.println(stringReceived);
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
	
	public boolean getTimeExpired(){
		return timeExpired;
	}
	
	public void setTimeExpired(boolean timeExpired) {
		this.timeExpired = timeExpired;
	}
	
	public void startTurn(){
		timer = new Timer();
		ClientTimer clientTimer = new ClientTimer(this);
		timer.schedule(clientTimer, ConnectionManagerImpl.getTimerTurn());
	}
	

	private class ClientTimer extends TimerTask{
		private ConnectionManagerRmiServerImpl con;
		
		public ClientTimer(ConnectionManagerRmiServerImpl con) {
			this.con = con;
		}

		@Override
		public void run() {
			con.endTurn();
		}
		
	}


	public void endTurn() {
		isRightTurn = false;
		timeExpired = true;
		synchronized (this) {
			notifyAll();
		}
	}
	
	public void cancelTimer(){
		timer.cancel();
	}
}
