package it.polimi.ingsw.HANDLER.GAME;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import it.polimi.ingsw.BOARD.ActionZone;
import it.polimi.ingsw.BOARD.Board;
import it.polimi.ingsw.BOARD.Position;
import it.polimi.ingsw.BOARD.Tower;
import it.polimi.ingsw.BOARD.Zone;
import it.polimi.ingsw.BONUS.ResourceBonus;
import it.polimi.ingsw.CARD.DevelopmentCardType;
import it.polimi.ingsw.CARD.LeaderCard;
import it.polimi.ingsw.CONTROLLER.PassTurnController;
import it.polimi.ingsw.GC_15.FamilyMember;
import it.polimi.ingsw.GC_15.Game;
import it.polimi.ingsw.GC_15.Player;
import it.polimi.ingsw.HANDLER.PassTurnHandler;
import it.polimi.ingsw.RESOURCE.Resource;
import it.polimi.ingsw.RESOURCE.ResourceType;

public class Manager {
	private Manager instance;
	
	private Manager() {
	}
	
	public Manager getManager() {
		if (instance == null){
			instance = new Manager();
		}
		return instance;
	}

	public static void turn(Player player) {
		ConnectionManager.startTurn(player);
		while(true){
			int choice = ConnectionManager.turnChoice(player);
			switch (choice) {
			case 1:
				actionManager(player);
				break;
			
			case 2:
				activationLeaderCardManager(player);
				break;
		
			case 3:
				discardLeaderCardManager(player);
				break;
		
			case 4:
				activationLeaderCardEffectManager(player);
				break;
		
			case 5:
				if (PassTurnHandler.handle(player)){
					return;
				}
				break;
			}
		}
			
	}

	private static void activationLeaderCardEffectManager(Player player) {
		// TODO Auto-generated method stub
		
	}

	private static void discardLeaderCardManager(Player player) {
		// TODO Auto-generated method stub
		
	}

	private static void activationLeaderCardManager(Player player) {
		// TODO Auto-generated method stub
		
	}

	private static void actionManager(Player player) {
		if (PassTurnController.getLastMove().equals(player)){
			ConnectionManager.moveAlreadyDone(player);
			return;
		}
		Board board = Game.getBoard();
		while(true){
			int choice = ConnectionManager.chooseZone(player, board);
			switch (choice) {
			case 1:
				if (zoneManager(player, board.getTower(DevelopmentCardType.TERRITORY))){
					return;
				}
				break;
			case 2:
				if (zoneManager(player, board.getTower(DevelopmentCardType.CHARACTER))){
					return;
				}
				break;
			case 3:
				if (zoneManager(player, board.getTower(DevelopmentCardType.BUILDING))){
					return;
				}
				break;
			case 4:
				if (zoneManager(player, board.getTower(DevelopmentCardType.VENTURE))){
					return;
				}
				break;
			case 5:
				if (zoneManager(player, board.getCouncilPalace())){
					return;
				}
				break;
			case 6:
				if (zoneManager(player, board.getHarvestArea())){
					return;
				}
				break;
			case 7:
				if (zoneManager(player, board.getProductioArea())){
					return;
				}
				break;
			case 8:
				if (zoneManager(player, board.getMarket())){
					return;
				}
				break;
			case 9:
				return;
			}

		
		}
		
	}
	
	
	/* Le posizioni vanno da 1 alla posizione massima
	 * Se ritorna posizione massima + 1, significa che devi tornare indietro
	 */
	private static boolean zoneManager(Player player, Zone zone) {
		Position[] positions = zone.getPositions();
		int choice = ConnectionManager.choosePosition(player, positions);
		if (choice == positions.length + 1){
			return false;
		}
		else {
			return familyMemberManager(player, zone, positions[choice - 1]);
		}
	}
	
	//Come prima, se il connectionManager ritorna numero di familiari +1, torna indietro, altrimenti usa quel familiare
	private static boolean familyMemberManager(Player player, Zone zone, Position position) {
		ArrayList<FamilyMember> familyMembers = player.getFamilyMembers();
		int choice = ConnectionManager.chooseFamilyMember(player, familyMembers);
		if (choice == familyMembers.size() + 1){
			return false;
		}
		else {
			return ActionHandler.handle(familyMembers.get(choice-1), zone, position);
		}
	}

	public static ArrayList<Resource> askForAlternativeCost(Player player, ArrayList<Resource> cost,
			ArrayList<Resource> alternativeCost) {
		int choice = ConnectionManager.askForAlternativeCost(player, cost, alternativeCost);
		if (choice == 1){
			return cost;
		}
		else return alternativeCost;
	}

	public static ResourceBonus getCouncilPrivilege(Player player, ArrayList<ResourceBonus> councilPrivileges) {
		int choice = ConnectionManager.askForCouncilPrivilege(player, councilPrivileges);
		return councilPrivileges.get(choice-1);
	}

	public static int askForServants(Player player) {
		int numberOfServants = player.getPersonalBoard().getResource(ResourceType.SERVANTS).getAmount();
		int choice = ConnectionManager.askForServants(player, numberOfServants);
		return choice;
	}

	public static Position askForAction(FamilyMember familyMember, ActionZone zone) {
		Player player = familyMember.getPlayer();
		Position[] zonePositions = zone.getPositions();
		int choice = ConnectionManager.askForAction(player, zonePositions);
		return zonePositions[choice - 1];
	}

	public static boolean askForExcommunication(Player player){
		//TODO 
		return false;
	}
	
	public static LeaderCard choiceLeaderCardToCopy() {
		//TODO
		return null;
	}
	
	public static void getHarvestProductionBonus() {
		//TODO
	}

}
