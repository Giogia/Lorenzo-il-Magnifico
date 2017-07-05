package it.polimi.ingsw.gui;

import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GuiSocketOutView implements Runnable {
	private String toSend = ""; //at the beginning answer is empty
	private PrintWriter socketOut;
	private final static Logger LOGGER = Logger.getLogger(GuiSocketView.class.getName());
	
	public GuiSocketOutView(PrintWriter socketOut) {
		this.socketOut = socketOut;
	}
	
	public synchronized void setToSend(String toSend) {
		this.toSend = toSend;
		notifyAll();
	}

	@Override
	public void run() {
		while(true){
			synchronized (this) {
				while(toSend.equals("")){
					try {
						this.wait();
					} catch (InterruptedException e) {
						LOGGER.log(Level.SEVERE, e.getMessage(),e);
						Thread.currentThread().interrupt();
					}
				}
				//Here thread has been notified. It means there is a string to send to server
				socketOut.println(toSend);
				socketOut.flush();
				toSend="";
			}
		}
	}
}

