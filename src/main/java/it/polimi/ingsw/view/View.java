package it.polimi.ingsw.view;

import java.util.ArrayList;

import it.polimi.ingsw.BOARD.Board;
import it.polimi.ingsw.BOARD.Position;
import it.polimi.ingsw.BONUS.ResourceBonus;
import it.polimi.ingsw.GC_15.FamilyMember;
import it.polimi.ingsw.GC_15.PersonalBoard;
import it.polimi.ingsw.GC_15.Player;
import it.polimi.ingsw.RESOURCE.Resource;
import it.polimi.ingsw.manager.ConnectionManager;

public interface View {
	public static ConnectionManager connectionManager = ConnectionManager.getConnectionManager();
	//Le view conterranno anche le connessioni

	void startTurn(Player player);

	int turnChoice();

	void moveAlreadyDone();

	//methods to put a family member
	int chooseZone(Board board);
	
	int choosePosition(Position[] positions);
	
	int chooseFamilyMember(ArrayList<FamilyMember> familyMembers);

	int askForAlternativeCost(ArrayList<Resource> cost, ArrayList<Resource> alternativeCost);

	int askForCouncilPrivilege(ArrayList<ResourceBonus> councilPrivileges);

	int askForServants(int numberOfServants);

	int askForInformation(Player[] players);

	void showPersonalBoard(PersonalBoard personalBoard);
	
	void cantPassTurn();
	
	void roundBegins();
	
	void hasWon(Player winner);
}
