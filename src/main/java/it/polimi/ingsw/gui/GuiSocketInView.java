package it.polimi.ingsw.gui;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import it.polimi.ingsw.BOARD.ActionZone;
import it.polimi.ingsw.BOARD.Position;
import it.polimi.ingsw.BONUS.Bonus;
import it.polimi.ingsw.BONUS.ResourceBonus;
import it.polimi.ingsw.CARD.DevelopmentCard;
import it.polimi.ingsw.CARD.LeaderCard;
import it.polimi.ingsw.GC_15.FamilyMember;
import it.polimi.ingsw.GC_15.Game;
import it.polimi.ingsw.GC_15.PersonalBonusTile;
import it.polimi.ingsw.GC_15.Player.Color;
import it.polimi.ingsw.RESOURCE.Resource;
import it.polimi.ingsw.manager.ActionSocket;
import it.polimi.ingsw.minigame.DevelopmentCardProxy;
import it.polimi.ingsw.minigame.GameProxy;
import it.polimi.ingsw.minigame.PositionProxy;
import it.polimi.ingsw.minigame.ResourceProxy;
import it.polimi.ingsw.minigame.TowerFloorProxy;
import it.polimi.ingsw.minigame.TowerProxy;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

//class that opens all the gui windows using socket 
public class GuiSocketInView implements Runnable {

	private static Object lock = new Object();
	private final static Logger LOGGER = Logger.getLogger(GuiSocketInView.class.getName());
	private ObjectInputStream socketIn;
	private static GameProxy game;
	private GuiController controller;
	private GuiSocketOutView socketOut;
	public boolean keepOn = true;
	
	public void setSocketOut(GuiSocketOutView socketOut) {
		this.socketOut = socketOut;
	}
	
	public void setController(GuiController controller) {
		this.controller = controller;
	}
	
	public GuiSocketInView(ObjectInputStream socketIn, PrintWriter socketOut) {
		this.socketIn = socketIn;
	}
	
	public static GameProxy getGame() {
		return game;
	}
	
	@Override
	public void run() {
		while(keepOn){
			// handles input messages coming from the server, just showing them to the user
			try {
				ActionSocket action =(ActionSocket) socketIn.readObject();
				ActionSocket.action question = action.getAction();
				
				switch (question) {
				
					case askForUsername:
						FXMLLoader loaderUsername = new FXMLLoader(getClass().getResource("Username.fxml"));
						UsernameWindow newWindow = new UsernameWindow(false, loaderUsername);//false means this is a socket client
						loaderUsername.setController(newWindow);
						Thread thread = new Thread(newWindow);
						Platform.setImplicitExit(false);
						Platform.runLater(thread);
						break;
						
					case chooseName:
						FXMLLoader loaderName = new FXMLLoader(getClass().getResource("Name.fxml"));
						NameWindow nameWindow = new NameWindow(false, loaderName);
						loaderName.setController(nameWindow);
						Thread thread1 = new Thread(nameWindow);
						Platform.runLater(thread1);
						break;
						
					case chooseColor:
						String[] availableColors = action.getAvailableColors();
						FXMLLoader loader = new FXMLLoader(getClass().getResource("Color.fxml"));
						ColorWindow colorWindow = new ColorWindow(false, loader);
						loader.setController(colorWindow);
						Thread thread2 = new Thread(colorWindow);
						Platform.runLater(thread2);
						Platform.runLater(new Runnable() {
							@Override
							public void run() {
								colorWindow.setButton(availableColors);
							}
						});
						break;
						
					case startGame:
						//game = action.getGame();
						//starting gui
						game = action.getGameProxy();
						synchronized (GuiSocketView.getLock()) {
							GuiSocketView.wait = false; //wake up GuiSocketView and he starts the main window
							GuiSocketView.getLock().notifyAll();
						}
						break;
						
					case startTurn: 
						String playerName = action.getMessage();
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
						break;
												
					case turnChoice:
					case chooseZone:
					case choosePosition:
						synchronized (GuiSocketOutView.getLock()) {
							GuiSocketOutView.setServerPass(true);
							GuiSocketOutView.getLock().notifyAll();
						}
						break;
						
					case moveAlreadyDone:
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
						break;
											
					case askForAlternativeCost:
						ArrayList<Resource> costDescriptions = action.getCosts();
						ArrayList<Resource> alternativeCostDescriptions = action.getAlternativeCosts();
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
						break;
						
					
					case askForCouncilPrivilege:
						ArrayList<ResourceBonus> councilPrivileges = action.getBonus();
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
						break;
					
					case askForServants:
						Platform.runLater(new Runnable() {
							@Override
							public void run() {
								controller.setChatLabel("How many servants do you want to use?");
								controller.disableButtons(true);
							}
						});
						break;
						
					case showDices:
					case askForInformation:
					case showPersonalBoard:
						break;
						

					case usernameHasAlreadyChosen:
						FXMLLoader loaderUsername2 = new FXMLLoader(getClass().getResource("Username.fxml"));
						UsernameWindow newWindow2 = new UsernameWindow(false, loaderUsername2);//false means this is a socket client
						loaderUsername2.setController(newWindow2);
						Thread thread3 = new Thread(newWindow2);
						Platform.runLater(thread3);
						break;
						
					case hasWon:
						String winner = action.getMessage();
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
						break;
					
						
					case roundBegins:
						GameProxy gameProxy = action.getGameProxy();
						ArrayList<DevelopmentCardProxy> cards = new ArrayList<>();
						for(TowerProxy towerProxy : gameProxy.getBoardProxy().getTowerProxies()){
							for (TowerFloorProxy towerFloorProxy : towerProxy.getTowerFloorProxies()){
								cards.add(towerFloorProxy.getDevelopmentCardProxy());
							}
						}
						synchronized (lock) {
							while(controller == null){
								try {
									lock.wait();
								} catch (InterruptedException e) {
									LOGGER.log(Level.SEVERE, e.getMessage(),e);
									Thread.currentThread().interrupt();
								}
							}
						}
						Platform.runLater(new Runnable() {
							@Override
							public void run() {
								controller.roundBegins(gameProxy);
								controller.setCards(cards);
							}
						});
						break;
						
					case cantPassTurn:
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
						break;
						
						
					case askForAction:
						ArrayList<ActionZone> zones = action.getZones();
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
						break;
						
					case askForActionPosition:
						Position[] positions = action.getPositions();
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
						break;
						
					case catchException:
						String message = action.getMessage();
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
									socketOut.setToSend("9");
								} catch (InterruptedException e) {
									LOGGER.log(Level.SEVERE, e.getMessage(),e);
									Thread.currentThread().interrupt();
								}
							}
						});
						break;
						
						
					case chooseFamilyMember:
						ArrayList<FamilyMember> familyMembers = action.getFamilyMembers();
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
						break;
						
					case askForLeaderCards:
						ArrayList<LeaderCard> leaderCards = action.getLeaderCards();
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
						break;
						
												
					case askForPersonalBonusTile:
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
						ArrayList<PersonalBonusTile> personalBonusTiles = action.getPersonalBonusTiles();
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
						break;
						
					case askForLeaderCardAction:
						Platform.runLater(new Runnable() {
							@Override
							public void run() {
								controller.setChatLabel("Choose the action you want to do with this Leader Card : \n"
										+ "1) activate this leader Card \n2) Discard this leader card");
								controller.disableButtons(true);
							}
						});
						break;
						
					case draftLeaderCards:
						ArrayList<LeaderCard> draftLeaderCards = action.getLeaderCards();
						Platform.runLater(new Runnable() {
							@Override
							public void run() {
								StringBuilder toSend = new StringBuilder();
								toSend.append("Choose the leader card you want \n");
								for(int i=1;i<draftLeaderCards.size()+1;i++){
									toSend.append(i+")"+draftLeaderCards.get(i-1).getDescription()+" \n");
								}
								
								controller.setChatLabel(toSend.toString());
								controller.disableButtons(true);
							}
						});
						break;
						
					case askForCardEffect:
						DevelopmentCard developmentCard = action.getDevelopmentCard();
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
						break;
						
					case askForExcommunication:
						Platform.runLater(new Runnable() {
							@Override
							public void run() {
								controller.setChatLabel("Do you want to be excommunicated? \n1) No \n2)Yes");
								controller.disableButtons(true);
							}
						});
						break;
						
					case notYourTurn:
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
						break;
						
					case wrongInput:
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
						break;						
						
					case integerError:
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
						break;
						
					case leftGame:
						Platform.runLater(new Runnable() {
							@Override
							public void run() {
								Alert alert = new Alert(AlertType.INFORMATION);
								alert.setTitle("Lorenzo Il Magnifico");
								alert.setHeaderText(null);
								alert.setContentText(action.getPlayerName() + " left the game!");

								alert.showAndWait();
							}
						});
						break;
						
					case reconnectedToGame:
						Platform.runLater(new Runnable() {
							@Override
							public void run() {
								Alert alert = new Alert(AlertType.INFORMATION);
								alert.setTitle("Lorenzo Il Magnifico");
								alert.setHeaderText(null);
								alert.setContentText(action.getPlayerName() + " has reconnected himself to the game!");

								alert.showAndWait();
							}
						});
						break;
						
					case timeExpired:
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
						break;
						
					case positionOccupied:
						PositionProxy positionProxy = action.getPositionProxy();
						Platform.runLater(new Runnable() {
							@Override
							public void run() {
								controller.updatePosition(positionProxy);
							}
						});
						break;
						
					case towerFloorOccupied:
						TowerFloorProxy towerFloorProxy = action.getTowerFloorProxy();
						DevelopmentCardProxy developmentCardProxy = action.getDevelopmentCardProxy();
						Platform.runLater(new Runnable() {
							@Override
							public void run() {
								controller.updateTowerFloor(towerFloorProxy, developmentCardProxy);
							}
						});
						break;
					
					case updateResources:
						ArrayList<ResourceProxy> resources = action.getResourceProxies();
						Color playerColor = action.getColor();
						Platform.runLater(new Runnable() {
							@Override
							public void run() {
								controller.updatePlayerResources(playerColor, resources);
							}
						});
				}
			} catch (ClassNotFoundException e) {
				LOGGER.log(Level.SEVERE, e.getMessage(),e);
			} catch (IOException e) {
				LOGGER.log(Level.SEVERE, e.getMessage(),e);
			}
		}
	}
}

