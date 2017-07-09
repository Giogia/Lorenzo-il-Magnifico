package it.polimi.ingsw.gui;

import java.rmi.RemoteException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import it.polimi.ingsw.manager.ConnectionManagerRmiServer;

//class that receive input from gui and send to server as a rmi client
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
		String answerSubstring = answer;
		while(answerSubstring.contains("$")){
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
			
			System.out.println("la stringa da inviare è: " + answerSubstring);
			String toSend = answerSubstring.substring(0, answerSubstring.indexOf('$'));
			System.out.println("toSend (primo numero da inviare) vale:" + toSend);
	        rmiServer.getAnswer(toSend, guiRmiView);
	        answerSubstring = answerSubstring.substring(2);
	        System.out.println("stringa ancora da inviare vale: "+answerSubstring);
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
		System.out.println("sono uscito dal while e answer vale:" + answerSubstring);
		System.out.println(serverPass);
		rmiServer.getAnswer(answerSubstring, guiRmiView);
	}
}
