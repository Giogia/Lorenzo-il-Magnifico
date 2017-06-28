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
import it.polimi.ingsw.BOARD.Board;
import it.polimi.ingsw.BOARD.Position;
import it.polimi.ingsw.BOARD.TowerFloor;
import it.polimi.ingsw.BONUS.ResourceBonus;
import it.polimi.ingsw.CARD.DevelopmentCard;
import it.polimi.ingsw.CARD.DevelopmentCardType;
import it.polimi.ingsw.CARD.LeaderCard;
import it.polimi.ingsw.GC_15.Dice;
import it.polimi.ingsw.GC_15.ExcommunicationTile;
import it.polimi.ingsw.GC_15.FamilyMember;
import it.polimi.ingsw.GC_15.Game;
import it.polimi.ingsw.GC_15.MyException;
import it.polimi.ingsw.GC_15.PersonalBoard;
import it.polimi.ingsw.GC_15.PersonalBonusTile;
import it.polimi.ingsw.RESOURCE.Resource;
import it.polimi.ingsw.manager.ConnectionManager;
import it.polimi.ingsw.manager.ConnectionManagerRmiServer;
import it.polimi.ingsw.manager.ConnectionManagerRmiServerImpl;
import it.polimi.ingsw.view.CliRmi;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GuiRmiView extends Application implements CliRmi{
	volatile boolean wait = true;
	Scene scene;
	Scene sceneGame;
	Stage primaryStage;
	private final static int RMI_PORT = 52365;
	private static final String NAME = "connectionManager";
	private static GuiRmiView client;
	private static GuiRmiCallback callback;
	private volatile static GuiController controller;
	private static Game game;
	
	public static GuiRmiCallback getCallback() {
		return callback;
	}
	
	public static GuiRmiView getClient() {
		return client;
	}
	
<<<<<<< Updated upstream
=======
	public static Game getGame() {
		return game;
	}
	
	public static ConnectionManagerRmiServerImpl getConnectionManagerRmiServerImpl() {
		return connectionManagerRmiServerImpl;
	}
	
>>>>>>> Stashed changes
	@Override
	public void start(Stage primaryStage) {
		try {
			synchronized (GuiRmiView.client) {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("Game.fxml"));
			controller = new GuiController();
			loader.setController(controller);
			
			System.out.println("sono nello start e ho settato il controller");
			controller.setLoader(loader);
			GuiRmiView.client.notifyAll();
			scene = new Scene(loader.load());
			scene.getStylesheets().add(getClass().getResource("styleGame.css").toExternalForm());
			primaryStage.setTitle("Lorenzo Il Magnifico");
			primaryStage.setScene(scene);
			primaryStage.show();
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws RemoteException, NotBoundException, MyException {
		Registry registry = LocateRegistry.getRegistry("localhost", RMI_PORT);
		System.out.println("preso referenza al registry");
		
		ConnectionManager connectionManager = (ConnectionManager) registry.lookup(NAME);
		System.out.println("connesso al connectionManager");
		
		client = new GuiRmiView();
		
		UnicastRemoteObject.exportObject(client, 0);
		connectionManager.register(client);//client register himself on server
		client.showStage();
	}
	
	public void showStage(){
		while(wait){
			synchronized (this) {
				try {
					wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
<<<<<<< Updated upstream
		
		Application.launch();
=======
		Application.launch();
>>>>>>> Stashed changes
	}

	@Override
	public void setConnectionManagerRmiServer(ConnectionManagerRmiServer connectionManagerRmiServer) throws RemoteException{
		callback = new GuiRmiCallback(connectionManagerRmiServer, client);
	}
	
	@Override
	public void askForUsername() throws RemoteException {
		UsernameWindow usernameWindow = new UsernameWindow();
		Thread thread = new Thread(usernameWindow);
		Platform.setImplicitExit(false);
		Platform.runLater(thread);
	}

	@Override
	public void askName() throws RemoteException {
		NameWindow nameWindow = new NameWindow();
		Thread thread = new Thread(nameWindow);
		Platform.runLater(thread);
	}

	@Override
	public void askColor(String[] availableColors){
		ColorWindow colorWindow = new ColorWindow();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Color.fxml"));
		loader.setController(colorWindow);
		colorWindow.setLoader(loader);
		Thread thread = new Thread(colorWindow);
		Platform.runLater(thread);
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				colorWindow.setButton(availableColors);
			}
		});
	}
	
	@Override
	public void startGame(Game game){
		
		this.game = game;
		//starting gui
		synchronized (this) {
			wait = false;
			notifyAll();
		}
		
	}
	
	@Override
	public void usernameHasAlreadyChoosen() throws RemoteException {
	
	}

	@Override
	public void wrongInput() throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void isNotYourTurn() throws RemoteException {
		
	}

	@Override
	public void askForLeaderCardAction() throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void startTurn(String playerName) throws RemoteException {
		
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
	public void roundBegins(Board board) throws RemoteException {
		ArrayList<DevelopmentCard> cards = new ArrayList<>();
		for(int typeTower = 0; typeTower < 4; typeTower++){
			for (TowerFloor floor : (TowerFloor[]) board.getTower(typeTower).getPositions()) {
				cards.add(floor.getDevelopmentCard());
			}
		}
		
		System.out.println("sono in round begins e il controller:" + controller);
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				while(controller == null){
					synchronized (GuiRmiView.client) {
						try {
							GuiRmiView.client.wait();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
				controller.setCards(cards);
			}
		});
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
	public void reconnectedToGame(String name) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void timeExpired() throws RemoteException {
		// TODO Auto-generated method stub
		
	}
}