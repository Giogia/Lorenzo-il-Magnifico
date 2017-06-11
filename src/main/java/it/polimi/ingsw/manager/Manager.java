package it.polimi.ingsw.manager;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Set;

import it.polimi.ingsw.BOARD.ActionZone;
import it.polimi.ingsw.BOARD.Board;
import it.polimi.ingsw.BOARD.HarvestArea;
import it.polimi.ingsw.BOARD.Position;
import it.polimi.ingsw.BOARD.ProductionArea;
import it.polimi.ingsw.BOARD.Tower;
import it.polimi.ingsw.BOARD.Zone;
import it.polimi.ingsw.BONUS.Bonus;
import it.polimi.ingsw.BONUS.ImmediateBonus;
import it.polimi.ingsw.BONUS.ResourceBonus;
import it.polimi.ingsw.CARD.Building;
import it.polimi.ingsw.CARD.DevelopmentCard;
import it.polimi.ingsw.CARD.DevelopmentCardType;
import it.polimi.ingsw.CARD.LeaderCard;
import it.polimi.ingsw.CARD.Territory;
import it.polimi.ingsw.CONTROLLER.PassTurnController;
import it.polimi.ingsw.GC_15.ExcommunicationTile;
import it.polimi.ingsw.GC_15.FamilyMember;
import it.polimi.ingsw.GC_15.Game;
import it.polimi.ingsw.GC_15.MyException;
import it.polimi.ingsw.GC_15.PersonalBoard;
import it.polimi.ingsw.GC_15.Player;
import it.polimi.ingsw.HANDLER.PassTurnHandler;
import it.polimi.ingsw.HANDLER.GAME.ActionHandler;
import it.polimi.ingsw.RESOURCE.Resource;
import it.polimi.ingsw.RESOURCE.ResourceType;

public class Manager{
	
	private Manager instance;
	
	private Manager() { }
	
	public Manager getManager() {
		if (instance == null){
			instance = new Manager();
		}
		return instance;
	}

	//begins the turn of the player 
	public static void turn(Player player) throws RemoteException{
		ConnectionManagerImpl.startTurn(player);
		while(true){
			int choice = ConnectionManagerImpl.turnChoice(player);
			switch (choice) {
			case 1:
				actionManager(player);
				break;
			
			case 2:
				leaderCardManager(player);
				break;
		
			case 3:
				activationLeaderCardEffectManager(player);
				break;
				
			case 4:
				askForInformation(player);
				break;
		
			case 5:
				if (PassTurnHandler.handle(player)){
					return;
				}
				//tell to player he can't pass turn
				ConnectionManagerImpl.cantPassTurn(player);
				break;
			}
		}
			
	}

	private static void leaderCardManager(Player player) {
		// TODO Auto-generated method stub
		
	}

	private static void askForInformation(Player player) throws RemoteException {
		Player[] players = player.getBoard().getGame().getBoard().getPlayers();
		String[] names = new String[players.length];
		for (int i = 0; i < players.length; i++) {
			names[i] = players[i].getName();
		}
		int choice = ConnectionManagerImpl.askForInformation(player, names);
		if (choice == players.length + 1){
			return;
		}
		PersonalBoard personalBoard = players[choice - 1].getPersonalBoard();
		ConnectionManagerImpl.showPersonalBoard(player, personalBoard);
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

	private static void actionManager(Player player) throws RemoteException {
		PassTurnController passTurnController = player.getBoard().getPassTurnController();
		if (passTurnController.getLastMove() != null){
			if (passTurnController.getLastMove().equals(player)){
				ConnectionManagerImpl.moveAlreadyDone(player);
				return;
			}
		}
		Board board = player.getBoard();
		while(true){
			int choice = ConnectionManagerImpl.chooseZone(player, board);
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
				ConnectionManagerImpl.catchException(exc.getMessage(), player);
			}
		}
		
	}
	
	
	/* Le posizioni vanno da 1 alla posizione massima
	 * Se ritorna posizione massima + 1, significa che devi tornare indietro
	 */
	private static boolean zoneManager(Player player, Zone zone) throws MyException, RemoteException {
		Position[] positions = zone.getPositions();
		int choice = ConnectionManagerImpl.choosePosition(player, positions);
		if (choice == positions.length + 1){
			return false;
		} else {
			return familyMemberManager(player, zone, positions[choice - 1]);
		}
	}
	
	//Come prima, se il connectionManager ritorna numero di familiari +1, torna indietro, altrimenti usa quel familiare
	private static boolean familyMemberManager(Player player, Zone zone, Position position) throws MyException, RemoteException{
		ArrayList<FamilyMember> familyMembers = player.getFamilyMembers();
		int choice = ConnectionManagerImpl.chooseFamilyMember(player, familyMembers);
		if (choice == familyMembers.size() + 1){
			return false;
		}
		else {
			return ActionHandler.handle(familyMembers.get(choice-1), zone, position);
		}
	}

	public static ArrayList<Resource> askForAlternativeCost(Player player, ArrayList<Resource> costs,
			ArrayList<Resource> alternativeCosts) throws RemoteException {
		int choice = ConnectionManagerImpl.askForAlternativeCost(player, costs, alternativeCosts);
		if (choice == 1){
			return costs;
		}
		else return alternativeCosts;
	}

	public static ResourceBonus getCouncilPrivilege(Player player, ArrayList<ResourceBonus> councilPrivileges) throws RemoteException {
		int choice = ConnectionManagerImpl.askForCouncilPrivilege(player, councilPrivileges);
		return councilPrivileges.get(choice-1);
	}

	public static int askForServants(Player player) throws RemoteException {
		int numberOfServants = player.getPersonalBoard().getResource(ResourceType.servants).getAmount();
		int choice = ConnectionManagerImpl.askForServants(player, numberOfServants);
		return choice;
	}

	public static Position askForAction(FamilyMember familyMember, ActionZone zone, Board board) throws RemoteException {
		zone = getBoardZone(zone, board);
		Player player = familyMember.getPlayer();
		Position[] zonePositions = zone.getPositions();
		int choice = ConnectionManagerImpl.chooseActionPosition(player, zonePositions);
		return zonePositions[choice - 1];
	}

	private static ActionZone getBoardZone(ActionZone zone, Board board) {
		if (zone instanceof Tower){
			DevelopmentCardType developmentCardType = ((Tower) zone).getDevelopmentCardType();
			zone = board.getTower(developmentCardType);
		}
		else if (zone instanceof ProductionArea){
			zone = board.getProductioArea();
		}
		else{
			zone = board.getHarvestArea();
		}
		return zone;
	}

	public static boolean askForExcommunication(Player player, ExcommunicationTile excommunicationTile){
		if (ConnectionManagerImpl.askForExcommunication(player, excommunicationTile) == 1){
			return true;
		}
		return false;
	}
	
	public static LeaderCard choiceLeaderCardToCopy() {
		//TODO PERMANENT
		return null;
	}
	
	public static void getHarvestProductionBonus() {
		//TODO PERMANENT
	}

	public static ActionZone askForZone(ArrayList<ActionZone> actionZones, Player player) throws RemoteException {
		int choice = ConnectionManagerImpl.askForZone(actionZones, player);
		return actionZones.get(choice - 1);
	}

	public static ArrayList<Bonus> chooseEffect(DevelopmentCard developmentCard){
		ArrayList<Bonus> choice= new ArrayList<>();
		//TODO chiedere di scegliere
		return choice;
	}
}
