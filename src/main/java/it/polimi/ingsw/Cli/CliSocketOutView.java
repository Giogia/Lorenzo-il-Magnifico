package it.polimi.ingsw.Cli;

import java.io.PrintWriter;
import java.util.Scanner;

public class CliSocketOutView implements Runnable{
	private PrintWriter socketOut;
	private Scanner scanner = new Scanner(System.in);
	public boolean keepOn = true;
	
	public CliSocketOutView(PrintWriter socketOut) {
		this.socketOut = socketOut;
	}
	
	@Override
	public void run(){
		while(keepOn){
			if(scanner.hasNext()){
				socketOut.println(scanner.nextLine());
				socketOut.flush();
			}
		}
	}
	
}
