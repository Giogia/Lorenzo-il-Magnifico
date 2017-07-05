package it.polimi.ingsw.gui;

import java.rmi.RemoteException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import it.polimi.ingsw.manager.ConnectionManagerRmiServer;

public class GuiRmiCallback{
	private boolean lastToSend = false;
	private static volatile Object lock = new Object();
	private static volatile boolean serverPass = true;
	private ConnectionManagerRmiServer rmiServer;
	private GuiRmiView guiRmiView;
	private final static Logger LOGGER = Logger.getLogger(GuiSocketView.class.getName());
	
	public static void setServerPass(boolean serverPass) {
		GuiRmiCallback.serverPass = serverPass;
	}
	
	public static Object getLock() {
		return lock;
	}
	
	public GuiRmiCallback(ConnectionManagerRmiServer rmiServer, GuiRmiView guiRmiView){
		this.rmiServer = rmiServer;
		this.guiRmiView = guiRmiView;
	}
	
	public void answer(String answer) throws RemoteException {
		synchronized (lock) {
			while(!serverPass){
				try {
					lock.wait();
				} catch (InterruptedException e) {
					LOGGER.log(Level.SEVERE, e.getMessage(),e);
					Thread.currentThread().interrupt();
				}
			}
		}//received pass from server
		while(answer.contains("$")){
			
			String toSend = answer.substring(0, answer.indexOf("$"));
			System.out.println("toSend vale:" + toSend+"prova");
	        rmiServer.getAnswer(toSend, guiRmiView);
	        answer = answer.substring(2);
	        System.out.println("answer vale:"+answer);
	        serverPass = false; //answer only at a one question end then can't talk
	        lastToSend = true;
		}
		if(lastToSend){
			synchronized (lock) {
				while(!serverPass){
					try {
						lock.wait();
					} catch (InterruptedException e) {
						LOGGER.log(Level.SEVERE, e.getMessage(),e);
						Thread.currentThread().interrupt();
					}
				}
			}//received pass from server
			lastToSend = false;
		}
		System.out.println("sono uscito dal while e answer vale:" + answer);
		System.out.println(serverPass);
		rmiServer.getAnswer(answer, guiRmiView);
	}
}
