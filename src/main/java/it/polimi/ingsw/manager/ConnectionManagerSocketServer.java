package it.polimi.ingsw.manager;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

import it.polimi.ingsw.gui.GuiRmiView;
import it.polimi.ingsw.manager.ActionSocket.action;

public class ConnectionManagerSocketServer implements Runnable {
	private Timer timer;
	private Scanner socketInClient;
	private ObjectOutputStream socketOutClient;
	private volatile String stringReceived;
	private volatile boolean isRightTurn = false;
	private volatile boolean isAvailable = false;
	private volatile boolean timeExpired = false;
	
	private final static Logger LOGGER = Logger.getLogger(ConnectionManagerSocketServer.class.getName());
	public static boolean keepOn = true;
	
	public ConnectionManagerSocketServer(Scanner socketInClient, ObjectOutputStream socketOutClient) {
		this.socketInClient = socketInClient;
		this.socketOutClient = socketOutClient;
	}
	
	@Override
	public void run() {
		while(keepOn){
			if(socketInClient.hasNextLine()){
				if(isRightTurn){
					stringReceived = socketInClient.nextLine();
					System.out.println(stringReceived);
					isAvailable=true;
					synchronized (this) {
						notifyAll();
					}
				}else{
					socketInClient.nextLine();
					ActionSocket act = new ActionSocket(action.notYourTurn);
					try {
						socketOutClient.writeObject(act);
						socketOutClient.flush();
					} catch (IOException e) {
						LOGGER.log(Level.SEVERE, e.getMessage(),e);
					}
				}
			}
		}
	}
	
	public void cancelTimer(){
		timer.cancel();
	}
	
	public String getStringReceived() {
		isAvailable = false;
		return stringReceived;
	}
	
	public boolean getIsAvailable(){
		return isAvailable;
	}
	
	public void setIsRightTurn(boolean isRightTurn){
		this.isRightTurn = isRightTurn;
	}
	
	public ObjectOutputStream getSocketOutClient() {
		return socketOutClient;
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
		private ConnectionManagerSocketServer con;
		
		public ClientTimer(ConnectionManagerSocketServer con) {
			this.con = con;
		}

		@Override
		public void run() {
			con.isRightTurn = false;
			con.timeExpired = true;
			con.endTurn();
		}
		
	}


	public void endTurn() {
		synchronized (this) {
			notifyAll();
		}
	}
}
