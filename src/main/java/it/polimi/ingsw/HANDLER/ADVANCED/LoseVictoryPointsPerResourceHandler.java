package it.polimi.ingsw.HANDLER.ADVANCED;

import java.util.ArrayList;

import it.polimi.ingsw.BONUS.ADVANCED.LoseVictoryPointsPerResourceBonus;
import it.polimi.ingsw.BONUS.ADVANCED.PermanentBonus;
import it.polimi.ingsw.GC_15.Player;
import it.polimi.ingsw.RESOURCE.Resource;
import it.polimi.ingsw.RESOURCE.ResourceType;

public class LoseVictoryPointsPerResourceHandler {
	
	public static void handle(Player player){
		ArrayList<PermanentBonus> playerBonus = player.getPersonalBoard().getPermanentBonus();
		for (PermanentBonus permanentBonus : playerBonus) {
			if (permanentBonus instanceof LoseVictoryPointsPerResourceBonus){
				modifyResource(player, (LoseVictoryPointsPerResourceBonus) permanentBonus);
			}
		}
	}

	private static void modifyResource(Player player, LoseVictoryPointsPerResourceBonus permanentBonus) {
		ArrayList<Resource> bonusResources = permanentBonus.getResources();
		int amountOfResources = 0;
		for (Resource resource : bonusResources) {
			Resource playerResource = player.getPersonalBoard().getResource(resource.getResourceType());
			amountOfResources = amountOfResources + (playerResource.getAmount()/resource.getAmount());
		}
		player.getPersonalBoard().getResource(ResourceType.victoryPoints).addAmount(-amountOfResources);
	}

}
