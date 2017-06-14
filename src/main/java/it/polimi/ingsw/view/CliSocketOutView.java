package it.polimi.ingsw.view;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class CliSocketOutView implements Runnable {
	private PrintWriter socketOut;

	public CliSocketOutView(PrintWriter socketOut) {
		this.socketOut = socketOut;
	}

	@Override
	public void run() {

		// Handles output messages, from the client to the server
		System.out.println("RUNNING");
		Scanner stdIn = new Scanner(System.in);
		while (true) {
			if(stdIn.hasNextLine()){
				socketOut.println((stdIn.nextLine()));
				socketOut.flush();
			}
			if(stdIn.hasNextInt()){
				int i = stdIn.nextInt();
				socketOut.print(i);
				socketOut.flush();
			}
		}
	}
}
