package it.polimi.ingsw.gui;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import it.polimi.ingsw.BOARD.ActionZone;
import it.polimi.ingsw.BOARD.Position;
import it.polimi.ingsw.BONUS.ResourceBonus;
import it.polimi.ingsw.CARD.DevelopmentCard;
import it.polimi.ingsw.CARD.LeaderCard;
import it.polimi.ingsw.GC_15.Dice;
import it.polimi.ingsw.GC_15.ExcommunicationTile;
import it.polimi.ingsw.GC_15.FamilyMember;
import it.polimi.ingsw.GC_15.PersonalBoard;
import it.polimi.ingsw.GC_15.PersonalBonusTile;
import it.polimi.ingsw.RESOURCE.Resource;
import it.polimi.ingsw.manager.ConnectionManager;
import it.polimi.ingsw.manager.ConnectionManagerRmiServer;
import it.polimi.ingsw.view.CliRmi;
import it.polimi.ingsw.view.CliRmiCallback;
import it.polimi.ingsw.view.CliRmiView;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class GuiRmiView extends Application implements CliRmi{
	Scene scene;
	Scene sceneGame;
	Stage primaryStage;
	private final static int RMI_PORT = 52365;
	private static final String NAME = "connectionManager";
	private ConnectionManagerRmiServer connectionManagerRmiServer;
	
	public GuiRmiView(ConnectionManagerRmiServer connectionManagerRmiServer) {
		this.connectionManagerRmiServer = connectionManagerRmiServer;
	}
	
	
	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader login = new FXMLLoader(getClass().getResource("Game.fxml"));
			scene = new Scene(login.load());
			
			scene.getStylesheets().add(getClass().getResource("styleGame.css").toExternalForm());

			GameController gameController = new GameController();
			gameController.setGuiRmiView(this);
			gameController.setRmiServer(connectionManagerRmiServer);
			
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
		
		GuiRmiView guiRmiView = new GuiRmiView(connectionManagerRmiServer);

		UnicastRemoteObject.exportObject(guiRmiView, 0);

		connectionManager.register(guiRmiView);

		//starting gui
		Application.launch(args);
	}

	@Override
	public void wrongInput() throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void isNotYourTurn() throws RemoteException {
		System.out.println("gnfodasnoignodsnfansdjggfamfdon");
	}

	@Override
	public void askForLeaderCardAction() throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void startTurn(String playerName) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void askName() throws RemoteException {
		NewWindow newWindow = new NewWindow();
		newWindow.setGuiRmiView(this);
		Thread thread = new Thread(newWindow);
		Platform.runLater(thread);
	}

	@Override
	public void askColor(String[] availableColors) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void turnChoice() throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void moveAlreadyDone() throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void chooseZone() throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void choosePosition(Position[] positions) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void chooseFamilyMember(ArrayList<FamilyMember> familyMembers) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void askForAlternativeCost(ArrayList<Resource> costDescriptions,
			ArrayList<Resource> alternativeCostDescriptions) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void askForCouncilPrivilege(ArrayList<ResourceBonus> councilPrivileges) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void askForServants(int numberOfServants) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void askForInformation(String[] playersNames) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void showPersonalBoard(PersonalBoard personalBoard) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void cantPassTurn() throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void roundBegins() throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hasWon(String winner) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void askForAction(ArrayList<ActionZone> zones) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void askForActionPosition(Position[] positions) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void catchException(String message) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void showDices(ArrayList<Dice> dices) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void askForLeaderCard(ArrayList<LeaderCard> leaderCards) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void askForPersonalBonusTile(ArrayList<PersonalBonusTile> personalBonusTiles) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draftLeaderCard(ArrayList<LeaderCard> leaderCards) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void askForExcommunication(ExcommunicationTile excommunicationTile) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void askForCardEffect(DevelopmentCard developmentCard) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void integerError() throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void leftGame(String name) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void askForUsername() throws RemoteException {
		NewWindow newWindow = new NewWindow();
		newWindow.setGuiRmiView(this);
		Thread thread = new Thread(newWindow);
		Platform.runLater(thread);
	}

	@Override
	public void reconnectedToGame(String name) throws RemoteException {
		// TODO Auto-generated method stub
		
	}
	
	 

	public void reply(String answer) throws RemoteException {
		connectionManagerRmiServer.getAnswer(answer, this);
	}
}
