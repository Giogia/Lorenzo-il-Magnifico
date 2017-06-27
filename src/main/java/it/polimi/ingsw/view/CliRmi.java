package it.polimi.ingsw.view;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import it.polimi.ingsw.BOARD.ActionZone;
import it.polimi.ingsw.BOARD.Board;
import it.polimi.ingsw.BOARD.Position;
import it.polimi.ingsw.BOARD.Zone;
import it.polimi.ingsw.BONUS.ResourceBonus;
import it.polimi.ingsw.CARD.DevelopmentCard;
import it.polimi.ingsw.CARD.LeaderCard;
import it.polimi.ingsw.GC_15.Dice;
import it.polimi.ingsw.GC_15.ExcommunicationTile;
import it.polimi.ingsw.GC_15.FamilyMember;
import it.polimi.ingsw.GC_15.PersonalBoard;
import it.polimi.ingsw.GC_15.PersonalBonusTile;
import it.polimi.ingsw.GC_15.Player;
import it.polimi.ingsw.RESOURCE.Resource;
import it.polimi.ingsw.manager.ConnectionManagerRmiServer;
import it.polimi.ingsw.manager.ConnectionManagerRmiServerImpl;


//this is the remote object through the server can tell to client what to do
public interface CliRmi extends Remote {
	
	void setConnectionManagerRmiServer(ConnectionManagerRmiServer connectionManagerRmiServer) throws RemoteException;
	
	void wrongInput() throws RemoteException;
	
	void isNotYourTurn() throws RemoteException;
	
	void askForLeaderCardAction() throws RemoteException;

	void startTurn(String playerName) throws RemoteException;
	
	void askName() throws RemoteException;
	
	void askColor(String[] availableColors) throws RemoteException;

	void turnChoice() throws RemoteException;

	void moveAlreadyDone() throws RemoteException;

	//methods to put a family member
	void chooseZone() throws RemoteException;
	
	void choosePosition(Position[] positions) throws RemoteException;
	
	void chooseFamilyMember(ArrayList<FamilyMember> familyMembers) throws RemoteException;

	void askForAlternativeCost(ArrayList<Resource> costDescriptions, ArrayList<Resource> alternativeCostDescriptions) throws RemoteException;

	void askForCouncilPrivilege(ArrayList<ResourceBonus> councilPrivileges) throws RemoteException;

	void askForServants(int numberOfServants) throws RemoteException;

	void askForInformation(String[] playersNames) throws RemoteException;

	void showPersonalBoard(PersonalBoard personalBoard) throws RemoteException;
	
	void cantPassTurn() throws RemoteException;
	
	void roundBegins() throws RemoteException;
	
	void hasWon(String winner) throws RemoteException;

	void askForAction(ArrayList<ActionZone> zones) throws RemoteException;

	void askForActionPosition(Position[] positions) throws RemoteException;

	void catchException(String message) throws RemoteException;
	
	void showDices(ArrayList<Dice> dices) throws RemoteException;

	void askForLeaderCard(ArrayList<LeaderCard> leaderCards) throws RemoteException;

	void askForPersonalBonusTile(ArrayList<PersonalBonusTile> personalBonusTiles) throws RemoteException;

	void draftLeaderCard(ArrayList<LeaderCard> leaderCards) throws RemoteException;

	void askForExcommunication(ExcommunicationTile excommunicationTile) throws RemoteException;

	void askForCardEffect(DevelopmentCard developmentCard) throws RemoteException;

	void integerError() throws RemoteException;

	void leftGame(String name) throws RemoteException;

	void askForUsername() throws RemoteException;

	void reconnectedToGame(String name) throws RemoteException;

	void usernameHasAlreadyChoosen() throws RemoteException;

	void timeExpired() throws RemoteException;
}

