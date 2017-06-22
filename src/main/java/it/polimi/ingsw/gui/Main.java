package it.polimi.ingsw.gui;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import it.polimi.ingsw.manager.ConnectionManager;
import it.polimi.ingsw.view.CliRmiView;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class Main extends Application {
	private Scene sceneLogin;
	private Scene sceneGame;
	private Stage primaryStage;
	private final static int RMI_PORT = 52365;
	private static final String NAME = "connectionManager";
	
	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		mainWindow();
	}
		
	public void mainWindow(){
		try {
			FXMLLoader login = new FXMLLoader(getClass().getResource("Game.fxml"));
	
			sceneLogin = new Scene(login.load());
			
			GameController controller = login.getController();
			controller.getMain(this);
			 
			primaryStage.setTitle("Lorenzo Il Magnifico");
			primaryStage.setScene(sceneLogin);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws RemoteException, NotBoundException {
		Registry registry = LocateRegistry.getRegistry("localhost", RMI_PORT);
		System.out.println("preso referenza al registry");
		
		ConnectionManager connectionManager = (ConnectionManager) registry.lookup(NAME);
		System.out.println("connesso al connectionManager");
		
		GameController controller = new GameController();

		UnicastRemoteObject.exportObject(controller, 0);

		connectionManager.register(controller);

		//starting gui
		Application.launch(args);
	}
}
