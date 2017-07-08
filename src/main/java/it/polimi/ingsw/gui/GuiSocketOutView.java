package it.polimi.ingsw.gui;

import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GuiSocketOutView implements Runnable {
	private boolean lastToSend = true;
	private static volatile Object lock = new Object();
	private String toSend = ""; //at the beginning answer is empty
	private PrintWriter socketOut;
	private final static Logger LOGGER = Logger.getLogger(GuiSocketView.class.getName());
	public static volatile boolean serverPass = true;
	public boolean keepOn = true;
	
	public static void setServerPass(boolean serverPass) {
		GuiSocketOutView.serverPass = serverPass;
	}
	
	public static Object getLock() {
		return lock;
	}
	
	public GuiSocketOutView(PrintWriter socketOut) {
		this.socketOut = socketOut;
	}
	
	public synchronized void setToSend(String toSend) {
		this.toSend = toSend;
		synchronized (this) {
			this.notifyAll();
		}
	}

	@Override
	public void run() {
		while(keepOn){
			while("".equals(toSend)){
				synchronized (this) {
					try {
						this.wait();
					} catch (InterruptedException e) {
						LOGGER.log(Level.SEVERE, e.getMessage(),e);
						Thread.currentThread().interrupt();
					}
				}
			}
			String answer;
			while(toSend.contains("$")){
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
				answer = toSend.substring(0, toSend.indexOf('$'));
				
				socketOut.println(answer);
				socketOut.flush();
				
		        toSend = toSend.substring(2);
		        serverPass = false; //answer only at a one question end then can't talk
		        lastToSend = true;
			}
			if (lastToSend){
				synchronized (this) {
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
				}
				lastToSend = false;
			}
			//Here thread has been notified. It means there is a string to send to server
			socketOut.println(toSend);
			socketOut.flush();
			toSend="";
		}
	}
}

