package it.polimi.ingsw.HANDLER.GAME;

import java.util.ArrayList;

import it.polimi.ingsw.BOARD.ActionZone;
import it.polimi.ingsw.BOARD.Board;
import it.polimi.ingsw.BOARD.Position;
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

public class CLIView {
	//TODO ho semplicemente copiato il vecchio Manager

	public static void turn(Player player) {
		String firstMessage = player.getName() + ", è il tuo turno.";
		//ConnectionManager.sendToView(player, firstMessage);
		while(true){
			String secondMessage = "1) Posiziona familiare \n 2) Attiva carta leader \n 3) Scarta carta leader \n 4) Attiva "
					+ "l'effetto di una carta leader \n 5) Passa il turno";
			int choice/* = ConnectionManager.sendToViewForInt(player, secondMessage) */ = 0;
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
				PassTurnHandler.handle(player);
				return;
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
			//ConnectionManager.sendToView(player, "Azione già effettuata");
			return;
		}
		Board board = Game.getBoard();
		while(true){
			String message = "1) Torre Territori \n 2) Torre Personaggi \n 3) Torre Edifici \n 4) Torre Imprese \n" + 
					"5) Palazzo del Consiglio \n 6) Zona Raccolto \n 7) Zona Produzione \n 8) Mercato \n 9) Torna indietro";
			int choice/* = ConnectionManager.sendToViewForInt(player, message)*/ = 0;
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

	private static boolean zoneManager(Player player, Zone zone) {
		Position[] positions = zone.getPositions();
		int positionCounter;
		for (positionCounter = 1; positionCounter <= positions.length; positionCounter++){
			String message = positionCounter + ") " + positions[positionCounter-1].getDescription();
			//ConnectionManager.sendToView(player, message);
		}
		String secondMessage = positionCounter + ") Torna indietro";
		int choice = 0 /*ConnectionManager.sendToViewForInt(player, secondMessage)*/;
		if (choice == positions.length + 1){
			return false;
		}
		else {
			return familyMemberManager(player, zone, positions[positionCounter - 1]);
		}
	}

	private static boolean familyMemberManager(Player player, Zone zone, Position position) {
		ArrayList<FamilyMember> familyMembers = player.getFamilyMembers();
		String message = "Scegli il familiare";
		//ConnectionManager.sendToView(player, message);
		int i;
		for (i = 1; i <= familyMembers.size(); i++) {
			String secondMessage = i + ") " + familyMembers.get(i-1).getDescription();
			//ConnectionManager.sendToView(player, secondMessage);
		}
		String thirdMessage = i + ") Torna indietro";
		int choice = 0;//ConnectionManager.sendToViewForInt(player, thirdMessage);
		if (choice == familyMembers.size() + 1){
			return false;
		}
		else {
			return ActionHandler.handle(familyMembers.get(i-1), zone, position);
		}
	}

	public static ArrayList<Resource> askForAlternativeCost(Player player, ArrayList<Resource> cost,
			ArrayList<Resource> alternativeCost) {
		//ConnectionManager.sendToView(player, "1) Primo costo");
		for (Resource resource : cost) {
			//ConnectionManager.sendToView(player, resource.getDescription());
		}
		//ConnectionManager.sendToView(player, "2) Secondo costo");
		for (Resource resource : alternativeCost) {
			//ConnectionManager.sendToView(player, resource.getDescription());
		}
		String message = "Fai la tua scelta";
		int choice = 0;//ConnectionManager.sendToViewForInt(player, message);
		if (choice == 1){
			return cost;
		}
		else return alternativeCost;
	}

	public static ResourceBonus getCouncilPrivilege(Player player, ArrayList<ResourceBonus> councilPrivileges) {
		for (int i = 1; i <= councilPrivileges.size(); i++) {
			String description = councilPrivileges.get(i - 1).getDescription();
			String message = i + ") " + description; 
			//ConnectionManager.sendToView(player, message);
		}
		int choice =0; //ConnectionManager.sendToViewForInt(player, "Fai la tua scelta");
		return councilPrivileges.get(choice-1);
	}

	public static int askForServants(Player player) {
		int numberOfServants = player.getPersonalBoard().getResource(ResourceType.SERVANTS).getAmount();
		String message = "Hai " + numberOfServants + " servitori. \n Quanti vuoi usarne?";
		int choice = 0;// ConnectionManager.sendToViewForInt(player, message);
		return choice;
	}

	public static Position askForAction(FamilyMember familyMember, ActionZone zone) {
		Player player = familyMember.getPlayer();
		String firstMessage = "Hai un'azione del valore di " + familyMember.getValue();
		//ConnectionManager.sendToView(player, firstMessage);
		Position[] zonePositions = zone.getPositions();
		for(int i = 1; i <= zonePositions.length; i++){
			String message = i + ") " + zonePositions[i-1].getDescription();
			//ConnectionManager.sendToView(player, message);
		}
		int choice = 0; //ConnectionManager.sendToViewForInt(player, "Fai la tua scelta");
		return zonePositions[choice];
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



