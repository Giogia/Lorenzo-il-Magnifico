package it.polimi.ingsw.view;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import it.polimi.ingsw.BOARD.ActionZone;
import it.polimi.ingsw.BOARD.Board;
import it.polimi.ingsw.BOARD.Position;
import it.polimi.ingsw.BOARD.Zone;
import it.polimi.ingsw.BONUS.ResourceBonus;
import it.polimi.ingsw.CARD.LeaderCard;
import it.polimi.ingsw.GC_15.Dice;
import it.polimi.ingsw.GC_15.FamilyMember;
import it.polimi.ingsw.GC_15.PersonalBoard;
import it.polimi.ingsw.GC_15.Player;
import it.polimi.ingsw.RESOURCE.Resource;

public interface ClientRMICallbackRemote extends Remote {
	
	int askForLeaderCardAction() throws RemoteException;

	void startTurn(String playerName) throws RemoteException;
	
	String askName() throws RemoteException;
	
	int askColor(String[] availableColors) throws RemoteException;

	int turnChoice() throws RemoteException;

	void moveAlreadyDone() throws RemoteException;

	//methods to put a family member
	int chooseZone() throws RemoteException;
	
	int choosePosition(Position[] positions) throws RemoteException;
	
	int chooseFamilyMember(ArrayList<FamilyMember> familyMembers) throws RemoteException;

	int askForAlternativeCost(ArrayList<Resource> costDescriptions, ArrayList<Resource> alternativeCostDescriptions) throws RemoteException;

	int askForCouncilPrivilege(ArrayList<ResourceBonus> councilPrivileges) throws RemoteException;

	int askForServants(int numberOfServants) throws RemoteException;

	int askForInformation(String[] playersNames) throws RemoteException;

	void showPersonalBoard(PersonalBoard personalBoard) throws RemoteException;
	
	void cantPassTurn() throws RemoteException;
	
	void roundBegins() throws RemoteException;
	
	void hasWon(String winner) throws RemoteException;

	int askForAction(ArrayList<ActionZone> zones) throws RemoteException;

	int askForActionPosition(Position[] positions) throws RemoteException;

	void catchException(String message) throws RemoteException;
	
	void showDices(ArrayList<Dice> dices) throws RemoteException;

	int askForLeaderCard(ArrayList<LeaderCard> leaderCardInHand) throws RemoteException;
}

