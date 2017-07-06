package it.polimi.ingsw.gui;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

import it.polimi.ingsw.GC_15.Game;
import it.polimi.ingsw.manager.ActionSocket;
import it.polimi.ingsw.minigame.GameProxy;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;


public class GuiSocketInView implements Runnable {
	
	private final static Logger LOGGER = Logger.getLogger(GuiSocketInView.class.getName());
	private ObjectInputStream socketIn;
	private static GameProxy game;
	
	public GuiSocketInView(ObjectInputStream socketIn, PrintWriter socketOut) {
		this.socketIn = socketIn;
	}
	
	public static GameProxy getGame() {
		return game;
	}
	
	@Override
	public void run() {
		while(true){
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
						synchronized (GuiSocketView.getLock()) {
							GuiSocketView.wait = false; //wake up GuiSocketView and he starts the main window
							GuiSocketView.getLock().notifyAll();
						}
						break;
						
					case startTurn: 
						
						
					case turnChoice:
						
						
					case moveAlreadyDone:
						
						
					case chooseZone:
						

					case choosePosition:
						
					
					case askForAlternativeCost:
						
					
					case askForCouncilPrivilege:
						
					
					case askForServants:
						
						
					case showDices:
						
						
					case hasWon:
					
						
					case roundBegins:
					
						
					case askForInformation:
						
						
					case showPersonalBoard:
						
						
					case cantPassTurn:
						
						
					case askForAction:
						
						
					case askForActionPosition:
						
						
					case catchException:
						
						
					case chooseFamilyMember:
						
						
					case askForLeaderCards:
						
						
					case askForPersonalBonusTile:
						
						
					case askForLeaderCardAction:
						
						
					case draftLeaderCards:
						
						
					case askForCardEffect:
						
						
					case askForExcommunication:
						
						
					case notYourTurn:
						
						
					case wrongInput:
						
						
					case integerError:
						
						
					case leftGame:
						
						
					case reconnectedToGame:
						
						
					case usernameHasAlreadyChosen:
						
				}
			} catch (ClassNotFoundException e) {
				LOGGER.log(Level.SEVERE, e.getMessage(),e);
			} catch (IOException e) {
				LOGGER.log(Level.SEVERE, e.getMessage(),e);
			}
		}
	}
}

