package it.polimi.ingsw.manager;

import java.util.ArrayList;
import java.util.HashMap;

import it.polimi.ingsw.BOARD.Board;
import it.polimi.ingsw.BOARD.Position;
import it.polimi.ingsw.BONUS.ResourceBonus;
import it.polimi.ingsw.GC_15.FamilyMember;
import it.polimi.ingsw.GC_15.Game;
import it.polimi.ingsw.GC_15.PersonalBoard;
import it.polimi.ingsw.GC_15.Player;
import it.polimi.ingsw.RESOURCE.Resource;
import it.polimi.ingsw.view.View;

public class ConnectionManager {
	private static ConnectionManager instance;
	private ArrayList<View> temporaryView = new ArrayList<>();
	private static HashMap<Player, View> playersView;
	//TODO forse hashmap per le connessioni
	
	
	public static ConnectionManager getConnectionManager() {
		if (instance == null){
			instance = new ConnectionManager();
		}
		return instance;
	}
	
	private ConnectionManager() {
	}

	public void acceptUser(View view){
		temporaryView.add(view);
		System.out.println("nuovo utente nella partita");
		if(temporaryView.size()==2){
			Game.start();
		}
	}
	
	public static HashMap<Player, View> getPlayersView() {
		return playersView;
	}
	
	public static void addPlayerView(Player player, View view){
		playersView.put(player, view);
	}
	
	public static View getView(Player player){
		return playersView.get(player);
	}

	public static void startTurn(Player player) {
		View view = getView(player);
		view.startTurn(player);
	}

	public static int turnChoice(Player player) {
		View view = getView(player);
		int choice = view.turnChoice();
		return choice;
	}

	public static void moveAlreadyDone(Player player) {
		View view = getView(player);
		view.moveAlreadyDone();
	}

	public static int chooseZone(Player player, Board board) {
		View view = getView(player);
		int choice = view.chooseZone(board);
		return choice;
	}

	public static int choosePosition(Player player, Position[] positions) {
		View view = getView(player);
		int choice = view.choosePosition(positions);
		return choice;
	}

	public static int chooseFamilyMember(Player player, ArrayList<FamilyMember> familyMembers) {
		View view = getView(player);
		int choice = view.chooseFamilyMember(familyMembers);
		return choice;
	}

	public static int askForAlternativeCost(Player player, ArrayList<Resource> cost,
			ArrayList<Resource> alternativeCost) {
		View view = getView(player);
		int choice = view.askForAlternativeCost(cost, alternativeCost);
		return choice;
	}

	public static int askForCouncilPrivilege(Player player, ArrayList<ResourceBonus> councilPrivileges) {
		View view = getView(player);
		int choice = view.askForCouncilPrivilege(councilPrivileges);
		return choice;
	}

	public static int askForServants(Player player, int numberOfServants) {
		View view = getView(player);
		int choice = view.askForServants(numberOfServants);
		return choice;
	}

	public static int askForInformation(Player player, Player[] players) {
		View view  = getView(player);
		int choice = view.askForInformation(players);
		return choice;
	}

	public static void showPersonalBoard(Player player, PersonalBoard personalBoard) {
		View view = getView(player);
		view.showPersonalBoard(personalBoard);
	}

}
