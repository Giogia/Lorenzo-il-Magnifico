package it.polimi.ingsw.gui;

import java.io.PrintWriter;
import java.util.Scanner;

public class GuiSocketOutView {
	private PrintWriter socketOut;
	public GuiSocketOutView(PrintWriter socketOut) {
		this.socketOut = socketOut;
	}
	
	public void answer(String answer){
		socketOut.println(answer);
		socketOut.flush();
	}
	
	
}

