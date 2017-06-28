package it.polimi.ingsw.gui;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import it.polimi.ingsw.GC_15.Game;
import it.polimi.ingsw.GC_15.Player.Color;
import it.polimi.ingsw.manager.ActionSocket;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;


public class GuiSocketInView implements Runnable {
	
	private final static Logger LOGGER = Logger.getLogger(GuiSocketInView.class.getName());
	
	private ObjectInputStream socketIn;
	
	public GuiSocketInView(ObjectInputStream socketIn, PrintWriter socketOut) {
		this.socketIn = socketIn;
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
						UsernameWindow newWindow = new UsernameWindow();
						Thread thread = new Thread(newWindow);
						Platform.runLater(thread);
						break;
						
					case chooseName:
						NameWindow nameWindow = new NameWindow();
						Thread thread1 = new Thread(nameWindow);
						Platform.runLater(thread1);
						break;
						
					case chooseColor:
						String[] availableColors = action.getAvailableColors();
						ColorWindow colorWindow = new ColorWindow();
						FXMLLoader loader = new FXMLLoader(getClass().getResource("Color.fxml"));
						loader.setController(colorWindow);
						colorWindow.setLoader(loader);
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
						Game game = action.getGame();
						//starting gui
						synchronized (this) {
							GuiSocketView.wait = false;
							notifyAll();
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

