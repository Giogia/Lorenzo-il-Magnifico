package it.polimi.ingsw.gui;

import java.io.PrintWriter;

public class GuiSocketOutView implements Runnable {
	private String toSend = ""; //at the beginning answer is empty
	private PrintWriter socketOut;
	
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
						// TODO Auto-generated catch block
						e.printStackTrace();
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

