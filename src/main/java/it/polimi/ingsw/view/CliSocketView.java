package it.polimi.ingsw.view;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import it.polimi.ingsw.BOARD.ActionZone;
import it.polimi.ingsw.BOARD.Position;
import it.polimi.ingsw.BONUS.ResourceBonus;
import it.polimi.ingsw.GC_15.FamilyMember;
import it.polimi.ingsw.GC_15.PersonalBoard;
import it.polimi.ingsw.RESOURCE.Resource;

public class CliSocketView{
	private final static String IP= "localhost";
	private final static int SOCKET_PORT = 29999;
		
	public void startClient() throws UnknownHostException, IOException {

		Socket socket = new Socket(IP, SOCKET_PORT);

		System.out.println("Connection created");

		ExecutorService executor = Executors.newFixedThreadPool(2);

		//Creates one thread to send messages to the server
		executor.submit(new CliSocketOutView(new PrintWriter(socket.getOutputStream())));
		
		//Creates one thread to receive messages from the server
		executor.submit(new CliSocketInView(new ObjectInputStream(socket.getInputStream()), new PrintWriter(socket.getOutputStream())));
	}
	
	public static void main(String[] args) throws UnknownHostException, IOException{
		CliSocketView client=new CliSocketView();
		client.startClient();
	}
}
