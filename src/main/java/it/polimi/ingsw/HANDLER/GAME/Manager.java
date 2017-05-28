package it.polimi.ingsw.HANDLER.GAME;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import it.polimi.ingsw.BOARD.Board;
import it.polimi.ingsw.BONUS.ResourceBonus;
import it.polimi.ingsw.GC_15.FamilyMember;
import it.polimi.ingsw.GC_15.Game;
import it.polimi.ingsw.GC_15.Player;
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
		String firstMessage = player.getName() + ", è il tuo turno.";
		ConnectionManager.sendToView(player, firstMessage);
		while(true){
			String secondMessage = "1) Posiziona familiare \n 2) Attiva carta leader \n 3) Scarta carta leader \n 4) Attiva "
					+ "l'effetto di una carta leader \n 5) Passa il turno";
			int choice = ConnectionManager.sendToViewForInt(player, secondMessage);
			switch (choice) {
		
			case 1:
				familyMemberManager(player);
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

	private static void familyMemberManager(Player player) {
		// TODO Auto-generated method stub
		
	}

	public static ArrayList<Resource> askForAlternativeCost(Player player, ArrayList<Resource> cost,
			ArrayList<Resource> alternativeCost) {
		ConnectionManager.sendToView(player, "1) Primo costo");
		for (Resource resource : cost) {
			ConnectionManager.sendToView(player, resource.getDescription());
		}
		ConnectionManager.sendToView(player, "2) Secondo costo");
		for (Resource resource : alternativeCost) {
			ConnectionManager.sendToView(player, resource.getDescription());
		}
		String message = "Fai la tua scelta";
		int choice = ConnectionManager.sendToViewForInt(player, message);
		if (choice == 1){
			return cost;
		}
		else return alternativeCost;
	}

	public static ResourceBonus getCouncilPrivilege(Player player, ArrayList<ResourceBonus> councilPrivileges) {
		for (int i = 1; i <= councilPrivileges.size(); i++) {
			String description = councilPrivileges.get(i - 1).getDescription();
			String message = i + ") " + description; 
			ConnectionManager.sendToView(player, message);
		}
		int choice = ConnectionManager.sendToViewForInt(player, "Fai la tua scelta");
		return councilPrivileges.get(choice-1);
	}

	public static int askForServants(Player player) {
		int numberOfServants = player.getPersonalBoard().getResource(ResourceType.SERVANTS).getAmount();
		String message = "Hai " + numberOfServants + " servitori. \n Quanti vuoi usarne?";
		int choice = ConnectionManager.sendToViewForInt(player, message);
		return choice;
	}

	



}
