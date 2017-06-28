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
import java.util.logging.Logger;

import it.polimi.ingsw.manager.ConnectionManagerRmiServerImpl;
import it.polimi.ingsw.view.CliSocketInOutView;
import it.polimi.ingsw.view.CliSocketOutView;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GuiSocketView extends Application{
	static boolean wait = true;
	Scene scene;
	Scene sceneGame;
	Stage primaryStage;
	private final static String IP= "localhost";
	private final static int SOCKET_PORT = 29999;
	private static ConnectionManagerRmiServerImpl connectionManagerRmiServerImpl;
	private static GuiSocketInView clientIn;
	private static GuiSocketOutView callback;
	
	private final static Logger LOGGER = Logger.getLogger(GuiSocketView.class.getName());
	
	@Override
	public void start(Stage primaryStage) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Game.fxml"));
		scene = new Scene(loader.load());
		scene.getStylesheets().add(getClass().getResource("styleGame.css").toExternalForm());
		
		GuiController controller = new GuiController();
		loader.setController(controller);
		controller.setLoader(loader);
		
		primaryStage.setTitle("Lorenzo Il Magnifico");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public void startClient() throws UnknownHostException, IOException {

		Socket socket = new Socket(IP, SOCKET_PORT);

		System.out.println("Connection created");

		ExecutorService executor = Executors.newFixedThreadPool(2);

		//Creates one thread to send messages to the server
		callback = new GuiSocketOutView(new PrintWriter(socket.getOutputStream()));

		//Creates one thread to receive messages from the server
		GuiSocketInView clientIn = new GuiSocketInView(new ObjectInputStream(socket.getInputStream()), new PrintWriter(socket.getOutputStream()));
		executor.submit(clientIn);
		showStage();
	}
	
	public static void main(String[] args) throws RemoteException, NotBoundException, UnknownHostException, IOException {
		GuiSocketView client = new GuiSocketView();
		client.startClient();
	}
	
	public static GuiSocketOutView getCallback() {
		return callback;
	}
	
	
	public void showStage(){
		/*while(wait){
			synchronized (this) {
				try {
					wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					private final static Logger LOGGER = Logger.getLogger(MyLogger.class.getName());
				}
			}
		}
		*/
		Application.launch();
	}
}

