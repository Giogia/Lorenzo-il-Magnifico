package it.polimi.ingsw.manager;

import java.util.ArrayList;
import java.util.Set;

import it.polimi.ingsw.BOARD.ActionZone;
import it.polimi.ingsw.BOARD.Board;
import it.polimi.ingsw.BOARD.HarvestArea;
import it.polimi.ingsw.BOARD.Position;
import it.polimi.ingsw.BOARD.ProductionArea;
import it.polimi.ingsw.BOARD.Tower;
import it.polimi.ingsw.BOARD.Zone;
import it.polimi.ingsw.BONUS.ResourceBonus;
import it.polimi.ingsw.CARD.DevelopmentCardType;
import it.polimi.ingsw.CARD.LeaderCard;
import it.polimi.ingsw.CONTROLLER.PassTurnController;
import it.polimi.ingsw.GC_15.FamilyMember;
import it.polimi.ingsw.GC_15.Game;
import it.polimi.ingsw.GC_15.MyException;
import it.polimi.ingsw.GC_15.PersonalBoard;
import it.polimi.ingsw.GC_15.Player;
import it.polimi.ingsw.HANDLER.PassTurnHandler;
import it.polimi.ingsw.HANDLER.GAME.ActionHandler;
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

	//begins the turn of the player 
	public static void turn(Player player){
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
				askForInformation(player);
				break;
		
			case 6:
				if (PassTurnHandler.handle(player)){
					return;
				}
				//tell to player he can't pass turn
				ConnectionManager.cantPassTurn(player);
				break;
			}
		}
			
	}

	private static void askForInformation(Player player) {
		Player[] players = Game.getBoard().getPlayers();
		int choice = ConnectionManager.askForInformation(player, players);
		if (choice == players.length + 1){
			return;
		}
		PersonalBoard personalBoard = players[choice - 1].getPersonalBoard();
		ConnectionManager.showPersonalBoard(player, personalBoard);
	}

	private static void activationLeaderCardEffectManager(Player player) {
		// TODO PERMANENT
		
	}

	private static void discardLeaderCardManager(Player player) {
		// TODO PERMANENT
		
	}

	private static void activationLeaderCardManager(Player player) {
		// TODO PERMANENT
		
	}

	private static void actionManager(Player player) {
		if (PassTurnController.getLastMove() != null){
			if (PassTurnController.getLastMove().equals(player)){
				ConnectionManager.moveAlreadyDone(player);
				return;
			}
		}
		Board board = Game.getBoard();
		while(true){
			int choice = ConnectionManager.chooseZone(player, board);
			try{
				switch (choice) {
				case 1:
					if (zoneManager(player, board.getTower(DevelopmentCardType.territory))){
						return;
					}
					break;
				case 2:
					if (zoneManager(player, board.getTower(DevelopmentCardType.character))){
						return;
					}
					break;
				case 3:
					if (zoneManager(player, board.getTower(DevelopmentCardType.building))){
						return;
					}
					break;
				case 4:
					if (zoneManager(player, board.getTower(DevelopmentCardType.venture))){
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
			catch(Exception exc){			
				ConnectionManager.catchException(exc.getMessage(), player);
			}
		}
		
	}
	
	
	/* Le posizioni vanno da 1 alla posizione massima
	 * Se ritorna posizione massima + 1, significa che devi tornare indietro
	 */
	private static boolean zoneManager(Player player, Zone zone) throws MyException {
		Position[] positions = zone.getPositions();
		int choice = ConnectionManager.choosePosition(player, positions);
		if (choice == positions.length + 1){
			return false;
		} else {
			return familyMemberManager(player, zone, positions[choice - 1]);
		}
	}
	
	//Come prima, se il connectionManager ritorna numero di familiari +1, torna indietro, altrimenti usa quel familiare
	private static boolean familyMemberManager(Player player, Zone zone, Position position) throws MyException{
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
		int numberOfServants = player.getPersonalBoard().getResource(ResourceType.servants).getAmount();
		int choice = ConnectionManager.askForServants(player, numberOfServants);
		return choice;
	}

	public static Position askForAction(FamilyMember familyMember, ActionZone zone) {
		zone = getBoardZone(zone);
		Player player = familyMember.getPlayer();
		Position[] zonePositions = zone.getPositions();
		int choice = ConnectionManager.chooseActionPosition(player, zonePositions);
		return zonePositions[choice - 1];
	}

	private static ActionZone getBoardZone(ActionZone zone) {
		if (zone instanceof Tower){
			DevelopmentCardType developmentCardType = ((Tower) zone).getDevelopmentCardType();
			zone = Game.getBoard().getTower(developmentCardType);
		}
		else if (zone instanceof ProductionArea){
			zone = Game.getBoard().getProductioArea();
		}
		else{
			zone = Game.getBoard().getHarvestArea();
		}
		return zone;
	}

	public static boolean askForExcommunication(Player player){
		//TODO PERMANENT 
		return false;
	}
	
	public static LeaderCard choiceLeaderCardToCopy() {
		//TODO PERMANENT
		return null;
	}
	
	public static void getHarvestProductionBonus() {
		//TODO PERMANENT
	}

	public static ActionZone askForZone(Set<ActionZone> actionZones, Player player) {
		ArrayList<ActionZone> zones = new ArrayList<>();
		for (ActionZone actionZone : actionZones) {
			zones.add(actionZone);
		}
		int choice = ConnectionManager.askForZone(zones, player);
		return zones.get(choice - 1);
	}

}
