package it.polimi.ingsw.view;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Scanner;

public class ClientOutHandler implements Runnable {
	private ObjectOutputStream socketOut;

	public ClientOutHandler(ObjectOutputStream socketOut) {
		this.socketOut = socketOut;
	}

	@Override
	public void run() {

		// Handles output messages, from the client to the server
		System.out.println("RUNNING");
		Scanner stdIn = new Scanner(System.in);
		Scanner stdInt = new Scanner(System.in);
		while (true) {
			try {
				if(stdIn.hasNextLine()){
					socketOut.writeBytes(stdIn.nextLine());
				}
				if(stdInt.hasNextInt()){
					socketOut.writeByte(stdInt.nextInt());
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
