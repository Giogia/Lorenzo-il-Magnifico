package it.polimi.ingsw.HANDLER.GAME;

import java.util.ArrayList;
import java.util.HashMap;

import it.polimi.ingsw.BOARD.Board;
import it.polimi.ingsw.BOARD.Position;
import it.polimi.ingsw.BONUS.ResourceBonus;
import it.polimi.ingsw.GC_15.FamilyMember;
import it.polimi.ingsw.GC_15.Player;
import it.polimi.ingsw.RESOURCE.Resource;

public class ConnectionManager {
	private static ConnectionManager instance;
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
	
	//TODO al posto di tutti i TODO vanno messi i metodi per gestire le connessioni

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
		// TODO Auto-generated method stub
		
	}

	public static int turnChoice(Player player) {
		// TODO Auto-generated method stub
		return 0;
	}

	public static void moveAlreadyDone(Player player) {
		// TODO Auto-generated method stub
		
	}

	public static int chooseZone(Player player, Board board) {
		// TODO Auto-generated method stub
		return 0;
	}

	public static int choosePosition(Player player, Position[] positions) {
		// TODO Auto-generated method stub
		return 0;
	}

	public static int chooseFamilyMember(Player player, ArrayList<FamilyMember> familyMembers) {
		// TODO Auto-generated method stub
		return 0;
	}

	public static int askForAlternativeCost(Player player, ArrayList<Resource> cost,
			ArrayList<Resource> alternativeCost) {
		// TODO Auto-generated method stub
		return 0;
	}

	public static int askForCouncilPrivilege(Player player, ArrayList<ResourceBonus> councilPrivileges) {
		// TODO Auto-generated method stub
		return 0;
	}

	public static int askForServants(Player player, int numberOfServants) {
		// TODO Auto-generated method stub
		return 0;
	}

	public static int askForAction(Player player, Position[] zonePositions) {
		// TODO Auto-generated method stub
		return 0;
	}

}
