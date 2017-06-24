package it.polimi.ingsw.gui;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import it.polimi.ingsw.manager.ConnectionManager;
import it.polimi.ingsw.manager.ConnectionManagerRmiServer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GuiRmiView extends Application {
	Scene scene;
	Scene sceneGame;
	Stage primaryStage;
	private final static int RMI_PORT = 52365;
	private static final String NAME = "connectionManager";
	
	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader login = new FXMLLoader(getClass().getResource("Game.fxml"));
			
			scene = new Scene(login.load());
			
			scene.getStylesheets().add(getClass().getResource("styleGame.css").toExternalForm());
			
			GuiController controller = login.getController();
			
			primaryStage.setTitle("Lorenzo Il Magnifico");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws RemoteException, NotBoundException {
		Registry registry = LocateRegistry.getRegistry("localhost", RMI_PORT);
		System.out.println("preso referenza al registry");
		
		ConnectionManager connectionManager = (ConnectionManager) registry.lookup(NAME);
		ConnectionManagerRmiServer connectionManagerRmiServer = (ConnectionManagerRmiServer) registry.lookup("connectionManagerRmiServer");
		System.out.println("connesso al connectionManager");
		
		ClientGui client = new ClientGui();

		UnicastRemoteObject.exportObject(client, 0);

		ExecutorService executor = Executors.newSingleThreadExecutor();
		executor.submit(new GuiRmiCallback(connectionManagerRmiServer, client));
		
		connectionManager.register(client);//client register himself on server

		//starting gui
		Application.launch(args);
	}
}