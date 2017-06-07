package it.polimi.ingsw.view;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import it.polimi.ingsw.BOARD.ActionZone;
import it.polimi.ingsw.BOARD.Board;
import it.polimi.ingsw.BOARD.Position;
import it.polimi.ingsw.BONUS.ResourceBonus;
import it.polimi.ingsw.GC_15.FamilyMember;
import it.polimi.ingsw.GC_15.PersonalBoard;
import it.polimi.ingsw.GC_15.Player;
import it.polimi.ingsw.RESOURCE.Resource;

public interface ClientRMICallbackRemote extends Remote, View {
	
	void startTurn(String playerName) throws RemoteException;
	
	String askName() throws RemoteException;
	
	int askColor() throws RemoteException;

	int turnChoice() throws RemoteException;

	void moveAlreadyDone() throws RemoteException;

	//methods to put a family member
	int chooseZone() throws RemoteException;
	
	int choosePosition(ArrayList<String> descriptions) throws RemoteException;
	
	int chooseFamilyMember(ArrayList<String> descriptions) throws RemoteException;

	int askForAlternativeCost(ArrayList<String> costDescriptions, ArrayList<String> alternativeCostDescriptions) throws RemoteException;

	int askForCouncilPrivilege(ArrayList<ResourceBonus> councilPrivileges) throws RemoteException;

	int askForServants(int numberOfServants) throws RemoteException;

	int askForInformation(String[] playersNames) throws RemoteException;

	void showPersonalBoard(String personalBoardDescription) throws RemoteException;
	
	void cantPassTurn() throws RemoteException;
	
	void roundBegins() throws RemoteException;
	
	void hasWon(String winner) throws RemoteException;

	int askForAction(ArrayList<String> zonesDescriptions) throws RemoteException;

	int askForActionPosition(String[] zonePositionsDescriptions) throws RemoteException;

	void catchException(String message) throws RemoteException;
}
