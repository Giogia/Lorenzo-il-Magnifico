package it.polimi.ingsw.gui;

import java.io.PrintWriter;
import java.util.Scanner;

public class GuiSocketOutView implements Runnable {
	private PrintWriter socketOut;
	private String answer;
	public GuiSocketOutView(PrintWriter socketOut) {
		this.socketOut = socketOut;
	}
	
	@Override
	public void run(){
		while(true){
			if(answer!=null){
				socketOut.println(answer);
				socketOut.flush();
			}
		}
	}
	
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	
}

