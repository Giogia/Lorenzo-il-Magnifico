package it.polimi.ingsw.gui;

import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import it.polimi.ingsw.BOARD.ActionZone;
import it.polimi.ingsw.BOARD.Position;
import it.polimi.ingsw.BONUS.Bonus;
import it.polimi.ingsw.BONUS.ResourceBonus;
import it.polimi.ingsw.CARD.DevelopmentCard;
import it.polimi.ingsw.CARD.LeaderCard;
import it.polimi.ingsw.Cli.CliRmi;
import it.polimi.ingsw.GC_15.*;
import it.polimi.ingsw.GC_15.Player.Color;
import it.polimi.ingsw.RESOURCE.Resource;
import it.polimi.ingsw.manager.ConnectionManager;
import it.polimi.ingsw.manager.ConnectionManagerRmiServer;
import it.polimi.ingsw.minigame.DevelopmentCardProxy;
import it.polimi.ingsw.minigame.GameProxy;
import it.polimi.ingsw.minigame.PositionProxy;
import it.polimi.ingsw.minigame.ResourceProxy;
import it.polimi.ingsw.minigame.TowerFloorProxy;
import it.polimi.ingsw.minigame.TowerProxy;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

//creates a client with gui 
//class that opens all the gui windows using gui
public class GuiRmiView extends Application implements CliRmi{
	volatile boolean wait = true;
	private static Object lock = new Object();
	private final static int RMI_PORT = 52365;
	private static final String NAME = "connectionManager";
	private static GuiRmiView client;
	private static GuiRmiCallback callback;
	private volatile static GuiController controller;
	private static GameProxy game;
	
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
		controller.setIsRmiClient(true);
		loader.setController(controller);
		
		System.out.println("sono il thread" + Thread.currentThread().getName() +" nello start e ho settato il controller");

		lock.notifyAll();
		System.out.println("notifico tutti dell'avvenuto set del controller in gui rmi view line 67");
		Scene scene = new Scene(loader.load());
		scene.getStylesheets().add(getClass().getResource("styleGame.css").toExternalForm());
		primaryStage.setTitle("Lorenzo Il Magnifico");
		primaryStage.setScene(scene);
		}
		primaryStage.setFullScreen(true);
		primaryStage.show();
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
		synchronized (this) {
			while(wait){
				try {
					wait();
				} catch (InterruptedException e) {
					LOGGER.log(Level.SEVERE, e.getMessage(),e);
					Thread.currentThread().interrupt();
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
	public void startGame(GameProxy game){
		GuiRmiView.game = game;
		//starting gui
		synchronized (this) {
			wait = false;
			notifyAll();
		}
	}
	
	@Override
	public void usernameHasAlreadyChosen() throws RemoteException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Username.fxml"));
		UsernameWindow usernameWindow = new UsernameWindow(true, loader);//true because it means this is a rmi client
		loader.setController(usernameWindow);
		Thread thread = new Thread(usernameWindow);
		Platform.setImplicitExit(false);
		Platform.runLater(thread);
	}

	@Override
	public void wrongInput() throws RemoteException {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				controller.disableButtons(true);
				
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Lorenzo Il Magnifico");
				alert.setHeaderText(null);
				alert.setContentText("The input must be an integer! Try again!");

				alert.showAndWait();
			}
		});
	}

	@Override
	public void isNotYourTurn() throws RemoteException {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				controller.disableButtons(true);
			
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Lorenzo Il Magnifico");
				alert.setHeaderText(null);
				alert.setContentText("Please, wait your turn!");

				alert.showAndWait();
			}
		});
	}

	@Override
	public void askForLeaderCardAction() throws RemoteException {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				controller.setChatLabel("Choose the action you want to do with this Leader Card : \n"
						+ "1) activate this leader Card \n2) Discard this leader card");
				controller.disableButtons(true);
			}
		});
	}

	@Override
	public void startTurn(String playerName) throws RemoteException {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				controller.disableButtons(false);//Now player can press button
	
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Lorenzo Il Magnifico");
				alert.setHeaderText(null);
				alert.setContentText(playerName + " is your turn!");

				alert.showAndWait();
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
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Lorenzo Il Magnifico");
				alert.setHeaderText(null);
				alert.setContentText("You have already positioned a family member. Choose another action.");

				alert.showAndWait();
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
			GuiRmiCallback.setServerPass(true);
			GuiRmiCallback.getLock().notifyAll();
		}
	}

	@Override
	public void chooseFamilyMember(ArrayList<FamilyMember> familyMembers) throws RemoteException {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				StringBuilder message = new StringBuilder();
				message.append("Choose the family member you want to use for the action: \n");
				for (int counter = 1; counter <= familyMembers.size(); counter++){
					message.append(counter + ") " + familyMembers.get(counter - 1).getDescription()+"\n");
				}
				int lastChoice = familyMembers.size() + 1;
				message.append(lastChoice + ") Go back\n");
				controller.setChatLabel(message.toString());
				controller.disableButtons(true);
			}
		});
	}

	@Override
	public void askForAlternativeCost(ArrayList<Resource> costDescriptions,
			ArrayList<Resource> alternativeCostDescriptions) throws RemoteException {
		
		Platform.runLater(new Runnable() {
			
			@Override
			public void run() {
				StringBuilder message = new StringBuilder();
				message.append("The card you have chosen has 2 costs. Choose one: \n");
				message.append("1) First cost\n");
				for (Resource resource : costDescriptions) {
					message.append(resource.getDescription() + "\n");
				}
				message.append("2) Secondary cost:\n");
				for (Resource alternativeResource : alternativeCostDescriptions ) {
					message.append(alternativeResource.getDescription() + "\n");
				}
				controller.setChatLabel(message.toString());
				controller.disableButtons(true);
			}
		});
		
	}

	@Override
	public void askForCouncilPrivilege(ArrayList<ResourceBonus> councilPrivileges) throws RemoteException {
		Platform.runLater(new Runnable() {
			
			@Override
			public void run() {
				StringBuilder message = new StringBuilder();
				message.append("Choose the bonus of the Council Privilege: \n");
				for (int counter = 1; counter <= councilPrivileges.size(); counter++){
					message.append(counter + ") " + councilPrivileges.get(counter - 1).getDescription() +"\n");
				}
				controller.setChatLabel(message.toString());
				controller.disableButtons(true);
			}
		});
		
	}

	@Override
	public void askForServants(int numberOfServants) throws RemoteException {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				controller.setChatLabel("How many servants do you want to use?");
				controller.disableButtons(true);
			}
		});
	}

	@Override
	public void askForInformation(String[] playersNames) throws RemoteException {
		// this method in Gui is useless
	}

	@Override
	public void showPersonalBoard(PersonalBoard personalBoard) throws RemoteException {
		// this method in Gui is useless
	}

	@Override
	public void cantPassTurn() throws RemoteException {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				controller.disableButtons(false);//Now player can press button
			
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Lorenzo Il Magnifico");
				alert.setHeaderText(null);
				alert.setContentText("You can't pass the turn. You have to place at least one family member.");

				alert.showAndWait();
			}
		});
	}

	@Override
	public void roundBegins(GameProxy game) throws RemoteException {
		ArrayList<DevelopmentCardProxy> cards = new ArrayList<>();
		for(TowerProxy towerProxy : game.getBoardProxy().getTowerProxies()){
			for (TowerFloorProxy towerFloorProxy : towerProxy.getTowerFloorProxies()){
				cards.add(towerFloorProxy.getDevelopmentCardProxy());
			}
		}
		
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				controller.roundBegins(game);
				controller.setCards(cards);
			}
		});
	}

	@Override
	public void hasWon(String winner) throws RemoteException {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				controller.disableButtons(true);
			
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Lorenzo Il Magnifico");
				alert.setHeaderText(null);
				alert.setContentText(winner + " has won the game!");

				alert.showAndWait();
			}
		});
	}

	@Override
	public void askForAction(ArrayList<ActionZone> zones) throws RemoteException {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				StringBuilder toSend = new StringBuilder();
				toSend.append("Choose the zone you want to activate the action bonus in: \n");
				for (int i = 1; i <= zones.size(); i++) {
					toSend.append(i + ") " + zones.get(i-1).getDescription()+"\n");
				}
				
				controller.setChatLabel(toSend.toString());
				controller.disableButtons(true);
			}
		});
	}

	@Override
	public void askForActionPosition(Position[] positions) throws RemoteException {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				StringBuilder toSend = new StringBuilder();
				toSend.append("Choose where you want to place your family member: \n");
				for (int counter = 1; counter <= positions.length; counter ++) {
					toSend.append(counter + ") " + positions[counter - 1].getDescription()+"\n");
				}
				
				controller.setChatLabel(toSend.toString());
				controller.disableButtons(true);
			}
		});
	}

	@Override
	public void catchException(String message) throws RemoteException {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				controller.disableButtons(false);
	
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Lorenzo Il Magnifico");
				alert.setHeaderText(null);
				alert.setContentText(message);

				alert.showAndWait();
				
				try {
					Thread.sleep(250);
					callback.answer("9");
				} catch (InterruptedException e) {
					LOGGER.log(Level.SEVERE, e.getMessage(),e);
					Thread.currentThread().interrupt();
				} catch (RemoteException e) {
					LOGGER.log(Level.SEVERE, e.getMessage(),e);
				}
			}
		});
	}

	@Override
	public void showDices(ArrayList<Dice> dices) throws RemoteException {
	}

	@Override
	public void askForLeaderCard(ArrayList<LeaderCard> leaderCards) throws RemoteException {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				StringBuilder toSend = new StringBuilder();
				toSend.append("Choose the leader card you want \n");
				for(int i=1;i<leaderCards.size()+1;i++){
					toSend.append(i+")"+leaderCards.get(i-1).getDescription()+"\n");
				}
				toSend.append(leaderCards.size()+1+") come back \n");
				
				controller.setChatLabel(toSend.toString());
				controller.disableButtons(true);
			}
		});
	}

	@Override
	public void askForPersonalBonusTile(ArrayList<PersonalBonusTile> personalBonusTiles) throws RemoteException {
		synchronized (lock) {
			while(controller == null){
				try {
					System.out.println("vado a dormire");
					lock.wait();
					System.out.println("mi sono risvegliato");
				} catch (InterruptedException e) {
					LOGGER.log(Level.SEVERE, e.getMessage(),e);
					Thread.currentThread().interrupt();
				}
			}
		}
		
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				StringBuilder toSend = new StringBuilder();
				toSend.append("Choose the personal bonus tile you want \n");
				for(int i=1;i<personalBonusTiles.size();i++){
					toSend.append(i+")"+personalBonusTiles.get(i).getDescription()+" \n");
				}
				
				controller.setChatLabel(toSend.toString());
				controller.disableButtons(true);
			}
		});
	}

	@Override
	public void draftLeaderCard(ArrayList<LeaderCard> leaderCards) throws RemoteException {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				StringBuilder toSend = new StringBuilder();
				toSend.append("Choose the leader card you want \n");
				for(int i=1;i<leaderCards.size()+1;i++){
					toSend.append(i+")"+leaderCards.get(i-1).getDescription()+" \n");
				}
				
				controller.setChatLabel(toSend.toString());
				controller.disableButtons(true);
			}
		});
	}

	@Override
	public void askForExcommunication(ExcommunicationTile excommunicationTile) throws RemoteException {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				controller.setChatLabel("Do you want to be excommunicated? \n1) No \n2)Yes");
				controller.disableButtons(true);
			}
		});
	}

	@Override
	public void askForCardEffect(DevelopmentCard developmentCard) throws RemoteException {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				StringBuilder toSend = new StringBuilder();
				toSend.append("Choose which effect of the card you want to acctivate : \n 1) First Effect: \n");
				int i =1;
				for(Bonus bonus : developmentCard.secondaryEffect){
					if(bonus instanceof ResourceBonus){
						toSend.append(i+")"+bonus.getDescription());
						i++;
					}
					else
						toSend.append(bonus.getDescription());
				}
				toSend.append(i+") Don't activate this card's Effect \n");
				
				controller.setChatLabel(toSend.toString());
				controller.disableButtons(true);
			}
		});
	}

	@Override
	public void integerError() throws RemoteException {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				controller.disableButtons(true);

				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Lorenzo Il Magnifico");
				alert.setHeaderText(null);
				alert.setContentText("The integer doesn't match any possible choice");

				alert.showAndWait();
			}
		});
	}

	@Override
	public void leftGame(String name) throws RemoteException {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Lorenzo Il Magnifico");
				alert.setHeaderText(null);
				alert.setContentText(name + " left the game!");

				alert.showAndWait();
			}
		});
	}


	@Override
	public void reconnectedToGame(String name) throws RemoteException {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Lorenzo Il Magnifico");
				alert.setHeaderText(null);
				alert.setContentText(name + " has reconnected himself to the game!");

				alert.showAndWait();
			}
		});
	}

	@Override
	public void timeExpired() throws RemoteException {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				controller.disableButtons(true);//Now player can't press button
				
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Lorenzo Il Magnifico");
				alert.setHeaderText(null);
				alert.setContentText("Time is expired!");

				alert.showAndWait();
			}
		});
	}

	@Override
	public void updateDueTowerFloorOccupied(TowerFloorProxy towerFloorProxy, DevelopmentCardProxy developmentCardProxy) throws RemoteException {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				controller.updateTowerFloor(towerFloorProxy, developmentCardProxy);
			}
		});
	}

	@Override
	public void updateDuePositionOccupied(PositionProxy positionProxy) throws RemoteException {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				controller.updatePosition(positionProxy);
			}
		});
	}
	
	@Override
	public void updatePlayerResources(Color playerColor, ArrayList<ResourceProxy> resources) throws RemoteException {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				controller.updatePlayerResources(playerColor, resources);
			}
		});
	}
}