package it.polimi.ingsw.gui;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

import it.polimi.ingsw.Cli.CliSocketInView;
import it.polimi.ingsw.Cli.CliSocketOutView;
import it.polimi.ingsw.manager.ConnectionManagerRmiServerImpl;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

//creates a client using gui
public class GuiSocketView extends Application{
	static boolean wait = true;
	private final static String IP= "localhost";
	private final static int SOCKET_PORT = 29999;
	private static GuiSocketInView clientIn;
	private static GuiSocketOutView callback;
	private static Object lock = new Object(); //lock to wait the answer from server with game
	private final static Logger LOGGER = Logger.getLogger(GuiSocketView.class.getName());
	private Socket socket;
	
	public static GuiSocketOutView getCallback() {
		return callback;
	}
	
	public static Object getLock() {
		return lock;
	}
	
	@Override
	public void start(Stage primaryStage) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Game.fxml"));
		GuiController controller = new GuiController(GuiSocketInView.getGame());
		controller.setIsRmiClient(false);
		loader.setController(controller);
		clientIn.setController(controller);
		
		Scene scene = new Scene(loader.load());
		scene.getStylesheets().add(getClass().getResource("styleGame.css").toExternalForm());
		
		primaryStage.setTitle("Lorenzo Il Magnifico");
		primaryStage.setScene(scene);
		primaryStage.setFullScreen(true);
		primaryStage.show();
	}
	
	public void showStage(){
		synchronized (lock) {
			while(wait){
				try {
					lock.wait();
				} catch (InterruptedException e) {
					LOGGER.log(Level.SEVERE, e.getMessage(),e);
					Thread.currentThread().interrupt();
				}
			}
		}
		Application.launch();
	}
		
	public void startClient() throws UnknownHostException, IOException {
		socket = new Socket(IP, SOCKET_PORT);

		System.out.println("Connection created");

		ExecutorService executor = Executors.newFixedThreadPool(2);

		//Creates one thread to send messages to the server
		callback = new GuiSocketOutView(new PrintWriter(socket.getOutputStream()));
		executor.submit(callback);
		
		//Creates one thread to receive messages from the server
		clientIn = new GuiSocketInView(new ObjectInputStream(socket.getInputStream()), new PrintWriter(socket.getOutputStream()));
		clientIn.setSocketOut(callback);
		executor.submit(clientIn);
		showStage();
	}
	
	public static void main(String[] args) throws RemoteException, NotBoundException, UnknownHostException, IOException {
		GuiSocketView client = new GuiSocketView();
		client.startClient();
	}
	
	public void close() throws IOException{
		socket.close();
		Thread.currentThread().interrupt();
	}

}

