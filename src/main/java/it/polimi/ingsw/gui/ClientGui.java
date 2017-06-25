package it.polimi.ingsw.gui;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;

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
import it.polimi.ingsw.manager.ConnectionManagerRmiServer;
import it.polimi.ingsw.view.CliRmi;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;

public class ClientGui implements CliRmi{

	public void startTurn(String playerName) throws RemoteException {
		System.out.println("inizia il turno");
		
	}

	public void askName() throws RemoteException {
		NewWindow newWindow = new NewWindow();
		Thread thread = new Thread(newWindow);
	
	
		Platform.runLater(thread);
	}
	
	public void turnChoice() throws RemoteException {
		
		Platform.runLater(new Runnable() {
            @Override public void run() {
           	 System.out.println("-------------sono arrivato qui------------------");
				/*action1.setText("See leader cards in your hand");
				action2.setText("Activate effect of a leader card");
				action3.setText("Pass the turn");
				action4.setVisible(false);
				action5.setVisible(false);*/
            }
        });
		//return 2;
	}

	@Override
	public void wrongInput() throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void isNotYourTurn() throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void askForLeaderCardAction() throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void askColor(String[] availableColors) throws RemoteException {
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
		Thread thread = new Thread(newWindow);
		Platform.runLater(thread);
	}

	@Override
	public void reconnectedToGame(String name) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setConnectionManagerRmiServer(ConnectionManagerRmiServer connectionManagerRmiServer)
			throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void usernameHasAlreadyChoosen() throws RemoteException {
		// TODO Auto-generated method stub
		
	}

}
