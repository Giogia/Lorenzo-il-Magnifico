package it.polimi.ingsw.gui;

import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

import it.polimi.ingsw.BOARD.ActionZone;
import it.polimi.ingsw.BOARD.Board;
import it.polimi.ingsw.BOARD.Position;
import it.polimi.ingsw.BOARD.TowerFloor;
import it.polimi.ingsw.BONUS.ResourceBonus;
import it.polimi.ingsw.CARD.DevelopmentCard;
import it.polimi.ingsw.CARD.DevelopmentCardType;
import it.polimi.ingsw.CARD.LeaderCard;
import it.polimi.ingsw.GC_15.*;
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
	private static Object lock = new Object();
	private final static int RMI_PORT = 52365;
	private static final String NAME = "connectionManager";
	private static GuiRmiView client;
	private static GuiRmiCallback callback;
	private volatile static GuiController controller;
	private static Game game;
	
	private final static Logger LOGGER = Logger.getLogger(GuiRmiView.class.getName());
	
	public static GuiRmiCallback getCallback() {
		return callback;
	}
	
	public static GuiRmiView getClient() {
		return client;
	}
	
	
	@Override
	public void start(Stage primaryStage) throws IOException {
		synchronized (lock) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Game.fxml"));
		controller = new GuiController(game);
		loader.setController(controller);
		
		System.out.println("sono il thread" + Thread.currentThread().getName() +" nello start e ho settato il controller");

		lock.notifyAll();
		System.out.println("notifico tutti");
		Scene scene = new Scene(loader.load());
		scene.getStylesheets().add(getClass().getResource("styleGame.css").toExternalForm());
		primaryStage.setTitle("Lorenzo Il Magnifico");
		primaryStage.setScene(scene);
		primaryStage.show();
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
	
	public void showStage(){//waiting the start game (with the reference to game)
		while(wait){
			synchronized (this) {
				try {
					wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					LOGGER.log(Level.SEVERE, e.getMessage(),e);
				}
			}
		}
		Application.launch();
	}

	@Override
	public void setConnectionManagerRmiServer(ConnectionManagerRmiServer connectionManagerRmiServer) throws RemoteException{
		callback = new GuiRmiCallback(connectionManagerRmiServer, client);
	}
	
	@Override
	public void askForUsername() throws RemoteException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Username.fxml"));
		UsernameWindow usernameWindow = new UsernameWindow(true, loader);//true because it means this is a rmi client
		loader.setController(usernameWindow);
		Thread thread = new Thread(usernameWindow);
		Platform.setImplicitExit(false);
		Platform.runLater(thread);
	}

	@Override
	public void askName() throws RemoteException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Name.fxml"));
		NameWindow nameWindow = new NameWindow(true, loader);
		loader.setController(nameWindow);
		Thread thread = new Thread(nameWindow);
		Platform.runLater(thread);
	}

	@Override
	public void askColor(String[] availableColors){
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Color.fxml"));
		ColorWindow colorWindow = new ColorWindow(true, loader);
		loader.setController(colorWindow);
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
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				controller.setChatLabel("Please, wait your turn!");
			}
		});
	}

	@Override
	public void askForLeaderCardAction() throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void startTurn(String playerName) throws RemoteException {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				controller.disableButtons(false);//Now player can press button
				controller.setChatLabel(playerName +" is your turn!");
			}
		});
	}


	@Override
	public void turnChoice() throws RemoteException {
		synchronized (GuiRmiCallback.getLock()) {
			GuiRmiCallback.setServerPass(true);
			GuiRmiCallback.getLock().notifyAll();
		}
	}

	@Override
	public void moveAlreadyDone() throws RemoteException {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				controller.setChatLabel("You have already positioned a family member. Choose another action.");
			}
		});
	}

	@Override
	public void chooseZone() throws RemoteException {
		synchronized (GuiRmiCallback.getLock()) {
			GuiRmiCallback.setServerPass(true);
			GuiRmiCallback.getLock().notifyAll();
		}
	}

	@Override
	public void choosePosition(Position[] positions) throws RemoteException {
		synchronized (GuiRmiCallback.getLock()) {
			GuiRmiCallback.setServerPass(true);;
			GuiRmiCallback.getLock().notifyAll();
		}
	}

	@Override
	public void chooseFamilyMember(ArrayList<FamilyMember> familyMembers) throws RemoteException {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				controller.setChatLabel("Choose the family member you want to use for the action.");
			}
		});
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
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				controller.setChatLabel("You have " + numberOfServants + " servants. How many of them do you want to use?");
			}
		});
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
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				controller.disableButtons(false);//Now player can press button
				controller.setChatLabel("You can't pass the turn, you have to place at least one family member.");
			}
		});
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
		while(controller == null){
			synchronized (lock) {
				try {
					System.out.println("vado a dormire");
					lock.wait();
					System.out.println("mi sono risvegliato");
				} catch (InterruptedException e) {
					LOGGER.log(Level.SEVERE, e.getMessage(),e);
				}
			}
		}
		System.out.println("controller: " + controller);
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				controller.setCards(cards);
			}
		});
	}

	@Override
	public void hasWon(String winner) throws RemoteException {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				controller.setChatLabel(winner + " has won the game!");
			}
		});
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
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				controller.setChatLabel(message);
			}
		});
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
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				controller.setChatLabel("Do you want to be excommunicated? \n1) No \n2)Yes");
			}
		});
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
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				controller.setChatLabel(name + " left the game!");
			}
		});
	}


	@Override
	public void reconnectedToGame(String name) throws RemoteException {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				controller.setChatLabel(name + " has reconnected himself to the game!");
			}
		});
	}

	@Override
	public void timeExpired() throws RemoteException {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				controller.disableButtons(false);//Now player can press button
				controller.setChatLabel("TIME IS EXPIRED!");
			}
		});
	}
}