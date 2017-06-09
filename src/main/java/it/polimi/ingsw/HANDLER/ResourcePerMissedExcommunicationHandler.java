package it.polimi.ingsw.HANDLER;

import java.util.ArrayList;

import it.polimi.ingsw.BONUS.AddResourceBonus;
import it.polimi.ingsw.BONUS.ADVANCED.PermanentBonus;
import it.polimi.ingsw.BONUS.ADVANCED.ResourcePerMissedExcommunicationBonus;
import it.polimi.ingsw.GC_15.Player;
import it.polimi.ingsw.RESOURCE.Resource;

public class ResourcePerMissedExcommunicationHandler {
	
	public static void handle(Player player){
		ArrayList<PermanentBonus> playerBonus = player.getPersonalBoard().getPermanentBonus();
		if (playerBonus != null){
			for (PermanentBonus permanentBonus : playerBonus) {
				if (permanentBonus instanceof ResourcePerMissedExcommunicationBonus){
					ArrayList<Resource> resources = ((ResourcePerMissedExcommunicationBonus) permanentBonus).getResources();
					AddResourceBonus missedExcommunicationBonus = new AddResourceBonus(resources);
					missedExcommunicationBonus.getImmediateBonus(player);
				}
			}
		}
	}

}
