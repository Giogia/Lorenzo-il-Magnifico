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
						
						break;
						
					case turnChoice:
						
						break;
						
					case moveAlreadyDone:
						
						break;
						
					case chooseZone:
						
						break;
						
					case choosePosition:
						
						break;
						
					case askForAlternativeCost:
						
						break;
						
					case askForCouncilPrivilege:
						
						break;
						
					case askForServants:
						break;
						
					case showDices:
						break;
						
					case hasWon:
						break;
					
					case roundBegins:
						break;
						
					case askForInformation:
						break;
						
					case showPersonalBoard:
						break;
						
					case cantPassTurn:
						break;
						
					case askForAction:
						break;
						
					case askForActionPosition:
						break;
						
					case catchException:
						break;
						
					case chooseFamilyMember:
						break;
						
					case askForLeaderCards:
						break;
						
					case askForPersonalBonusTile:
						break;
						
					case askForLeaderCardAction:
						break;
						
					case draftLeaderCards:
						break;
						
					case askForCardEffect:
						break;
					
					case askForExcommunication:
						break;
						
					case notYourTurn:
						break;
						
					case wrongInput:
						break;
						
					case integerError:
						break;
						
					case leftGame:
						break;
						
					case reconnectedToGame:
						break;
						
					case usernameHasAlreadyChoosen:
						break;
				}
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				LOGGER.log(Level.SEVERE, e.getMessage(),e);
			} catch (IOException e) {
				LOGGER.log(Level.SEVERE, e.getMessage(),e);
				e.printStackTrace();
			}
		}
	}
}

