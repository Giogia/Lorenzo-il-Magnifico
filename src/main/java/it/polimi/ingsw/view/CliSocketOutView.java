package it.polimi.ingsw.view;

import java.io.PrintWriter;
import java.util.Scanner;

public class CliSocketOutView implements Runnable{
	private PrintWriter socketOut;
	private Scanner scanner = new Scanner(System.in);
	
	public CliSocketOutView(PrintWriter socketOut) {
		this.socketOut = socketOut;
	}
	
	@Override
	public void run(){
		while(true){
			if(scanner.hasNext()){
				socketOut.println(scanner.nextLine());
				socketOut.flush();
			}
		}
	}
	
}
