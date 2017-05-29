package it.polimi.ingsw.HANDLER.GAME;

import java.util.ArrayList;

import it.polimi.ingsw.BOARD.Board;
import it.polimi.ingsw.BOARD.Position;
import it.polimi.ingsw.BONUS.ResourceBonus;
import it.polimi.ingsw.GC_15.FamilyMember;
import it.polimi.ingsw.GC_15.PersonalBoard;
import it.polimi.ingsw.GC_15.Player;
import it.polimi.ingsw.RESOURCE.Resource;

public interface View {
	//Le view conterranno anche le connessioni


	void print(String message);

	int printForInt(String message);

	String printForString(String message);

	void startTurn();

	int turnChoice();

	void moveAlreadyDone();

	int chooseZone(Board board);

	int choosePosition(Position[] positions);

	int chooseFamilyMember(ArrayList<FamilyMember> familyMembers);

	int askForAlternativeCost(ArrayList<Resource> cost, ArrayList<Resource> alternativeCost);

	int askForCouncilPrivilege(ArrayList<ResourceBonus> councilPrivileges);

	int askForServants(int numberOfServants);

	int askForInformation(Player[] players);

	void showPersonalBoard(PersonalBoard personalBoard);

	

}
