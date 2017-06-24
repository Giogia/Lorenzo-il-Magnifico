package it.polimi.ingsw.gui;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import it.polimi.ingsw.manager.ActionSocket;
import javafx.application.Platform;


public class GuiSocketInView implements Runnable {
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
					case chooseName:
						break;
						
					case chooseColor:
						
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
					
					case askForUsername:
						NewWindow newWindow = new NewWindow();
						Thread thread = new Thread(newWindow);
						Platform.runLater(thread);
						break;
						
					case reconnectedToGame:
						break;
						
					case usernameHasAlreadyChoosen:
						break;
				}
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}

