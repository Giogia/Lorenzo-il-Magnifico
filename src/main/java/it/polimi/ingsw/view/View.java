package it.polimi.ingsw.view;

import java.rmi.RemoteException;
import java.util.ArrayList;

import it.polimi.ingsw.BOARD.ActionZone;
import it.polimi.ingsw.BOARD.Position;
import it.polimi.ingsw.BOARD.Zone;
import it.polimi.ingsw.BONUS.ResourceBonus;
import it.polimi.ingsw.GC_15.ExcommunicationTile;
import it.polimi.ingsw.GC_15.FamilyMember;
import it.polimi.ingsw.GC_15.PersonalBoard;
import it.polimi.ingsw.RESOURCE.Resource;

public interface View {
void startTurn(String playerName) throws Exception;
	
	String askName() throws Exception;
	
	int askColor(String[] availableColors) throws Exception;

	int turnChoice() throws Exception;

	void moveAlreadyDone() throws Exception;

	int chooseZone() throws Exception;
	
	int choosePosition(Position[] positions) throws Exception;
	
	int chooseFamilyMember(ArrayList<FamilyMember> familyMembers) throws Exception;

	int askForAlternativeCost(ArrayList<Resource> costs, ArrayList<Resource> alternativeCosts) throws Exception;

	int askForCouncilPrivilege(ArrayList<ResourceBonus> councilPrivileges) throws Exception;

	int askForServants(int numberOfServants) throws Exception;

	int askForInformation(String[] playersNames) throws Exception;

	void showPersonalBoard(PersonalBoard personalBoard) throws Exception;
	
	void cantPassTurn() throws Exception;
	
	void roundBegins() throws Exception;
	
	void hasWon(String winner) throws Exception;

	int askForAction(ArrayList<ActionZone> zone) throws Exception;

	int askForActionPosition(Position[] positions) throws Exception;

}
